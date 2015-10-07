/**
 * This class is generated by jOOQ
 */
package org.jooq.test.vertica.generatedclasses.tables.records;


import java.math.BigDecimal;
import java.math.BigInteger;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.test.vertica.generatedclasses.tables.T_639NumbersTable;
import org.jooq.test.vertica.generatedclasses.tables.interfaces.IT_639NumbersTable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_639NumbersTableRecord extends UpdatableRecordImpl<T_639NumbersTableRecord> implements Record13<Long, Long, Long, Long, Long, Byte, Short, Integer, Long, BigInteger, BigDecimal, Double, Double>, IT_639NumbersTable {

	private static final long serialVersionUID = 660429133;

	/**
	 * Setter for <code>public.t_639_numbers_table.ID</code>.
	 */
	@Override
	public void setId(Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.ID</code>.
	 */
	@Override
	public Long getId() {
		return (Long) getValue(0);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.BYTE</code>.
	 */
	@Override
	public void setByte(Long value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.BYTE</code>.
	 */
	@Override
	public Long getByte() {
		return (Long) getValue(1);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.SHORT</code>.
	 */
	@Override
	public void setShort(Long value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.SHORT</code>.
	 */
	@Override
	public Long getShort() {
		return (Long) getValue(2);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.INTEGER</code>.
	 */
	@Override
	public void setInteger(Long value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.INTEGER</code>.
	 */
	@Override
	public Long getInteger() {
		return (Long) getValue(3);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.LONG</code>.
	 */
	@Override
	public void setLong(Long value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.LONG</code>.
	 */
	@Override
	public Long getLong() {
		return (Long) getValue(4);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.BYTE_DECIMAL</code>.
	 */
	@Override
	public void setByteDecimal(Byte value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.BYTE_DECIMAL</code>.
	 */
	@Override
	public Byte getByteDecimal() {
		return (Byte) getValue(5);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.SHORT_DECIMAL</code>.
	 */
	@Override
	public void setShortDecimal(Short value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.SHORT_DECIMAL</code>.
	 */
	@Override
	public Short getShortDecimal() {
		return (Short) getValue(6);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.INTEGER_DECIMAL</code>.
	 */
	@Override
	public void setIntegerDecimal(Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.INTEGER_DECIMAL</code>.
	 */
	@Override
	public Integer getIntegerDecimal() {
		return (Integer) getValue(7);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.LONG_DECIMAL</code>.
	 */
	@Override
	public void setLongDecimal(Long value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.LONG_DECIMAL</code>.
	 */
	@Override
	public Long getLongDecimal() {
		return (Long) getValue(8);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.BIG_INTEGER</code>.
	 */
	@Override
	public void setBigInteger(BigInteger value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.BIG_INTEGER</code>.
	 */
	@Override
	public BigInteger getBigInteger() {
		return (BigInteger) getValue(9);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.BIG_DECIMAL</code>.
	 */
	@Override
	public void setBigDecimal(BigDecimal value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.BIG_DECIMAL</code>.
	 */
	@Override
	public BigDecimal getBigDecimal() {
		return (BigDecimal) getValue(10);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.FLOAT</code>.
	 */
	@Override
	public void setFloat(Double value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.FLOAT</code>.
	 */
	@Override
	public Double getFloat() {
		return (Double) getValue(11);
	}

	/**
	 * Setter for <code>public.t_639_numbers_table.DOUBLE</code>.
	 */
	@Override
	public void setDouble(Double value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>public.t_639_numbers_table.DOUBLE</code>.
	 */
	@Override
	public Double getDouble() {
		return (Double) getValue(12);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Long> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row13<Long, Long, Long, Long, Long, Byte, Short, Integer, Long, BigInteger, BigDecimal, Double, Double> fieldsRow() {
		return (Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row13<Long, Long, Long, Long, Long, Byte, Short, Integer, Long, BigInteger, BigDecimal, Double, Double> valuesRow() {
		return (Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field2() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.BYTE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field3() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.SHORT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field4() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.INTEGER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field5() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.LONG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Byte> field6() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.BYTE_DECIMAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Short> field7() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.SHORT_DECIMAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field8() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.INTEGER_DECIMAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field9() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.LONG_DECIMAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<BigInteger> field10() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.BIG_INTEGER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<BigDecimal> field11() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.BIG_DECIMAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field12() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.FLOAT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Double> field13() {
		return T_639NumbersTable.T_639_NUMBERS_TABLE.DOUBLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value2() {
		return getByte();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value3() {
		return getShort();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value4() {
		return getInteger();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value5() {
		return getLong();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Byte value6() {
		return getByteDecimal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short value7() {
		return getShortDecimal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value8() {
		return getIntegerDecimal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value9() {
		return getLongDecimal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger value10() {
		return getBigInteger();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal value11() {
		return getBigDecimal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value12() {
		return getFloat();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value13() {
		return getDouble();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value1(Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value2(Long value) {
		setByte(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value3(Long value) {
		setShort(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value4(Long value) {
		setInteger(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value5(Long value) {
		setLong(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value6(Byte value) {
		setByteDecimal(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value7(Short value) {
		setShortDecimal(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value8(Integer value) {
		setIntegerDecimal(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value9(Long value) {
		setLongDecimal(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value10(BigInteger value) {
		setBigInteger(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value11(BigDecimal value) {
		setBigDecimal(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value12(Double value) {
		setFloat(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord value13(Double value) {
		setDouble(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_639NumbersTableRecord values(Long value1, Long value2, Long value3, Long value4, Long value5, Byte value6, Short value7, Integer value8, Long value9, BigInteger value10, BigDecimal value11, Double value12, Double value13) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		value13(value13);
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(IT_639NumbersTable from) {
		setId(from.getId());
		setByte(from.getByte());
		setShort(from.getShort());
		setInteger(from.getInteger());
		setLong(from.getLong());
		setByteDecimal(from.getByteDecimal());
		setShortDecimal(from.getShortDecimal());
		setIntegerDecimal(from.getIntegerDecimal());
		setLongDecimal(from.getLongDecimal());
		setBigInteger(from.getBigInteger());
		setBigDecimal(from.getBigDecimal());
		setFloat(from.getFloat());
		setDouble(from.getDouble());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends IT_639NumbersTable> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached T_639NumbersTableRecord
	 */
	public T_639NumbersTableRecord() {
		super(T_639NumbersTable.T_639_NUMBERS_TABLE);
	}

	/**
	 * Create a detached, initialised T_639NumbersTableRecord
	 */
	public T_639NumbersTableRecord(Long id, Long byte_, Long short_, Long integer, Long long_, Byte byteDecimal, Short shortDecimal, Integer integerDecimal, Long longDecimal, BigInteger bigInteger, BigDecimal bigDecimal, Double float_, Double double_) {
		super(T_639NumbersTable.T_639_NUMBERS_TABLE);

		setValue(0, id);
		setValue(1, byte_);
		setValue(2, short_);
		setValue(3, integer);
		setValue(4, long_);
		setValue(5, byteDecimal);
		setValue(6, shortDecimal);
		setValue(7, integerDecimal);
		setValue(8, longDecimal);
		setValue(9, bigInteger);
		setValue(10, bigDecimal);
		setValue(11, float_);
		setValue(12, double_);
	}
}
