/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.packages.pkg_976;

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
public class F_976 extends org.jooq.impl.AbstractRoutine<java.time.LocalDateTime> {

	private static final long serialVersionUID = 889514743;

	/**
	 * The parameter <code>TEST.PKG_976.F_976.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<java.time.LocalDateTime> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.DATE, false, new org.jooq.test.all.converters.LocalDateTimeConverter());

	/**
	 * The parameter <code>TEST.PKG_976.F_976.I</code>.
	 */
	public static final org.jooq.Parameter<java.time.LocalDateTime> I = createParameter("I", org.jooq.impl.SQLDataType.DATE, false, new org.jooq.test.all.converters.LocalDateTimeConverter());

	/**
	 * Create a new routine call instance
	 */
	public F_976() {
		super("F_976", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.test.oracle5.generatedclasses.packages.Pkg_976.PKG_976, org.jooq.impl.SQLDataType.DATE, new org.jooq.test.all.converters.LocalDateTimeConverter());

		setReturnParameter(RETURN_VALUE);
		addInParameter(I);
	}

	/**
	 * Set the <code>I</code> parameter IN value to the routine
	 */
	public void setI(java.time.LocalDateTime value) {
		setValue(org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976.I, value);
	}

	/**
	 * Set the <code>I</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setI(org.jooq.Field<java.time.LocalDateTime> field) {
		setField(I, field);
	}
}
