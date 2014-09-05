/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.udt.u_author_type;

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
public class GetBooks extends org.jooq.impl.AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = 2080547131;

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.SELF</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord> SELF = createParameter("SELF", org.jooq.test.oracle5.generatedclasses.udt.UAuthorType.U_AUTHOR_TYPE.getDataType());

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.BOOK1</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UBookTypeRecord> BOOK1 = createParameter("BOOK1", org.jooq.test.oracle5.generatedclasses.udt.UBookType.U_BOOK_TYPE.getDataType());

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.BOOK2</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UBookTypeRecord> BOOK2 = createParameter("BOOK2", org.jooq.test.oracle5.generatedclasses.udt.UBookType.U_BOOK_TYPE.getDataType());

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.BOOKS</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord> BOOKS = createParameter("BOOKS", org.jooq.test.oracle5.generatedclasses.udt.UBookType.U_BOOK_TYPE.getDataType().asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord.class));

	/**
	 * Create a new routine call instance
	 */
	public GetBooks() {
		super("GET_BOOKS", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.test.oracle5.generatedclasses.udt.UAuthorType.U_AUTHOR_TYPE);

		addInOutParameter(SELF);
		addOutParameter(BOOK1);
		addOutParameter(BOOK2);
		addOutParameter(BOOKS);
	}

	/**
	 * Set the <code>SELF</code> parameter IN value to the routine
	 */
	public void setSelf(org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord value) {
		setValue(org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetBooks.SELF, value);
	}

	/**
	 * Get the <code>SELF</code> parameter OUT value from the routine
	 */
	public org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord getSelf() {
		return getValue(SELF);
	}

	/**
	 * Get the <code>BOOK1</code> parameter OUT value from the routine
	 */
	public org.jooq.test.oracle5.generatedclasses.udt.records.UBookTypeRecord getBook1() {
		return getValue(BOOK1);
	}

	/**
	 * Get the <code>BOOK2</code> parameter OUT value from the routine
	 */
	public org.jooq.test.oracle5.generatedclasses.udt.records.UBookTypeRecord getBook2() {
		return getValue(BOOK2);
	}

	/**
	 * Get the <code>BOOKS</code> parameter OUT value from the routine
	 */
	public org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord getBooks() {
		return getValue(BOOKS);
	}
}
