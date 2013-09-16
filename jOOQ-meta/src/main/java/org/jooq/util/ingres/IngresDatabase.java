/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * This work is triple-licensed under ASL 2.0, AGPL 3.0, and jOOQ EULA
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   ASL 2.0 or jOOQ EULA.
 * - If you're using this work with at least one commercial database, you may
 *   choose AGPL 3.0 or jOOQ EULA.
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
 * AGPL 3.0
 * -----------------------------------------------------------------------------
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this library.
 * If not, see http://www.gnu.org/licenses.
 *
 * jOOQ End User License Agreement:
 * -----------------------------------------------------------------------------
 * This library is commercial software; you may not redistribute it nor
 * modify it.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ End User
 * License Agreement for more details: http://www.jooq.org/eula
 */
package org.jooq.util.ingres;

import static org.jooq.impl.DSL.row;
import static org.jooq.impl.DSL.trim;
import static org.jooq.util.ingres.ingres.Tables.IICONSTRAINTS;
import static org.jooq.util.ingres.ingres.Tables.IIDB_COMMENTS;
import static org.jooq.util.ingres.ingres.Tables.IIREF_CONSTRAINTS;
import static org.jooq.util.ingres.ingres.Tables.IISCHEMA;
import static org.jooq.util.ingres.ingres.Tables.IISEQUENCES;
import static org.jooq.util.ingres.ingres.Tables.IITABLES;
import static org.jooq.util.ingres.ingres.tables.Iikeys.IIKEYS;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record4;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.AbstractDatabase;
import org.jooq.util.ArrayDefinition;
import org.jooq.util.ColumnDefinition;
import org.jooq.util.DataTypeDefinition;
import org.jooq.util.DefaultDataTypeDefinition;
import org.jooq.util.DefaultRelations;
import org.jooq.util.DefaultSequenceDefinition;
import org.jooq.util.EnumDefinition;
import org.jooq.util.PackageDefinition;
import org.jooq.util.RoutineDefinition;
import org.jooq.util.SchemaDefinition;
import org.jooq.util.SequenceDefinition;
import org.jooq.util.TableDefinition;
import org.jooq.util.UDTDefinition;
import org.jooq.util.ingres.ingres.tables.Iiconstraints;
import org.jooq.util.ingres.ingres.tables.IidbComments;
import org.jooq.util.ingres.ingres.tables.Iikeys;
import org.jooq.util.ingres.ingres.tables.IirefConstraints;
import org.jooq.util.ingres.ingres.tables.Iischema;
import org.jooq.util.ingres.ingres.tables.Iisequences;
import org.jooq.util.ingres.ingres.tables.Iitables;

/**
 * @author Lukas Eder
 */
public class IngresDatabase extends AbstractDatabase {

    @Override
    protected DSLContext create0() {
        return DSL.using(getConnection(), SQLDialect.INGRES);
    }

    @Override
    protected void loadPrimaryKeys(DefaultRelations relations) throws SQLException {
        for (Record record : fetchKeys("P")) {
            SchemaDefinition schema = getSchema(record.getValue(trim(Iiconstraints.SCHEMA_NAME)));
            String key = record.getValue(trim(Iiconstraints.CONSTRAINT_NAME));
            String tableName = record.getValue(trim(Iiconstraints.TABLE_NAME));
            String columnName = record.getValue(trim(Iikeys.COLUMN_NAME));

            TableDefinition table = getTable(schema, tableName);
            if (table != null) {
                relations.addPrimaryKey(key, table.getColumn(columnName));
            }
        }
    }

    @Override
    protected void loadUniqueKeys(DefaultRelations relations) throws SQLException {
        for (Record record : fetchKeys("U")) {
            SchemaDefinition schema = getSchema(record.getValue(trim(Iiconstraints.SCHEMA_NAME)));
            String key = record.getValue(trim(Iiconstraints.CONSTRAINT_NAME));
            String tableName = record.getValue(trim(Iiconstraints.TABLE_NAME));
            String columnName = record.getValue(trim(Iikeys.COLUMN_NAME));

            TableDefinition table = getTable(schema, tableName);
            if (table != null) {
                relations.addPrimaryKey(key, table.getColumn(columnName));
            }
        }
    }

    private Result<Record4<String, String, String, String>> fetchKeys(String constraintType) {
        return create().select(
                    trim(Iiconstraints.SCHEMA_NAME),
                    trim(Iiconstraints.TABLE_NAME),
                    trim(Iiconstraints.CONSTRAINT_NAME),
                    trim(Iikeys.COLUMN_NAME))
                .from(IICONSTRAINTS)
                .join(IIKEYS)
                .on(row(Iiconstraints.CONSTRAINT_NAME, Iiconstraints.SCHEMA_NAME)
                    .eq(Iikeys.CONSTRAINT_NAME, Iikeys.SCHEMA_NAME))
                .where(Iiconstraints.SCHEMA_NAME.in(getInputSchemata()))
                .and(Iiconstraints.CONSTRAINT_TYPE.trim().equal(constraintType))
                .orderBy(
                    Iiconstraints.SCHEMA_NAME.asc(),
                    Iiconstraints.TABLE_NAME.asc(),
                    Iiconstraints.CONSTRAINT_NAME.asc(),
                    Iikeys.KEY_POSITION.asc())
                .fetch();
    }

    @Override
    protected void loadForeignKeys(DefaultRelations relations) throws SQLException {
        Result<?> result = create()
            .select(
                trim(IirefConstraints.REF_SCHEMA_NAME),
                trim(IirefConstraints.REF_CONSTRAINT_NAME),
                trim(IirefConstraints.UNIQUE_CONSTRAINT_NAME),
                trim(IirefConstraints.UNIQUE_SCHEMA_NAME),
                trim(IirefConstraints.REF_TABLE_NAME),
                trim(Iikeys.COLUMN_NAME))
            .from(IICONSTRAINTS)
            .join(IIREF_CONSTRAINTS)
            .on(row(Iiconstraints.CONSTRAINT_NAME, Iiconstraints.SCHEMA_NAME)
                .eq(IirefConstraints.REF_CONSTRAINT_NAME, IirefConstraints.REF_SCHEMA_NAME))
            .join(IIKEYS)
            .on(row(IirefConstraints.REF_CONSTRAINT_NAME, IirefConstraints.REF_SCHEMA_NAME)
                .eq(Iikeys.CONSTRAINT_NAME, Iikeys.SCHEMA_NAME))
            .where(Iiconstraints.SCHEMA_NAME.in(getInputSchemata()))
            .and(Iiconstraints.CONSTRAINT_TYPE.equal("R"))
            .orderBy(
                IirefConstraints.REF_SCHEMA_NAME.asc(),
                IirefConstraints.REF_TABLE_NAME.asc(),
                IirefConstraints.REF_CONSTRAINT_NAME.asc(),
                Iikeys.KEY_POSITION.asc())
            .fetch();

        for (Record record : result) {
            SchemaDefinition foreignKeySchema = getSchema(record.getValue(trim(IirefConstraints.REF_SCHEMA_NAME)));
            SchemaDefinition uniqueKeySchema = getSchema(record.getValue(trim(IirefConstraints.UNIQUE_SCHEMA_NAME)));

            String foreignKey = record.getValue(trim(IirefConstraints.REF_CONSTRAINT_NAME));
            String foreignKeyTable = record.getValue(trim(IirefConstraints.REF_TABLE_NAME));
            String foreignKeyColumn = record.getValue(trim(Iikeys.COLUMN_NAME));
            String uniqueKey = record.getValue(trim(IirefConstraints.UNIQUE_CONSTRAINT_NAME));

            TableDefinition referencingTable = getTable(foreignKeySchema, foreignKeyTable);

            if (referencingTable != null) {
                ColumnDefinition referencingColumn = referencingTable.getColumn(foreignKeyColumn);
                relations.addForeignKey(foreignKey, uniqueKey, referencingColumn, uniqueKeySchema);
            }
        }
    }

    @Override
    protected void loadCheckConstraints(DefaultRelations r) throws SQLException {
        // Currently not supported
    }

    @Override
    protected List<SchemaDefinition> getSchemata0() throws SQLException {
        List<SchemaDefinition> result = new ArrayList<SchemaDefinition>();

        for (String name : create()
                .select(trim(Iischema.SCHEMA_NAME))
                .from(IISCHEMA)
                .fetch(trim(Iischema.SCHEMA_NAME))) {

            result.add(new SchemaDefinition(this, name, ""));
        }

        return result;
    }

    @Override
    protected List<SequenceDefinition> getSequences0() throws SQLException {
        List<SequenceDefinition> result = new ArrayList<SequenceDefinition>();

        for (Record record : create().select(
                    trim(Iisequences.SEQ_OWNER),
                    trim(Iisequences.SEQ_NAME),
                    trim(Iisequences.DATA_TYPE))
                .from(IISEQUENCES)
                .where(Iisequences.SEQ_OWNER.in(getInputSchemata()))
                .orderBy(
                    Iisequences.SEQ_OWNER,
                    Iisequences.SEQ_NAME)
                .fetch()) {

            SchemaDefinition schema = getSchema(record.getValue(trim(Iisequences.SEQ_OWNER)));

            DataTypeDefinition type = new DefaultDataTypeDefinition(
                this, schema,
                record.getValue(trim(Iisequences.DATA_TYPE))
            );

            result.add(new DefaultSequenceDefinition(
                schema, record.getValue(trim(Iisequences.SEQ_NAME)), type));
        }

        return result;
    }

    @Override
    protected List<TableDefinition> getTables0() throws SQLException {
        List<TableDefinition> result = new ArrayList<TableDefinition>();

        for (Record record : create().select(
                    trim(Iitables.TABLE_OWNER),
                    trim(Iitables.TABLE_NAME),
                    trim(IidbComments.LONG_REMARK))
                .from(IITABLES)
                .leftOuterJoin(IIDB_COMMENTS)
                .on(Iitables.TABLE_NAME.equal(IidbComments.OBJECT_NAME))
                .and(Iitables.TABLE_OWNER.equal(IidbComments.OBJECT_OWNER))
                .and(IidbComments.OBJECT_TYPE.equal("T"))
                .and(IidbComments.TEXT_SEQUENCE.equal(1L))
                .where(Iitables.TABLE_OWNER.in(getInputSchemata()))
                .orderBy(
                    trim(Iitables.TABLE_OWNER),
                    trim(Iitables.TABLE_NAME))
                .fetch()) {

            result.add(new IngresTableDefinition(
                getSchema(record.getValue(trim(Iitables.TABLE_OWNER))),
                record.getValue(trim(Iitables.TABLE_NAME)),
                record.getValue(trim(IidbComments.LONG_REMARK))));
        }

        return result;
    }

    @Override
    protected List<RoutineDefinition> getRoutines0() throws SQLException {
        List<RoutineDefinition> result = new ArrayList<RoutineDefinition>();
        return result;
    }

    @Override
    protected List<PackageDefinition> getPackages0() throws SQLException {
        List<PackageDefinition> result = new ArrayList<PackageDefinition>();
        return result;
    }

    @Override
    protected List<EnumDefinition> getEnums0() throws SQLException {
        List<EnumDefinition> result = new ArrayList<EnumDefinition>();
        return result;
    }

    @Override
    protected List<UDTDefinition> getUDTs0() throws SQLException {
        List<UDTDefinition> result = new ArrayList<UDTDefinition>();
        return result;
    }

    @Override
    protected List<ArrayDefinition> getArrays0() throws SQLException {
        List<ArrayDefinition> result = new ArrayList<ArrayDefinition>();
        return result;
    }
}
