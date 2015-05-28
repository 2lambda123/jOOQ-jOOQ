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

package org.jooq;

import java.util.EnumSet;
import java.util.Set;

/**
 * Dialects and dialect families as supported by jOOQ.
 * <p>
 * jOOQ supports a variety of dialects, which are grouped into dialect families.
 * For instance, the SQL Server dialect family {@link #POSTGRES} is specialised
 * by its dialects
 * <ul>
 * <li> {@link #POSTGRES_9_3}</li>
 * <li> {@link #POSTGRES_9_4}</li>
 * </ul>
 *
 * @author Lukas Eder
 */
public enum SQLDialect {

    /**
     * The standard SQL dialect family.
     *
     * @deprecated - [#3844] - 3.6.0 - {@link #DEFAULT} will replace this
     *             pseudo-dialect.
     */
    @Deprecated
    SQL99("", false),

    /**
     * The default SQL dialect.
     * <p>
     * This dialect is chosen in the absence of a more explicit dialect. It is
     * not intended to be used with any actual database as it may combined
     * dialect-specific things from various dialects.
     */
    DEFAULT("", false),

    // -------------------------------------------------------------------------
    // SQL dialects for free usage
    // -------------------------------------------------------------------------

    /**
     * The CUBRID SQL dialect family.
     */
    CUBRID("CUBRID", false),

    /**
     * The Apache Derby SQL dialect family.
     */
    DERBY("Derby", false),

    /**
     * The Firebird SQL dialect family.
     */
    FIREBIRD("Firebird", false),

    /**
     * The H2 SQL dialect family.
     */
    H2("H2", false),

    /**
     * The Hypersonic SQL dialect family.
     */
    HSQLDB("HSQLDB", false),

    /**
     * The MariaDB dialect family.
     */
    MARIADB("MariaDB", false),

    /**
     * The MySQL dialect family.
     */
    MYSQL("MySQL", false),

    /**
     * The PostgreSQL dialect family.
     * <p>
     * While this family (and its dialects) have been observed to work to some
     * extent on Amazon RedShift as well, we strongly suggest you use the
     * official {@link #REDSHIFT} support, instead.
     */
    POSTGRES("Postgres", false),

    /**
     * The PostgreSQL dialect family.
     * <p>
     * While this family (and its dialects) have been observed to work to some
     * extent on Amazon RedShift as well, we strongly suggest you use the
     * official {@link #REDSHIFT} support, instead.
     */
    POSTGRES_9_3("Postgres", false, POSTGRES, null),

    /**
     * The PostgreSQL dialect family.
     * <p>
     * While this family (and its dialects) have been observed to work to some
     * extent on Amazon RedShift as well, we strongly suggest you use the
     * official {@link #REDSHIFT} support, instead.
     */
    POSTGRES_9_4("Postgres", false, POSTGRES, POSTGRES_9_3),

    /**
     * The SQLite dialect family.
     */
    SQLITE("SQLite", false),

    // -------------------------------------------------------------------------
    // SQL dialects for commercial usage
    // -------------------------------------------------------------------------

    /* [pro] */

    /**
     * The MS Access SQL dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ACCESS("Access", true),

    /**
     * The MS Access 2013 SQL dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ACCESS2013("Access", true, ACCESS),

    /**
     * The Sybase Adaptive Server SQL dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ASE("ASE", true),

    /**
     * The IBM DB2 SQL dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    DB2("DB2", true),

    /**
     * The IBM DB2 9.x SQL dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    DB2_9("DB2", true, DB2),

    /**
     * The IBM DB2 10.x SQL dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    DB2_10("DB2", true, DB2, DB2_9),

    /**
     * The SAP HANA SQL dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    HANA("Hana", true),

    /**
     * The Informix SQL dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    INFORMIX("Informix", true),

    /**
     * The Ingres dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    INGRES("Ingres", true),

    /**
     * The Oracle dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ORACLE("Oracle", true),

    /**
     * The Oracle 10g dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ORACLE10G("Oracle", true, ORACLE),

    /**
     * The Oracle 11g dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ORACLE11G("Oracle", true, ORACLE, ORACLE10G),

    /**
     * The Oracle 12c dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    ORACLE12C("Oracle", true, ORACLE, ORACLE11G),

    /**
     * The Amazon Redshift dialect.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    REDSHIFT("Redshift", true),

    /**
     * The SQL Server dialect family.
     * <p>
     * Use this family (or its dialects) for SQL Server or SQL Azure.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    SQLSERVER("SQLServer", true),

    /**
     * The SQL Server 2008 dialect.
     * <p>
     * Use this family (or its dialects) for SQL Server or SQL Azure.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    SQLSERVER2008("SQLServer", true, SQLSERVER),

    /**
     * The SQL Server 2012 dialect.
     * <p>
     * Use this family (or its dialects) for SQL Server or SQL Azure.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    SQLSERVER2012("SQLServer", true, SQLSERVER, SQLSERVER2008),

    /**
     * The SQL Server 2014 dialect.
     * <p>
     * Use this family (or its dialects) for SQL Server or SQL Azure.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    SQLSERVER2014("SQLServer", true, SQLSERVER, SQLSERVER2012),

    /**
     * The Sybase SQL Anywhere dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    SYBASE("Sybase", true),

    /**
     * The Vertica dialect family.
     * <p>
     * This dialect is available in commercial jOOQ distributions, only.
     */
    VERTICA("Vertica", true),

    /* [/pro] */

    ;

    private static final SQLDialect[] FAMILIES;

    static {
        Set<SQLDialect> set = EnumSet.noneOf(SQLDialect.class);

        for (SQLDialect dialect : values()) {
            set.add(dialect.family());
        }

        FAMILIES = set.toArray(new SQLDialect[set.size()]);
    }

    private final String              name;
    private final boolean             commercial;
    private final SQLDialect          family;
    private SQLDialect                predecessor;
    private final ThirdParty          thirdParty;

    private SQLDialect(String name, boolean commercial) {
        this(name, commercial, null, null);
    }

    private SQLDialect(String name, boolean commercial, SQLDialect family) {
        this(name, commercial, family, null);
    }

    private SQLDialect(String name, boolean commercial, SQLDialect family, SQLDialect predecessor) {
        this.name = name;
        this.commercial = commercial;
        this.family = family == null ? this : family;
        this.predecessor = predecessor == null ? this : predecessor;

        if (family != null)
            family.predecessor = this;

        this.thirdParty = new ThirdParty();
    }

    /**
     * Whether this dialect is supported with the jOOQ commercial license only.
     */
    public final boolean commercial() {
        return commercial;
    }

    /**
     * The dialect family.
     * <p>
     * This returns the dialect itself, if it has no "parent family". E.g.
     * <code><pre>
     * SQLSERVER == SQLSERVER2012.family();
     * SQLSERVER == SQLSERVER2008.family();
     * SQLSERVER == SQLSERVER.family();
     * </pre></code>
     */
    public final SQLDialect family() {
        return family;
    }

    /**
     * The predecessor dialect.
     * <p>
     * If this is a dialect version (e.g. {@link #POSTGRES_9_4}) within a family
     * (e.g. {@link #POSTGRES}), then the predecessor will point to the
     * historically previous dialect version (e.g. {@link #POSTGRES_9_3}) within
     * the same family, or to the dialect itself if there was no predecessor
     * explicitly supported by jOOQ.
     */
    public final SQLDialect predecessor() {
        return predecessor;
    }

    /**
     * Whether this dialect precedes an other dialect from the same family.
     * <p>
     * This returns:
     * <ul>
     * <li><code>true</code> if this dialect is the same as the other dialect</li>
     * <li><code>true</code> if this dialect precedes the other dialect via any
     * number of calls to {@link #predecessor()}</li>
     * </ul>
     * The above also implies that:
     * <ul>
     * <li><code>false</code> if the two dialects do not belong to the same
     * family</li>
     * </ul>
     * <p>
     * This is useful to see if some feature is supported by <em>"at least"</em>
     * a given dialect version. Example: <code><pre>
     * // Do this block only if the chosen dialect supports PostgreSQL 9.4+ features
     * if (POSTGRES_9_4.precedes(dialect)) {
     * }
     *
     * // Do this block only if the chosen dialect supports PostgreSQL 9.3+ features
     * else if (POSTGRES_9_3.precedes(dialect)) {
     * }
     *
     * // Fall back to pre-PostgreSQL 9.3 behaviour
     * else {
     * }
     * </pre></code>
     */
    public final boolean precedes(SQLDialect other) {
        if (family != other.family)
            return false;

        SQLDialect candidate = other;
        while (candidate != null) {
            if (this == candidate)
                return true;

            if (candidate == candidate.predecessor())
                return false;

            candidate = candidate.predecessor();
        }

        return false;
    }

    /**
     * The name of this dialect as it appears in related class names.
     */
    public final String getName() {
        return name;
    }

    /**
     * The name of this dialect as it appears in related package names.
     */
    public final String getNameLC() {
        return name == null ? null : name.toLowerCase();
    }

    /**
     * The name of this dialect as it appears in related enum values.
     */
    public final String getNameUC() {
        return name == null ? null : name.toUpperCase();
    }

    /**
     * Get a list of all {@link SQLDialect#family()} values.
     */
    public static final SQLDialect[] families() {
        return FAMILIES.clone();
    }

    /**
     * Get access to third party representations of this {@link SQLDialect}.
     */
    public final ThirdParty thirdParty() {
        return thirdParty;
    }

    /**
     * Third party representations of the enclosing {@link SQLDialect}.
     */
    public final class ThirdParty {

        /**
         * The Spring DB name or <code>null</code>, if the db name is not
         * supported by Spring.
         * <p>
         * The name returned by this method corresponds to the DB id as
         * referenced in
         * <code>org/springframework/jdbc/support/sql-error-codes.xml</code>
         */
        public final String springDbName() {
            switch (SQLDialect.this.family) {
                /* [pro] */
                case ASE:       return "Sybase";
                case DB2:       return "DB2";
                case HANA:      return "Hana";
                case INFORMIX:  return "Informix";
                case ORACLE:    return "Oracle";
                case SQLSERVER: return "MS-SQL";
                /* [/pro] */

                case DERBY:     return "Derby";
                case H2:        return "H2";
                case HSQLDB:    return "HSQL";
                case MARIADB:
                case MYSQL:     return "MySQL";
                case POSTGRES:  return "PostgreSQL";

                default:        return null;
            }
        }

        /**
         * The Hibernate dialect name or <code>null</code>, if the dialect is
         * not supported by Hibernate.
         * <p>
         *
         * @see <a href=
         *      "http://docs.jboss.org/hibernate/orm/5.0/javadocs/org/hibernate/dialect/package-summary.html">
         *      http://docs.jboss.org/hibernate/orm/5.0/javadocs/org/hibernate/
         *      dialect/package-summary.html</a>
         */
        public final String hibernateDialect() {
            switch (SQLDialect.this) {
                /* [pro] */
                case ACCESS:
                case ACCESS2013:    return null;
                case ASE:           return "org.hibernate.dialect.SybaseASE15Dialect";
                case DB2_9:
                case DB2_10:
                case DB2:           return "org.hibernate.dialect.DB2Dialect";
                case HANA:          return "org.hibernate.dialect.HANARowStoreDialect";
                case INFORMIX:      return "org.hibernate.dialect.InformixDialect";
                case INGRES:        return "org.hibernate.dialect.Ingres10Dialect";
                case ORACLE10G:
                case ORACLE11G:     return "org.hibernate.dialect.Oracle10gDialect";
                case ORACLE12C:
                case ORACLE:        return "org.hibernate.dialect.Oracle12cDialect";
                case REDSHIFT:      return "org.hibernate.dialect.PostgreSQL81Dialect";
                case SQLSERVER2008: return "org.hibernate.dialect.SQLServer2008Dialect";
                case SQLSERVER2012:
                case SQLSERVER2014:
                case SQLSERVER:     return "org.hibernate.dialect.SQLServer2012Dialect";
                case SYBASE:        return "org.hibernate.dialect.SybaseAnywhereDialect";
                /* [/pro] */

                case CUBRID:        return "org.hibernate.dialect.CUBRIDDialect";
                case DERBY:         return "org.hibernate.dialect.DerbyTenSevenDialect";
                case FIREBIRD:      return "org.hibernate.dialect.FirebirdDialect";
                case H2:            return "org.hibernate.dialect.H2Dialect";
                case HSQLDB:        return "org.hibernate.dialect.HSQLDialect";
                case MARIADB:
                case MYSQL:         return "org.hibernate.dialect.MySQL5Dialect";
                case POSTGRES_9_3:  return "org.hibernate.dialect.PostgreSQL92Dialect";
                case POSTGRES_9_4:
                case POSTGRES:      return "org.hibernate.dialect.PostgreSQL94Dialect";
                case SQLITE:        return null;

                default:            return null;
            }
        }
    }
}
