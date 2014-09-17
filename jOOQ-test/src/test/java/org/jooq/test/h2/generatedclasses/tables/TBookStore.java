/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables;

/**
 * A book store
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TBookStore extends org.jooq.impl.TableImpl<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = 922366780;

	/**
	 * The singleton instance of <code>PUBLIC.T_BOOK_STORE</code>
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.TBookStore T_BOOK_STORE = new org.jooq.test.h2.generatedclasses.tables.TBookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord> getRecordType() {
		return org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord.class;
	}

	/**
	 * The column <code>PUBLIC.T_BOOK_STORE.NAME</code>. The books store name
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord, java.lang.String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(400).nullable(false), this, "The books store name");

	/**
	 * Create a <code>PUBLIC.T_BOOK_STORE</code> table reference
	 */
	public TBookStore() {
		this("T_BOOK_STORE", null);
	}

	/**
	 * Create an aliased <code>PUBLIC.T_BOOK_STORE</code> table reference
	 */
	public TBookStore(java.lang.String alias) {
		this(alias, org.jooq.test.h2.generatedclasses.tables.TBookStore.T_BOOK_STORE);
	}

	private TBookStore(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord> aliased) {
		this(alias, aliased, null);
	}

	private TBookStore(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.h2.generatedclasses.Public.PUBLIC, aliased, parameters, "A book store");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord> getPrimaryKey() {
		return org.jooq.test.h2.generatedclasses.Keys.UK_T_BOOK_STORE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord>>asList(org.jooq.test.h2.generatedclasses.Keys.UK_T_BOOK_STORE_NAME);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.h2.generatedclasses.tables.TBookStore as(java.lang.String alias) {
		return new org.jooq.test.h2.generatedclasses.tables.TBookStore(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.h2.generatedclasses.tables.TBookStore rename(java.lang.String name) {
		return new org.jooq.test.h2.generatedclasses.tables.TBookStore(name, null);
	}
}
