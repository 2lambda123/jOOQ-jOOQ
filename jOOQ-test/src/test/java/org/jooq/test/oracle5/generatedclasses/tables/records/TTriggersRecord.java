/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TTriggersRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.oracle5.generatedclasses.tables.records.TTriggersRecord> implements org.jooq.Record3<java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal> {

	private static final long serialVersionUID = 1315497168;

	/**
	 * Setter for <code>TEST.T_TRIGGERS.ID_GENERATED</code>.
	 */
	public void setIdGenerated(java.math.BigDecimal value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>TEST.T_TRIGGERS.ID_GENERATED</code>.
	 */
	public java.math.BigDecimal getIdGenerated() {
		return (java.math.BigDecimal) getValue(0);
	}

	/**
	 * Setter for <code>TEST.T_TRIGGERS.ID</code>.
	 */
	public void setId(java.math.BigDecimal value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>TEST.T_TRIGGERS.ID</code>.
	 */
	public java.math.BigDecimal getId() {
		return (java.math.BigDecimal) getValue(1);
	}

	/**
	 * Setter for <code>TEST.T_TRIGGERS.COUNTER</code>.
	 */
	public void setCounter(java.math.BigDecimal value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>TEST.T_TRIGGERS.COUNTER</code>.
	 */
	public java.math.BigDecimal getCounter() {
		return (java.math.BigDecimal) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.math.BigDecimal> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal> fieldsRow() {
		return (org.jooq.Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal> valuesRow() {
		return (org.jooq.Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field1() {
		return org.jooq.test.oracle5.generatedclasses.tables.TTriggers.T_TRIGGERS.ID_GENERATED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field2() {
		return org.jooq.test.oracle5.generatedclasses.tables.TTriggers.T_TRIGGERS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field3() {
		return org.jooq.test.oracle5.generatedclasses.tables.TTriggers.T_TRIGGERS.COUNTER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value1() {
		return getIdGenerated();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value2() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value3() {
		return getCounter();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TTriggersRecord value1(java.math.BigDecimal value) {
		setIdGenerated(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TTriggersRecord value2(java.math.BigDecimal value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TTriggersRecord value3(java.math.BigDecimal value) {
		setCounter(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TTriggersRecord values(java.math.BigDecimal value1, java.math.BigDecimal value2, java.math.BigDecimal value3) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TTriggersRecord
	 */
	public TTriggersRecord() {
		super(org.jooq.test.oracle5.generatedclasses.tables.TTriggers.T_TRIGGERS);
	}

	/**
	 * Create a detached, initialised TTriggersRecord
	 */
	public TTriggersRecord(java.math.BigDecimal idGenerated, java.math.BigDecimal id, java.math.BigDecimal counter) {
		super(org.jooq.test.oracle5.generatedclasses.tables.TTriggers.T_TRIGGERS);

		setValue(0, idGenerated);
		setValue(1, id);
		setValue(2, counter);
	}
}
