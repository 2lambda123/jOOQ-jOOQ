/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.routines;

/**
 * This class is generated by jOOQ.
 */
public class P_ENHANCE_ADDRESS2 extends org.jooq.impl.AbstractRoutine<java.lang.Void> implements java.lang.Cloneable {

	private static final long serialVersionUID = 1612594378;


	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle3.generatedclasses.udt.records.U_ADDRESS_TYPE> ADDRESS = createParameter("ADDRESS", org.jooq.test.oracle3.generatedclasses.udt.U_ADDRESS_TYPE.U_ADDRESS_TYPE.getDataType());

	/**
	 * Create a new routine call instance
	 */
	public P_ENHANCE_ADDRESS2() {
		super("P_ENHANCE_ADDRESS2", org.jooq.test.oracle3.generatedclasses.TEST.TEST);

		addOutParameter(ADDRESS);
	}

	public org.jooq.test.oracle3.generatedclasses.udt.records.U_ADDRESS_TYPE getADDRESS() {
		return getValue(ADDRESS);
	}
}
