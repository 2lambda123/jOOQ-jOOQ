/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VAuthorRecord extends org.jooq.impl.TableRecordImpl<org.jooq.test.postgres.generatedclasses.tables.records.VAuthorRecord> implements org.jooq.Record6<java.lang.Integer, java.lang.String, java.lang.String, java.sql.Date, java.lang.Integer, org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord> {

	private static final long serialVersionUID = 871672374;

	/**
	 * Setter for <code>public.v_author.id</code>. 
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.v_author.id</code>. 
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>public.v_author.first_name</code>. 
	 */
	public void setFirstName(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.v_author.first_name</code>. 
	 */
	public java.lang.String getFirstName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>public.v_author.last_name</code>. 
	 */
	public void setLastName(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.v_author.last_name</code>. 
	 */
	public java.lang.String getLastName() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>public.v_author.date_of_birth</code>. 
	 */
	public void setDateOfBirth(java.sql.Date value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.v_author.date_of_birth</code>. 
	 */
	public java.sql.Date getDateOfBirth() {
		return (java.sql.Date) getValue(3);
	}

	/**
	 * Setter for <code>public.v_author.year_of_birth</code>. 
	 */
	public void setYearOfBirth(java.lang.Integer value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.v_author.year_of_birth</code>. 
	 */
	public java.lang.Integer getYearOfBirth() {
		return (java.lang.Integer) getValue(4);
	}

	/**
	 * Setter for <code>public.v_author.address</code>. 
	 */
	public void setAddress(org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.v_author.address</code>. 
	 */
	public org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord getAddress() {
		return (org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.String, java.lang.String, java.sql.Date, java.lang.Integer, org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord> fieldsRow() {
		return (org.jooq.Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row6<java.lang.Integer, java.lang.String, java.lang.String, java.sql.Date, java.lang.Integer, org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord> valuesRow() {
		return (org.jooq.Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR.FIRST_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR.LAST_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field4() {
		return org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR.DATE_OF_BIRTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field5() {
		return org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR.YEAR_OF_BIRTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord> field6() {
		return org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR.ADDRESS;
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
	public java.lang.String value2() {
		return getFirstName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getLastName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value4() {
		return getDateOfBirth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value5() {
		return getYearOfBirth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord value6() {
		return getAddress();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached VAuthorRecord
	 */
	public VAuthorRecord() {
		super(org.jooq.test.postgres.generatedclasses.tables.VAuthor.V_AUTHOR);
	}
}
