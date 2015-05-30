/**
 * Copyright (c) 2009-2015, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * This work is dual-licensed
 * - under the Apache Software License 2.0 (the "ASL")
 * - under the jOOQ License and Maintenance Agreement (the "jOOQ License")
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   either ASL or jOOQ License.
 * - If you're using this work with at least one commercial database, you must
 *   choose jOOQ License
 *
 * For more information, please visit http://www.jooq.org/licenses
 *
 * Apache Software License 2.0:
 * -----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * jOOQ License and Maintenance Agreement:
 * -----------------------------------------------------------------------------
 * Data Geekery grants the Customer the non-exclusive, timely limited and
 * non-transferable license to install and use the Software under the terms of
 * the jOOQ License and Maintenance Agreement.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ License
 * and Maintenance Agreement for more details: http://www.jooq.org/licensing
 */
package org.jooq.impl;

import static java.util.Arrays.asList;
import static org.jooq.Clause.CREATE_TABLE;
import static org.jooq.Clause.CREATE_TABLE_AS;
import static org.jooq.Clause.CREATE_TABLE_COLUMNS;
import static org.jooq.Clause.CREATE_TABLE_NAME;
import static org.jooq.SQLDialect.ACCESS;
import static org.jooq.SQLDialect.ASE;
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.HANA;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLSERVER;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.Utils.DATA_SELECT_INTO_TABLE;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Clause;
import org.jooq.Configuration;
import org.jooq.Context;
import org.jooq.CreateTableAsStep;
import org.jooq.CreateTableColumnStep;
import org.jooq.CreateTableFinalStep;
import org.jooq.CreateTableOnCommitStep;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Select;
import org.jooq.Table;

/**
 * @author Lukas Eder
 */
class CreateTableImpl<R extends Record> extends AbstractQuery implements

    // Cascading interface implementations for CREATE TABLE behaviour
    CreateTableAsStep<R>,
    CreateTableColumnStep {

    /**
     * Generated UID
     */
    private static final long       serialVersionUID = 8904572826501186329L;

    private final Table<?>          table;
    private Select<?>               select;
    private final List<Field<?>>    columnFields;
    private final List<DataType<?>> columnTypes;
    private final boolean           temporary;
    private OnCommit                onCommit;

    CreateTableImpl(Configuration configuration, Table<?> table, boolean temporary) {
        super(configuration);

        this.table = table;
        this.temporary = temporary;
        this.columnFields = new ArrayList<Field<?>>();
        this.columnTypes = new ArrayList<DataType<?>>();
    }

    // ------------------------------------------------------------------------
    // XXX: DSL API
    // ------------------------------------------------------------------------

    @Override
    public final CreateTableOnCommitStep as(Select<? extends R> s) {
        this.select = s;
        return this;
    }

    @Override
    public final <T> CreateTableColumnStep column(Field<T> field, DataType<T> type) {
        columnFields.add(field);
        columnTypes.add(type);
        return this;
    }

    @Override
    public final CreateTableColumnStep column(String field, DataType<?> type) {
        columnFields.add(field(name(field), type));
        columnTypes.add(type);
        return this;
    }

    @Override
    public final CreateTableFinalStep onCommitDeleteRows() {
        onCommit = OnCommit.DELETE_ROWS;
        return this;
    }

    @Override
    public final CreateTableFinalStep onCommitPreserveRows() {
        onCommit = OnCommit.PRESERVE_ROWS;
        return this;
    }

    @Override
    public final CreateTableFinalStep onCommitDrop() {
        onCommit = OnCommit.DROP;
        return this;
    }

    // ------------------------------------------------------------------------
    // XXX: QueryPart API
    // ------------------------------------------------------------------------

    @Override
    public final void accept(Context<?> ctx) {

        if (select != null) {

            /* [pro] */
            if (asList(ACCESS, ASE, SQLSERVER).contains(ctx.configuration().dialect().family())) {
                acceptSelectInto(ctx);
            }
            else
            /* [/pro] */
            {
                acceptCreateTableAsSelect(ctx);
            }
        }
        else {
            ctx.start(CREATE_TABLE);
            toSQLCreateTableName(ctx);
            ctx.start(CREATE_TABLE_COLUMNS)
               .sql('(')
               .formatIndentStart()
               .formatNewLine();

            boolean qualify = ctx.qualify();
            ctx.qualify(false);

            for (int i = 0; i < columnFields.size(); i++) {
                DataType<?> type = columnTypes.get(i);

                ctx.visit(columnFields.get(i))
                   .sql(' ');
                Utils.toSQLDDLTypeDeclaration(ctx, type);

                if (type.nullable()) {

                    // [#4321] Not all dialects support explicit NULL type declarations
                    if (!asList(DERBY).contains(ctx.family()))
                        ctx.sql(' ').keyword("null");
                }
                else {
                    ctx.sql(' ').keyword("not null");
                }

                if (i < columnFields.size() - 1)
                    ctx.sql(',').formatSeparator();
            }

            ctx.qualify(qualify);
            ctx.formatIndentEnd()
               .formatNewLine()
               .sql(')')
               .end(CREATE_TABLE_COLUMNS);
            toSQLOnCommit(ctx);
            ctx.end(CREATE_TABLE);
        }
    }

    private final void acceptCreateTableAsSelect(Context<?> ctx) {
        ctx.start(CREATE_TABLE);
        toSQLCreateTableName(ctx);
        toSQLOnCommit(ctx);
        ctx.formatSeparator()
           .keyword("as");

        /* [pro] */
        if (ctx.family() == HANA) {
            ctx.sql(" (")
               .formatIndentStart()
               .formatNewLine();
        }
        else
        /* [/pro] */
        {
            ctx.formatSeparator();
        }

        ctx.start(CREATE_TABLE_AS)
           .visit(select)
           .end(CREATE_TABLE_AS);

        /* [pro] */
        if (ctx.family() == HANA) {
            ctx.formatIndentEnd()
               .formatNewLine()
               .sql(')');
        }
        /* [/pro] */

        ctx.end(CREATE_TABLE);
    }

    private final void toSQLCreateTableName(Context<?> ctx) {
        ctx.start(CREATE_TABLE_NAME)
           .keyword("create")
           .sql(' ');

        if (temporary)
            if (ctx.family() == POSTGRES)
                ctx.keyword("temporary").sql(' ');
            else
                ctx.keyword("global temporary").sql(' ');

        ctx.keyword("table")
           .sql(' ')
           .visit(table)
           .end(CREATE_TABLE_NAME);
    }

    private final void toSQLOnCommit(Context<?> ctx) {
        if (temporary && onCommit != null) {
            switch (onCommit) {
                case DELETE_ROWS:   ctx.formatSeparator().keyword("on commit delete rows");   break;
                case PRESERVE_ROWS: ctx.formatSeparator().keyword("on commit preserve rows"); break;
                case DROP:          ctx.formatSeparator().keyword("on commit drop");          break;
            }
        }
    }

    private final void acceptSelectInto(Context<?> ctx) {
        ctx.data(DATA_SELECT_INTO_TABLE, table);
        ctx.visit(select);
        ctx.data().remove(DATA_SELECT_INTO_TABLE);
    }

    @Override
    public final Clause[] clauses(Context<?> ctx) {
        return null;
    }

    private enum OnCommit {
        DELETE_ROWS,
        PRESERVE_ROWS,
        DROP;
    }
}
