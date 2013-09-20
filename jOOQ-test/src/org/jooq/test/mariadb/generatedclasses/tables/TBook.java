/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mariadb.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 *
 * An entity holding books
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TBook extends org.jooq.impl.TableImpl<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord> {

	private static final long serialVersionUID = 201548186;

	/**
	 * The singleton instance of <code>test.t_book</code>
	 */
	public static final org.jooq.test.mariadb.generatedclasses.tables.TBook T_BOOK = new org.jooq.test.mariadb.generatedclasses.tables.TBook();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord> getRecordType() {
		return org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord.class;
	}

	/**
	 * The column <code>test.t_book.ID</code>. The book ID
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), T_BOOK);

	/**
	 * The column <code>test.t_book.AUTHOR_ID</code>. The author ID in entity 'author'
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.Integer> AUTHOR_ID = createField("AUTHOR_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), T_BOOK);

	/**
	 * The column <code>test.t_book.co_author_id</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.Integer> CO_AUTHOR_ID = createField("co_author_id", org.jooq.impl.SQLDataType.INTEGER, T_BOOK);

	/**
	 * The column <code>test.t_book.DETAILS_ID</code>. Some more details about the book
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.Integer> DETAILS_ID = createField("DETAILS_ID", org.jooq.impl.SQLDataType.INTEGER, T_BOOK);

	/**
	 * The column <code>test.t_book.TITLE</code>. The book's title
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.String> TITLE = createField("TITLE", org.jooq.impl.SQLDataType.CLOB.length(65535).nullable(false), T_BOOK);

	/**
	 * The column <code>test.t_book.PUBLISHED_IN</code>. The year the book was published in
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.Integer> PUBLISHED_IN = createField("PUBLISHED_IN", org.jooq.impl.SQLDataType.INTEGER.nullable(false), T_BOOK);

	/**
	 * The column <code>test.t_book.LANGUAGE_ID</code>. The language of the book
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.Integer> LANGUAGE_ID = createField("LANGUAGE_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), T_BOOK);

	/**
	 * The column <code>test.t_book.CONTENT_TEXT</code>. Some textual content of the book
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, java.lang.String> CONTENT_TEXT = createField("CONTENT_TEXT", org.jooq.impl.SQLDataType.CLOB, T_BOOK);

	/**
	 * The column <code>test.t_book.CONTENT_PDF</code>. Some binary content of the book
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, byte[]> CONTENT_PDF = createField("CONTENT_PDF", org.jooq.impl.SQLDataType.BLOB, T_BOOK);

	/**
	 * The column <code>test.t_book.STATUS</code>. The book's stock status
	 */
	public static final org.jooq.TableField<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, org.jooq.test.mariadb.generatedclasses.enums.TBookStatus> STATUS = createField("STATUS", org.jooq.util.mariadb.MariaDBDataType.VARCHAR.asEnumDataType(org.jooq.test.mariadb.generatedclasses.enums.TBookStatus.class), T_BOOK);

	/**
	 * No further instances allowed
	 */
	private TBook() {
		super("t_book", org.jooq.test.mariadb.generatedclasses.Test.TEST);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord> getPrimaryKey() {
		return org.jooq.test.mariadb.generatedclasses.Keys.KEY_T_BOOK_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord>>asList(org.jooq.test.mariadb.generatedclasses.Keys.KEY_T_BOOK_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.mariadb.generatedclasses.tables.records.TBookRecord, ?>>asList(org.jooq.test.mariadb.generatedclasses.Keys.FK_T_BOOK_AUTHOR_ID, org.jooq.test.mariadb.generatedclasses.Keys.FK_T_BOOK_CO_AUTHOR_ID, org.jooq.test.mariadb.generatedclasses.Keys.FK_T_BOOK_LANGUAGE_ID);
	}
}
