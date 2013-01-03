/**
 * Copyright (c) 2009-2013, Lukas Eder, lukas.eder@gmail.com
 * All rights reserved.
 *
 * This software is licensed to you under the Apache License, Version 2.0
 * (the "License"); You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name "jOOQ" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.jooq.impl;

import static java.util.Arrays.asList;
import static org.jooq.SQLDialect.CUBRID;
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.FIREBIRD;
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.MYSQL;
import static org.jooq.SQLDialect.ORACLE;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLSERVER;
import static org.jooq.SQLDialect.SYBASE;

import org.jooq.BindContext;
import org.jooq.QueryPart;
import org.jooq.RenderContext;
import org.jooq.SQLDialect;

/**
 * @author Lukas Eder
 */
class Alias<Q extends QueryPart> extends AbstractQueryPart {

    private static final long serialVersionUID = -2456848365524191614L;
    private final Q           wrapped;
    private final String      alias;
    private final String[]    fieldAliases;
    private final boolean     wrapInParentheses;

    Alias(Q wrapped, String alias) {
        this(wrapped, alias, null, false);
    }

    Alias(Q wrapped, String alias, boolean wrapInParentheses) {
        this(wrapped, alias, null, wrapInParentheses);
    }

    Alias(Q wrapped, String alias, String[] fieldAliases) {
        this(wrapped, alias, fieldAliases, false);
    }

    Alias(Q wrapped, String alias, String[] fieldAliases, boolean wrapInParentheses) {
        this.wrapped = wrapped;
        this.alias = alias;
        this.fieldAliases = fieldAliases;
        this.wrapInParentheses = wrapInParentheses;
    }

    final Q wrapped() {
        return wrapped;
    }

    @Override
    public final void toSQL(RenderContext context) {
        if (context.declareFields() || context.declareTables()) {
            SQLDialect dialect = context.getDialect();
            boolean simulateDerivedColumnList = asList(ORACLE).contains(dialect);

            // [#1801] Some databases don't support "derived column names" at
            // all. They can be simulated using common table expressions
            if (fieldAliases != null && simulateDerivedColumnList) {
                context.sql("(").formatIndentStart().formatNewLine()
                       .keyword("with").sql(" v");
                toSQLDerivedColumnList(context);
                context.sql(" ").keyword("as").sql(" (").formatIndentStart().formatNewLine()
                       .keyword("select * from").sql(" (")
                       .sql(wrapped)
                       .sql(")").formatIndentEnd().formatNewLine()
                       .sql(")").formatSeparator()
                       .keyword("select * from")
                       .sql(" v").formatIndentEnd().formatNewLine()
                       .sql(")");
            }

            // [#1801] Some databases don't allow "derived column names" in
            // "simple class specifications". Hence, wrap the table reference in
            // a subselect

            // Feature requests placed here:
            // http://jira.cubrid.org/browse/ENGINE-96
            // http://tracker.firebirdsql.org/browse/CORE-4025
            else if (fieldAliases != null && asList(CUBRID, FIREBIRD, SQLSERVER, SYBASE).contains(dialect) && wrapped instanceof TableImpl) {
                context.sql("(").formatIndentStart().formatNewLine()
                       .keyword("select * from")
                       .sql(wrapped)
                       .formatIndentEnd().formatNewLine()
                       .sql(")");
            }

            // The default behaviour
            else {
                if (wrapInParentheses) {
                    context.sql("(");
                }

                context.sql(wrapped);

                if (wrapInParentheses) {
                    context.sql(")");
                }
            }

            // [#291] some aliases cause trouble, if they are not explicitly marked using "as"
            if (asList(DERBY, HSQLDB, MYSQL, POSTGRES).contains(dialect)) {
                context.keyword(" as");
            }

            context.sql(" ");
            context.literal(alias);

            // [#1801] Add field aliases to the table alias, if applicable
            if (fieldAliases != null && !simulateDerivedColumnList) {
                toSQLDerivedColumnList(context);
            }

            else {
                // [#756] If the aliased object is an anonymous table (usually an
                // unnested array), then field names must be part of the alias
                // declaration. For example:
                //
                // SELECT t.column_value FROM UNNEST(ARRAY[1, 2]) AS t(column_value)

                // TODO: Is this still needed?
                switch (dialect) {
                    case HSQLDB:
                    case POSTGRES: {
                        // The javac compiler doesn't like casting of generics
                        Object o = wrapped;

                        if (context.declareTables() && o instanceof ArrayTable) {
                            ArrayTable table = (ArrayTable) o;

                            context.sql("(");
                            Utils.fieldNames(context, table.getFields());
                            context.sql(")");
                        }

                        break;
                    }
                }
            }
        }
        else {
            context.literal(alias);
        }
    }

    private void toSQLDerivedColumnList(RenderContext context) {
        String separator = "";

        context.sql("(");

        for (int i = 0; i < fieldAliases.length; i++) {
            context.sql(separator);
            context.literal(fieldAliases[i]);

            separator = ", ";
        }

        context.sql(")");
    }

    @Override
    public final void bind(BindContext context) {
        if (context.declareFields() || context.declareTables()) {
            context.bind(wrapped);
        }
        else {
            // Don't bind any values
        }
    }

    @Override
    public final boolean declaresFields() {
        return true;
    }

    @Override
    public final boolean declaresTables() {
        return true;
    }
}
