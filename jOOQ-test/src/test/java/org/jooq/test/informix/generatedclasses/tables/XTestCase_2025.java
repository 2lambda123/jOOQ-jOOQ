/**
 * This class is generated by jOOQ
 */
package org.jooq.test.informix.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_2025 extends org.jooq.impl.TableImpl<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record> {

	private static final long serialVersionUID = 23903312;

	/**
	 * The singleton instance of <code>informix.x_test_case_2025</code>
	 */
	public static final org.jooq.test.informix.generatedclasses.tables.XTestCase_2025 X_TEST_CASE_2025 = new org.jooq.test.informix.generatedclasses.tables.XTestCase_2025();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record> getRecordType() {
		return org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record.class;
	}

	/**
	 * The column <code>informix.x_test_case_2025.ref_id</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record, java.lang.Integer> REF_ID = createField("ref_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>informix.x_test_case_2025.ref_name</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record, java.lang.String> REF_NAME = createField("ref_name", org.jooq.impl.SQLDataType.VARCHAR.length(10), this, "");

	/**
	 * Create a <code>informix.x_test_case_2025</code> table reference
	 */
	public XTestCase_2025() {
		this("x_test_case_2025", null);
	}

	/**
	 * Create an aliased <code>informix.x_test_case_2025</code> table reference
	 */
	public XTestCase_2025(java.lang.String alias) {
		this(alias, org.jooq.test.informix.generatedclasses.tables.XTestCase_2025.X_TEST_CASE_2025);
	}

	private XTestCase_2025(java.lang.String alias, org.jooq.Table<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record> aliased) {
		this(alias, aliased, null);
	}

	private XTestCase_2025(java.lang.String alias, org.jooq.Table<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.informix.generatedclasses.Informix.INFORMIX, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_2025Record, ?>>asList(org.jooq.test.informix.generatedclasses.Keys.R5148_4842, org.jooq.test.informix.generatedclasses.Keys.R5148_4843, org.jooq.test.informix.generatedclasses.Keys.R5148_4844);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.informix.generatedclasses.tables.XTestCase_2025 as(java.lang.String alias) {
		return new org.jooq.test.informix.generatedclasses.tables.XTestCase_2025(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.informix.generatedclasses.tables.XTestCase_2025 rename(java.lang.String name) {
		return new org.jooq.test.informix.generatedclasses.tables.XTestCase_2025(name, null);
	}
}
