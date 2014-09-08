/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.packages.library;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PkgFAuthorExists2 extends org.jooq.impl.AbstractRoutine<java.math.BigDecimal> {

	private static final long serialVersionUID = 668323961;

	/**
	 * The parameter <code>TEST.LIBRARY.PKG_F_AUTHOR_EXISTS.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * The parameter <code>TEST.LIBRARY.PKG_F_AUTHOR_EXISTS.AUTHOR_NAME</code>.
	 */
	public static final org.jooq.Parameter<java.lang.String> AUTHOR_NAME = createParameter("AUTHOR_NAME", org.jooq.impl.SQLDataType.VARCHAR, false);

	/**
	 * The parameter <code>TEST.LIBRARY.PKG_F_AUTHOR_EXISTS.UNUSED</code>.
	 */
	public static final org.jooq.Parameter<java.math.BigDecimal> UNUSED = createParameter("UNUSED", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * Create a new routine call instance
	 */
	public PkgFAuthorExists2() {
		super("PKG_F_AUTHOR_EXISTS", org.jooq.test.oracle.generatedclasses.test.Test.TEST, org.jooq.test.oracle.generatedclasses.test.packages.Library.LIBRARY, org.jooq.impl.SQLDataType.NUMERIC);

		setReturnParameter(RETURN_VALUE);
		addInParameter(AUTHOR_NAME);
		addInParameter(UNUSED);
		setOverloaded(true);
	}

	/**
	 * Set the <code>AUTHOR_NAME</code> parameter IN value to the routine
	 */
	public void setAuthorName(java.lang.String value) {
		setValue(org.jooq.test.oracle.generatedclasses.test.packages.library.PkgFAuthorExists2.AUTHOR_NAME, value);
	}

	/**
	 * Set the <code>AUTHOR_NAME</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setAuthorName(org.jooq.Field<java.lang.String> field) {
		setField(AUTHOR_NAME, field);
	}

	/**
	 * Set the <code>UNUSED</code> parameter IN value to the routine
	 */
	public void setUnused(java.lang.Number value) {
		setNumber(org.jooq.test.oracle.generatedclasses.test.packages.library.PkgFAuthorExists2.UNUSED, value);
	}

	/**
	 * Set the <code>UNUSED</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setUnused(org.jooq.Field<? extends java.lang.Number> field) {
		setNumber(UNUSED, field);
	}
}
