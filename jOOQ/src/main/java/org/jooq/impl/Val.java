/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 * 
 * This work is dual-licensed Open Source, under LGPL and jOOQ EULA
 * =============================================================================
 * You may freely choose which license applies to you. For more information 
 * about licensing, please visit http://www.jooq.org/licenses
 * 
 * LGPL:  
 * -----------------------------------------------------------------------------
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this library.
 * If not, see http://www.gnu.org/licenses.
 * 
 * jOOQ End User License Agreement:
 * -----------------------------------------------------------------------------
 * This library is commercial software; you may not redistribute it nor
 * modify it.
 * 
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ End User
 * License Agreement for more details.
 * 
 * You should have received a copy of the jOOQ End User License Agreement
 * along with this library.
 * If not, see http://www.jooq.org/eula
 */
package org.jooq.impl;

import static java.lang.Boolean.TRUE;
import static java.lang.Integer.toOctalString;
import static java.util.Arrays.asList;
import static org.jooq.SQLDialect.ASE;
import static org.jooq.SQLDialect.CUBRID;
import static org.jooq.SQLDialect.DB2;
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.FIREBIRD;
import static org.jooq.SQLDialect.H2;
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.INGRES;
import static org.jooq.SQLDialect.MARIADB;
import static org.jooq.SQLDialect.MYSQL;
import static org.jooq.SQLDialect.ORACLE;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLITE;
import static org.jooq.SQLDialect.SQLSERVER;
import static org.jooq.SQLDialect.SYBASE;
import static org.jooq.conf.ParamType.NAMED;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.using;
import static org.jooq.tools.StringUtils.leftPad;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.jooq.ArrayRecord;
import org.jooq.BindContext;
import org.jooq.Converter;
import org.jooq.DataType;
import org.jooq.EnumType;
import org.jooq.RenderContext;
import org.jooq.SQLDialect;
import org.jooq.Schema;
import org.jooq.UDTRecord;
import org.jooq.tools.StringUtils;
import org.jooq.types.Interval;

/**
 * @author Lukas Eder
 */
class Val<T> extends AbstractParam<T> {

    private static final long   serialVersionUID = 6807729087019209084L;
    private static final char[] HEX              = "0123456789abcdef".toCharArray();

    Val(T value, DataType<T> type) {
        super(value, type);
    }

    Val(T value, DataType<T> type, String paramName) {
        super(value, type, paramName);
    }

    // ------------------------------------------------------------------------
    // XXX: Field API
    // ------------------------------------------------------------------------

    @Override
    public final void toSQL(RenderContext context) {

        // Casting can be enforced or prevented
        switch (context.castMode()) {
            case NEVER:
                toSQL(context, value, getType());
                return;

            case ALWAYS:
                toSQLCast(context);
                return;

            case SOME:

                // This dialect must cast
                if (context.cast()) {
                    toSQLCast(context);
                }

                // In some cases, we should still cast
                else if (shouldCast(context)) {
                    toSQLCast(context);
                }
                else {
                    toSQL(context, value, getType());
                }

                return;
        }

        // See if we "should" cast, to stay on the safe side
        if (shouldCast(context)) {
            toSQLCast(context);
        }

        // Most RDBMS can infer types for bind values
        else {
            toSQL(context, value, getType());
        }
    }

    private final boolean shouldCast(RenderContext context) {

        // In default mode, casting is only done when parameters are NOT inlined
        if (!isInline(context)) {

            // Generated enums should not be cast...
            if (!(value instanceof EnumType)) {
                switch (context.configuration().dialect()) {

                    // These dialects can hardly detect the type of a bound constant.
                    case DB2:
                    case DERBY:
                    case FIREBIRD:

                    // These dialects have some trouble, when they mostly get it right.
                    case H2:
                    case HSQLDB:

                    // [#1261] There are only a few corner-cases, where this is
                    // really needed. Check back on related CUBRID bugs
                    case CUBRID:

                    // [#1029] Postgres and [#632] Sybase need explicit casting
                    // in very rare cases.
                    case POSTGRES:
                    case SYBASE: {
                        return true;
                    }
                }
            }
        }

        // [#566] JDBC doesn't explicitly support interval data types. To be on
        // the safe side, always cast these types in those dialects that support
        // them
        if (getDataType().isInterval()) {
            switch (context.configuration().dialect().family()) {
                case ORACLE:
                case POSTGRES:
                    return true;
            }
        }

        return false;
    }

    /**
     * Render the bind variable including a cast, if necessary
     */
    private final void toSQLCast(RenderContext context) {
        DataType<T> dataType = getDataType(context.configuration());
        DataType<T> type = dataType.getSQLDataType();
        SQLDialect dialect = context.configuration().dialect();

        // [#822] Some RDBMS need precision / scale information on BigDecimals
        if (value != null && getType() == BigDecimal.class && asList(CUBRID, DB2, DERBY, FIREBIRD, HSQLDB).contains(dialect)) {

            // Add precision / scale on BigDecimals
            int scale = ((BigDecimal) value).scale();
            int precision = scale + ((BigDecimal) value).precision();

            // Firebird's max precision is 18
            if (dialect == FIREBIRD) {
                precision = Math.min(precision, 18);
            }

            toSQLCast(context, dataType, 0, precision, scale);
        }

        // [#1028] Most databases don't know an OTHER type (except H2, HSQLDB).
        else if (SQLDataType.OTHER == type) {

            // If the bind value is set, it can be used to derive the cast type
            if (value != null) {
                toSQLCast(context, DefaultDataType.getDataType(dialect, value.getClass()), 0, 0, 0);
            }

            // [#632] [#722] Current integration tests show that Ingres and
            // Sybase can do without casting in most cases.
            else if (asList(INGRES, SYBASE).contains(dialect)) {
                context.sql(getBindVariable(context));
            }

            // Derby and DB2 must have a type associated with NULL. Use VARCHAR
            // as a workaround. That's probably not correct in all cases, though
            else {
                toSQLCast(context, DefaultDataType.getDataType(dialect, String.class), 0, 0, 0);
            }
        }

        // [#1029] Postgres generally doesn't need the casting. Only in the
        // above case where the type is OTHER
        // [#1125] Also with temporal data types, casting is needed some times
        // [#1130] TODO type can be null for ARRAY types, etc.
        else if (dialect == POSTGRES && (type == null || !type.isTemporal())) {
            toSQL(context, value, getType());
        }

        // [#1727] VARCHAR types should be cast to their actual lengths in some
        // dialects
        else if ((type == SQLDataType.VARCHAR || type == SQLDataType.CHAR) && asList(FIREBIRD).contains(dialect)) {
            toSQLCast(context, dataType, getValueLength(), 0, 0);
        }

        // In all other cases, the bind variable can be cast normally
        else {
            toSQLCast(context, dataType, dataType.length(), dataType.precision(), dataType.scale());
        }
    }

    private final int getValueLength() {
        String string = (String) value;
        if (string == null) {
            return 1;
        }

        else {
            int length = string.length();

            // If non 7-bit ASCII characters are present, multiply the length by
            // 4 to be sure that even UTF-32 collations will fit. But don't use
            // larger numbers than Derby's upper limit 32672
            for (int i = 0; i < length; i++) {
                if (string.charAt(i) > 127) {
                    return Math.min(32672, 4 * length);
                }
            }

            return Math.min(32672, length);
        }
    }

    private final void toSQLCast(RenderContext context, DataType<?> type, int length, int precision, int scale) {
        context.keyword("cast").sql("(");
        toSQL(context, value, getType());
        context.sql(" ").keyword("as").sql(" ")
               .sql(type.length(length).precision(precision, scale).getCastTypeName(context.configuration()))
               .sql(")");
    }

    /**
     * Get a bind variable, depending on value of
     * {@link RenderContext#namedParams()}
     */
    private final String getBindVariable(RenderContext context) {
        if (context.paramType() == NAMED) {
            int index = context.nextIndex();

            if (StringUtils.isBlank(getParamName())) {
                return ":" + index;
            }
            else {
                return ":" + getName();
            }
        }
        else {
            return "?";
        }
    }

    /**
     * Inlining abstraction
     */
    private final void toSQL(RenderContext context, Object val) {
        if (val == null) {
            toSQL(context, val, Object.class);
        }
        else {
            toSQL(context, val, val.getClass());
        }
    }

    /**
     * Inlining abstraction
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private final void toSQL(RenderContext context, Object val, Class<?> type) {
        SQLDialect family = context.configuration().dialect().family();

        // [#650] Check first, if we have a converter for the supplied type
        Converter<?, ?> converter = DataTypes.converter(type);
        if (converter != null) {
            val = ((Converter) converter).to(val);
            type = converter.fromType();
        }

        if (isInline(context)) {
            // [#2223] Some type-casts in this section may seem unnecessary, e.g.
            // ((Boolean) val).toString(). They have been put in place to avoid
            // accidental type confusions where type != val.getClass(), and thus
            // SQL injection may occur

            if (val == null) {
                context.keyword("null");
            }
            else if (type == Boolean.class) {

                // [#1153] Some dialects don't support boolean literals
                // TRUE and FALSE
                if (asList(ASE, DB2, FIREBIRD, ORACLE, SQLSERVER, SQLITE, SYBASE).contains(family)) {
                    context.sql(((Boolean) val) ? "1" : "0");
                }
                else {
                    context.keyword(((Boolean) val).toString());
                }
            }

            // [#1154] Binary data cannot always be inlined
            else if (type == byte[].class) {
                byte[] binary = (byte[]) val;

                if (asList(ASE, SQLSERVER, SYBASE).contains(family)) {
                    context.sql("0x")
                           .sql(convertBytesToHex(binary));
                }
                else if (family == DB2) {
                    context.keyword("blob")
                           .sql("(X'")
                           .sql(convertBytesToHex(binary))
                           .sql("')");
                }
                else if (asList(DERBY, H2, HSQLDB, INGRES, MARIADB, MYSQL, SQLITE).contains(family)) {
                    context.sql("X'")
                           .sql(convertBytesToHex(binary))
                           .sql("'");
                }
                else if (asList(ORACLE).contains(family)) {
                    context.keyword("hextoraw('")
                           .sql(convertBytesToHex(binary))
                           .sql("')");
                }
                else if (family == POSTGRES) {
                    context.sql("E'")
                           .sql(convertBytesToPostgresOctal(binary))
                           .keyword("'::bytea");
                }

                // This default behaviour is used in debug logging for dialects
                // that do not support inlining binary data
                else {
                    context.sql("X'")
                           .sql(convertBytesToHex(binary))
                           .sql("'");
                }
            }

            // Interval extends Number, so let Interval come first!
            else if (Interval.class.isAssignableFrom(type)) {
                context.sql("'")
                       .sql(escape(val))
                       .sql("'");
            }

            else if (Number.class.isAssignableFrom(type)) {
                context.sql(((Number) val).toString());
            }

            // [#1156] Date/Time data types should be inlined using JDBC
            // escape syntax
            else if (type == Date.class) {

                // The SQLite JDBC driver does not implement the escape syntax
                // [#1253] SQL Server and Sybase do not implement date literals
                if (asList(ASE, SQLITE, SQLSERVER, SYBASE).contains(family)) {
                    context.sql("'").sql(escape(val)).sql("'");
                }

                // [#1253] Derby doesn't support the standard literal
                else if (family == DERBY) {
                    context.keyword("date('").sql(escape(val)).sql("')");
                }

                // Most dialects implement SQL standard date literals
                else {
                    context.keyword("date '").sql(escape(val)).sql("'");
                }
            }
            else if (type == Timestamp.class) {

                // The SQLite JDBC driver does not implement the escape syntax
                // [#1253] SQL Server and Sybase do not implement timestamp literals
                if (asList(ASE, SQLITE, SQLSERVER, SYBASE).contains(family)) {
                    context.sql("'").sql(escape(val)).sql("'");
                }

                // [#1253] Derby doesn't support the standard literal
                else if (family == DERBY) {
                    context.keyword("timestamp('").sql(escape(val)).sql("')");
                }

                // CUBRID timestamps have no fractional seconds
                else if (family == CUBRID) {
                    context.keyword("datetime '").sql(escape(val)).sql("'");
                }

                // Most dialects implement SQL standard timestamp literals
                else {
                    context.keyword("timestamp '").sql(escape(val)).sql("'");
                }
            }
            else if (type == Time.class) {

                // The SQLite JDBC driver does not implement the escape syntax
                // [#1253] SQL Server and Sybase do not implement time literals
                if (asList(ASE, SQLITE, SQLSERVER, SYBASE).contains(family)) {
                    context.sql("'").sql(escape(val)).sql("'");
                }

                // [#1253] Derby doesn't support the standard literal
                else if (family == DERBY) {
                    context.keyword("time").sql("('").sql(escape(val)).sql("')");
                }

                // [#1253] Oracle doesn't know time literals
                else if (family == ORACLE) {
                    context.keyword("timestamp").sql(" '1970-01-01 ").sql(escape(val)).sql("'");
                }

                // Most dialects implement SQL standard time literals
                else {
                    context.keyword("time").sql(" '").sql(escape(val)).sql("'");
                }
            }
            else if (type.isArray()) {
                String separator = "";

                // H2 renders arrays as rows
                if (family == H2) {
                    context.sql("(");

                    for (Object o : ((Object[]) val)) {
                        context.sql(separator);
                        toSQL(context, o, type.getComponentType());
                        separator = ", ";
                    }

                    context.sql(")");
                }

                // By default, render HSQLDB / POSTGRES syntax
                else {
                    context.keyword("ARRAY");
                    context.sql("[");

                    for (Object o : ((Object[]) val)) {
                        context.sql(separator);
                        toSQL(context, o, type.getComponentType());
                        separator = ", ";
                    }

                    context.sql("]");
                }
            }
            else if (ArrayRecord.class.isAssignableFrom(type)) {
                context.sql(val.toString(), true);
            }
            else if (EnumType.class.isAssignableFrom(type)) {
                toSQL(context, ((EnumType) val).getLiteral());
            }
            else if (UDTRecord.class.isAssignableFrom(type)) {
                context.sql("[UDT]");
            }

            // Known fall-through types:
            // - Blob, Clob (both not supported by jOOQ)
            // - String
            // - UUID
            else {
                context.sql("'")
                       .sql(escape(val), true)
                       .sql("'");
            }
        }

        // In Postgres, some additional casting must be done in some cases...
        // TODO: Improve this implementation with [#215] (cast support)
        else if (family == SQLDialect.POSTGRES) {

            // Postgres needs explicit casting for array types
            if (type.isArray() && byte[].class != type) {
                context.sql(getBindVariable(context));
                context.sql("::");
                context.keyword(DefaultDataType.getDataType(family, type).getCastTypeName(context.configuration()));
            }

            // ... and also for enum types
            else if (EnumType.class.isAssignableFrom(type)) {
                context.sql(getBindVariable(context));

                // [#968] Don't cast "synthetic" enum types (note, val can be null!)
                EnumType e = (EnumType) type.getEnumConstants()[0];
                Schema schema = e.getSchema();

                if (schema != null) {
                    context.sql("::");

                    schema = using(context.configuration()).map(schema);
                    if (schema != null && TRUE.equals(context.configuration().settings().isRenderSchema())) {
                        context.visit(schema);
                        context.sql(".");
                    }

                    context.visit(name(e.getName()));
                }
            }

            else {
                context.sql(getBindVariable(context));
            }
        }

        else {
            context.sql(getBindVariable(context));
        }
    }

    /**
     * Escape a string literal by replacing <code>'</code> by <code>''</code>
     */
    private final String escape(Object val) {
        return val.toString().replace("'", "''");
    }

    @Override
    public final void bind(BindContext context) {

        // [#1302] Bind value only if it was not explicitly forced to be inlined
        if (!isInline()) {
            context.bindValue(value, getType());
        }
    }

    // ------------------------------------------------------------------------
    // XXX: Param API
    // ------------------------------------------------------------------------

    /**
     * Convert a byte array to a hex encoded string.
     *
     * @param value the byte array
     * @return the hex encoded string
     */
    private static final String convertBytesToHex(byte[] value) {
        return convertBytesToHex(value, value.length);
    }

    /**
     * Convert a byte array to a hex encoded string.
     *
     * @param value the byte array
     * @param len the number of bytes to encode
     * @return the hex encoded string
     */
    private static final String convertBytesToHex(byte[] value, int len) {
        char[] buff = new char[len + len];
        char[] hex = HEX;
        for (int i = 0; i < len; i++) {
            int c = value[i] & 0xff;
            buff[i + i] = hex[c >> 4];
            buff[i + i + 1] = hex[c & 0xf];
        }
        return new String(buff);
    }

    /**
     * Postgres uses octals instead of hex encoding
     */
    private static final String convertBytesToPostgresOctal(byte[] binary) {
        StringBuilder sb = new StringBuilder();

        for (byte b : binary) {
            sb.append("\\\\");
            sb.append(leftPad(toOctalString(b), 3, '0'));
        }

        return sb.toString();
    }
}
