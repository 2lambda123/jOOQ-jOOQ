/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.routines;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PGetTwoCursors extends org.jooq.impl.AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = 202976199;

	/**
	 * The parameter <code>TEST.P_GET_TWO_CURSORS.BOOKS</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.Result<org.jooq.Record>> BOOKS = createParameter("BOOKS", org.jooq.impl.SQLDataType.RESULT, false);

	/**
	 * The parameter <code>TEST.P_GET_TWO_CURSORS.AUTHORS</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.Result<org.jooq.Record>> AUTHORS = createParameter("AUTHORS", org.jooq.impl.SQLDataType.RESULT, false);

	/**
	 * Create a new routine call instance
	 */
	public PGetTwoCursors() {
		super("P_GET_TWO_CURSORS", org.jooq.test.oracle5.generatedclasses.Test.TEST);

		addOutParameter(BOOKS);
		addOutParameter(AUTHORS);
	}

	/**
	 * Get the <code>BOOKS</code> parameter OUT value from the routine
	 */
	public org.jooq.Result<org.jooq.Record> getBooks() {
		return getValue(BOOKS);
	}

	/**
	 * Get the <code>AUTHORS</code> parameter OUT value from the routine
	 */
	public org.jooq.Result<org.jooq.Record> getAuthors() {
		return getValue(AUTHORS);
	}
}
