/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_986_2Record extends org.jooq.impl.TableRecordImpl<org.jooq.test.postgres.generatedclasses.tables.records.T_986_2Record> implements org.jooq.Record1<java.lang.Integer> {

	private static final long serialVersionUID = -1224895717;

	/**
	 * Setter for <code>public.t_986_2.ref</code>. 
	 */
	public void setRef(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.t_986_2.ref</code>. 
	 */
	public java.lang.Integer getRef() {
		return (java.lang.Integer) getValue(0);
	}

	// -------------------------------------------------------------------------
	// Record1 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row1<java.lang.Integer> fieldsRow() {
		return (org.jooq.Row1) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row1<java.lang.Integer> valuesRow() {
		return (org.jooq.Row1) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.jooq.test.postgres.generatedclasses.tables.T_986_2.T_986_2.REF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getRef();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached T_986_2Record
	 */
	public T_986_2Record() {
		super(org.jooq.test.postgres.generatedclasses.tables.T_986_2.T_986_2);
	}
}
