/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.packages.pkg_1358;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class P______ABC_2 extends org.jooq.impl.AbstractRoutine<java.lang.Void> implements java.lang.Cloneable {

	private static final long serialVersionUID = 609787862;

	/**
	 * The parameter <code>PKG_1358.P.J</code>. 
	 */
	public static final org.jooq.Parameter<java.lang.String> J = createParameter("J", org.jooq.impl.SQLDataType.VARCHAR);

	/**
	 * Create a new routine call instance
	 */
	public P______ABC_2() {
		super("P", org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.oracle3.generatedclasses.packages.PKG_1358.PKG_1358);

		addInParameter(J);
		setOverloaded(true);
	}

	/**
	 * Set the <code>J</code> parameter IN value to the routine
	 */
	public void setJ(java.lang.String value) {
		setValue(org.jooq.test.oracle3.generatedclasses.packages.pkg_1358.P______ABC_2.J, value);
	}
}
