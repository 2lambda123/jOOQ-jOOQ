/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.packages;

/**
 * Convenience access to all stored procedures and functions in PKG_976
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Pkg_976 extends org.jooq.impl.PackageImpl {

	private static final long serialVersionUID = 718173708;

	/**
	 * The singleton instance of <code>TEST.PKG_976</code>
	 */
	public static final org.jooq.test.oracle5.generatedclasses.packages.Pkg_976 PKG_976 = new org.jooq.test.oracle5.generatedclasses.packages.Pkg_976();

	/**
	 * Call <code>TEST.PKG_976.F_976</code>
	 */
	public static java.time.LocalDateTime f_976(org.jooq.Configuration configuration, java.time.LocalDateTime i) {
		org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976 f = new org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976();
		f.setI(i);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.PKG_976.F_976</code> as a field
	 */
	public static org.jooq.Field<java.time.LocalDateTime> f_976(java.time.LocalDateTime i) {
		org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976 f = new org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976();
		f.setI(i);

		return f.asField();
	}

	/**
	 * Get <code>TEST.PKG_976.F_976</code> as a field
	 */
	public static org.jooq.Field<java.time.LocalDateTime> f_976(org.jooq.Field<java.time.LocalDateTime> i) {
		org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976 f = new org.jooq.test.oracle5.generatedclasses.packages.pkg_976.F_976();
		f.setI(i);

		return f.asField();
	}

	/**
	 * Call <code>TEST.PKG_976.P_976</code>
	 */
	public static java.time.LocalDateTime p_976(org.jooq.Configuration configuration, java.time.LocalDateTime i) {
		org.jooq.test.oracle5.generatedclasses.packages.pkg_976.P_976 p = new org.jooq.test.oracle5.generatedclasses.packages.pkg_976.P_976();
		p.setI(i);

		p.execute(configuration);
		return p.getO();
	}

	/**
	 * No further instances allowed
	 */
	private Pkg_976() {
		super("PKG_976", org.jooq.test.oracle5.generatedclasses.Test.TEST);
	}
}
