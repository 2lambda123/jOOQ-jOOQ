/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlite.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
public class XTestCase_64_69Record extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_64_69Record> {

	private static final long serialVersionUID = -583241867;

	/**
	 * The table column <code>x_test_case_64_69.ID</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.ID, value);
	}

	/**
	 * The table column <code>x_test_case_64_69.ID</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.ID);
	}

	/**
	 * The table column <code>x_test_case_64_69.ID</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.util.List<org.jooq.test.sqlite.generatedclasses.tables.records.XTestCase_71Record> fetchXTestCase_71List() {
		return create()
			.selectFrom(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71)
			.where(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_71.TEST_CASE_64_69_ID.equal(getValueAsShort(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.ID)))
			.fetch();
	}

	/**
	 * The table column <code>x_test_case_64_69.UNUSED_ID</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT fk_x_test_case_64_69_X_UNUSED_1
	 * FOREIGN KEY (UNUSED_ID)
	 * REFERENCES x_unused (ID)
	 * </pre></code>
	 */
	public void setUnusedId(java.lang.Integer value) {
		setValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.UNUSED_ID, value);
	}

	/**
	 * The table column <code>x_test_case_64_69.UNUSED_ID</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT fk_x_test_case_64_69_X_UNUSED_1
	 * FOREIGN KEY (UNUSED_ID)
	 * REFERENCES x_unused (ID)
	 * </pre></code>
	 */
	public java.lang.Integer getUnusedId() {
		return getValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.UNUSED_ID);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.sqlite.generatedclasses.tables.records.XUnusedRecord 
	 * XUnusedRecord}
	 */
	public void setUnusedId(org.jooq.test.sqlite.generatedclasses.tables.records.XUnusedRecord value) {
		if (value == null) {
			setValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.UNUSED_ID, null);
		}
		else {
			setValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.UNUSED_ID, value.getValue(org.jooq.test.sqlite.generatedclasses.tables.XUnused.ID));
		}
	}

	/**
	 * The table column <code>x_test_case_64_69.UNUSED_ID</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT fk_x_test_case_64_69_X_UNUSED_1
	 * FOREIGN KEY (UNUSED_ID)
	 * REFERENCES x_unused (ID)
	 * </pre></code>
	 */
	public org.jooq.test.sqlite.generatedclasses.tables.records.XUnusedRecord fetchXUnused() {
		return create()
			.selectFrom(org.jooq.test.sqlite.generatedclasses.tables.XUnused.X_UNUSED)
			.where(org.jooq.test.sqlite.generatedclasses.tables.XUnused.ID.equal(getValue(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.UNUSED_ID)))
			.fetchOne();
	}

	/**
	 * Create a detached XTestCase_64_69Record
	 */
	public XTestCase_64_69Record() {
		super(org.jooq.test.sqlite.generatedclasses.tables.XTestCase_64_69.X_TEST_CASE_64_69);
	}
}
