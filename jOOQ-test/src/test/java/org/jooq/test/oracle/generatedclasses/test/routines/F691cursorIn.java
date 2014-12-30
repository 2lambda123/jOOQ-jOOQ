/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;


import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.Result;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class F691cursorIn extends AbstractRoutine<BigDecimal> {

	private static final long serialVersionUID = 1533873290;

	/**
	 * The parameter <code>TEST.F691CURSOR_IN.RETURN_VALUE</code>.
	 */
	public static final Parameter<BigDecimal> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * The parameter <code>TEST.F691CURSOR_IN.C</code>.
	 */
	public static final Parameter<Result<org.jooq.Record>> C = createParameter("C", org.jooq.impl.SQLDataType.RESULT, false);

	/**
	 * Create a new routine call instance
	 */
	public F691cursorIn() {
		super("F691CURSOR_IN", Test.TEST, org.jooq.impl.SQLDataType.NUMERIC);

		setReturnParameter(RETURN_VALUE);
		addInParameter(C);
	}

	/**
	 * Set the <code>C</code> parameter IN value to the routine
	 */
	public void setC(Result<org.jooq.Record> value) {
		setValue(C, value);
	}

	/**
	 * Set the <code>C</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setC(Field<Result<org.jooq.Record>> field) {
		setField(C, field);
	}
}
