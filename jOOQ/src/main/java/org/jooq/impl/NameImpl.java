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

import java.util.Arrays;

import javax.annotation.Generated;

import org.jooq.Clause;
import org.jooq.CommonTableExpression;
import org.jooq.Context;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Select;
import org.jooq.WindowDefinition;
import org.jooq.WindowSpecification;
import org.jooq.tools.StringUtils;

/**
 * The default implementation for a SQL identifier
 *
 * @author Lukas Eder
 */
class NameImpl extends AbstractQueryPart implements Name {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = 8562325639223483938L;

    private String[]          qualifiedName;

    NameImpl(String[] qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @Override
    public final void accept(Context<?> ctx) {

        // [#3437] Fully qualify this field only if allowed in the current context
        if (ctx.qualify()) {
            String separator = "";

            for (String name : qualifiedName) {
                if (!StringUtils.isEmpty(name)) {
                    ctx.sql(separator).literal(name);
                    separator = ".";
                }
            }
        }
        else {
            ctx.literal(qualifiedName[qualifiedName.length - 1]);
        }
    }

    @Override
    public final Clause[] clauses(Context<?> ctx) {
        return null;
    }

    @Override
    public final String[] getName() {
        return qualifiedName;
    }

    @Override
    public final WindowDefinition as(WindowSpecification window) {
        return new WindowDefinitionImpl(this, window);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <R extends Record> CommonTableExpression<R> as(Select<R> select) {
        return fields(new String[0]).as(select);
    }

    @Override
    public final DerivedColumnListImpl fields(String... fieldNames) {
        if (qualifiedName.length != 1)
            throw new IllegalStateException("Cannot create a DerivedColumnList from a qualified name : " + Arrays.asList(qualifiedName));

        return new DerivedColumnListImpl(qualifiedName[0], fieldNames);
    }

    // [jooq-tools] START [fields]

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1) {
        return fields(new String[] { fieldName1 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2) {
        return fields(new String[] { fieldName1, fieldName2 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16, fieldName17 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16, fieldName17, fieldName18 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16, fieldName17, fieldName18, fieldName19 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19, String fieldName20) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16, fieldName17, fieldName18, fieldName19, fieldName20 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19, String fieldName20, String fieldName21) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16, fieldName17, fieldName18, fieldName19, fieldName20, fieldName21 });
    }

    @Generated("This method was generated using jOOQ-tools")
    @Override
    public final DerivedColumnListImpl fields(String fieldName1, String fieldName2, String fieldName3, String fieldName4, String fieldName5, String fieldName6, String fieldName7, String fieldName8, String fieldName9, String fieldName10, String fieldName11, String fieldName12, String fieldName13, String fieldName14, String fieldName15, String fieldName16, String fieldName17, String fieldName18, String fieldName19, String fieldName20, String fieldName21, String fieldName22) {
        return fields(new String[] { fieldName1, fieldName2, fieldName3, fieldName4, fieldName5, fieldName6, fieldName7, fieldName8, fieldName9, fieldName10, fieldName11, fieldName12, fieldName13, fieldName14, fieldName15, fieldName16, fieldName17, fieldName18, fieldName19, fieldName20, fieldName21, fieldName22 });
    }

// [jooq-tools] END [fields]

    // ------------------------------------------------------------------------
    // XXX: Object API
    // ------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Arrays.hashCode(getName());
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        // [#1626] NameImpl equality can be decided without executing the
        // rather expensive implementation of AbstractQueryPart.equals()
        if (that instanceof NameImpl) {
            return Arrays.equals(getName(), (((NameImpl) that).getName()));
        }

        return super.equals(that);
    }
}
