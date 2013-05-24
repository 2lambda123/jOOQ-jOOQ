/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "X_TEST_CASE_85")
public class XTestCase_85Record extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.firebird.generatedclasses.tables.records.XTestCase_85Record> implements org.jooq.Record3<java.lang.Integer, java.lang.Integer, java.lang.String>, org.jooq.test.firebird.generatedclasses.tables.interfaces.IXTestCase_85 {

	private static final long serialVersionUID = 38579761;

	/**
	 * Setter for <code>X_TEST_CASE_85.ID</code>. 
	 */
	@Override
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>X_TEST_CASE_85.ID</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "ID", unique = true, nullable = false, length = 4)
	@javax.validation.constraints.NotNull
	@Override
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>X_TEST_CASE_85.X_UNUSED_ID</code>. 
	 */
	@Override
	public void setXUnusedId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>X_TEST_CASE_85.X_UNUSED_ID</code>. 
	 */
	@javax.persistence.Column(name = "X_UNUSED_ID", length = 4)
	@Override
	public java.lang.Integer getXUnusedId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>X_TEST_CASE_85.X_UNUSED_NAME</code>. 
	 */
	@Override
	public void setXUnusedName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>X_TEST_CASE_85.X_UNUSED_NAME</code>. 
	 */
	@javax.persistence.Column(name = "X_UNUSED_NAME", length = 10)
	@javax.validation.constraints.Size(max = 10)
	@Override
	public java.lang.String getXUnusedName() {
		return (java.lang.String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.Integer, java.lang.String> fieldsRow() {
		return (org.jooq.Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.Integer, java.lang.Integer, java.lang.String> valuesRow() {
		return (org.jooq.Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.jooq.test.firebird.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.jooq.test.firebird.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85.X_UNUSED_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.jooq.test.firebird.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85.X_UNUSED_NAME;
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
		return getXUnusedId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getXUnusedName();
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.firebird.generatedclasses.tables.interfaces.IXTestCase_85 from) {
		setId(from.getId());
		setXUnusedId(from.getXUnusedId());
		setXUnusedName(from.getXUnusedName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.firebird.generatedclasses.tables.interfaces.IXTestCase_85> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached XTestCase_85Record
	 */
	public XTestCase_85Record() {
		super(org.jooq.test.firebird.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85);
	}
}
