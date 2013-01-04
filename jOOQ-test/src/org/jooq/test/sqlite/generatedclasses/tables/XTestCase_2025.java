/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlite.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class XTestCase_2025 extends org.jooq.impl.TableImpl<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record> {

	private static final long serialVersionUID = 1589841763;

	/**
	 * The singleton instance of <code>x_test_case_2025</code>
	 */
	public static final org.jooq.test.sqlite.generatedclasses.tables.XTestCase_2025 X_TEST_CASE_2025 = new org.jooq.test.sqlite.generatedclasses.tables.XTestCase_2025();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record> getRecordType() {
		return org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record.class;
	}

	/**
	 * The column <code>x_test_case_2025.ref_id</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record, java.lang.Integer> REF_ID = createField("ref_id", org.jooq.impl.SQLDataType.INTEGER, X_TEST_CASE_2025);

	/**
	 * The column <code>x_test_case_2025.ref_name</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record, java.lang.String> REF_NAME = createField("ref_name", org.jooq.impl.SQLDataType.VARCHAR.length(10), X_TEST_CASE_2025);

	/**
	 * No further instances allowed
	 */
	private XTestCase_2025() {
		super("x_test_case_2025", org.jooq.test.sqlite.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_2025Record, ?>>asList(org.jooq.test.sqlite.generatedclasses.Keys.FK_X_TEST_CASE_2025_X_UNUSED_1, org.jooq.test.sqlite.generatedclasses.Keys.FK_X_TEST_CASE_2025_X_TEST_CASE_71_1, org.jooq.test.sqlite.generatedclasses.Keys.FK_X_TEST_CASE_2025_X_TEST_CASE_85_1);
	}
}
