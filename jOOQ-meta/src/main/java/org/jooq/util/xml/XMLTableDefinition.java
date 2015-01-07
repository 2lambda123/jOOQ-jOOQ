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
package org.jooq.util.xml;

import static org.jooq.util.xml.XMLDatabase.unbox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jooq.tools.StringUtils;
import org.jooq.util.AbstractTableDefinition;
import org.jooq.util.ColumnDefinition;
import org.jooq.util.DataTypeDefinition;
import org.jooq.util.DefaultColumnDefinition;
import org.jooq.util.DefaultDataTypeDefinition;
import org.jooq.util.SchemaDefinition;
import org.jooq.util.xml.jaxb.Column;
import org.jooq.util.xml.jaxb.InformationSchema;
import org.jooq.util.xml.jaxb.Table;

/**
 * @author Lukas Eder
 */
public class XMLTableDefinition extends AbstractTableDefinition {

    private final InformationSchema info;
    private final Table             table;

    public XMLTableDefinition(SchemaDefinition schema, InformationSchema info, Table table) {
        super(schema, table.getTableName(), "");

        this.info = info;
        this.table = table;
    }

    @Override
    protected List<ColumnDefinition> getElements0() throws SQLException {
        List<ColumnDefinition> result = new ArrayList<ColumnDefinition>();

        for (Column column : info.getColumns()) {
            if (StringUtils.equals(table.getTableCatalog(), column.getTableCatalog()) &&
                StringUtils.equals(table.getTableSchema(), column.getTableSchema()) &&
                StringUtils.equals(table.getTableName(), column.getTableName())) {

                SchemaDefinition schema = getDatabase().getSchema(column.getTableSchema());

                DataTypeDefinition type = new DefaultDataTypeDefinition(
                    getDatabase(),
                    schema,
                    column.getDataType(),
                    unbox(column.getCharacterMaximumLength()),
                    unbox(column.getNumericPrecision()),
                    unbox(column.getNumericScale()),
                    column.isIsNullable(),
                    column.getColumnDefault() != null
                );

                result.add(new DefaultColumnDefinition(
                    this,
                    column.getColumnName(),
                    unbox(column.getOrdinalPosition()),
                    type,
                    column.getIdentityGeneration() != null,
                    ""
                ));
            }
        }

        return result;
    }
}
