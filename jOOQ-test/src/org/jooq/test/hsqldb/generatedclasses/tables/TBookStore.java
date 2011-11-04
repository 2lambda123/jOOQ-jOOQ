/**
 * This class is generated by jOOQ
 */
package org.jooq.test.hsqldb.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.0"},
                            comments = "This class is generated by jOOQ")
public class TBookStore extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = -1589352259;

	/**
	 * The singleton instance of T_BOOK_STORE
	 */
	public static final org.jooq.test.hsqldb.generatedclasses.tables.TBookStore T_BOOK_STORE = new org.jooq.test.hsqldb.generatedclasses.tables.TBookStore();

	/**
	 * The class holding records for this type
	 */
	private static final java.lang.Class<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord> __RECORD_TYPE = org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord.class;

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord> getRecordType() {
		return __RECORD_TYPE;
	}

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord, java.lang.Integer> ID = new org.jooq.impl.TableFieldImpl<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord, java.lang.Integer>("ID", org.jooq.impl.SQLDataType.INTEGER, T_BOOK_STORE);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord, java.lang.String> NAME = new org.jooq.impl.TableFieldImpl<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord, java.lang.String>("NAME", org.jooq.impl.SQLDataType.VARCHAR, T_BOOK_STORE);

	/**
	 * No further instances allowed
	 */
	private TBookStore() {
		super("T_BOOK_STORE", org.jooq.test.hsqldb.generatedclasses.Public.PUBLIC);
	}

	@Override
	public org.jooq.Identity<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord, java.lang.Integer> getIdentity() {
		return org.jooq.test.hsqldb.generatedclasses.Keys.IDENTITY_T_BOOK_STORE;
	}

	@Override
	public org.jooq.UniqueKey<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord> getMainKey() {
		return org.jooq.test.hsqldb.generatedclasses.Keys.UK_T_BOOK_STORE_NAME;
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookStoreRecord>>asList(org.jooq.test.hsqldb.generatedclasses.Keys.UK_T_BOOK_STORE_NAME);
	}
}
