/**
 * This class is generated by jOOQ
 */
package org.jooq.test.ingres.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 *
 * A book store
 */
@java.lang.SuppressWarnings("all")
public class TBookStore extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = -1299629079;

	/**
	 * The singleton instance of <code>test.t_book_store</code>
	 */
	public static final org.jooq.test.ingres.generatedclasses.tables.TBookStore T_BOOK_STORE = new org.jooq.test.ingres.generatedclasses.tables.TBookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord> getRecordType() {
		return org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord.class;
	}

	/**
	 * The column <code>test.t_book_store.name</code>. The books store name
	 */
	public static final org.jooq.TableField<org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, T_BOOK_STORE);

	/**
	 * No further instances allowed
	 */
	private TBookStore() {
		super("t_book_store", org.jooq.test.ingres.generatedclasses.Test.TEST);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord> getPrimaryKey() {
		return org.jooq.test.ingres.generatedclasses.Keys.UK_T_BOOK_STORE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.ingres.generatedclasses.tables.records.TBookStoreRecord>>asList(org.jooq.test.ingres.generatedclasses.Keys.UK_T_BOOK_STORE_NAME);
	}
}
