/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.routines;

/**
 * This class is generated by jOOQ.
 */
public class P_TABLES4 extends org.jooq.impl.AbstractRoutine<java.lang.Void> implements java.lang.Cloneable {

	private static final long serialVersionUID = 433180962;


	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle3.generatedclasses.udt.records.U_BOOK_TABLE> IN_TABLE = createParameter("IN_TABLE", org.jooq.test.oracle3.generatedclasses.udt.U_BOOK_TYPE.U_BOOK_TYPE.getDataType().asArrayDataType(org.jooq.test.oracle3.generatedclasses.udt.records.U_BOOK_TABLE.class));

	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle3.generatedclasses.udt.records.U_BOOK_TABLE> OUT_TABLE = createParameter("OUT_TABLE", org.jooq.test.oracle3.generatedclasses.udt.U_BOOK_TYPE.U_BOOK_TYPE.getDataType().asArrayDataType(org.jooq.test.oracle3.generatedclasses.udt.records.U_BOOK_TABLE.class));

	/**
	 * Create a new routine call instance
	 */
	public P_TABLES4() {
		super("P_TABLES4", org.jooq.test.oracle3.generatedclasses.TEST.TEST);

		addInParameter(IN_TABLE);
		addOutParameter(OUT_TABLE);
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter to the routine
	 */
	public void setIN_TABLE(org.jooq.test.oracle3.generatedclasses.udt.records.U_BOOK_TABLE value) {
		setValue(IN_TABLE, value);
	}

	public org.jooq.test.oracle3.generatedclasses.udt.records.U_BOOK_TABLE getOUT_TABLE() {
		return getValue(OUT_TABLE);
	}
}
