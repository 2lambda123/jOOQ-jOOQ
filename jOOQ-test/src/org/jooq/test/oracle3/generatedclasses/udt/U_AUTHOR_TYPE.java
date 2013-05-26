/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.udt;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class U_AUTHOR_TYPE extends org.jooq.impl.UDTImpl<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE> implements java.lang.Cloneable, org.jooq.Package {

	private static final long serialVersionUID = -1777013013;

	/**
	 * The singleton instance of <code>U_AUTHOR_TYPE</code>
	 */
	public static final org.jooq.test.oracle3.generatedclasses.udt.U_AUTHOR_TYPE U_AUTHOR_TYPE = new org.jooq.test.oracle3.generatedclasses.udt.U_AUTHOR_TYPE();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE> getRecordType() {
		return org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE.class;
	}

	/**
	 * The attribute <code>U_AUTHOR_TYPE.ID</code>. 
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, U_AUTHOR_TYPE);

	/**
	 * The attribute <code>U_AUTHOR_TYPE.FIRST_NAME</code>. 
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE, java.lang.String> FIRST_NAME = createField("FIRST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), U_AUTHOR_TYPE);

	/**
	 * The attribute <code>U_AUTHOR_TYPE.LAST_NAME</code>. 
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE, java.lang.String> LAST_NAME = createField("LAST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), U_AUTHOR_TYPE);

	/**
	 * Call <code>U_AUTHOR_TYPE.COUNT_BOOKS</code>
	 */
	public static java.math.BigDecimal call_COUNT_BOOKS(org.jooq.Configuration configuration, org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE SELF) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.COUNT_BOOKS f = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.COUNT_BOOKS();
		f.setSELF(SELF);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>U_AUTHOR_TYPE.COUNT_BOOKS</code> as a field
	 */
	public static org.jooq.Field<java.math.BigDecimal> call_COUNT_BOOKS(org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE SELF) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.COUNT_BOOKS f = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.COUNT_BOOKS();
		f.setSELF(SELF);

		return f.asField();
	}

	/**
	 * Get <code>U_AUTHOR_TYPE.COUNT_BOOKS</code> as a field
	 */
	public static org.jooq.Field<java.math.BigDecimal> call_COUNT_BOOKS(org.jooq.Field<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE> SELF) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.COUNT_BOOKS f = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.COUNT_BOOKS();
		f.setSELF(SELF);

		return f.asField();
	}

	/**
	 * Call <code>U_AUTHOR_TYPE.GET_AUTHOR</code>
	 */
	public static org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE call_GET_AUTHOR(org.jooq.Configuration configuration, java.lang.Number P_ID) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_AUTHOR f = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_AUTHOR();
		f.setP_ID(P_ID);

		f.execute(configuration);
		return f.getReturnValue();
	}

	/**
	 * Get <code>U_AUTHOR_TYPE.GET_AUTHOR</code> as a field
	 */
	public static org.jooq.Field<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE> call_GET_AUTHOR(java.lang.Number P_ID) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_AUTHOR f = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_AUTHOR();
		f.setP_ID(P_ID);

		return f.asField();
	}

	/**
	 * Get <code>U_AUTHOR_TYPE.GET_AUTHOR</code> as a field
	 */
	public static org.jooq.Field<org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE> call_GET_AUTHOR(org.jooq.Field<? extends java.lang.Number> P_ID) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_AUTHOR f = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_AUTHOR();
		f.setP_ID(P_ID);

		return f.asField();
	}

	/**
	 * Call <code>U_AUTHOR_TYPE.GET_BOOKS</code>
	 */
	public static org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_BOOKS call_GET_BOOKS(org.jooq.Configuration configuration, org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE SELF) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_BOOKS p = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.GET_BOOKS();
		p.setSELF(SELF);

		p.execute(configuration);
		return p;
	}

	/**
	 * Call <code>U_AUTHOR_TYPE.LOAD</code>
	 */
	public static org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE call_LOAD(org.jooq.Configuration configuration, org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE SELF) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.LOAD p = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.LOAD();
		p.setSELF(SELF);

		p.execute(configuration);
		return p.getSELF();
	}

	/**
	 * Call <code>U_AUTHOR_TYPE.NEW_AUTHOR</code>
	 */
	public static org.jooq.test.oracle3.generatedclasses.udt.records.U_AUTHOR_TYPE call_NEW_AUTHOR(org.jooq.Configuration configuration, java.lang.Number P_ID, java.lang.String P_FIRST_NAME, java.lang.String P_LAST_NAME) {
		org.jooq.test.oracle3.generatedclasses.udt.u_author_type.NEW_AUTHOR p = new org.jooq.test.oracle3.generatedclasses.udt.u_author_type.NEW_AUTHOR();
		p.setP_ID(P_ID);
		p.setP_FIRST_NAME(P_FIRST_NAME);
		p.setP_LAST_NAME(P_LAST_NAME);

		p.execute(configuration);
		return p.getP_AUTHOR();
	}

	/**
	 * No further instances allowed
	 */
	private U_AUTHOR_TYPE() {
		super("U_AUTHOR_TYPE", org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);

		// Initialise data type
		getDataType();
	}
}
