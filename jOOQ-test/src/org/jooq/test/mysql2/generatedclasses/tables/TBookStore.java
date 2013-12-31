/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mysql2.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 *
 * A book store
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TBookStore extends org.jooq.impl.TableImpl<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = 1606403557;

	/**
	 * The singleton instance of <code>test2.t_book_store</code>
	 */
	public static final org.jooq.test.mysql2.generatedclasses.tables.TBookStore T_BOOK_STORE = new org.jooq.test.mysql2.generatedclasses.tables.TBookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord> getRecordType() {
		return org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord.class;
	}

	/**
	 * The column <code>test2.t_book_store.name</code>. The books store name
	 */
	public final org.jooq.TableField<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(400).nullable(false), this);

	/**
	 * Create a <code>test2.t_book_store</code> table reference
	 */
	public TBookStore() {
		super("t_book_store", org.jooq.test.mysql2.generatedclasses.Test2.TEST2);
	}

	/**
	 * Create an aliased <code>test2.t_book_store</code> table reference
	 */
	public TBookStore(java.lang.String alias) {
		super(alias, org.jooq.test.mysql2.generatedclasses.Test2.TEST2, org.jooq.test.mysql2.generatedclasses.tables.TBookStore.T_BOOK_STORE);
	}

	private TBookStore(java.lang.String alias, org.jooq.Table<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord> aliased) {
		super(alias, org.jooq.test.mysql2.generatedclasses.Test2.TEST2, aliased);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord> getPrimaryKey() {
		return org.jooq.test.mysql2.generatedclasses.Keys.KEY_T_BOOK_STORE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.mysql2.generatedclasses.tables.records.TBookStoreRecord>>asList(org.jooq.test.mysql2.generatedclasses.Keys.KEY_T_BOOK_STORE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.mysql2.generatedclasses.tables.TBookStore as(java.lang.String alias) {
		return new org.jooq.test.mysql2.generatedclasses.tables.TBookStore(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.mysql2.generatedclasses.tables.TBookStore rename(java.lang.String name) {
		return new org.jooq.test.mysql2.generatedclasses.tables.TBookStore(name, null);
	}
}
