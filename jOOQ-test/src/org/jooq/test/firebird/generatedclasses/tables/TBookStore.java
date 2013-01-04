/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TBookStore extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = 55077525;

	/**
	 * The singleton instance of <code>T_BOOK_STORE</code>
	 */
	public static final org.jooq.test.firebird.generatedclasses.tables.TBookStore T_BOOK_STORE = new org.jooq.test.firebird.generatedclasses.tables.TBookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord> getRecordType() {
		return org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord.class;
	}

	/**
	 * The column <code>T_BOOK_STORE.NAME</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord, java.lang.String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(400), this);

	/**
	 * Create a <code>T_BOOK_STORE</code> table reference
	 */
	public TBookStore() {
		super("T_BOOK_STORE", org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * Create an aliased <code>T_BOOK_STORE</code> table reference
	 */
	public TBookStore(java.lang.String alias) {
		super(alias, org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.firebird.generatedclasses.tables.TBookStore.T_BOOK_STORE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord> getMainKey() {
		return org.jooq.test.firebird.generatedclasses.Keys.UK_T_BOOK_STORE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.firebird.generatedclasses.tables.records.TBookStoreRecord>>asList(org.jooq.test.firebird.generatedclasses.Keys.UK_T_BOOK_STORE_NAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.firebird.generatedclasses.tables.TBookStore as(java.lang.String alias) {
		return new org.jooq.test.firebird.generatedclasses.tables.TBookStore(alias);
	}
}
