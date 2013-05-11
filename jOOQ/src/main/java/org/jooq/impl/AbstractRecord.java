/**
 * Copyright (c) 2009-2013, Lukas Eder, lukas.eder@gmail.com
 * All rights reserved.
 *
 * This software is licensed to you under the Apache License, Version 2.0
 * (the "License"); You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name "jOOQ" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.jooq.impl;

import static java.util.Arrays.asList;
import static org.jooq.KeepResultSetMode.UPDATE_ON_CHANGE;
import static org.jooq.impl.Utils.getAnnotatedGetter;
import static org.jooq.impl.Utils.getAnnotatedMembers;
import static org.jooq.impl.Utils.getMatchingGetter;
import static org.jooq.impl.Utils.getMatchingMembers;
import static org.jooq.impl.Utils.hasColumnAnnotations;
import static org.jooq.impl.Utils.translate;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jooq.Attachable;
import org.jooq.Converter;
import org.jooq.Field;
import org.jooq.KeepResultSetMode;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.UniqueKey;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.InvalidResultException;
import org.jooq.exception.MappingException;
import org.jooq.impl.CursorImpl.CursorResultSet;
import org.jooq.tools.Convert;
import org.jooq.tools.JooqLogger;

/**
 * A general base class for all {@link Record} types
 *
 * @author Lukas Eder
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
abstract class AbstractRecord extends AbstractStore implements Record {

    /**
     * Generated UID
     */
    private static final long       serialVersionUID = -6052512608911220404L;
    private static final JooqLogger log              = JooqLogger.getLogger(AbstractRecord.class);

    final RowImpl                   fields;
    final Value<?>[]                values;
    transient KeepResultSetMode     keepResultSetMode;
    transient CursorResultSet       rs;
    transient int                   rsIndex;

    AbstractRecord(Collection<? extends Field<?>> fields) {
        this(new RowImpl(fields));
    }

    AbstractRecord(Field<?>... fields) {
        this(new RowImpl(fields));
    }

    AbstractRecord(RowImpl fields) {
        this.fields = fields;
        this.values = new Value<?>[fields.size()];

        for (int i = 0; i < values.length; i++) {
            values[i] = new Value<Object>(null);
        }
    }

    // ------------------------------------------------------------------------
    // XXX: Attachable API
    // ------------------------------------------------------------------------

    @Override
    final List<Attachable> getAttachables() {
        List<Attachable> result = new ArrayList<Attachable>();

        int size = size();
        for (int i = 0; i < size; i++) {
            Object value = getValue0(i).getValue();

            if (value instanceof Attachable) {
                result.add((Attachable) value);
            }
        }

        return result;
    }

    // ------------------------------------------------------------------------
    // XXX: FieldProvider API
    // ------------------------------------------------------------------------

    @Override
    public final <T> Field<T> field(Field<T> field) {
        return fieldsRow().field(field);
    }

    @Override
    public final Field<?> field(String name) {
        return fieldsRow().field(name);
    }

    @Override
    public final Field<?> field(int index) {
        return index >= 0 && index < fields.size() ? fields.field(index) : null;
    }

    @Override
    public final Field<?>[] fields() {
        return fields.fields();
    }

    // ------------------------------------------------------------------------
    // XXX: Record API
    // ------------------------------------------------------------------------

    @Override
    public final int size() {
        return fields.size();
    }

    @Override
    public final <T> T getValue(Field<T> field) {
        return getValue0(field).getValue();
    }

    @Override
    public final <T> T getValue(Field<T> field, T defaultValue) {
        return getValue0(field).getValue(defaultValue);
    }

    @Override
    public final <T> T getValue(Field<?> field, Class<? extends T> type) {
        return Convert.convert(getValue(field), type);
    }

    @Override
    public final <T> T getValue(Field<?> field, Class<? extends T> type, T defaultValue) {
        final T result = getValue(field, type);
        return result == null ? defaultValue : result;
    }

    @Override
    public final <T, U> U getValue(Field<T> field, Converter<? super T, U> converter) {
        return converter.from(getValue(field));
    }

    @Override
    public final <T, U> U getValue(Field<T> field, Converter<? super T, U> converter, U defaultValue) {
        final U result = getValue(field, converter);
        return result == null ? defaultValue : result;
    }

    @Override
    public final Object getValue(int index) {
        return getValue0(index).getValue();
    }

    @Override
    public final Object getValue(int index, Object defaultValue) {
        final Object result = getValue(index);
        return result == null ? defaultValue : result;
    }

    @Override
    public final <T> T getValue(int index, Class<? extends T> type) {
        return Convert.convert(getValue(index), type);
    }

    @Override
    public final <T> T getValue(int index, Class<? extends T> type, T defaultValue) {
        final T result = getValue(index, type);
        return result == null ? defaultValue : result;
    }

    @Override
    public final <U> U getValue(int index, Converter<?, U> converter) {
        return Convert.convert(getValue(index), converter);
    }

    @Override
    public final <U> U getValue(int index, Converter<?, U> converter, U defaultValue) {
        final U result = getValue(index, converter);
        return result == null ? defaultValue : result;
    }

    @Override
    public final Object getValue(String fieldName) {
        return getValue(field(fieldName));
    }

    @Override
    public final Object getValue(String fieldName, Object defaultValue) {
        return getValue((Field<Object>) field(fieldName), defaultValue);
    }

    @Override
    public final <T> T getValue(String fieldName, Class<? extends T> type) {
        return Convert.convert(getValue(fieldName), type);
    }

    @Override
    public final <T> T getValue(String fieldName, Class<? extends T> type, T defaultValue) {
        final T result = getValue(fieldName, type);
        return result == null ? defaultValue : result;
    }

    @Override
    public final <U> U getValue(String fieldName, Converter<?, U> converter) {
        return Convert.convert(getValue(fieldName), converter);
    }

    @Override
    public final <U> U getValue(String fieldName, Converter<?, U> converter, U defaultValue) {
        final U result = getValue(fieldName, converter);
        return result == null ? defaultValue : result;
    }

    final <T> Value<T> getValue0(int index) {
        Value<?>[] v = getValues();

        if (index >= v.length) {
            throw new IllegalArgumentException("Field " + index + " is not contained in list");
        }

        return (Value<T>) v[index];
    }

    final <T> Value<T> getValue0(Field<T> field) {
        return getValue0(fieldsRow().indexOf(field));
    }

    final Value<?>[] getValues() {
        return values;
    }

    @Override
    public final <T> void setValue(Field<T> field, T value) {
        Value<T> val = getValue0(field);

        // [#1846] Execute this first to fail early, when UPDATE_ON_CHANGE fails
        if (rs != null && keepResultSetMode == UPDATE_ON_CHANGE) {
            int index = fieldsRow().indexOf(field);
            int columnIndex = index + 1;

            if (log.isDebugEnabled()) {
                log.debug("Updating Result", "Updating Result position " + rsIndex + ":" + columnIndex + " with value " + value);
            }

            try {
                if (rs.getRow() != rsIndex) {
                    rs.absolute(rsIndex);
                }

                // [#1846] TODO: Add more typesafety here
                rs.updateObject(columnIndex, value);

                // [#1846] TODO: Update only in case of KeepResultSetMode.UPDATE_ON_CHANGE
                rs.updateRow();
            }
            catch (SQLException e) {
                throw translate("Error when updating ResultSet", e);
            }

            setValue(index, new Value<Object>(value));
        }

        // [#1846] In all other cases, correctly handle changed flags
        else {
            UniqueKey<?> key = getPrimaryKey();

            // Normal fields' changed flag is always set to true
            if (key == null || !key.getFields().contains(field)) {
                val.setValue(value);
            }

            // The primary key's changed flag might've been set previously
            else if (val.isChanged()) {
                val.setValue(value);
            }

            // [#979] If the primary key is being changed, all other fields' flags
            // need to be set to true for in case this record is stored again, an
            // INSERT statement will thus be issued
            else {
                val.setValue(value, true);

                if (val.isChanged()) {
                    changed(true);
                }
            }
        }
    }

    @Override
    public final <T, U> void setValue(Field<T> field, U value, Converter<T, ? super U> converter) {
        setValue(field, converter.to(value));
    }

    final void setValues(Field<?>[] fields, AbstractRecord record) {
        for (Field<?> field : fields) {
            setValue(field, record.getValue0(field));
        }
    }

    final void setValue(Field<?> field, Value<?> value) {
        setValue(fieldsRow().indexOf(field), value);
    }

    final void setValue(int index, Value<?> value) {
        getValues()[index] = value;
    }

    /**
     * Subclasses may type-unsafely set a value to a record index. This method
     * takes care of converting the value to the appropriate type.
     */
    protected final void setValue(int index, Object value) {
        getValue0(index).setValue(Convert.convert(value, fields.type(index)));
    }

    /**
     * Subclasses may override this
     */
    UniqueKey<?> getPrimaryKey() {
        return null;
    }

    /*
     * This method is overridden covariantly by TableRecordImpl
     */
    @Override
    public Record original() {
        AbstractRecord result = Utils.newRecord(getClass(), fields.fields.fields, configuration());
        Value<?>[] v = getValues();

        for (int i = 0; i < v.length; i++) {
            result.setValue(i, new Value<Object>(v[i].getOriginal()));
        }

        return result;
    }

    @Override
    public final <T> T original(Field<T> field) {
        return (T) original(fieldsRow().indexOf(field));
    }

    @Override
    public final Object original(int fieldIndex) {
        return getValues()[fieldIndex].getOriginal();
    }

    @Override
    public final Object original(String fieldName) {
        return original(fieldsRow().indexOf(fieldName));
    }

    @Override
    public final boolean changed() {
        for (Value<?> value : getValues()) {
            if (value.isChanged()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public final boolean changed(Field<?> field) {
        return changed(fieldsRow().indexOf(field));
    }

    @Override
    public final boolean changed(int fieldIndex) {
        return getValue0(fieldIndex).isChanged();
    }

    @Override
    public final boolean changed(String fieldName) {
        return changed(fieldsRow().indexOf(fieldName));
    }

    @Override
    public final void changed(boolean changed) {
        for (Value<?> value : getValues()) {
            value.setChanged(changed);
        }
    }

    @Override
    public final void changed(Field<?> field, boolean changed) {
        changed(fieldsRow().indexOf(field), changed);
    }

    @Override
    public final void changed(int fieldIndex, boolean changed) {
        getValue0(fieldIndex).setChanged(changed);
    }

    @Override
    public final void changed(String fieldName, boolean changed) {
        changed(fieldsRow().indexOf(fieldName), changed);
    }

    @Override
    public final void reset() {
        for (Value<?> value : getValues()) {
            value.reset();
        }
    }

    @Override
    public final void reset(Field<?> field) {
        reset(fieldsRow().indexOf(field));
    }

    @Override
    public final void reset(int fieldIndex) {
        getValue0(fieldIndex).reset();
    }

    @Override
    public final void reset(String fieldName) {
        reset(fieldsRow().indexOf(fieldName));
    }

    @Override
    public final Object[] intoArray() {
        return into(Object[].class);
    }

    @Override
    public final Map<String, Object> intoMap() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Field<?> field = fields.field(i);

            if (map.put(field.getName(), getValue(i)) != null) {
                throw new InvalidResultException("Field " + field.getName() + " is not unique in Record : " + this);
            }
        }

        return map;
    }

    @Override
    public final <E> E into(Class<? extends E> type) {
        return new ReflectionMapper<Record, E>(fields.fields(), type).map(this);
    }

    @Override
    public final <E> E into(E object) {
        if (object == null) {
            throw new NullPointerException("Cannot copy Record into null");
        }

        Class<E> type = (Class<E>) object.getClass();

        try {
            return new ReflectionMapper<Record, E>(fields.fields(), type, object).map(this);
        }

        // Pass MappingExceptions on to client code
        catch (MappingException e) {
            throw e;
        }

        // All other reflection exceptions are intercepted
        catch (Exception e) {
            throw new MappingException("An error ocurred when mapping record to " + type, e);
        }
    }

    @Override
    public final <R extends Record> R into(Table<R> table) {
        try {
            R result = Utils.newRecord(table, configuration());

            for (Field<?> targetField : table.fields()) {
                Field<?> sourceField = field(targetField);

                if (sourceField != null) {
                    Utils.setValue(result, targetField, this, sourceField);
                }
            }

            // [#1522] If the primary key has been fully fetched, then changed
            // flags should all be reset in order for the returned record to be
            // updatable using store()
            if (result instanceof AbstractRecord) {
                UniqueKey<?> key = ((AbstractRecord) result).getPrimaryKey();

                if (key != null) {
                    boolean isKeySet = true;

                    for (Field<?> field : key.getFields()) {
                        isKeySet = isKeySet && (field(field) != null);
                    }

                    if (isKeySet) {
                        result.changed(false);
                    }
                }
            }

            return result;
        }

        // All reflection exceptions are intercepted
        catch (Exception e) {
            throw new MappingException("An error ocurred when mapping record to " + table, e);
        }
    }

    @Override
    public final ResultSet intoResultSet() {
        ResultImpl<Record> result = new ResultImpl<Record>(configuration(), rs, fields.fields.fields);
        result.add(this);
        return result.intoResultSet();
    }

    @Override
    public final <E> E map(RecordMapper<Record, E> mapper) {
        return mapper.map(this);
    }

    @Override
    public final void from(Object source) {
        if (source == null) return;

        // [#1987] Distinguish between various types to load data from
        // Maps are loaded using a {field-name -> value} convention
        if (source instanceof Map) {
            fromMap((Map<String, ?>) source);
        }

        // Arrays are loaded through index mapping
        else if (source instanceof Object[]) {
            fromArray((Object[]) source);
        }

        // All other types are expected to be POJOs
        else {
            Class<?> type = source.getClass();

            try {
                boolean useAnnotations = hasColumnAnnotations(type);

                for (Field<?> field : fields.fields) {
                    List<java.lang.reflect.Field> members;
                    Method method;

                    // Annotations are available and present
                    if (useAnnotations) {
                        members = getAnnotatedMembers(type, field.getName());
                        method = getAnnotatedGetter(type, field.getName());
                    }

                    // No annotations are present
                    else {
                        members = getMatchingMembers(type, field.getName());
                        method = getMatchingGetter(type, field.getName());
                    }

                    // Use only the first applicable method or member
                    if (method != null) {
                        Utils.setValue(this, field, method.invoke(source));
                    }
                    else if (members.size() > 0) {
                        from(source, members.get(0), field);
                    }
                }
            }

            // All reflection exceptions are intercepted
            catch (Exception e) {
                throw new MappingException("An error ocurred when mapping record from " + type, e);
            }
        }
    }

    @Override
    public final void fromMap(Map<String, ?> map) {
        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Field<?> field = fields.field(i);
            String name = field.getName();

            // Set only those values contained in the map
            if (map.containsKey(name)) {
                Utils.setValue(this, field, map.get(name));
            }
        }
    }

    @Override
    public final void fromArray(Object... array) {
        int size = fields.size();
        for (int i = 0; i < size && i < array.length; i++) {
            Utils.setValue(this, fields.field(i), array[i]);
        }
    }

    /**
     * This method was implemented with [#799]. It may be useful to make it
     * public for broader use...?
     */
    protected final void from(Record source) {
        for (Field<?> field : fields.fields) {
            Field<?> sourceField = source.field(field);

            if (sourceField != null) {
                Utils.setValue(this, field, source, sourceField);
            }
        }
    }

    private final void from(Object source, java.lang.reflect.Field member, Field<?> field)
        throws IllegalAccessException {

        Class<?> mType = member.getType();

        if (mType.isPrimitive()) {
            if (mType == byte.class) {
                Utils.setValue(this, field, member.getByte(source));
            }
            else if (mType == short.class) {
                Utils.setValue(this, field, member.getShort(source));
            }
            else if (mType == int.class) {
                Utils.setValue(this, field, member.getInt(source));
            }
            else if (mType == long.class) {
                Utils.setValue(this, field, member.getLong(source));
            }
            else if (mType == float.class) {
                Utils.setValue(this, field, member.getFloat(source));
            }
            else if (mType == double.class) {
                Utils.setValue(this, field, member.getDouble(source));
            }
            else if (mType == boolean.class) {
                Utils.setValue(this, field, member.getBoolean(source));
            }
            else if (mType == char.class) {
                Utils.setValue(this, field, member.getChar(source));
            }
        }
        else {
            Utils.setValue(this, field, member.get(source));
        }
    }

    // -------------------------------------------------------------------------
    // XXX: Methods related to the underlying ResultSet (if applicable)
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     * <p>
     * Subclasses may override this
     */
    @Override
    public int delete() {
        checkRsAvailable("Cannot delete record. No ResultSet available");

        try {

            // [#2265] TODO: This code is prototypical.
            rs.absolute(rsIndex - 1);
            rs.deleteRow();

            return 1;
        }
        catch (SQLException e) {
            throw translate("Cannot delete record", e);
        }
    }

    @Override
    public final void refresh() {
        refresh(fields.fields.fields);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Subclasses may override this
     */
    @Override
    public void refresh(Field<?>... f) {
        checkRsAvailable("Cannot refresh record. No ResultSet available");

        try {

            // [#2265] TODO: This code is prototypical. fetchLazy() is not
            // the best way to fetch a record
            rs.absolute(rsIndex - 1);
            AbstractRecord record = (AbstractRecord) create().fetchLazy(rs).fetchOne();
            setValues(f, record);
        }
        catch (SQLException e) {
            throw translate("Cannot refresh record", e);
        }
    }

    private final void checkRsAvailable(String message) {
        if (rs == null) {
            throw new DataAccessException(message);
        }
    }

    @Override
    public final void close() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        }
        catch (SQLException e) {
            throw translate("Cannot close ResultSet", e);
        }
    }

    @Override
    public final ResultSet resultSet() {
        return rs;
    }

    // ------------------------------------------------------------------------
    // XXX: Object and Comparable API
    // ------------------------------------------------------------------------

    @Override
    public String toString() {
        Result<AbstractRecord> result = new ResultImpl<AbstractRecord>(configuration(), null, fields.fields.fields);
        result.add(this);
        return result.toString();
    }

    @Override
    public int compareTo(Record that) {
        // Note: keep this implementation in-sync with AbstractStore.equals()!

        if (that == null) {
            throw new NullPointerException();
        }
        if (size() != that.size()) {
            throw new ClassCastException(String.format("Trying to compare incomparable records (wrong degree):\n%s\n%s", this, that));
        }

        Class<?>[] thisTypes = this.fieldsRow().types();
        Class<?>[] thatTypes = that.fieldsRow().types();

        if (!asList(thisTypes).equals(asList(thatTypes))) {
            throw new ClassCastException(String.format("Trying to compare incomparable records (type mismatch):\n%s\n%s", this, that));
        }

        for (int i = 0; i < size(); i++) {
            final Object thisValue = getValue(i);
            final Object thatValue = that.getValue(i);

            // [#1850] Only return -1/+1 early. In all other cases,
            // continue checking the remaining fields
            if (thisValue == null && thatValue == null) {
                continue;
            }

            // Order column values in a SQL NULLS LAST manner
            else if (thisValue == null) {
                return 1;
            }

            else if (thatValue == null) {
                return -1;
            }

            // [#985] Compare arrays too.
            else if (thisValue.getClass().isArray() && thatValue.getClass().isArray()) {

                // Might be byte[]
                if (thisValue.getClass() == byte[].class) {
                    int compare = compare((byte[]) thisValue, (byte[]) thatValue);

                    if (compare != 0) {
                        return compare;
                    }
                }

                // Other primitive types are not expected
                else if (!thisValue.getClass().getComponentType().isPrimitive()) {
                    int compare = compare((Object[]) thisValue, (Object[]) thatValue);

                    if (compare != 0) {
                        return compare;
                    }
                }

                else {
                    throw new ClassCastException(String.format("Unsupported data type in natural ordering: %s", thisValue.getClass()));
                }
            }
            else {
                int compare = ((Comparable) thisValue).compareTo(thatValue);

                if (compare != 0) {
                    return compare;
                }
            }
        }

        // If we got through the above loop, the two records are equal
        return 0;
    }

    /**
     * Compare two byte arrays
     */
    final int compare(byte[] array1, byte[] array2) {
        int length = Math.min(array1.length, array2.length);

        for (int i = 0; i < length; i++) {
            int v1 = (array1[i] & 0xff);
            int v2 = (array2[i] & 0xff);

            if (v1 != v2) {
                return v1 < v2 ? -1 : 1;
            }
        }

        return array1.length - array2.length;
    }

    /**
     * Compare two arrays
     */
    final int compare(Object[] array1, Object[] array2) {
        int length = Math.min(array1.length, array2.length);

        for (int i = 0; i < length; i++) {
            int compare = ((Comparable) array1[i]).compareTo(array2[i]);

            if (compare != 0) {
                return compare;
            }
        }

        return array1.length - array2.length;
    }
}
