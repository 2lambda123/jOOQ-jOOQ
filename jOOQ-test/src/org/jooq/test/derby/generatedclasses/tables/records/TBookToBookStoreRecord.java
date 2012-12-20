/**
 * This class is generated by jOOQ
 */
package org.jooq.test.derby.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TBookToBookStoreRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.derby.generatedclasses.tables.records.TBookToBookStoreRecord> implements org.jooq.Record3<java.lang.String, java.lang.Integer, java.lang.Integer> {

	private static final long serialVersionUID = 805501575;

	/**
	 * Setter for <code>TEST.T_BOOK_TO_BOOK_STORE.BOOK_STORE_NAME</code>. 
	 */
	public void setBookStoreName(java.lang.String value) {
		setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME, value);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord}
	 */
	public void setBookStoreName(org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord value) {
		if (value == null) {
			setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME, null);
		}
		else {
			setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME, value.getValue(org.jooq.test.derby.generatedclasses.tables.TBookStore.NAME));
		}
	}

	/**
	 * Getter for <code>TEST.T_BOOK_TO_BOOK_STORE.BOOK_STORE_NAME</code>. 
	 */
	public java.lang.String getBookStoreName() {
		return getValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME);
	}

	/**
	 * Setter for <code>TEST.T_BOOK_TO_BOOK_STORE.BOOK_ID</code>. 
	 */
	public void setBookId(java.lang.Integer value) {
		setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_ID, value);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.derby.generatedclasses.tables.records.TBookRecord}
	 */
	public void setBookId(org.jooq.test.derby.generatedclasses.tables.records.TBookRecord value) {
		if (value == null) {
			setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_ID, null);
		}
		else {
			setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_ID, value.getValue(org.jooq.test.derby.generatedclasses.tables.TBook.ID));
		}
	}

	/**
	 * Getter for <code>TEST.T_BOOK_TO_BOOK_STORE.BOOK_ID</code>. 
	 */
	public java.lang.Integer getBookId() {
		return getValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_ID);
	}

	/**
	 * Setter for <code>TEST.T_BOOK_TO_BOOK_STORE.STOCK</code>. 
	 */
	public void setStock(java.lang.Integer value) {
		setValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.STOCK, value);
	}

	/**
	 * Getter for <code>TEST.T_BOOK_TO_BOOK_STORE.STOCK</code>. 
	 */
	public java.lang.Integer getStock() {
		return getValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.STOCK);
	}

	// -------------------------------------------------------------------------
	// Foreign key navigation methods
	// -------------------------------------------------------------------------

	/**
	 * Fetch a <code>TEST.T_BOOK_STORE</code> referenced by this <code>TEST.T_BOOK_TO_BOOK_STORE</code>
	 */
	public org.jooq.test.derby.generatedclasses.tables.records.TBookStoreRecord fetchTBookStore() {
		return create()
			.selectFrom(org.jooq.test.derby.generatedclasses.tables.TBookStore.T_BOOK_STORE)
			.where(org.jooq.test.derby.generatedclasses.tables.TBookStore.NAME.equal(getValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME)))
			.fetchOne();
	}

	/**
	 * Fetch a <code>TEST.T_BOOK</code> referenced by this <code>TEST.T_BOOK_TO_BOOK_STORE</code>
	 */
	public org.jooq.test.derby.generatedclasses.tables.records.TBookRecord fetchTBook() {
		return create()
			.selectFrom(org.jooq.test.derby.generatedclasses.tables.TBook.T_BOOK)
			.where(org.jooq.test.derby.generatedclasses.tables.TBook.ID.equal(getValue(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_ID)))
			.fetchOne();
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record2<java.lang.String, java.lang.Integer> key() {
		return (org.jooq.Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.String, java.lang.Integer, java.lang.Integer> fieldsRow() {
		return org.jooq.impl.Factory.row(field1(), field2(), field3());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.String, java.lang.Integer, java.lang.Integer> valuesRow() {
		return org.jooq.impl.Factory.row(value1(), value2(), value3());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.BOOK_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.STOCK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getBookStoreName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getBookId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value3() {
		return getStock();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TBookToBookStoreRecord
	 */
	public TBookToBookStoreRecord() {
		super(org.jooq.test.derby.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE);
	}
}
