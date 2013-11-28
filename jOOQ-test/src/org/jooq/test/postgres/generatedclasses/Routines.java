/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses;

/**
 * This class is generated by jOOQ.
 *
 * Convenience access to all stored procedures and functions in public
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

	/**
	 * Call <code>public.f_arrays</code>
	 */
	public static java.lang.Integer[] fArrays1(org.jooq.Configuration configuration, java.lang.Integer[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays1 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays1();
		f.setInArray(inArray);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_arrays</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer[]> fArrays1(java.lang.Integer[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays1 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays1();
		f.setInArray(inArray);

		return f.asField();
	}

	/**
	 * Get <code>public.f_arrays</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer[]> fArrays1(org.jooq.Field<java.lang.Integer[]> inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays1 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays1();
		f.setInArray(inArray);

		return f.asField();
	}

	/**
	 * Call <code>public.f_arrays</code>
	 */
	public static java.lang.String[] fArrays3(org.jooq.Configuration configuration, java.lang.String[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays3 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays3();
		f.setInArray(inArray);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_arrays</code> as a field
	 */
	public static org.jooq.Field<java.lang.String[]> fArrays3(java.lang.String[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays3 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays3();
		f.setInArray(inArray);

		return f.asField();
	}

	/**
	 * Get <code>public.f_arrays</code> as a field
	 */
	public static org.jooq.Field<java.lang.String[]> fArrays3(org.jooq.Field<java.lang.String[]> inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays3 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays3();
		f.setInArray(inArray);

		return f.asField();
	}

	/**
	 * Call <code>public.f_arrays</code>
	 */
	public static java.lang.Long[] fArrays2(org.jooq.Configuration configuration, java.lang.Long[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays2 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays2();
		f.setInArray(inArray);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_arrays</code> as a field
	 */
	public static org.jooq.Field<java.lang.Long[]> fArrays2(java.lang.Long[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays2 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays2();
		f.setInArray(inArray);

		return f.asField();
	}

	/**
	 * Get <code>public.f_arrays</code> as a field
	 */
	public static org.jooq.Field<java.lang.Long[]> fArrays2(org.jooq.Field<java.lang.Long[]> inArray) {
		org.jooq.test.postgres.generatedclasses.routines.FArrays2 f = new org.jooq.test.postgres.generatedclasses.routines.FArrays2();
		f.setInArray(inArray);

		return f.asField();
	}

	/**
	 * Call <code>public.f_author_exists</code>
	 */
	public static java.lang.Integer fAuthorExists(org.jooq.Configuration configuration, java.lang.String authorName) {
		org.jooq.test.postgres.generatedclasses.routines.FAuthorExists f = new org.jooq.test.postgres.generatedclasses.routines.FAuthorExists();
		f.setAuthorName(authorName);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_author_exists</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fAuthorExists(java.lang.String authorName) {
		org.jooq.test.postgres.generatedclasses.routines.FAuthorExists f = new org.jooq.test.postgres.generatedclasses.routines.FAuthorExists();
		f.setAuthorName(authorName);

		return f.asField();
	}

	/**
	 * Get <code>public.f_author_exists</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fAuthorExists(org.jooq.Field<java.lang.String> authorName) {
		org.jooq.test.postgres.generatedclasses.routines.FAuthorExists f = new org.jooq.test.postgres.generatedclasses.routines.FAuthorExists();
		f.setAuthorName(authorName);

		return f.asField();
	}

	/**
	 * Call <code>public.f_get_one_cursor</code>
	 */
	public static org.jooq.Result<org.jooq.Record> fGetOneCursor(org.jooq.Configuration configuration, java.lang.Integer[] bookIds) {
		org.jooq.test.postgres.generatedclasses.routines.FGetOneCursor f = new org.jooq.test.postgres.generatedclasses.routines.FGetOneCursor();
		f.setBookIds(bookIds);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_get_one_cursor</code> as a field
	 */
	public static org.jooq.Field<org.jooq.Result<org.jooq.Record>> fGetOneCursor(java.lang.Integer[] bookIds) {
		org.jooq.test.postgres.generatedclasses.routines.FGetOneCursor f = new org.jooq.test.postgres.generatedclasses.routines.FGetOneCursor();
		f.setBookIds(bookIds);

		return f.asField();
	}

	/**
	 * Get <code>public.f_get_one_cursor</code> as a field
	 */
	public static org.jooq.Field<org.jooq.Result<org.jooq.Record>> fGetOneCursor(org.jooq.Field<java.lang.Integer[]> bookIds) {
		org.jooq.test.postgres.generatedclasses.routines.FGetOneCursor f = new org.jooq.test.postgres.generatedclasses.routines.FGetOneCursor();
		f.setBookIds(bookIds);

		return f.asField();
	}

	/**
	 * Call <code>public.f_number</code>
	 */
	public static java.lang.Integer fNumber(org.jooq.Configuration configuration, java.lang.Integer n) {
		org.jooq.test.postgres.generatedclasses.routines.FNumber f = new org.jooq.test.postgres.generatedclasses.routines.FNumber();
		f.setN(n);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_number</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fNumber(java.lang.Integer n) {
		org.jooq.test.postgres.generatedclasses.routines.FNumber f = new org.jooq.test.postgres.generatedclasses.routines.FNumber();
		f.setN(n);

		return f.asField();
	}

	/**
	 * Get <code>public.f_number</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fNumber(org.jooq.Field<java.lang.Integer> n) {
		org.jooq.test.postgres.generatedclasses.routines.FNumber f = new org.jooq.test.postgres.generatedclasses.routines.FNumber();
		f.setN(n);

		return f.asField();
	}

	/**
	 * Call <code>public.f_one</code>
	 */
	public static java.lang.Integer fOne(org.jooq.Configuration configuration) {
		org.jooq.test.postgres.generatedclasses.routines.FOne f = new org.jooq.test.postgres.generatedclasses.routines.FOne();

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_one</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> fOne() {
		org.jooq.test.postgres.generatedclasses.routines.FOne f = new org.jooq.test.postgres.generatedclasses.routines.FOne();

		return f.asField();
	}

	/**
	 * Call <code>public.f_search_book</code>
	 */
	public static java.lang.Object fSearchBook(org.jooq.Configuration configuration, java.lang.String pTitle, java.lang.Long pLimit, java.lang.Long pOffset) {
		org.jooq.test.postgres.generatedclasses.routines.FSearchBook f = new org.jooq.test.postgres.generatedclasses.routines.FSearchBook();
		f.setPTitle(pTitle);
		f.setPLimit(pLimit);
		f.setPOffset(pOffset);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f_search_book</code> as a field
	 */
	public static org.jooq.Field<java.lang.Object> fSearchBook(java.lang.String pTitle, java.lang.Long pLimit, java.lang.Long pOffset) {
		org.jooq.test.postgres.generatedclasses.routines.FSearchBook f = new org.jooq.test.postgres.generatedclasses.routines.FSearchBook();
		f.setPTitle(pTitle);
		f.setPLimit(pLimit);
		f.setPOffset(pOffset);

		return f.asField();
	}

	/**
	 * Get <code>public.f_search_book</code> as a field
	 */
	public static org.jooq.Field<java.lang.Object> fSearchBook(org.jooq.Field<java.lang.String> pTitle, org.jooq.Field<java.lang.Long> pLimit, org.jooq.Field<java.lang.Long> pOffset) {
		org.jooq.test.postgres.generatedclasses.routines.FSearchBook f = new org.jooq.test.postgres.generatedclasses.routines.FSearchBook();
		f.setPTitle(pTitle);
		f.setPLimit(pLimit);
		f.setPOffset(pOffset);

		return f.asField();
	}

	/**
	 * Call <code>public.f317</code>
	 */
	public static java.lang.Integer f317(org.jooq.Configuration configuration, java.lang.Integer p1, java.lang.Integer p2, java.lang.Integer p3, java.lang.Integer p4) {
		org.jooq.test.postgres.generatedclasses.routines.F317 f = new org.jooq.test.postgres.generatedclasses.routines.F317();
		f.setP1(p1);
		f.setP2(p2);
		f.setP3(p3);
		f.setP4(p4);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.f317</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> f317(java.lang.Integer p1, java.lang.Integer p2, java.lang.Integer p3, java.lang.Integer p4) {
		org.jooq.test.postgres.generatedclasses.routines.F317 f = new org.jooq.test.postgres.generatedclasses.routines.F317();
		f.setP1(p1);
		f.setP2(p2);
		f.setP3(p3);
		f.setP4(p4);

		return f.asField();
	}

	/**
	 * Get <code>public.f317</code> as a field
	 */
	public static org.jooq.Field<java.lang.Integer> f317(org.jooq.Field<java.lang.Integer> p1, org.jooq.Field<java.lang.Integer> p2, org.jooq.Field<java.lang.Integer> p3, org.jooq.Field<java.lang.Integer> p4) {
		org.jooq.test.postgres.generatedclasses.routines.F317 f = new org.jooq.test.postgres.generatedclasses.routines.F317();
		f.setP1(p1);
		f.setP2(p2);
		f.setP3(p3);
		f.setP4(p4);

		return f.asField();
	}

	/**
	 * Call <code>public.p_arrays</code>
	 */
	public static java.lang.Integer[] pArrays1(org.jooq.Configuration configuration, java.lang.Integer[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.PArrays1 p = new org.jooq.test.postgres.generatedclasses.routines.PArrays1();
		p.setInArray(inArray);

		p.execute(configuration);
		return p.getOutArray();
	}

	/**
	 * Call <code>public.p_arrays</code>
	 */
	public static java.lang.Long[] pArrays2(org.jooq.Configuration configuration, java.lang.Long[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.PArrays2 p = new org.jooq.test.postgres.generatedclasses.routines.PArrays2();
		p.setInArray(inArray);

		p.execute(configuration);
		return p.getOutArray();
	}

	/**
	 * Call <code>public.p_arrays</code>
	 */
	public static java.lang.String[] pArrays3(org.jooq.Configuration configuration, java.lang.String[] inArray) {
		org.jooq.test.postgres.generatedclasses.routines.PArrays3 p = new org.jooq.test.postgres.generatedclasses.routines.PArrays3();
		p.setInArray(inArray);

		p.execute(configuration);
		return p.getOutArray();
	}

	/**
	 * Call <code>public.p_author_exists</code>
	 */
	public static java.lang.Integer pAuthorExists(org.jooq.Configuration configuration, java.lang.String authorName) {
		org.jooq.test.postgres.generatedclasses.routines.PAuthorExists p = new org.jooq.test.postgres.generatedclasses.routines.PAuthorExists();
		p.setAuthorName(authorName);

		p.execute(configuration);
		return p.getResult();
	}

	/**
	 * Call <code>public.p_create_author</code>
	 */
	public static void pCreateAuthor(org.jooq.Configuration configuration) {
		org.jooq.test.postgres.generatedclasses.routines.PCreateAuthor p = new org.jooq.test.postgres.generatedclasses.routines.PCreateAuthor();

		p.execute(configuration);
	}

	/**
	 * Call <code>public.p_create_author_by_name</code>
	 */
	public static void pCreateAuthorByName(org.jooq.Configuration configuration, java.lang.String firstName, java.lang.String lastName) {
		org.jooq.test.postgres.generatedclasses.routines.PCreateAuthorByName p = new org.jooq.test.postgres.generatedclasses.routines.PCreateAuthorByName();
		p.setFirstName(firstName);
		p.setLastName(lastName);

		p.execute(configuration);
	}

	/**
	 * Call <code>public.p_enhance_address1</code>
	 */
	public static java.lang.String pEnhanceAddress1(org.jooq.Configuration configuration, org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord address) {
		org.jooq.test.postgres.generatedclasses.routines.PEnhanceAddress1 p = new org.jooq.test.postgres.generatedclasses.routines.PEnhanceAddress1();
		p.setAddress(address);

		p.execute(configuration);
		return p.getNo();
	}

	/**
	 * Call <code>public.p_enhance_address2</code>
	 */
	public static org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord pEnhanceAddress2(org.jooq.Configuration configuration) {
		org.jooq.test.postgres.generatedclasses.routines.PEnhanceAddress2 p = new org.jooq.test.postgres.generatedclasses.routines.PEnhanceAddress2();

		p.execute(configuration);
		return p.getAddress();
	}

	/**
	 * Call <code>public.p_enhance_address3</code>
	 */
	public static org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord pEnhanceAddress3(org.jooq.Configuration configuration, org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord address) {
		org.jooq.test.postgres.generatedclasses.routines.PEnhanceAddress3 p = new org.jooq.test.postgres.generatedclasses.routines.PEnhanceAddress3();
		p.setAddress(address);

		p.execute(configuration);
		return p.getAddress();
	}

	/**
	 * Call <code>public.p_get_one_cursor</code>
	 */
	public static org.jooq.test.postgres.generatedclasses.routines.PGetOneCursor pGetOneCursor(org.jooq.Configuration configuration, java.lang.Integer[] bookIds) {
		org.jooq.test.postgres.generatedclasses.routines.PGetOneCursor p = new org.jooq.test.postgres.generatedclasses.routines.PGetOneCursor();
		p.setBookIds(bookIds);

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>public.p_get_two_cursors</code>
	 */
	public static org.jooq.test.postgres.generatedclasses.routines.PGetTwoCursors pGetTwoCursors(org.jooq.Configuration configuration) {
		org.jooq.test.postgres.generatedclasses.routines.PGetTwoCursors p = new org.jooq.test.postgres.generatedclasses.routines.PGetTwoCursors();

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>public.p_triggers</code>
	 */
	public static java.lang.Object pTriggers(org.jooq.Configuration configuration) {
		org.jooq.test.postgres.generatedclasses.routines.PTriggers f = new org.jooq.test.postgres.generatedclasses.routines.PTriggers();

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>public.p_triggers</code> as a field
	 */
	public static org.jooq.Field<java.lang.Object> pTriggers() {
		org.jooq.test.postgres.generatedclasses.routines.PTriggers f = new org.jooq.test.postgres.generatedclasses.routines.PTriggers();

		return f.asField();
	}

	/**
	 * Call <code>public.p_unused</code>
	 */
	public static org.jooq.test.postgres.generatedclasses.routines.PUnused pUnused(org.jooq.Configuration configuration, java.lang.String in1, java.lang.Integer out2) {
		org.jooq.test.postgres.generatedclasses.routines.PUnused p = new org.jooq.test.postgres.generatedclasses.routines.PUnused();
		p.setIn1(in1);
		p.setOut2(out2);

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>public.p391</code>
	 */
	public static org.jooq.test.postgres.generatedclasses.routines.P391 p391(org.jooq.Configuration configuration, java.lang.Integer i1, java.lang.Integer io1, java.lang.Integer io2, java.lang.Integer i2) {
		org.jooq.test.postgres.generatedclasses.routines.P391 p = new org.jooq.test.postgres.generatedclasses.routines.P391();
		p.setI1(i1);
		p.setIo1(io1);
		p.setIo2(io2);
		p.setI2(i2);

		p.execute(configuration);
		return p;
	}
}
