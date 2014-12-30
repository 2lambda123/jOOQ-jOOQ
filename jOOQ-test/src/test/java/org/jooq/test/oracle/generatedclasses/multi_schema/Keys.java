/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.multi_schema;


import org.jooq.ForeignKey;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.TBook;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.TBookSale;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TAuthorRecord;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TBookRecord;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TBookSaleRecord;
import org.jooq.test.oracle.generatedclasses.test.tables.records.TBookToBookStoreRecord;
import org.jooq.test.oracle.generatedclasses.test.tables.records.TLanguageRecord;


/**
 * A class modelling foreign key relationships between tables of the <code>MULTI_SCHEMA</code> 
 * schema
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<TAuthorRecord> PK_T_AUTHOR = UniqueKeys0.PK_T_AUTHOR;
	public static final UniqueKey<TBookRecord> PK_T_BOOK = UniqueKeys0.PK_T_BOOK;
	public static final UniqueKey<TBookSaleRecord> PK_T_BOOK_SALE = UniqueKeys0.PK_T_BOOK_SALE;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------

	public static final ForeignKey<TBookRecord, TAuthorRecord> FK_T_BOOK_AUTHOR_ID = ForeignKeys0.FK_T_BOOK_AUTHOR_ID;
	public static final ForeignKey<TBookRecord, TAuthorRecord> FK_T_BOOK_CO_AUTHOR_ID = ForeignKeys0.FK_T_BOOK_CO_AUTHOR_ID;
	public static final ForeignKey<TBookRecord, TLanguageRecord> FK_T_BOOK_LANGUAGE_ID = ForeignKeys0.FK_T_BOOK_LANGUAGE_ID;
	public static final ForeignKey<TBookSaleRecord, TBookToBookStoreRecord> FK_T_BOOK_TO_BOOK_STORE = ForeignKeys0.FK_T_BOOK_TO_BOOK_STORE;

	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<TAuthorRecord> PK_T_AUTHOR = createUniqueKey(TAuthor.T_AUTHOR, TAuthor.T_AUTHOR.ID);
		public static final UniqueKey<TBookRecord> PK_T_BOOK = createUniqueKey(TBook.T_BOOK, TBook.T_BOOK.ID);
		public static final UniqueKey<TBookSaleRecord> PK_T_BOOK_SALE = createUniqueKey(TBookSale.T_BOOK_SALE, TBookSale.T_BOOK_SALE.ID);
	}

	private static class ForeignKeys0 extends AbstractKeys {
		public static final ForeignKey<TBookRecord, TAuthorRecord> FK_T_BOOK_AUTHOR_ID = createForeignKey(org.jooq.test.oracle.generatedclasses.multi_schema.Keys.PK_T_AUTHOR, TBook.T_BOOK, TBook.T_BOOK.AUTHOR_ID);
		public static final ForeignKey<TBookRecord, TAuthorRecord> FK_T_BOOK_CO_AUTHOR_ID = createForeignKey(org.jooq.test.oracle.generatedclasses.multi_schema.Keys.PK_T_AUTHOR, TBook.T_BOOK, TBook.T_BOOK.CO_AUTHOR_ID);
		public static final ForeignKey<TBookRecord, TLanguageRecord> FK_T_BOOK_LANGUAGE_ID = createForeignKey(org.jooq.test.oracle.generatedclasses.test.Keys.PK_T_LANGUAGE, TBook.T_BOOK, TBook.T_BOOK.LANGUAGE_ID);
		public static final ForeignKey<TBookSaleRecord, TBookToBookStoreRecord> FK_T_BOOK_TO_BOOK_STORE = createForeignKey(org.jooq.test.oracle.generatedclasses.test.Keys.PK_B2BS, TBookSale.T_BOOK_SALE, TBookSale.T_BOOK_SALE.BOOK_STORE_NAME, TBookSale.T_BOOK_SALE.BOOK_ID);
	}
}
