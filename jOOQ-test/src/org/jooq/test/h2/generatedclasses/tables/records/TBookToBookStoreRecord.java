/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 *
 * An m:n relation between books and book stores
 */
public class TBookToBookStoreRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.h2.generatedclasses.tables.records.TBookToBookStoreRecord> implements org.jooq.test.h2.generatedclasses.tables.interfaces.ITBookToBookStore {

	private static final long serialVersionUID = 1217868862;

	/**
	 * The book store name
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_B2BS_BS_NAME
	 * FOREIGN KEY (BOOK_STORE_NAME)
	 * REFERENCES PUBLIC.T_BOOK_STORE (NAME)
	 * </pre></code>
	 */
	@Override
	public void setBookStoreName(java.lang.String value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME, value);
	}

	/**
	 * The book store name
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_B2BS_BS_NAME
	 * FOREIGN KEY (BOOK_STORE_NAME)
	 * REFERENCES PUBLIC.T_BOOK_STORE (NAME)
	 * </pre></code>
	 */
	@Override
	public java.lang.String getBookStoreName() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord 
	 * TBookStoreRecord}
	 */
	public void setBookStoreName(org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord value) {
		if (value == null) {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME, null);
		}
		else {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME, value.getValue(org.jooq.test.h2.generatedclasses.tables.TBookStore.NAME));
		}
	}

	/**
	 * The book store name
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_B2BS_BS_NAME
	 * FOREIGN KEY (BOOK_STORE_NAME)
	 * REFERENCES PUBLIC.T_BOOK_STORE (NAME)
	 * </pre></code>
	 */
	public org.jooq.test.h2.generatedclasses.tables.records.TBookStoreRecord fetchTBookStore() {
		return create()
			.selectFrom(org.jooq.test.h2.generatedclasses.tables.TBookStore.T_BOOK_STORE)
			.where(org.jooq.test.h2.generatedclasses.tables.TBookStore.NAME.equal(getValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME)))
			.fetchOne();
	}

	/**
	 * The book ID
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_B2BS_B_ID
	 * FOREIGN KEY (BOOK_ID)
	 * REFERENCES PUBLIC.T_BOOK (ID)
	 * </pre></code>
	 */
	@Override
	public void setBookId(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_ID, value);
	}

	/**
	 * The book ID
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_B2BS_B_ID
	 * FOREIGN KEY (BOOK_ID)
	 * REFERENCES PUBLIC.T_BOOK (ID)
	 * </pre></code>
	 */
	@Override
	public java.lang.Integer getBookId() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_ID);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.h2.generatedclasses.tables.records.TBookRecord 
	 * TBookRecord}
	 */
	public void setBookId(org.jooq.test.h2.generatedclasses.tables.records.TBookRecord value) {
		if (value == null) {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_ID, null);
		}
		else {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_ID, value.getValue(org.jooq.test.h2.generatedclasses.tables.TBook.ID));
		}
	}

	/**
	 * The book ID
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_B2BS_B_ID
	 * FOREIGN KEY (BOOK_ID)
	 * REFERENCES PUBLIC.T_BOOK (ID)
	 * </pre></code>
	 */
	public org.jooq.test.h2.generatedclasses.tables.records.TBookRecord fetchTBook() {
		return create()
			.selectFrom(org.jooq.test.h2.generatedclasses.tables.TBook.T_BOOK)
			.where(org.jooq.test.h2.generatedclasses.tables.TBook.ID.equal(getValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_ID)))
			.fetchOne();
	}

	/**
	 * The number of books on stock
	 */
	@Override
	public void setStock(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.STOCK, value);
	}

	/**
	 * The number of books on stock
	 */
	@Override
	public java.lang.Integer getStock() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.STOCK);
	}

	/**
	 * Create a detached TBookToBookStoreRecord
	 */
	public TBookToBookStoreRecord() {
		super(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE);
	}
}
