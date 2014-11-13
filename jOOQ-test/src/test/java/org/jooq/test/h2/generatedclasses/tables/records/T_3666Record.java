/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_3666Record extends org.jooq.impl.TableRecordImpl<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record> implements org.jooq.Record6<java.lang.Long, java.math.BigDecimal, java.lang.Short, java.lang.Short, java.lang.Byte, java.math.BigDecimal>, org.jooq.test.h2.generatedclasses.tables.interfaces.IT_3666 {

	private static final long serialVersionUID = -351050461;

	/**
	 * Setter for <code>PUBLIC.T_3666.E1</code>.
	 */
	@Override
	public T_3666Record setE1(java.lang.Long value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>PUBLIC.T_3666.E1</code>.
	 */
	@Override
	public java.lang.Long getE1() {
		return (java.lang.Long) getValue(0);
	}

	/**
	 * Setter for <code>PUBLIC.T_3666.E2</code>.
	 */
	@Override
	public T_3666Record setE2(java.math.BigDecimal value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>PUBLIC.T_3666.E2</code>.
	 */
	@Override
	public java.math.BigDecimal getE2() {
		return (java.math.BigDecimal) getValue(1);
	}

	/**
	 * Setter for <code>PUBLIC.T_3666.E3</code>.
	 */
	@Override
	public T_3666Record setE3(java.lang.Short value) {
		setValue(2, value);
		return this;
	}

	/**
	 * Getter for <code>PUBLIC.T_3666.E3</code>.
	 */
	@Override
	public java.lang.Short getE3() {
		return (java.lang.Short) getValue(2);
	}

	/**
	 * Setter for <code>PUBLIC.T_3666.E4</code>.
	 */
	@Override
	public T_3666Record setE4(java.lang.Short value) {
		setValue(3, value);
		return this;
	}

	/**
	 * Getter for <code>PUBLIC.T_3666.E4</code>.
	 */
	@Override
	public java.lang.Short getE4() {
		return (java.lang.Short) getValue(3);
	}

	/**
	 * Setter for <code>PUBLIC.T_3666.E5</code>.
	 */
	@Override
	public T_3666Record setE5(java.lang.Byte value) {
		setValue(4, value);
		return this;
	}

	/**
	 * Getter for <code>PUBLIC.T_3666.E5</code>.
	 */
	@Override
	public java.lang.Byte getE5() {
		return (java.lang.Byte) getValue(4);
	}

	/**
	 * Setter for <code>PUBLIC.T_3666.E6</code>.
	 */
	@Override
	public T_3666Record setE6(java.math.BigDecimal value) {
		setValue(5, value);
		return this;
	}

	/**
	 * Getter for <code>PUBLIC.T_3666.E6</code>.
	 */
	@Override
	public java.math.BigDecimal getE6() {
		return (java.math.BigDecimal) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Long, java.math.BigDecimal, java.lang.Short, java.lang.Short, java.lang.Byte, java.math.BigDecimal> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Long, java.math.BigDecimal, java.lang.Short, java.lang.Short, java.lang.Byte, java.math.BigDecimal> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Long> field1() {
		return org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666.E1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field2() {
		return org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666.E2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Short> field3() {
		return org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666.E3;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Short> field4() {
		return org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666.E4;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Byte> field5() {
		return org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666.E5;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field6() {
		return org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666.E6;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Long value1() {
		return getE1();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value2() {
		return getE2();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Short value3() {
		return getE3();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Short value4() {
		return getE4();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Byte value5() {
		return getE5();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value6() {
		return getE6();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record value1(java.lang.Long value) {
		setE1(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record value2(java.math.BigDecimal value) {
		setE2(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record value3(java.lang.Short value) {
		setE3(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record value4(java.lang.Short value) {
		setE4(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record value5(java.lang.Byte value) {
		setE5(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record value6(java.math.BigDecimal value) {
		setE6(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_3666Record values(java.lang.Long value1, java.math.BigDecimal value2, java.lang.Short value3, java.lang.Short value4, java.lang.Byte value5, java.math.BigDecimal value6) {
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.h2.generatedclasses.tables.interfaces.IT_3666 from) {
		setE1(from.getE1());
		setE2(from.getE2());
		setE3(from.getE3());
		setE4(from.getE4());
		setE5(from.getE5());
		setE6(from.getE6());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.h2.generatedclasses.tables.interfaces.IT_3666> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached T_3666Record
	 */
	public T_3666Record() {
		super(org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666);
	}

	/**
	 * Create a detached, initialised T_3666Record
	 */
	public T_3666Record(java.lang.Long e1, java.math.BigDecimal e2, java.lang.Short e3, java.lang.Short e4, java.lang.Byte e5, java.math.BigDecimal e6) {
		super(org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666);

		setValue(0, e1);
		setValue(1, e2);
		setValue(2, e3);
		setValue(3, e4);
		setValue(4, e5);
		setValue(5, e6);
	}
}
