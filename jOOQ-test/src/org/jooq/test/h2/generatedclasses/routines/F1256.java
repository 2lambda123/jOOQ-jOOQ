/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.routines;

/**
 * This class is generated by jOOQ.
 */
public class F1256 extends org.jooq.impl.AbstractRoutine<java.lang.String> {

	private static final long serialVersionUID = 42955818;


	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.lang.String> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.VARCHAR);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.lang.String> P1 = createParameter("P1", org.jooq.impl.SQLDataType.VARCHAR);

	/**
	 * Create a new routine call instance
	 */
	public F1256() {
		super(org.jooq.SQLDialect.H2, "F1256", org.jooq.test.h2.generatedclasses.Public.PUBLIC, org.jooq.impl.SQLDataType.VARCHAR);

		setReturnParameter(RETURN_VALUE);
		addInParameter(P1);
	}

	/**
	 * Set the <code>P1</code> parameter to the routine
	 */
	public void setP1(java.lang.String value) {
		setValue(P1, value);
	}

	/**
	 * Set the <code>P1</code> parameter to the function
	 * <p>
	 * Use this method only, if the function is called as a {@link org.jooq.Field} in a {@link org.jooq.Select} statement!
	 */
	public void setP1(org.jooq.Field<java.lang.String> field) {
		setField(P1, field);
	}
}
