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
public class FPipelinedArray1 extends org.jooq.impl.AbstractRoutine<org.jooq.test.oracle5.generatedclasses.udt.records.UNumberArrayRecord> {

	private static final long serialVersionUID = 270261013;

	/**
	 * The parameter <code>TEST.F_PIPELINED_ARRAY1.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UNumberArrayRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.NUMERIC.asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UNumberArrayRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public FPipelinedArray1() {
		super("F_PIPELINED_ARRAY1", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.NUMERIC.asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UNumberArrayRecord.class));

		setReturnParameter(RETURN_VALUE);
	}
}
