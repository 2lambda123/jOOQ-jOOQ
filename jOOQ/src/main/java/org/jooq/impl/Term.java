/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
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
 * and Maintenance Agreement for more details: http://www.jooq.org/eula
 */
package org.jooq.impl;

import org.jooq.SQLDialect;

/**
 * The term dictionary enumerates standard expressions and their
 * dialect-specific variants if applicable
 *
 * @author Lukas Eder
 */
enum Term {

    ATAN2 {
        @Override
        public String translate(SQLDialect dialect) {
            /* [com] */
            switch (dialect.family()) {
                case ASE:
                case SQLSERVER:
                    return "atn2";
            }

            /* [/com] */
            return "atan2";
        }
    },
    BIT_LENGTH {
        @Override
        public String translate(SQLDialect dialect) {
            switch (dialect.family()) {
                /* [com] */
                case ASE:
                    return "8 * datalength";

                case SQLSERVER:
                    return "8 * len";

                case ORACLE:
                    return "8 * lengthb";

                case DB2:
                case INGRES:
                case SYBASE:
                /* [/com] */
                case DERBY:
                case SQLITE:
                    return "8 * length";
            }

            return "bit_length";
        }
    },
    CHAR_LENGTH {
        @Override
        public String translate(SQLDialect dialect) {
            switch (dialect.family()) {
                /* [com] */
                case SQLSERVER:
                    return "len";

                case DB2:
                case INGRES:
                case ORACLE:
                case SYBASE:
                /* [/com] */
                case DERBY:
                case SQLITE:
                    return "length";
            }

            return "char_length";
        }
    },
    LIST_AGG {
        @Override
        public String translate(SQLDialect dialect) {
            switch (dialect.family()) {
                /* [com] */
                // DB2 needs to do some rather complex XML manipulation to
                // achieve the same results. XMLAGG() itself cannot do it
                case DB2:
                    return "xmlagg";

                case ORACLE:
                    return "listagg";

                case SYBASE:
                    return "list";

                /* [/com] */
                case CUBRID:
                case H2:
                case HSQLDB:
                case MARIADB:
                case MYSQL:
                    return "group_concat";

                case POSTGRES:
                    return "string_agg";
            }

            return "listagg";
        }
    },
    OCTET_LENGTH {
        @Override
        public String translate(SQLDialect dialect) {
            switch (dialect.family()) {
                /* [com] */
                case SQLSERVER:
                    return "len";

                case ORACLE:
                    return "lengthb";

                case DB2:
                case INGRES:
                case SYBASE:
                /* [/com] */
                case DERBY:
                case SQLITE:
                    return "length";
            }

            return "octet_length";
        }
    },
    ROW_NUMBER {
        @Override
        public String translate(SQLDialect dialect) {
            switch (dialect.family()) {
                case HSQLDB:
                    return "rownum";
            }

            return "row_number";
        }
    },
    STDDEV_POP {
        @Override
        public String translate(SQLDialect dialect) {
            /* [com] */
            switch (dialect.family()) {
                case DB2:
                    return "stddev";

                case SQLSERVER:
                    return "stdevp";
            }

            /* [/com] */
            return "stddev_pop";
        }
    },
    STDDEV_SAMP {
        @Override
        public String translate(SQLDialect dialect) {
            /* [com] */
            switch (dialect.family()) {
                case DB2:
                    return "stddev";

                case SQLSERVER:
                    return "stdev";
            }

            /* [/com] */
            return "stddev_samp";
        }
    },
    VAR_POP {
        @Override
        public String translate(SQLDialect dialect) {
            /* [com] */
            switch (dialect.family()) {
                case DB2:
                    return "variance";

                case SQLSERVER:
                    return "varp";
            }

            /* [/com] */
            return "var_pop";
        }
    },
    VAR_SAMP {
        @Override
        public String translate(SQLDialect dialect) {
            /* [com] */
            switch (dialect.family()) {
                case DB2:
                    return "variance";

                case SQLSERVER:
                    return "var";
            }

            /* [/com] */
            return "var_samp";
        }
    },

    ;

    /**
     * Translate the term to its dialect-specific variant
     */
    abstract String translate(SQLDialect dialect);
}
