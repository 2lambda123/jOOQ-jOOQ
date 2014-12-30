/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.u_author_type;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.UAuthorType;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UAuthorTypeRecord;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UBookTableRecord;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UBookTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GetBooks extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -1695524268;

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.SELF</code>.
	 */
	public static final Parameter<UAuthorTypeRecord> SELF = createParameter("SELF", org.jooq.test.oracle.generatedclasses.test.udt.UAuthorType.U_AUTHOR_TYPE.getDataType(), false);

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.BOOK1</code>.
	 */
	public static final Parameter<UBookTypeRecord> BOOK1 = createParameter("BOOK1", org.jooq.test.oracle.generatedclasses.test.udt.UBookType.U_BOOK_TYPE.getDataType(), false);

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.BOOK2</code>.
	 */
	public static final Parameter<UBookTypeRecord> BOOK2 = createParameter("BOOK2", org.jooq.test.oracle.generatedclasses.test.udt.UBookType.U_BOOK_TYPE.getDataType(), false);

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.GET_BOOKS.BOOKS</code>.
	 */
	public static final Parameter<UBookTableRecord> BOOKS = createParameter("BOOKS", org.jooq.test.oracle.generatedclasses.test.udt.UBookType.U_BOOK_TYPE.getDataType().asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UBookTableRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public GetBooks() {
		super("GET_BOOKS", Test.TEST, UAuthorType.U_AUTHOR_TYPE);

		addInOutParameter(SELF);
		addOutParameter(BOOK1);
		addOutParameter(BOOK2);
		addOutParameter(BOOKS);
	}

	/**
	 * Set the <code>SELF</code> parameter IN value to the routine
	 */
	public void setSelf(UAuthorTypeRecord value) {
		setValue(SELF, value);
	}

	/**
	 * Get the <code>SELF</code> parameter OUT value from the routine
	 */
	public UAuthorTypeRecord getSelf() {
		return getValue(SELF);
	}

	/**
	 * Get the <code>BOOK1</code> parameter OUT value from the routine
	 */
	public UBookTypeRecord getBook1() {
		return getValue(BOOK1);
	}

	/**
	 * Get the <code>BOOK2</code> parameter OUT value from the routine
	 */
	public UBookTypeRecord getBook2() {
		return getValue(BOOK2);
	}

	/**
	 * Get the <code>BOOKS</code> parameter OUT value from the routine
	 */
	public UBookTableRecord getBooks() {
		return getValue(BOOKS);
	}
}
