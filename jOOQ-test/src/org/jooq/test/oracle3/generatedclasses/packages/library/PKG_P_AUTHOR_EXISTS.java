/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.packages.library;

/**
 * This class is generated by jOOQ.
 */
public class PKG_P_AUTHOR_EXISTS extends org.jooq.impl.AbstractRoutine<java.lang.Void> implements java.lang.Cloneable {

	private static final long serialVersionUID = 416895860;


	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.lang.String> AUTHOR_NAME = createParameter("AUTHOR_NAME", org.jooq.impl.SQLDataType.VARCHAR);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> RESULT = createParameter("RESULT", org.jooq.impl.SQLDataType.NUMERIC);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> UNUSED = createParameter("UNUSED", org.jooq.impl.SQLDataType.NUMERIC);

	/**
	 * Create a new routine call instance
	 */
	public PKG_P_AUTHOR_EXISTS() {
		super("PKG_P_AUTHOR_EXISTS", org.jooq.test.oracle3.generatedclasses.TEST.TEST, org.jooq.test.oracle3.generatedclasses.packages.LIBRARY.LIBRARY);

		addInParameter(AUTHOR_NAME);
		addOutParameter(RESULT);
		addInOutParameter(UNUSED);
		setOverloaded(true);
	}

	/**
	 * Set the <code>AUTHOR_NAME</code> parameter to the routine
	 */
	public void setAUTHOR_NAME(java.lang.String value) {
		setValue(AUTHOR_NAME, value);
	}

	/**
	 * Set the <code>UNUSED</code> parameter to the routine
	 */
	public void setUNUSED(java.lang.Number value) {
		setNumber(UNUSED, value);
	}

	public java.math.BigDecimal getRESULT() {
		return getValue(RESULT);
	}

	public java.math.BigDecimal getUNUSED() {
		return getValue(UNUSED);
	}
}
