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
public class F691cursorIn extends org.jooq.impl.AbstractRoutine<java.math.BigDecimal> {

	private static final long serialVersionUID = -1316036500;

	/**
	 * The parameter <code>TEST.F691CURSOR_IN.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * The parameter <code>TEST.F691CURSOR_IN.C</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.Result<org.jooq.Record>> C = createParameter("C", org.jooq.impl.SQLDataType.RESULT, false);

	/**
	 * Create a new routine call instance
	 */
	public F691cursorIn() {
		super("F691CURSOR_IN", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.NUMERIC);

		setReturnParameter(RETURN_VALUE);
		addInParameter(C);
	}

	/**
	 * Set the <code>C</code> parameter IN value to the routine
	 */
	public void setC(org.jooq.Result<org.jooq.Record> value) {
		setValue(org.jooq.test.oracle5.generatedclasses.routines.F691cursorIn.C, value);
	}

	/**
	 * Set the <code>C</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setC(org.jooq.Field<org.jooq.Result<org.jooq.Record>> field) {
		setField(C, field);
	}
}
