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
public class PEnhanceAddress1 extends org.jooq.impl.AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -1021488024;

	/**
	 * The parameter <code>TEST.P_ENHANCE_ADDRESS1.ADDRESS</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UAddressTypeRecord> ADDRESS = createParameter("ADDRESS", org.jooq.test.oracle5.generatedclasses.udt.UAddressType.U_ADDRESS_TYPE.getDataType(), false);

	/**
	 * The parameter <code>TEST.P_ENHANCE_ADDRESS1.NO</code>.
	 */
	public static final org.jooq.Parameter<java.lang.String> NO = createParameter("NO", org.jooq.impl.SQLDataType.VARCHAR, false);

	/**
	 * Create a new routine call instance
	 */
	public PEnhanceAddress1() {
		super("P_ENHANCE_ADDRESS1", org.jooq.test.oracle5.generatedclasses.Test.TEST);

		addInParameter(ADDRESS);
		addOutParameter(NO);
	}

	/**
	 * Set the <code>ADDRESS</code> parameter IN value to the routine
	 */
	public void setAddress(org.jooq.test.oracle5.generatedclasses.udt.records.UAddressTypeRecord value) {
		setValue(org.jooq.test.oracle5.generatedclasses.routines.PEnhanceAddress1.ADDRESS, value);
	}

	/**
	 * Get the <code>NO</code> parameter OUT value from the routine
	 */
	public java.lang.String getNo() {
		return getValue(NO);
	}
}
