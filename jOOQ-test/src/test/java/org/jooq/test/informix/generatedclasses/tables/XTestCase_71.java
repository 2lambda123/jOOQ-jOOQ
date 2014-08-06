/**
 * This class is generated by jOOQ
 */
package org.jooq.test.informix.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_71 extends org.jooq.impl.TableImpl<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record> {

	private static final long serialVersionUID = -631125055;

	/**
	 * The singleton instance of <code>informix.x_test_case_71</code>
	 */
	public static final org.jooq.test.informix.generatedclasses.tables.XTestCase_71 X_TEST_CASE_71 = new org.jooq.test.informix.generatedclasses.tables.XTestCase_71();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record> getRecordType() {
		return org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record.class;
	}

	/**
	 * The column <code>informix.x_test_case_71.id</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>informix.x_test_case_71.test_case_64_69_id</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record, java.lang.Integer> TEST_CASE_64_69_ID = createField("test_case_64_69_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>informix.x_test_case_71</code> table reference
	 */
	public XTestCase_71() {
		this("x_test_case_71", null);
	}

	/**
	 * Create an aliased <code>informix.x_test_case_71</code> table reference
	 */
	public XTestCase_71(java.lang.String alias) {
		this(alias, org.jooq.test.informix.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71);
	}

	private XTestCase_71(java.lang.String alias, org.jooq.Table<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record> aliased) {
		this(alias, aliased, null);
	}

	private XTestCase_71(java.lang.String alias, org.jooq.Table<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.informix.generatedclasses.Informix.INFORMIX, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record> getPrimaryKey() {
		return org.jooq.test.informix.generatedclasses.Keys.U19150_20578;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record>>asList(org.jooq.test.informix.generatedclasses.Keys.U19150_20578);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.informix.generatedclasses.tables.records.XTestCase_71Record, ?>>asList(org.jooq.test.informix.generatedclasses.Keys.R19150_20579);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.informix.generatedclasses.tables.XTestCase_71 as(java.lang.String alias) {
		return new org.jooq.test.informix.generatedclasses.tables.XTestCase_71(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.informix.generatedclasses.tables.XTestCase_71 rename(java.lang.String name) {
		return new org.jooq.test.informix.generatedclasses.tables.XTestCase_71(name, null);
	}
}
