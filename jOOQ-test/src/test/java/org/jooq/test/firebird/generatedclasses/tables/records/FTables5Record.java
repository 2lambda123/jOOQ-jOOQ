/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "F_TABLES5")
public class FTables5Record extends org.jooq.impl.TableRecordImpl<org.jooq.test.firebird.generatedclasses.tables.records.FTables5Record> implements org.jooq.Record2<java.lang.Integer, java.lang.Integer>, org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables5 {

	private static final long serialVersionUID = -564621046;

	/**
	 * Setter for <code>F_TABLES5.V</code>.
	 */
	@Override
	public void setV(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>F_TABLES5.V</code>.
	 */
	@javax.persistence.Column(name = "V")
	@Override
	public java.lang.Integer getV() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>F_TABLES5.S</code>.
	 */
	@Override
	public void setS(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>F_TABLES5.S</code>.
	 */
	@javax.persistence.Column(name = "S")
	@Override
	public java.lang.Integer getS() {
		return (java.lang.Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return org.jooq.test.firebird.generatedclasses.tables.FTables5.F_TABLES5.V;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.jooq.test.firebird.generatedclasses.tables.FTables5.F_TABLES5.S;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getV();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getS();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FTables5Record value1(java.lang.Integer value) {
		setV(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FTables5Record value2(java.lang.Integer value) {
		setS(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FTables5Record values(java.lang.Integer value1, java.lang.Integer value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables5 from) {
		setV(from.getV());
		setS(from.getS());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables5> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached FTables5Record
	 */
	public FTables5Record() {
		super(org.jooq.test.firebird.generatedclasses.tables.FTables5.F_TABLES5);
	}

	/**
	 * Create a detached, initialised FTables5Record
	 */
	public FTables5Record(java.lang.Integer v, java.lang.Integer s) {
		super(org.jooq.test.firebird.generatedclasses.tables.FTables5.F_TABLES5);

		setValue(0, v);
		setValue(1, s);
	}
}
