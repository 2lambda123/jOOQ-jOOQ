/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sybase.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class XTestCase_71 extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record> {

	private static final long serialVersionUID = -302551338;

	/**
	 * The singleton instance of <code>DBA.x_test_case_71</code>
	 */
	public static final org.jooq.test.sybase.generatedclasses.tables.XTestCase_71 X_TEST_CASE_71 = new org.jooq.test.sybase.generatedclasses.tables.XTestCase_71();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record> getRecordType() {
		return org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record.class;
	}

	/**
	 * The column <code>DBA.x_test_case_71.ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>DBA.x_test_case_71.TEST_CASE_64_69_ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record, java.lang.Integer> TEST_CASE_64_69_ID = createField("TEST_CASE_64_69_ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * Create a <code>DBA.x_test_case_71</code> table reference
	 */
	public XTestCase_71() {
		super("x_test_case_71", org.jooq.test.sybase.generatedclasses.Dba.DBA);
	}

	/**
	 * Create an aliased <code>DBA.x_test_case_71</code> table reference
	 */
	public XTestCase_71(java.lang.String alias) {
		super(alias, org.jooq.test.sybase.generatedclasses.Dba.DBA, org.jooq.test.sybase.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record> getPrimaryKey() {
		return org.jooq.test.sybase.generatedclasses.Keys.X_TEST_CASE_71__PK_X_TEST_CASE_71;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record>>asList(org.jooq.test.sybase.generatedclasses.Keys.X_TEST_CASE_71__PK_X_TEST_CASE_71);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.sybase.generatedclasses.tables.records.XTestCase_71Record, ?>>asList(org.jooq.test.sybase.generatedclasses.Keys.X_TEST_CASE_71__FK_X_TEST_CASE_71);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.sybase.generatedclasses.tables.XTestCase_71 as(java.lang.String alias) {
		return new org.jooq.test.sybase.generatedclasses.tables.XTestCase_71(alias);
	}
}
