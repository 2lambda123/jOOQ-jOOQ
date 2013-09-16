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
package org.jooq.util.db2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import org.jooq.DataType;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;

/**
 * Supported data types for the {@link SQLDialect#DB2} dialect
 *
 * @see <a href="http://publib.boulder.ibm.com/infocenter/db2luw/v9/index.jsp?topic=/com.ibm.db2.udb.apdv.java.doc/doc/rjvjdata.htm">http://publib.boulder.ibm.com/infocenter/db2luw/v9/index.jsp?topic=/com.ibm.db2.udb.apdv.java.doc/doc/rjvjdata.htm</a>
 * @author Espen Stromsnes
 */
public class DB2DataType {

    // -------------------------------------------------------------------------
    // Default SQL data types and synonyms thereof
    // -------------------------------------------------------------------------

    public static final DataType<Short>      SMALLINT           = new DefaultDataType<Short>(SQLDialect.DB2, SQLDataType.SMALLINT, "smallint");
    public static final DataType<Integer>    INTEGER            = new DefaultDataType<Integer>(SQLDialect.DB2, SQLDataType.INTEGER, "integer");
    public static final DataType<Long>       BIGINT             = new DefaultDataType<Long>(SQLDialect.DB2, SQLDataType.BIGINT, "bigint");
    public static final DataType<Float>      REAL               = new DefaultDataType<Float>(SQLDialect.DB2, SQLDataType.REAL, "real");
    public static final DataType<Double>     DOUBLE             = new DefaultDataType<Double>(SQLDialect.DB2, SQLDataType.DOUBLE, "double");
    public static final DataType<BigDecimal> DECIMAL            = new DefaultDataType<BigDecimal>(SQLDialect.DB2, SQLDataType.DECIMAL, "decimal");
    public static final DataType<BigDecimal> DECFLOAT           = new DefaultDataType<BigDecimal>(SQLDialect.DB2, SQLDataType.DECIMAL, "decfloat");
    public static final DataType<String>     VARCHAR            = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.VARCHAR, "varchar", "varchar(32672)");
    public static final DataType<String>     CHAR               = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CHAR, "char", "varchar(32672)");
    public static final DataType<String>     CHARACTER          = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CHAR, "character", "varchar(32672)");
    public static final DataType<String>     LONGVARCHAR        = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.LONGVARCHAR, "long varchar");
    public static final DataType<String>     CLOB               = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CLOB, "clob");
    public static final DataType<byte[]>     BLOB               = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.BLOB, "blob");
    public static final DataType<Date>       DATE               = new DefaultDataType<Date>(SQLDialect.DB2, SQLDataType.DATE, "date");
    public static final DataType<Time>       TIME               = new DefaultDataType<Time>(SQLDialect.DB2, SQLDataType.TIME, "time");
    public static final DataType<Timestamp>  TIMESTAMP          = new DefaultDataType<Timestamp>(SQLDialect.DB2, SQLDataType.TIMESTAMP, "timestamp");

    // -------------------------------------------------------------------------
    // Compatibility types for supported SQLDialect.DB2, SQLDataTypes
    // -------------------------------------------------------------------------

    protected static final DataType<byte[]>     __BINARY        = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.BINARY, "blob");
    protected static final DataType<Boolean>    __BIT           = new DefaultDataType<Boolean>(SQLDialect.DB2, SQLDataType.BIT, "smallint");
    protected static final DataType<Boolean>    __BOOLEAN       = new DefaultDataType<Boolean>(SQLDialect.DB2, SQLDataType.BOOLEAN, "smallint");
    protected static final DataType<byte[]>     __LONGVARBINARY = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.LONGVARBINARY, "blob");
    protected static final DataType<String>     __NCHAR         = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.NCHAR, "char", "varchar(32672)");
    protected static final DataType<String>     __NCLOB         = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.NCLOB, "clob");
    protected static final DataType<String>     __LONGNVARCHAR  = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.LONGNVARCHAR, "long varchar");
    protected static final DataType<BigDecimal> __NUMERIC       = new DefaultDataType<BigDecimal>(SQLDialect.DB2, SQLDataType.NUMERIC, "decimal", "decimal");
    protected static final DataType<String>     __NVARCHAR      = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.NVARCHAR, "varchar", "varchar(32672)");
    protected static final DataType<Byte>       __TINYINT       = new DefaultDataType<Byte>(SQLDialect.DB2, SQLDataType.TINYINT, "smallint");
    protected static final DataType<byte[]>     __VARBINARY     = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.VARBINARY, "blob");

    // -------------------------------------------------------------------------
    // Compatibility types for supported Java types
    // -------------------------------------------------------------------------

    protected static final DataType<BigInteger> __BIGINTEGER    = new DefaultDataType<BigInteger>(SQLDialect.DB2, SQLDataType.DECIMAL_INTEGER, "decimal", "decimal(31)");
    protected static final DataType<UUID>       __UUID          = new DefaultDataType<UUID>(SQLDialect.DB2, SQLDataType.UUID, "varchar", "varchar(36)");

    // -------------------------------------------------------------------------
    // Dialect-specific data types and synonyms thereof
    // -------------------------------------------------------------------------

    public static final DataType<String> XML                    = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CLOB, "xml");
    public static final DataType<String> DBCLOB                 = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CLOB, "dbclob");
    public static final DataType<String> GRAPHIC                = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CLOB, "graphic");
    public static final DataType<String> VARGRAPHIC             = new DefaultDataType<String>(SQLDialect.DB2, SQLDataType.CLOB, "vargraphic");
    public static final DataType<byte[]> CHARFORBITDATA         = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.BLOB, "char for bit data");
    public static final DataType<byte[]> VARCHARFORBITDATA      = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.BLOB, "varchar(32672) for bit data");
    public static final DataType<byte[]> ROWID                  = new DefaultDataType<byte[]>(SQLDialect.DB2, SQLDataType.BLOB, "rowid");
}
