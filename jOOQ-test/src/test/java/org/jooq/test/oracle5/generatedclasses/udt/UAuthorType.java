/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.udt;

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
public class UAuthorType extends org.jooq.impl.UDTImpl<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord> implements org.jooq.Package {

	private static final long serialVersionUID = -186102785;

	/**
	 * The singleton instance of <code>TEST.U_AUTHOR_TYPE</code>
	 */
	public static final org.jooq.test.oracle5.generatedclasses.udt.UAuthorType U_AUTHOR_TYPE = new org.jooq.test.oracle5.generatedclasses.udt.UAuthorType();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord> getRecordType() {
		return org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord.class;
	}

	/**
	 * The attribute <code>TEST.U_AUTHOR_TYPE.ID</code>.
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord, java.math.BigDecimal> ID = createField("ID", org.jooq.impl.SQLDataType.NUMERIC, U_AUTHOR_TYPE);

	/**
	 * The attribute <code>TEST.U_AUTHOR_TYPE.FIRST_NAME</code>.
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord, java.lang.String> FIRST_NAME = createField("FIRST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), U_AUTHOR_TYPE);

	/**
	 * The attribute <code>TEST.U_AUTHOR_TYPE.LAST_NAME</code>.
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord, java.lang.String> LAST_NAME = createField("LAST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), U_AUTHOR_TYPE);

	/**
	 * Call <code>TEST.U_AUTHOR_TYPE.COUNT_BOOKS</code>
	 */
	public static java.math.BigDecimal countBooks(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord self) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.CountBooks f = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.CountBooks();
		f.setSelf(self);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.U_AUTHOR_TYPE.COUNT_BOOKS</code> as a field
	 */
	public static org.jooq.Field<java.math.BigDecimal> countBooks(org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord self) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.CountBooks f = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.CountBooks();
		f.setSelf(self);

		return f.asField();
	}

	/**
	 * Get <code>TEST.U_AUTHOR_TYPE.COUNT_BOOKS</code> as a field
	 */
	public static org.jooq.Field<java.math.BigDecimal> countBooks(org.jooq.Field<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord> self) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.CountBooks f = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.CountBooks();
		f.setSelf(self);

		return f.asField();
	}

	/**
	 * Call <code>TEST.U_AUTHOR_TYPE.GET_AUTHOR</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord getAuthor(org.jooq.Configuration configuration, java.math.BigDecimal pId) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetAuthor f = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetAuthor();
		f.setPId(pId);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>TEST.U_AUTHOR_TYPE.GET_AUTHOR</code> as a field
	 */
	public static org.jooq.Field<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord> getAuthor(java.math.BigDecimal pId) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetAuthor f = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetAuthor();
		f.setPId(pId);

		return f.asField();
	}

	/**
	 * Get <code>TEST.U_AUTHOR_TYPE.GET_AUTHOR</code> as a field
	 */
	public static org.jooq.Field<org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord> getAuthor(org.jooq.Field<java.math.BigDecimal> pId) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetAuthor f = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetAuthor();
		f.setPId(pId);

		return f.asField();
	}

	/**
	 * Call <code>TEST.U_AUTHOR_TYPE.GET_BOOKS</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetBooks getBooks(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord self) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetBooks p = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.GetBooks();
		p.setSelf(self);

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>TEST.U_AUTHOR_TYPE.LOAD</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord load(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord self) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.Load p = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.Load();
		p.setSelf(self);

		p.execute(configuration);
		return p.getSelf();
	}

	/**
	 * Call <code>TEST.U_AUTHOR_TYPE.NEW_AUTHOR</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.records.UAuthorTypeRecord newAuthor(org.jooq.Configuration configuration, java.math.BigDecimal pId, java.lang.String pFirstName, java.lang.String pLastName) {
		org.jooq.test.oracle5.generatedclasses.udt.u_author_type.NewAuthor p = new org.jooq.test.oracle5.generatedclasses.udt.u_author_type.NewAuthor();
		p.setPId(pId);
		p.setPFirstName(pFirstName);
		p.setPLastName(pLastName);

		p.execute(configuration);
		return p.getPAuthor();
	}

	/**
	 * No further instances allowed
	 */
	private UAuthorType() {
		super("U_AUTHOR_TYPE", org.jooq.test.oracle5.generatedclasses.Test.TEST);

		// Initialise data type
		getDataType();
	}
}
