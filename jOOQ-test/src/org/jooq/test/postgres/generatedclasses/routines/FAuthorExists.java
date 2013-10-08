/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.routines;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FAuthorExists extends org.jooq.impl.AbstractRoutine<java.lang.Integer> {

	private static final long serialVersionUID = 1019619441;

	/**
	 * The parameter <code>public.f_author_exists.RETURN_VALUE</code>. 
	 */
	public static final org.jooq.Parameter<java.lang.Integer> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER);

	/**
	 * The parameter <code>public.f_author_exists.author_name</code>. 
	 */
	public static final org.jooq.Parameter<java.lang.String> AUTHOR_NAME = createParameter("author_name", org.jooq.impl.SQLDataType.VARCHAR);

	/**
	 * Create a new routine call instance
	 */
	public FAuthorExists() {
		super("f_author_exists", org.jooq.test.postgres.generatedclasses.Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER);

		setReturnParameter(RETURN_VALUE);
		addInParameter(AUTHOR_NAME);
	}

	/**
	 * Set the <code>author_name</code> parameter IN value to the routine
	 */
	public void setAuthorName(java.lang.String value) {
		setValue(org.jooq.test.postgres.generatedclasses.routines.FAuthorExists.AUTHOR_NAME, value);
	}

	/**
	 * Set the <code>author_name</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public FAuthorExists setAuthorName(org.jooq.Field<java.lang.String> field) {
		setField(AUTHOR_NAME, field);
		return this;
	}
}
