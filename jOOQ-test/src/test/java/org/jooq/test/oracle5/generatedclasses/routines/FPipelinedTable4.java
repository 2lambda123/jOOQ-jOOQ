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
public class FPipelinedTable4 extends org.jooq.impl.AbstractRoutine<org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord> {

	private static final long serialVersionUID = 1894746334;

	/**
	 * The parameter <code>TEST.F_PIPELINED_TABLE4.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.test.oracle5.generatedclasses.udt.UBookType.U_BOOK_TYPE.getDataType().asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord.class));

	/**
	 * Create a new routine call instance
	 */
	public FPipelinedTable4() {
		super("F_PIPELINED_TABLE4", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.test.oracle5.generatedclasses.udt.UBookType.U_BOOK_TYPE.getDataType().asArrayDataType(org.jooq.test.oracle5.generatedclasses.udt.records.UBookTableRecord.class));

		setReturnParameter(RETURN_VALUE);
	}
}
