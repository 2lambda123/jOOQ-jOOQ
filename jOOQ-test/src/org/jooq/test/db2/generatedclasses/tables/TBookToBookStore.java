/**
 * This class is generated by jOOQ
 */
package org.jooq.test.db2.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TBookToBookStore extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord> {

	private static final long serialVersionUID = -1255578113;

	/**
	 * The singleton instance of <code>LUKAS.T_BOOK_TO_BOOK_STORE</code>
	 */
	public static final org.jooq.test.db2.generatedclasses.tables.TBookToBookStore T_BOOK_TO_BOOK_STORE = new org.jooq.test.db2.generatedclasses.tables.TBookToBookStore();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord> getRecordType() {
		return org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord.class;
	}

	/**
	 * The column <code>LUKAS.T_BOOK_TO_BOOK_STORE.BOOK_STORE_NAME</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord, java.lang.String> BOOK_STORE_NAME = createField("BOOK_STORE_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(400), T_BOOK_TO_BOOK_STORE);

	/**
	 * The column <code>LUKAS.T_BOOK_TO_BOOK_STORE.BOOK_ID</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord, java.lang.Integer> BOOK_ID = createField("BOOK_ID", org.jooq.impl.SQLDataType.INTEGER, T_BOOK_TO_BOOK_STORE);

	/**
	 * The column <code>LUKAS.T_BOOK_TO_BOOK_STORE.STOCK</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord, java.lang.Integer> STOCK = createField("STOCK", org.jooq.impl.SQLDataType.INTEGER, T_BOOK_TO_BOOK_STORE);

	/**
	 * No further instances allowed
	 */
	private TBookToBookStore() {
		super("T_BOOK_TO_BOOK_STORE", org.jooq.test.db2.generatedclasses.Lukas.LUKAS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord> getPrimaryKey() {
		return org.jooq.test.db2.generatedclasses.Keys.T_BOOK_TO_BOOK_STORE__PK_B2BS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord>>asList(org.jooq.test.db2.generatedclasses.Keys.T_BOOK_TO_BOOK_STORE__PK_B2BS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.db2.generatedclasses.tables.records.TBookToBookStoreRecord, ?>>asList(org.jooq.test.db2.generatedclasses.Keys.T_BOOK_TO_BOOK_STORE__FK_B2BS_BS_NAME, org.jooq.test.db2.generatedclasses.Keys.T_BOOK_TO_BOOK_STORE__FK_B2BS_B_ID);
	}
}
