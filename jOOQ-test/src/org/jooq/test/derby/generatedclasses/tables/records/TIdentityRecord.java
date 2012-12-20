/**
 * This class is generated by jOOQ
 */
package org.jooq.test.derby.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TIdentityRecord extends org.jooq.impl.TableRecordImpl<org.jooq.test.derby.generatedclasses.tables.records.TIdentityRecord> implements org.jooq.Record2<java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = -1019643275;

	/**
	 * Setter for <code>TEST.T_IDENTITY.ID</code>. 
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.derby.generatedclasses.tables.TIdentity.ID, value);
	}

	/**
	 * Getter for <code>TEST.T_IDENTITY.ID</code>. 
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.derby.generatedclasses.tables.TIdentity.ID);
	}

	/**
	 * Setter for <code>TEST.T_IDENTITY.VAL</code>. 
	 */
	public void setVal(java.lang.Integer value) {
		setValue(org.jooq.test.derby.generatedclasses.tables.TIdentity.VAL, value);
	}

	/**
	 * Getter for <code>TEST.T_IDENTITY.VAL</code>. 
	 */
	public java.lang.Integer getVal() {
		return getValue(org.jooq.test.derby.generatedclasses.tables.TIdentity.VAL);
	}

	// -------------------------------------------------------------------------
	// Foreign key navigation methods
	// -------------------------------------------------------------------------

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> fieldsRow() {
		return org.jooq.impl.Factory.row(field1(), field2());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> valuesRow() {
		return org.jooq.impl.Factory.row(value1(), value2());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.jooq.test.derby.generatedclasses.tables.TIdentity.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.jooq.test.derby.generatedclasses.tables.TIdentity.VAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getVal();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TIdentityRecord
	 */
	public TIdentityRecord() {
		super(org.jooq.test.derby.generatedclasses.tables.TIdentity.T_IDENTITY);
	}
}
