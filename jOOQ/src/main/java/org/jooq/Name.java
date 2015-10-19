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

import static org.jooq.SQLDialect.CUBRID;
import static org.jooq.SQLDialect.DB2;
import static org.jooq.SQLDialect.FIREBIRD;
import static org.jooq.SQLDialect.H2;
import static org.jooq.SQLDialect.HANA;
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.INFORMIX;
import static org.jooq.SQLDialect.ORACLE;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLSERVER;
import static org.jooq.SQLDialect.SYBASE;

import javax.annotation.Generated;

import org.jooq.conf.Settings;

/**
 * A SQL identifier <code>QueryPart</code>.
 * <p>
 * A <code>Name</code> is a {@link QueryPart} that renders a SQL identifier
 * according to the settings specified in {@link Settings#getRenderNameStyle()}.
 *
 * @author Lukas Eder
 */
public interface Name extends QueryPart {

    /**
     * The qualified name of this SQL identifier.
     */
    String[] getName();

    /**
     * Create a {@link WindowDefinition} from this name.
     * <p>
     * This creates a window definition that can be
     * <ul>
     * <li>declared in the <code>WINDOW</code> clause (see
     * {@link SelectWindowStep#window(WindowDefinition...)}</li>
     * <li>referenced from the <code>OVER</code> clause (see
     * {@link AggregateFunction#over(WindowDefinition)}</li>
     * </ul>
     */
    @Support({ CUBRID, DB2, HANA, INFORMIX, POSTGRES, ORACLE, SQLSERVER, SYBASE })
    WindowDefinition as(WindowSpecification window);

    /**
     * Specify a subselect to refer to by the <code>Name</code> to form a common
     * table expression.
     * <p>
     * Column names are implicitly inherited from the <code>SELECT</code>
     * statement.
     */
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    <R extends Record> CommonTableExpression<R> as(Select<R> select);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList fields(String... fieldNames);

    // [jooq-tools] START [fields]

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList1 fields(String fieldName1);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList2 fields(String fieldName1, String fieldName2);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList3 fields(String fieldName1, String fieldName2, String fieldName3);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList4 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList5 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList6 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList7 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList8 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList9 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList10 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList11 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList12 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList13 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList14 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList15 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList16 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList17 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList18 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList19 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList20 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19, String fieldName20);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList21 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19, String fieldName20, String fieldName21);

    /**
     * Add a list of fields to this name to make this name a
     * {@link DerivedColumnList}.
     * <p>
     * The <code>DerivedColumnList</code> can then be used along with a
     * subselect to form a {@link CommonTableExpression} to be used with
     * <code>WITH</code> clauses.
     */
    @Generated("This method was generated using jOOQ-tools")
    @Support({ DB2, FIREBIRD, H2, HSQLDB, ORACLE, POSTGRES, SQLSERVER, SYBASE })
    DerivedColumnList22 fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19, String fieldName20, String fieldName21, String fieldName22);

// [jooq-tools] END [fields]
}
