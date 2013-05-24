/**
 * This class is generated by jOOQ
 */
package org.jooq.test.hsqldb.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TBookToBookStoreRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.hsqldb.generatedclasses.tables.records.TBookToBookStoreRecord> implements org.jooq.Record3<java.lang.String, java.lang.Integer, java.lang.Integer>, org.jooq.test.hsqldb.generatedclasses.tables.interfaces.ITBookToBookStore {

	private static final long serialVersionUID = -570506090;

	/**
	 * Setter for <code>PUBLIC.T_BOOK_TO_BOOK_STORE.BOOK_STORE_NAME</code>. 
	 */
	@Override
	public void setBookStoreName(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>PUBLIC.T_BOOK_TO_BOOK_STORE.BOOK_STORE_NAME</code>. 
	 */
	@Override
	public java.lang.String getBookStoreName() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>PUBLIC.T_BOOK_TO_BOOK_STORE.BOOK_ID</code>. 
	 */
	@Override
	public void setBookId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>PUBLIC.T_BOOK_TO_BOOK_STORE.BOOK_ID</code>. 
	 */
	@Override
	public java.lang.Integer getBookId() {
		return (java.lang.Integer) getValue(1);
	}

	/**
	 * Setter for <code>PUBLIC.T_BOOK_TO_BOOK_STORE.STOCK</code>. 
	 */
	@Override
	public void setStock(java.lang.Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>PUBLIC.T_BOOK_TO_BOOK_STORE.STOCK</code>. 
	 */
	@Override
	public java.lang.Integer getStock() {
		return (java.lang.Integer) getValue(2);
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
		return (org.jooq.Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row3<java.lang.String, java.lang.Integer, java.lang.Integer> valuesRow() {
		return (org.jooq.Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return org.jooq.test.hsqldb.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE.BOOK_STORE_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return org.jooq.test.hsqldb.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE.BOOK_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field3() {
		return org.jooq.test.hsqldb.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE.STOCK;
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
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.hsqldb.generatedclasses.tables.interfaces.ITBookToBookStore from) {
		setBookStoreName(from.getBookStoreName());
		setBookId(from.getBookId());
		setStock(from.getStock());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.hsqldb.generatedclasses.tables.interfaces.ITBookToBookStore> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TBookToBookStoreRecord
	 */
	public TBookToBookStoreRecord() {
		super(org.jooq.test.hsqldb.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE);
	}
}
