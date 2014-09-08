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
public class PArrays3 extends org.jooq.impl.AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -417705782;

	/**
	 * The parameter <code>TEST.P_ARRAYS3.IN_ARRAY</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UStringArrayRecord> IN_ARRAY = createParameter("IN_ARRAY", org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UStringArrayRecord.class), false);

	/**
	 * The parameter <code>TEST.P_ARRAYS3.OUT_ARRAY</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UStringArrayRecord> OUT_ARRAY = createParameter("OUT_ARRAY", org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UStringArrayRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public PArrays3() {
		super("P_ARRAYS3", org.jooq.test.oracle5.generatedclasses.Test.TEST);

		addInParameter(IN_ARRAY);
		addOutParameter(OUT_ARRAY);
	}

	/**
	 * Set the <code>IN_ARRAY</code> parameter IN value to the routine
	 */
	public void setInArray(org.jooq.test.oracle5.generatedclasses.udt.records.UStringArrayRecord value) {
		setValue(org.jooq.test.oracle5.generatedclasses.routines.PArrays3.IN_ARRAY, value);
	}

	/**
	 * Get the <code>OUT_ARRAY</code> parameter OUT value from the routine
	 */
	public org.jooq.test.oracle5.generatedclasses.udt.records.UStringArrayRecord getOutArray() {
		return getValue(OUT_ARRAY);
	}
}
