/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "F_TABLES3")
public class FTables3Record extends org.jooq.impl.TableRecordImpl<org.jooq.test.firebird.generatedclasses.tables.records.FTables3Record> implements org.jooq.Record1<java.lang.String>, org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables3 {

	private static final long serialVersionUID = 1470844955;

	/**
	 * Setter for <code>F_TABLES3.COLUMN_VALUE</code>.
	 */
	@Override
	public void setColumnValue(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>F_TABLES3.COLUMN_VALUE</code>.
	 */
	@javax.persistence.Column(name = "COLUMN_VALUE", length = 1)
	@javax.validation.constraints.Size(max = 1)
	@Override
	public java.lang.String getColumnValue() {
		return (java.lang.String) getValue(0);
	}

	// -------------------------------------------------------------------------
	// Record1 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row1<java.lang.String> fieldsRow() {
		return (org.jooq.Row1) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row1<java.lang.String> valuesRow() {
		return (org.jooq.Row1) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return org.jooq.test.firebird.generatedclasses.tables.FTables3.F_TABLES3.COLUMN_VALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getColumnValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FTables3Record value1(java.lang.String value) {
		setColumnValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FTables3Record values(java.lang.String value1) {
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables3 from) {
		setColumnValue(from.getColumnValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables3> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached FTables3Record
	 */
	public FTables3Record() {
		super(org.jooq.test.firebird.generatedclasses.tables.FTables3.F_TABLES3);
	}

	/**
	 * Create a detached, initialised FTables3Record
	 */
	public FTables3Record(java.lang.String columnValue) {
		super(org.jooq.test.firebird.generatedclasses.tables.FTables3.F_TABLES3);

		setValue(0, columnValue);
	}
}
