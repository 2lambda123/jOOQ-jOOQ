/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlserver.generatedclasses;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class Dbo extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = 377007686;

	/**
	 * The singleton instance of dbo
	 */
	public static final Dbo DBO = new Dbo();

	/**
	 * No further instances allowed
	 */
	private Dbo() {
		super("dbo");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List<org.jooq.Table<?>> result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			org.jooq.test.sqlserver.generatedclasses.tables.T_639NumbersTable.T_639_NUMBERS_TABLE,
			org.jooq.test.sqlserver.generatedclasses.tables.T_658Ref.T_658_REF,
			org.jooq.test.sqlserver.generatedclasses.tables.T_725LobTest.T_725_LOB_TEST,
			org.jooq.test.sqlserver.generatedclasses.tables.T_785.T_785,
			org.jooq.test.sqlserver.generatedclasses.tables.TAuthor.T_AUTHOR,
			org.jooq.test.sqlserver.generatedclasses.tables.TBook.T_BOOK,
			org.jooq.test.sqlserver.generatedclasses.tables.TBookStore.T_BOOK_STORE,
			org.jooq.test.sqlserver.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE,
			org.jooq.test.sqlserver.generatedclasses.tables.TBooleans.T_BOOLEANS,
			org.jooq.test.sqlserver.generatedclasses.tables.TDates.T_DATES,
			org.jooq.test.sqlserver.generatedclasses.tables.TIdentity.T_IDENTITY,
			org.jooq.test.sqlserver.generatedclasses.tables.TIdentityPk.T_IDENTITY_PK,
			org.jooq.test.sqlserver.generatedclasses.tables.TTriggers.T_TRIGGERS,
			org.jooq.test.sqlserver.generatedclasses.tables.VAuthor.V_AUTHOR,
			org.jooq.test.sqlserver.generatedclasses.tables.VBook.V_BOOK,
			org.jooq.test.sqlserver.generatedclasses.tables.VLibrary.V_LIBRARY,
			org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_64_69.X_TEST_CASE_64_69,
			org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71,
			org.jooq.test.sqlserver.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85,
			org.jooq.test.sqlserver.generatedclasses.tables.XUnused.X_UNUSED);
	}
}
