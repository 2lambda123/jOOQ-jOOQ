/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 *
 * An entity holding books
 */
public class TBookRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.h2.generatedclasses.tables.records.TBookRecord> implements org.jooq.test.h2.generatedclasses.tables.interfaces.ITBook {

	private static final long serialVersionUID = -832621196;

	/**
	 * The book ID
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	@Override
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.ID, value);
	}

	/**
	 * The book ID
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	@Override
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.ID);
	}

	/**
	 * The book ID
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.util.List<org.jooq.test.h2.generatedclasses.tables.records.TBookToBookStoreRecord> fetchTBookToBookStoreList() {
		return create()
			.selectFrom(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE)
			.where(org.jooq.test.h2.generatedclasses.tables.TBookToBookStore.BOOK_ID.equal(getValue(org.jooq.test.h2.generatedclasses.tables.TBook.ID)))
			.fetch();
	}

	/**
	 * The author ID in entity 'author'
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_AUTHOR_ID
	 * FOREIGN KEY (AUTHOR_ID)
	 * REFERENCES PUBLIC.T_AUTHOR (ID)
	 * </pre></code>
	 */
	@Override
	public void setAuthorId(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.AUTHOR_ID, value);
	}

	/**
	 * The author ID in entity 'author'
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_AUTHOR_ID
	 * FOREIGN KEY (AUTHOR_ID)
	 * REFERENCES PUBLIC.T_AUTHOR (ID)
	 * </pre></code>
	 */
	@Override
	public java.lang.Integer getAuthorId() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.AUTHOR_ID);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.h2.generatedclasses.tables.records.TAuthorRecord 
	 * TAuthorRecord}
	 */
	public void setAuthorId(org.jooq.test.h2.generatedclasses.tables.records.TAuthorRecord value) {
		if (value == null) {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBook.AUTHOR_ID, null);
		}
		else {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBook.AUTHOR_ID, value.getValue(org.jooq.test.h2.generatedclasses.tables.TAuthor.ID));
		}
	}

	/**
	 * The author ID in entity 'author'
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_AUTHOR_ID
	 * FOREIGN KEY (AUTHOR_ID)
	 * REFERENCES PUBLIC.T_AUTHOR (ID)
	 * </pre></code>
	 */
	public org.jooq.test.h2.generatedclasses.tables.records.TAuthorRecord fetchTAuthorByAuthorId() {
		return create()
			.selectFrom(org.jooq.test.h2.generatedclasses.tables.TAuthor.T_AUTHOR)
			.where(org.jooq.test.h2.generatedclasses.tables.TAuthor.ID.equal(getValue(org.jooq.test.h2.generatedclasses.tables.TBook.AUTHOR_ID)))
			.fetchOne();
	}

	/**
	 * The table column <code>PUBLIC.T_BOOK.CO_AUTHOR_ID</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_CO_AUTHOR_ID
	 * FOREIGN KEY (CO_AUTHOR_ID)
	 * REFERENCES PUBLIC.T_AUTHOR (ID)
	 * </pre></code>
	 */
	@Override
	public void setCoAuthorId(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.CO_AUTHOR_ID, value);
	}

	/**
	 * The table column <code>PUBLIC.T_BOOK.CO_AUTHOR_ID</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_CO_AUTHOR_ID
	 * FOREIGN KEY (CO_AUTHOR_ID)
	 * REFERENCES PUBLIC.T_AUTHOR (ID)
	 * </pre></code>
	 */
	@Override
	public java.lang.Integer getCoAuthorId() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.CO_AUTHOR_ID);
	}

	/**
	 * Link this record to a given {@link org.jooq.test.h2.generatedclasses.tables.records.TAuthorRecord 
	 * TAuthorRecord}
	 */
	public void setCoAuthorId(org.jooq.test.h2.generatedclasses.tables.records.TAuthorRecord value) {
		if (value == null) {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBook.CO_AUTHOR_ID, null);
		}
		else {
			setValue(org.jooq.test.h2.generatedclasses.tables.TBook.CO_AUTHOR_ID, value.getValue(org.jooq.test.h2.generatedclasses.tables.TAuthor.ID));
		}
	}

	/**
	 * The table column <code>PUBLIC.T_BOOK.CO_AUTHOR_ID</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_CO_AUTHOR_ID
	 * FOREIGN KEY (CO_AUTHOR_ID)
	 * REFERENCES PUBLIC.T_AUTHOR (ID)
	 * </pre></code>
	 */
	public org.jooq.test.h2.generatedclasses.tables.records.TAuthorRecord fetchTAuthorByCoAuthorId() {
		return create()
			.selectFrom(org.jooq.test.h2.generatedclasses.tables.TAuthor.T_AUTHOR)
			.where(org.jooq.test.h2.generatedclasses.tables.TAuthor.ID.equal(getValue(org.jooq.test.h2.generatedclasses.tables.TBook.CO_AUTHOR_ID)))
			.fetchOne();
	}

	/**
	 * The table column <code>PUBLIC.T_BOOK.DETAILS_ID</code>
	 */
	@Override
	public void setDetailsId(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.DETAILS_ID, value);
	}

	/**
	 * The table column <code>PUBLIC.T_BOOK.DETAILS_ID</code>
	 */
	@Override
	public java.lang.Integer getDetailsId() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.DETAILS_ID);
	}

	/**
	 * The book's title
	 */
	@Override
	public void setTitle(java.lang.String value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.TITLE, value);
	}

	/**
	 * The book's title
	 */
	@Override
	public java.lang.String getTitle() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.TITLE);
	}

	/**
	 * The year the book was published in
	 */
	@Override
	public void setPublishedIn(java.lang.Integer value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.PUBLISHED_IN, value);
	}

	/**
	 * The year the book was published in
	 */
	@Override
	public java.lang.Integer getPublishedIn() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.PUBLISHED_IN);
	}

	/**
	 * The language of the book
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_LANGUAGE_ID
	 * FOREIGN KEY (LANGUAGE_ID)
	 * REFERENCES PUBLIC.T_LANGUAGE (ID)
	 * </pre></code>
	 */
	@Override
	public void setLanguageId(org.jooq.test.h2.generatedclasses.enums.TLanguage value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.LANGUAGE_ID, value);
	}

	/**
	 * The language of the book
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT FK_T_BOOK_LANGUAGE_ID
	 * FOREIGN KEY (LANGUAGE_ID)
	 * REFERENCES PUBLIC.T_LANGUAGE (ID)
	 * </pre></code>
	 */
	@Override
	public org.jooq.test.h2.generatedclasses.enums.TLanguage getLanguageId() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.LANGUAGE_ID);
	}

	/**
	 * Some textual content of the book
	 */
	@Override
	public void setContentText(java.lang.String value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.CONTENT_TEXT, value);
	}

	/**
	 * Some textual content of the book
	 */
	@Override
	public java.lang.String getContentText() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.CONTENT_TEXT);
	}

	/**
	 * Some binary content of the book
	 */
	@Override
	public void setContentPdf(byte[] value) {
		setValue(org.jooq.test.h2.generatedclasses.tables.TBook.CONTENT_PDF, value);
	}

	/**
	 * Some binary content of the book
	 */
	@Override
	public byte[] getContentPdf() {
		return getValue(org.jooq.test.h2.generatedclasses.tables.TBook.CONTENT_PDF);
	}

	/**
	 * Create a detached TBookRecord
	 */
	public TBookRecord() {
		super(org.jooq.test.h2.generatedclasses.tables.TBook.T_BOOK);
	}
}
