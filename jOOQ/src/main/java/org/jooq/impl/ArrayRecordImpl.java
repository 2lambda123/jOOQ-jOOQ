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

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jooq.ArrayRecord;
import org.jooq.Attachable;
import org.jooq.Configuration;
import org.jooq.DataType;
import org.jooq.Schema;
import org.jooq.tools.Convert;

/**
 * A common base class for Oracle ARRAY types
 * <p>
 * This type is for JOOQ INTERNAL USE only. Do not reference directly
 *
 * @author Lukas Eder
 */
public class ArrayRecordImpl<T> extends AbstractStore implements ArrayRecord<T> {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -908937248705184108L;

    private final Schema      schema;
    private final DataType<T> type;
    private final String      name;
    private T[]               array;

    /**
     * Create an empty array record
     */
    @SuppressWarnings("unchecked")
    protected ArrayRecordImpl(Schema schema, String name, DataType<T> type, Configuration configuration) {
        super(configuration);

        this.schema = schema;
        this.name = name;
        this.type = type;

        // Array data type initialisation
        type.asArrayDataType(getClass());
    }

    /**
     * Create an empty array record
     */
    protected ArrayRecordImpl(Schema schema, String name, DataType<T> type) {
        this(schema, name, type, null);
    }

    // -------------------------------------------------------------------------
    // The Attachable API
    // -------------------------------------------------------------------------

    @Override
    final List<Attachable> getAttachables() {
        List<Attachable> result = new ArrayList<Attachable>();

        if (Attachable.class.isAssignableFrom(type.getType())) {
            for (T element : get()) {
                result.add((Attachable) element);
            }
        }

        return result;
    }

    // -------------------------------------------------------------------------
    // The ArrayRecord API
    // -------------------------------------------------------------------------

    @Override
    final T getValue(int index) {
        return get()[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T[] get() {
        if (array == null) {
            return (T[]) Array.newInstance(type.getType(), 0);
        }
        else {
            return array;
        }
    }

    @Override
    public final List<T> getList() {
        if (array == null) {
            return Collections.emptyList();
        }
        else {
            return Arrays.asList(array);
        }
    }

    @Override
    public final void set(T... array) {
        this.array = array;
    }

    @Override
    public final void set(java.sql.Array array) throws SQLException {
        if (array == null) {
            this.array = null;
        }
        else {
            Object o;

            // [#1179 #1376 #1377] This is needed to load TABLE OF OBJECT
            // [#884] TODO: This name is used in inlined SQL. It should be
            // correctly escaped and schema mapped!
            o = array.getArray(DataTypes.udtRecords());
            this.array = Convert.convert(o, type.getArrayType());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void setList(List<? extends T> list) {
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray((T[]) Array.newInstance(type.getType(), 0));
        }
    }

    @Override
    public final int size() {
        return get().length;
    }

    @Override
    public final String getName() {

        // [#1179] When Schema is present, the name is not fully qualified
        if (schema != null) {
            return schema.getName() + "." + name;
        }

        // When Schema is absent, the name is fully qualified (deprecated, pre 2.0.5)
        else {
            return name;
        }
    }

    @Override
    public final DataType<T> getDataType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String separator = "";

        result.append(getName());
        result.append("(");

        if (array != null) {
            for (T t : array) {
                result.append(separator);
                result.append(t);

                separator = ", ";
            }
        }

        result.append(")");
        return result.toString();
    }

    @Override
    public final Iterator<T> iterator() {
        return getList().iterator();
    }
}
