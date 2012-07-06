/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlserver.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
public class XTestCase_71Record extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.sqlserver.generatedclasses.tables.records.XTestCase_71Record> {

	private static final long serialVersionUID = 1362591146;

	/**
	 * The table column <code>dbo.x_test_case_71.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.ID, value);
	}

	/**
	 * The table column <code>dbo.x_test_case_71.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.ID);
	}

	/**
	 * The table column <code>dbo.x_test_case_71.test_case_64_69_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT fk_x_test_case_71
	 * FOREIGN KEY (test_case_64_69_id)
	 * REFERENCES dbo.x_test_case_64_69 (id)
	 * </pre></code>
	 */
	public void setTestCase_64_69Id(java.lang.Integer value) {
		setValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.TEST_CASE_64_69_ID, value);
	}

	/**
	 * The table column <code>dbo.x_test_case_71.test_case_64_69_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT fk_x_test_case_71
	 * FOREIGN KEY (test_case_64_69_id)
	 * REFERENCES dbo.x_test_case_64_69 (id)
	 * </pre></code>
	 */
	public java.lang.Integer getTestCase_64_69Id() {
		return getValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.TEST_CASE_64_69_ID);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.sqlserver.generatedclasses.tables.records.XTestCase_64_69Record 
	 * XTestCase_64_69Record}
	 */
	public void setTestCase_64_69Id(org.jooq.test.sqlserver.generatedclasses.tables.records.XTestCase_64_69Record value) {
		if (value == null) {
			setValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.TEST_CASE_64_69_ID, null);
		}
		else {
			setValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.TEST_CASE_64_69_ID, value.getValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_64_69.X_TEST_CASE_64_69.ID));
		}
	}

	/**
	 * The table column <code>dbo.x_test_case_71.test_case_64_69_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT fk_x_test_case_71
	 * FOREIGN KEY (test_case_64_69_id)
	 * REFERENCES dbo.x_test_case_64_69 (id)
	 * </pre></code>
	 */
	public org.jooq.test.sqlserver.generatedclasses.tables.records.XTestCase_64_69Record fetchXTestCase_64_69() {
		return create()
			.selectFrom(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_64_69.X_TEST_CASE_64_69)
			.where(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_64_69.X_TEST_CASE_64_69.ID.equal(getValue(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71.TEST_CASE_64_69_ID)))
			.fetchOne();
	}

	/**
	 * Create a detached XTestCase_71Record
	 */
	public XTestCase_71Record() {
		super(org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71);
	}
}
