/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mysql.generatedclasses;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.0"},
                            comments = "This class is generated by jOOQ")
public class Test extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = 1253072355;

	/**
	 * The singleton instance of test
	 */
	public static final Test TEST = new Test();

	/**
	 * No further instances allowed
	 */
	private Test() {
		super("test");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			org.jooq.test.mysql.generatedclasses.tables.T_639NumbersTable.T_639_NUMBERS_TABLE,
			org.jooq.test.mysql.generatedclasses.tables.T_658Ref.T_658_REF,
			org.jooq.test.mysql.generatedclasses.tables.T_725LobTest.T_725_LOB_TEST,
			org.jooq.test.mysql.generatedclasses.tables.T_785.T_785,
			org.jooq.test.mysql.generatedclasses.tables.TAuthor.T_AUTHOR,
			org.jooq.test.mysql.generatedclasses.tables.TBook.T_BOOK,
			org.jooq.test.mysql.generatedclasses.tables.TBookStore.T_BOOK_STORE,
			org.jooq.test.mysql.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE,
			org.jooq.test.mysql.generatedclasses.tables.TTriggers.T_TRIGGERS,
			org.jooq.test.mysql.generatedclasses.tables.VAuthor.V_AUTHOR,
			org.jooq.test.mysql.generatedclasses.tables.VBook.V_BOOK,
			org.jooq.test.mysql.generatedclasses.tables.VLibrary.V_LIBRARY,
			org.jooq.test.mysql.generatedclasses.tables.XTestCase_64_69.X_TEST_CASE_64_69,
			org.jooq.test.mysql.generatedclasses.tables.XTestCase_71.X_TEST_CASE_71,
			org.jooq.test.mysql.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85,
			org.jooq.test.mysql.generatedclasses.tables.XUnused.X_UNUSED);
	}
}
