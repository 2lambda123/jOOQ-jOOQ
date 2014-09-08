/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.packages.library;

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
public class PkgPAuthorExists2 extends org.jooq.impl.AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -449697025;

	/**
	 * The parameter <code>TEST.LIBRARY.PKG_P_AUTHOR_EXISTS.AUTHOR_NAME</code>.
	 */
	public static final org.jooq.Parameter<java.lang.String> AUTHOR_NAME = createParameter("AUTHOR_NAME", org.jooq.impl.SQLDataType.VARCHAR, false);

	/**
	 * The parameter <code>TEST.LIBRARY.PKG_P_AUTHOR_EXISTS.RESULT</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> RESULT = createParameter("RESULT", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * The parameter <code>TEST.LIBRARY.PKG_P_AUTHOR_EXISTS.UNUSED</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> UNUSED = createParameter("UNUSED", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * Create a new routine call instance
	 */
	public PkgPAuthorExists2() {
		super("PKG_P_AUTHOR_EXISTS", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.test.oracle5.generatedclasses.packages.Library.LIBRARY);

		addInParameter(AUTHOR_NAME);
		addOutParameter(RESULT);
		addInOutParameter(UNUSED);
		setOverloaded(true);
	}

	/**
	 * Set the <code>AUTHOR_NAME</code> parameter IN value to the routine
	 */
	public void setAuthorName(java.lang.String value) {
		setValue(org.jooq.test.oracle5.generatedclasses.packages.library.PkgPAuthorExists2.AUTHOR_NAME, value);
	}

	/**
	 * Set the <code>UNUSED</code> parameter IN value to the routine
	 */
	public void setUnused(java.math.BigDecimal value) {
		setValue(org.jooq.test.oracle5.generatedclasses.packages.library.PkgPAuthorExists2.UNUSED, value);
	}

	/**
	 * Get the <code>RESULT</code> parameter OUT value from the routine
	 */
	public java.math.BigDecimal getResult() {
		return getValue(RESULT);
	}

	/**
	 * Get the <code>UNUSED</code> parameter OUT value from the routine
	 */
	public java.math.BigDecimal getUnused() {
		return getValue(UNUSED);
	}
}
