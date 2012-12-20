/**
 * This class is generated by jOOQ
 */
package org.jooq.test.db2.generatedclasses;

/**
 * This class is generated by jOOQ.
 *
 * Convenience access to all stored procedures and functions in TEST
 */
@java.lang.SuppressWarnings("all")
public final class Routines {

	/**
	 * Call <code>TEST.F317</code>
	 */
	public static java.lang.Integer f317(org.jooq.Configuration configuration, java.lang.Integer p1, java.lang.Integer p2, java.lang.Integer p3, java.lang.Integer p4) {
		org.jooq.test.db2.generatedclasses.routines.F317 f = new org.jooq.test.db2.generatedclasses.routines.F317();
		f.setP1(p1);
		f.setP2(p2);
		f.setP3(p3);
		f.setP4(p4);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.F317</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> f317(java.lang.Integer p1, java.lang.Integer p2, java.lang.Integer p3, java.lang.Integer p4) {
		org.jooq.test.db2.generatedclasses.routines.F317 f = new org.jooq.test.db2.generatedclasses.routines.F317();
		f.setP1(p1);
		f.setP2(p2);
		f.setP3(p3);
		f.setP4(p4);

		return f.asField();
	}

	/**
	 * Get <code>TEST.F317</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> f317(org.jooq.Field<java.lang.Integer> p1, org.jooq.Field<java.lang.Integer> p2, org.jooq.Field<java.lang.Integer> p3, org.jooq.Field<java.lang.Integer> p4) {
		org.jooq.test.db2.generatedclasses.routines.F317 f = new org.jooq.test.db2.generatedclasses.routines.F317();
		f.setP1(p1);
		f.setP2(p2);
		f.setP3(p3);
		f.setP4(p4);

		return f.asField();
	}

	/**
	 * Call <code>TEST.F_AUTHOR_EXISTS</code>
	 */
	public static java.lang.Integer fAuthorExists(org.jooq.Configuration configuration, java.lang.String authorName) {
		org.jooq.test.db2.generatedclasses.routines.FAuthorExists f = new org.jooq.test.db2.generatedclasses.routines.FAuthorExists();
		f.setAuthorName(authorName);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.F_AUTHOR_EXISTS</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fAuthorExists(java.lang.String authorName) {
		org.jooq.test.db2.generatedclasses.routines.FAuthorExists f = new org.jooq.test.db2.generatedclasses.routines.FAuthorExists();
		f.setAuthorName(authorName);

		return f.asField();
	}

	/**
	 * Get <code>TEST.F_AUTHOR_EXISTS</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fAuthorExists(org.jooq.Field<java.lang.String> authorName) {
		org.jooq.test.db2.generatedclasses.routines.FAuthorExists f = new org.jooq.test.db2.generatedclasses.routines.FAuthorExists();
		f.setAuthorName(authorName);

		return f.asField();
	}

	/**
	 * Call <code>TEST.F_NUMBER</code>
	 */
	public static java.lang.Integer fNumber(org.jooq.Configuration configuration, java.lang.Integer n) {
		org.jooq.test.db2.generatedclasses.routines.FNumber f = new org.jooq.test.db2.generatedclasses.routines.FNumber();
		f.setN(n);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.F_NUMBER</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fNumber(java.lang.Integer n) {
		org.jooq.test.db2.generatedclasses.routines.FNumber f = new org.jooq.test.db2.generatedclasses.routines.FNumber();
		f.setN(n);

		return f.asField();
	}

	/**
	 * Get <code>TEST.F_NUMBER</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fNumber(org.jooq.Field<java.lang.Integer> n) {
		org.jooq.test.db2.generatedclasses.routines.FNumber f = new org.jooq.test.db2.generatedclasses.routines.FNumber();
		f.setN(n);

		return f.asField();
	}

	/**
	 * Call <code>TEST.F_ONE</code>
	 */
	public static java.lang.Integer fOne(org.jooq.Configuration configuration) {
		org.jooq.test.db2.generatedclasses.routines.FOne f = new org.jooq.test.db2.generatedclasses.routines.FOne();

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.F_ONE</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fOne() {
		org.jooq.test.db2.generatedclasses.routines.FOne f = new org.jooq.test.db2.generatedclasses.routines.FOne();

		return f.asField();
	}

	/**
	 * Call <code>TEST.P391</code>
	 */
	public static org.jooq.test.db2.generatedclasses.routines.P391 p391(org.jooq.Configuration configuration, java.lang.Integer i1, java.lang.Integer io1, java.lang.Integer io2, java.lang.Integer i2) {
		org.jooq.test.db2.generatedclasses.routines.P391 p = new org.jooq.test.db2.generatedclasses.routines.P391();
		p.setI1(i1);
		p.setIo1(io1);
		p.setIo2(io2);
		p.setI2(i2);

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>TEST.P_AUTHOR_EXISTS</code>
	 */
	public static java.lang.Integer pAuthorExists(org.jooq.Configuration configuration, java.lang.String authorName) {
		org.jooq.test.db2.generatedclasses.routines.PAuthorExists p = new org.jooq.test.db2.generatedclasses.routines.PAuthorExists();
		p.setAuthorName(authorName);

		p.execute(configuration);
		return p.getResult();
	}

	/**
	 * Call <code>TEST.P_CREATE_AUTHOR</code>
	 */
	public static void pCreateAuthor(org.jooq.Configuration configuration) {
		org.jooq.test.db2.generatedclasses.routines.PCreateAuthor p = new org.jooq.test.db2.generatedclasses.routines.PCreateAuthor();

		p.execute(configuration);
	}

	/**
	 * Call <code>TEST.P_CREATE_AUTHOR_BY_NAME</code>
	 */
	public static void pCreateAuthorByName(org.jooq.Configuration configuration, java.lang.String firstName, java.lang.String lastName) {
		org.jooq.test.db2.generatedclasses.routines.PCreateAuthorByName p = new org.jooq.test.db2.generatedclasses.routines.PCreateAuthorByName();
		p.setFirstName(firstName);
		p.setLastName(lastName);

		p.execute(configuration);
	}

	/**
	 * Call <code>TEST.P_DEFAULT</code>
	 */
	public static org.jooq.test.db2.generatedclasses.routines.PDefault pDefault(org.jooq.Configuration configuration, java.lang.Integer pInNumber, java.lang.String pInVarchar, java.sql.Date pInDate) {
		org.jooq.test.db2.generatedclasses.routines.PDefault p = new org.jooq.test.db2.generatedclasses.routines.PDefault();
		p.setPInNumber(pInNumber);
		p.setPInVarchar(pInVarchar);
		p.setPInDate(pInDate);

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>TEST.P_UNUSED</code>
	 */
	public static org.jooq.test.db2.generatedclasses.routines.PUnused pUnused(org.jooq.Configuration configuration, java.lang.String in1, java.lang.Integer out2) {
		org.jooq.test.db2.generatedclasses.routines.PUnused p = new org.jooq.test.db2.generatedclasses.routines.PUnused();
		p.setIn1(in1);
		p.setOut2(out2);

		p.execute(configuration);
		return p;
	}

	/**
	 * No further instances allowed
	 */
	private Routines() {}
}
