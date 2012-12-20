/**
 * This class is generated by jOOQ
 */
package org.jooq.test.derby.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TBookStore extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = 173901995;

	/**
	 * The singleton instance of <code>TEST.T_BOOK_STORE</code>
	 */
	public static final org.jooq.test.derby.generatedclasses.tables.TBookStore T_BOOK_STORE = new org.jooq.test.derby.generatedclasses.tables.TBookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord> getRecordType() {
		return org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord.class;
	}

	/**
	 * The column <code>TEST.T_BOOK_STORE.NAME</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord, java.lang.String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR, T_BOOK_STORE);

	/**
	 * No further instances allowed
	 */
	private TBookStore() {
		super("T_BOOK_STORE", org.jooq.test.derby.generatedclasses.Test.TEST);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord> getMainKey() {
		return org.jooq.test.derby.generatedclasses.Keys.UK_T_BOOK_STORE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord>>asList(org.jooq.test.derby.generatedclasses.Keys.UK_T_BOOK_STORE_NAME);
	}
}
