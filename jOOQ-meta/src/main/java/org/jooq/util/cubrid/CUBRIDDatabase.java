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

package org.jooq.util.cubrid;

import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.DSL.val;
import static org.jooq.util.cubrid.dba.Tables.DB_CLASS;
import static org.jooq.util.cubrid.dba.Tables.DB_INDEX;
import static org.jooq.util.cubrid.dba.Tables.DB_INDEX_KEY;
import static org.jooq.util.cubrid.dba.Tables.DB_SERIAL;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.util.AbstractDatabase;
import org.jooq.util.ArrayDefinition;
import org.jooq.util.ColumnDefinition;
import org.jooq.util.DataTypeDefinition;
import org.jooq.util.DefaultEnumDefinition;
import org.jooq.util.DefaultRelations;
import org.jooq.util.DefaultSequenceDefinition;
import org.jooq.util.EnumDefinition;
import org.jooq.util.PackageDefinition;
import org.jooq.util.RoutineDefinition;
import org.jooq.util.SchemaDefinition;
import org.jooq.util.SequenceDefinition;
import org.jooq.util.TableDefinition;
import org.jooq.util.UDTDefinition;

/**
 * @author Lukas Eder
 */
public class CUBRIDDatabase extends AbstractDatabase {

    @Override
    protected void loadPrimaryKeys(DefaultRelations relations) throws SQLException {
        for (Record record : fetchKeys(DB_INDEX.IS_UNIQUE.isTrue().and(DB_INDEX.IS_PRIMARY_KEY.isFalse()))) {
            String key = record.getValue("constraint_name", String.class);
            String tableName = record.getValue(DB_CLASS.CLASS_NAME);
            String columnName = record.getValue(DB_INDEX_KEY.KEY_ATTR_NAME);

            TableDefinition table = getTable(getSchemata().get(0), tableName);
            if (table != null) {
                relations.addUniqueKey(key, table.getColumn(columnName));
            }
        }
    }

    @Override
    protected void loadUniqueKeys(DefaultRelations relations) throws SQLException {
        for (Record record : fetchKeys(DB_INDEX.IS_PRIMARY_KEY.isTrue())) {
            String key = record.getValue("constraint_name", String.class);
            String tableName = record.getValue(DB_CLASS.CLASS_NAME);
            String columnName = record.getValue(DB_INDEX_KEY.KEY_ATTR_NAME);

            TableDefinition table = getTable(getSchemata().get(0), tableName);
            if (table != null) {
                relations.addPrimaryKey(key, table.getColumn(columnName));
            }
        }
    }

    private Result<Record3<String, String, String>> fetchKeys(Condition condition) {
        return
        create().select(
                    concat(DB_CLASS.CLASS_NAME, val("__"), DB_INDEX.INDEX_NAME).as("constraint_name"),
                    DB_INDEX_KEY.KEY_ATTR_NAME,
                    DB_CLASS.CLASS_NAME)
                .from(DB_INDEX)
                .join(DB_CLASS).on(DB_INDEX.CLASS_NAME.equal(DB_CLASS.CLASS_NAME))
                .join(DB_INDEX_KEY).on(
                    DB_INDEX_KEY.INDEX_NAME.equal(DB_INDEX.INDEX_NAME).and(
                    DB_INDEX_KEY.CLASS_NAME.equal(DB_INDEX.CLASS_NAME)))
                .where(condition)
                .orderBy(
                    DB_INDEX.INDEX_NAME.asc())
                .fetch();
    }

    @Override
    protected void loadForeignKeys(DefaultRelations relations) throws SQLException {
        ConnectionProvider provider = create().configuration().connectionProvider();
        Connection connection = null;

        try {
            connection = provider.acquire();
            DatabaseMetaData meta = connection.getMetaData();

            for (String table : create()
                    .selectDistinct(DB_INDEX.CLASS_NAME)
                    .from(DB_INDEX)
                    .where(DB_INDEX.IS_FOREIGN_KEY.isTrue())
                    .fetch(DB_INDEX.CLASS_NAME)) {

                for (Record record : create().fetch(meta.getImportedKeys(null, null, table))) {
                    String foreignKeyName =
                        record.getValue("FKTABLE_NAME", String.class) +
                        "__" +
                        record.getValue("FK_NAME", String.class);
                    String foreignKeyTableName = record.getValue("FKTABLE_NAME", String.class);
                    String foreignKeyColumnName = record.getValue("FKCOLUMN_NAME", String.class);
                    String uniqueKeyName =
                        record.getValue("PKTABLE_NAME", String.class) +
                        "__" +
                        record.getValue("PK_NAME", String.class);

                    TableDefinition referencingTable = getTable(getSchemata().get(0), foreignKeyTableName);
                    if (referencingTable != null) {
                        ColumnDefinition column = referencingTable.getColumn(foreignKeyColumnName);
                        relations.addForeignKey(foreignKeyName, uniqueKeyName, column, getSchemata().get(0));
                    }
                }
            }
        }
        finally {
            if (connection != null) {
                provider.release(connection);
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
        result.add(new SchemaDefinition(this, "", ""));
        return result;
    }

    @Override
    protected List<SequenceDefinition> getSequences0() throws SQLException {
        List<SequenceDefinition> result = new ArrayList<SequenceDefinition>();

        for (Record record : create()
                .select(
                    DB_SERIAL.NAME,
                    DB_SERIAL.MAX_VAL)
                .from(DB_SERIAL)
                .fetch()) {

            BigInteger value = record.getValue(DB_SERIAL.MAX_VAL, BigInteger.class, BigInteger.valueOf(Long.MAX_VALUE));
            DataTypeDefinition type = getDataTypeForMAX_VAL(getSchemata().get(0), value);

            result.add(new DefaultSequenceDefinition(
                getSchemata().get(0),
                record.getValue(DB_SERIAL.NAME),
                type));
        }

        return result;
    }

    @Override
    protected List<TableDefinition> getTables0() throws SQLException {
        List<TableDefinition> result = new ArrayList<TableDefinition>();

        for (Record record : create()
                .select(
                    DB_CLASS.CLASS_NAME)
                .from(DB_CLASS)
                .orderBy(
                    DB_CLASS.CLASS_NAME.asc())
                .fetch()) {

            String name = record.getValue(DB_CLASS.CLASS_NAME);

            CUBRIDTableDefinition table = new CUBRIDTableDefinition(getSchemata().get(0), name, null);
            result.add(table);
        }

        return result;
    }

    @Override
    protected List<EnumDefinition> getEnums0() throws SQLException {
        List<EnumDefinition> result = new ArrayList<EnumDefinition>();

        for (TableDefinition tableDefinition : getTables(getSchemata().get(0))) {
            for (Record record : create().fetch("SHOW COLUMNS FROM {0} WHERE TYPE LIKE 'ENUM(%)'", fieldByName(tableDefinition.getInputName()))) {
                String table = tableDefinition.getInputName();
                String column = record.getValue("Field", String.class);
                String columnType = record.getValue("Type", String.class);
                String name = table + "_" + column;

                ColumnDefinition columnDefinition = tableDefinition.getColumn(column);

                // [#1137] Avoid generating enum classes for enum types that
                // are explicitly forced to another type
                if (getConfiguredForcedType(columnDefinition) == null) {
                    DefaultEnumDefinition definition = new DefaultEnumDefinition(getSchemata().get(0), name, "");
                    for (String string : columnType.replaceAll("ENUM\\(|\\)", "").split(",")) {
                        definition.addLiteral(string.trim().replaceAll("'", ""));
                    }

                    result.add(definition);
                }
            }
        }

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
    protected DSLContext create0() {
        return DSL.using(getConnection(), SQLDialect.CUBRID);
    }
}
