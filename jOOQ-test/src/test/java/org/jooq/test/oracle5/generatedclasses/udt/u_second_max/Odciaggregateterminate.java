/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.udt.u_second_max;

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
public class Odciaggregateterminate extends org.jooq.impl.AbstractRoutine<java.math.BigDecimal> {

	private static final long serialVersionUID = -1599161258;

	/**
	 * The parameter <code>TEST.U_SECOND_MAX.ODCIAGGREGATETERMINATE.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.NUMERIC);

	/**
	 * The parameter <code>TEST.U_SECOND_MAX.ODCIAGGREGATETERMINATE.SELF</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord> SELF = createParameter("SELF", org.jooq.test.oracle5.generatedclasses.udt.USecondMax.U_SECOND_MAX.getDataType());

	/**
	 * The parameter <code>TEST.U_SECOND_MAX.ODCIAGGREGATETERMINATE.RETURNVALUE</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> RETURNVALUE = createParameter("RETURNVALUE", org.jooq.impl.SQLDataType.NUMERIC);

	/**
	 * The parameter <code>TEST.U_SECOND_MAX.ODCIAGGREGATETERMINATE.FLAGS</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> FLAGS = createParameter("FLAGS", org.jooq.impl.SQLDataType.NUMERIC);

	/**
	 * Create a new routine call instance
	 */
	public Odciaggregateterminate() {
		super("ODCIAGGREGATETERMINATE", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.test.oracle5.generatedclasses.udt.USecondMax.U_SECOND_MAX, org.jooq.impl.SQLDataType.NUMERIC);

		setReturnParameter(RETURN_VALUE);
		addInParameter(SELF);
		addOutParameter(RETURNVALUE);
		addInParameter(FLAGS);
	}

	/**
	 * Set the <code>SELF</code> parameter IN value to the routine
	 */
	public void setSelf(org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord value) {
		setValue(org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateterminate.SELF, value);
	}

	/**
	 * Set the <code>FLAGS</code> parameter IN value to the routine
	 */
	public void setFlags(java.math.BigDecimal value) {
		setValue(org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateterminate.FLAGS, value);
	}

	/**
	 * Get the <code>RETURNVALUE</code> parameter OUT value from the routine
	 */
	public java.math.BigDecimal getReturnvalue() {
		return getValue(RETURNVALUE);
	}
}
