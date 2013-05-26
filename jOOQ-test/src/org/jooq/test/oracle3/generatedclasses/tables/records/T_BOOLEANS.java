/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "T_BOOLEANS")
public class T_BOOLEANS extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS> implements java.lang.Cloneable, org.jooq.Record11<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer>, org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_BOOLEANS_INTERFACE {

	private static final long serialVersionUID = -855116946;

	/**
	 * Setter for <code>T_BOOLEANS.ID</code>. 
	 */
	@Override
	public void setID(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.ID</code>. 
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "ID", unique = true, nullable = false, precision = 7)
	@Override
	public java.lang.Integer getID() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>T_BOOLEANS.ONE_ZERO</code>. 
	 */
	@Override
	public void setONE_ZERO(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.ONE_ZERO</code>. 
	 */
	@javax.persistence.Column(name = "ONE_ZERO", precision = 7)
	@Override
	public java.lang.Integer getONE_ZERO() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>T_BOOLEANS.TRUE_FALSE_LC</code>. 
	 */
	@Override
	public void setTRUE_FALSE_LC(java.lang.String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.TRUE_FALSE_LC</code>. 
	 */
	@javax.persistence.Column(name = "TRUE_FALSE_LC", length = 5)
	@Override
	public java.lang.String getTRUE_FALSE_LC() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>T_BOOLEANS.TRUE_FALSE_UC</code>. 
	 */
	@Override
	public void setTRUE_FALSE_UC(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.TRUE_FALSE_UC</code>. 
	 */
	@javax.persistence.Column(name = "TRUE_FALSE_UC", length = 5)
	@Override
	public java.lang.String getTRUE_FALSE_UC() {
		return (java.lang.String) getValue(3);
	}

	/**
	 * Setter for <code>T_BOOLEANS.YES_NO_LC</code>. 
	 */
	@Override
	public void setYES_NO_LC(java.lang.String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.YES_NO_LC</code>. 
	 */
	@javax.persistence.Column(name = "YES_NO_LC", length = 3)
	@Override
	public java.lang.String getYES_NO_LC() {
		return (java.lang.String) getValue(4);
	}

	/**
	 * Setter for <code>T_BOOLEANS.YES_NO_UC</code>. 
	 */
	@Override
	public void setYES_NO_UC(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.YES_NO_UC</code>. 
	 */
	@javax.persistence.Column(name = "YES_NO_UC", length = 3)
	@Override
	public java.lang.String getYES_NO_UC() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>T_BOOLEANS.Y_N_LC</code>. 
	 */
	@Override
	public void setY_N_LC(java.lang.String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.Y_N_LC</code>. 
	 */
	@javax.persistence.Column(name = "Y_N_LC", length = 1)
	@Override
	public java.lang.String getY_N_LC() {
		return (java.lang.String) getValue(6);
	}

	/**
	 * Setter for <code>T_BOOLEANS.Y_N_UC</code>. 
	 */
	@Override
	public void setY_N_UC(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.Y_N_UC</code>. 
	 */
	@javax.persistence.Column(name = "Y_N_UC", length = 1)
	@Override
	public java.lang.String getY_N_UC() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>T_BOOLEANS.VC_BOOLEAN</code>. 
	 */
	@Override
	public void setVC_BOOLEAN(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.VC_BOOLEAN</code>. 
	 */
	@javax.persistence.Column(name = "VC_BOOLEAN", length = 1)
	@Override
	public java.lang.String getVC_BOOLEAN() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>T_BOOLEANS.C_BOOLEAN</code>. 
	 */
	@Override
	public void setC_BOOLEAN(java.lang.String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.C_BOOLEAN</code>. 
	 */
	@javax.persistence.Column(name = "C_BOOLEAN", length = 1)
	@Override
	public java.lang.String getC_BOOLEAN() {
		return (java.lang.String) getValue(9);
	}

	/**
	 * Setter for <code>T_BOOLEANS.N_BOOLEAN</code>. 
	 */
	@Override
	public void setN_BOOLEAN(java.lang.Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>T_BOOLEANS.N_BOOLEAN</code>. 
	 */
	@javax.persistence.Column(name = "N_BOOLEAN", precision = 7)
	@Override
	public java.lang.Integer getN_BOOLEAN() {
		return (java.lang.Integer) getValue(10);
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
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row11<java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer> valuesRow() {
		return (org.jooq.Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.ONE_ZERO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.TRUE_FALSE_LC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.TRUE_FALSE_UC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field5() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.YES_NO_LC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.YES_NO_UC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field7() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.Y_N_LC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.Y_N_UC;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.VC_BOOLEAN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field10() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.C_BOOLEAN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field11() {
		return org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS.N_BOOLEAN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getID();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getONE_ZERO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getTRUE_FALSE_LC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getTRUE_FALSE_UC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value5() {
		return getYES_NO_LC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getYES_NO_UC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value7() {
		return getY_N_LC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getY_N_UC();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getVC_BOOLEAN();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value10() {
		return getC_BOOLEAN();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value11() {
		return getN_BOOLEAN();
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_BOOLEANS_INTERFACE from) {
		setID(from.getID());
		setONE_ZERO(from.getONE_ZERO());
		setTRUE_FALSE_LC(from.getTRUE_FALSE_LC());
		setTRUE_FALSE_UC(from.getTRUE_FALSE_UC());
		setYES_NO_LC(from.getYES_NO_LC());
		setYES_NO_UC(from.getYES_NO_UC());
		setY_N_LC(from.getY_N_LC());
		setY_N_UC(from.getY_N_UC());
		setVC_BOOLEAN(from.getVC_BOOLEAN());
		setC_BOOLEAN(from.getC_BOOLEAN());
		setN_BOOLEAN(from.getN_BOOLEAN());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_BOOLEANS_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached T_BOOLEANS
	 */
	public T_BOOLEANS() {
		super(org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS);
	}
}
