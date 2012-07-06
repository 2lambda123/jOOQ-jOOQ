/**
 * This class is generated by jOOQ
 */
package org.jooq.test.ase.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
public class TBookRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.ase.generatedclasses.tables.records.TBookRecord> {

	private static final long serialVersionUID = 350077450;

	/**
	 * The table column <code>dbo.t_book.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.ID, value);
	}

	/**
	 * The table column <code>dbo.t_book.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.ID);
	}

	/**
	 * The table column <code>dbo.t_book.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.util.List<org.jooq.test.ase.generatedclasses.tables.records.TBookToBookStoreRecord> fetchTBookToBookStoreList() {
		return create()
			.selectFrom(org.jooq.test.ase.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE)
			.where(org.jooq.test.ase.generatedclasses.tables.TBookToBookStore.BOOK_ID.equal(getValue(org.jooq.test.ase.generatedclasses.tables.TBook.ID)))
			.fetch();
	}

	/**
	 * The table column <code>dbo.t_book.author_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_author_id
	 * FOREIGN KEY (author_id)
	 * REFERENCES dbo.t_author (id)
	 * </pre></code>
	 */
	public void setAuthorId(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.AUTHOR_ID, value);
	}

	/**
	 * The table column <code>dbo.t_book.author_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_author_id
	 * FOREIGN KEY (author_id)
	 * REFERENCES dbo.t_author (id)
	 * </pre></code>
	 */
	public java.lang.Integer getAuthorId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.AUTHOR_ID);
	}

	/**
	 * The table column <code>dbo.t_book.author_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_author_id
	 * FOREIGN KEY (author_id)
	 * REFERENCES dbo.t_author (id)
	 * </pre></code>
	 */
	public org.jooq.test.ase.generatedclasses.tables.records.TAuthorRecord fetchTAuthorByAuthorId() {
		return create()
			.selectFrom(org.jooq.test.ase.generatedclasses.tables.TAuthor.T_AUTHOR)
			.where(org.jooq.test.ase.generatedclasses.tables.TAuthor.ID.equal(getValue(org.jooq.test.ase.generatedclasses.tables.TBook.AUTHOR_ID)))
			.fetchOne();
	}

	/**
	 * The table column <code>dbo.t_book.co_author_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_co_author_id
	 * FOREIGN KEY (co_author_id)
	 * REFERENCES dbo.t_author (id)
	 * </pre></code>
	 */
	public void setCoAuthorId(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.CO_AUTHOR_ID, value);
	}

	/**
	 * The table column <code>dbo.t_book.co_author_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_co_author_id
	 * FOREIGN KEY (co_author_id)
	 * REFERENCES dbo.t_author (id)
	 * </pre></code>
	 */
	public java.lang.Integer getCoAuthorId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.CO_AUTHOR_ID);
	}

	/**
	 * The table column <code>dbo.t_book.co_author_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_co_author_id
	 * FOREIGN KEY (co_author_id)
	 * REFERENCES dbo.t_author (id)
	 * </pre></code>
	 */
	public org.jooq.test.ase.generatedclasses.tables.records.TAuthorRecord fetchTAuthorByCoAuthorId() {
		return create()
			.selectFrom(org.jooq.test.ase.generatedclasses.tables.TAuthor.T_AUTHOR)
			.where(org.jooq.test.ase.generatedclasses.tables.TAuthor.ID.equal(getValue(org.jooq.test.ase.generatedclasses.tables.TBook.CO_AUTHOR_ID)))
			.fetchOne();
	}

	/**
	 * The table column <code>dbo.t_book.details_id</code>
	 */
	public void setDetailsId(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.DETAILS_ID, value);
	}

	/**
	 * The table column <code>dbo.t_book.details_id</code>
	 */
	public java.lang.Integer getDetailsId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.DETAILS_ID);
	}

	/**
	 * The table column <code>dbo.t_book.title</code>
	 */
	public void setTitle(java.lang.String value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.TITLE, value);
	}

	/**
	 * The table column <code>dbo.t_book.title</code>
	 */
	public java.lang.String getTitle() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.TITLE);
	}

	/**
	 * The table column <code>dbo.t_book.published_in</code>
	 */
	public void setPublishedIn(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.PUBLISHED_IN, value);
	}

	/**
	 * The table column <code>dbo.t_book.published_in</code>
	 */
	public java.lang.Integer getPublishedIn() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.PUBLISHED_IN);
	}

	/**
	 * The table column <code>dbo.t_book.language_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_language_id
	 * FOREIGN KEY (language_id)
	 * REFERENCES dbo.t_language (id)
	 * </pre></code>
	 */
	public void setLanguageId(org.jooq.test.ase.generatedclasses.enums.TLanguage value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.LANGUAGE_ID, value);
	}

	/**
	 * The table column <code>dbo.t_book.language_id</code>
	 * <p>
	 * This column is part of a FOREIGN KEY: <code><pre>
	 * CONSTRAINT t_book__fk_t_book_language_id
	 * FOREIGN KEY (language_id)
	 * REFERENCES dbo.t_language (id)
	 * </pre></code>
	 */
	public org.jooq.test.ase.generatedclasses.enums.TLanguage getLanguageId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.LANGUAGE_ID);
	}

	/**
	 * The table column <code>dbo.t_book.content_text</code>
	 */
	public void setContentText(java.lang.String value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.CONTENT_TEXT, value);
	}

	/**
	 * The table column <code>dbo.t_book.content_text</code>
	 */
	public java.lang.String getContentText() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.CONTENT_TEXT);
	}

	/**
	 * The table column <code>dbo.t_book.content_pdf</code>
	 */
	public void setContentPdf(byte[] value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TBook.CONTENT_PDF, value);
	}

	/**
	 * The table column <code>dbo.t_book.content_pdf</code>
	 */
	public byte[] getContentPdf() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TBook.CONTENT_PDF);
	}

	/**
	 * Create a detached TBookRecord
	 */
	public TBookRecord() {
		super(org.jooq.test.ase.generatedclasses.tables.TBook.T_BOOK);
	}
}
