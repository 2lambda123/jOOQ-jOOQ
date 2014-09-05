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
public class TTempRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.oracle5.generatedclasses.tables.records.TTempRecord> implements org.jooq.Record1<java.math.BigDecimal> {

	private static final long serialVersionUID = -1318765614;

	/**
	 * Setter for <code>TEST.T_TEMP.ID</code>.
	 */
	public void setId(java.math.BigDecimal value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>TEST.T_TEMP.ID</code>.
	 */
	public java.math.BigDecimal getId() {
		return (java.math.BigDecimal) getValue(0);
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
	// Record1 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row1<java.math.BigDecimal> fieldsRow() {
		return (org.jooq.Row1) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row1<java.math.BigDecimal> valuesRow() {
		return (org.jooq.Row1) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.math.BigDecimal> field1() {
		return org.jooq.test.oracle5.generatedclasses.tables.TTemp.T_TEMP.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.math.BigDecimal value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TTempRecord value1(java.math.BigDecimal value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TTempRecord values(java.math.BigDecimal value1) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TTempRecord
	 */
	public TTempRecord() {
		super(org.jooq.test.oracle5.generatedclasses.tables.TTemp.T_TEMP);
	}

	/**
	 * Create a detached, initialised TTempRecord
	 */
	public TTempRecord(java.math.BigDecimal id) {
		super(org.jooq.test.oracle5.generatedclasses.tables.TTemp.T_TEMP);

		setValue(0, id);
	}
}
