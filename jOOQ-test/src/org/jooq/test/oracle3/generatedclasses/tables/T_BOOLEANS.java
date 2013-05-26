/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_BOOLEANS extends org.jooq.impl.TableImpl<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS> implements java.lang.Cloneable {

	private static final long serialVersionUID = 1095291012;

	/**
	 * The singleton instance of <code>T_BOOLEANS</code>
	 */
	public static final org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS T_BOOLEANS = new org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS> getRecordType() {
		return org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS.class;
	}

	/**
	 * The column <code>T_BOOLEANS.ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>T_BOOLEANS.ONE_ZERO</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.Integer> ONE_ZERO = createField("ONE_ZERO", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>T_BOOLEANS.TRUE_FALSE_LC</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> TRUE_FALSE_LC = createField("TRUE_FALSE_LC", org.jooq.impl.SQLDataType.VARCHAR.length(5), this);

	/**
	 * The column <code>T_BOOLEANS.TRUE_FALSE_UC</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> TRUE_FALSE_UC = createField("TRUE_FALSE_UC", org.jooq.impl.SQLDataType.VARCHAR.length(5), this);

	/**
	 * The column <code>T_BOOLEANS.YES_NO_LC</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> YES_NO_LC = createField("YES_NO_LC", org.jooq.impl.SQLDataType.VARCHAR.length(3), this);

	/**
	 * The column <code>T_BOOLEANS.YES_NO_UC</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> YES_NO_UC = createField("YES_NO_UC", org.jooq.impl.SQLDataType.VARCHAR.length(3), this);

	/**
	 * The column <code>T_BOOLEANS.Y_N_LC</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> Y_N_LC = createField("Y_N_LC", org.jooq.impl.SQLDataType.CHAR.length(1), this);

	/**
	 * The column <code>T_BOOLEANS.Y_N_UC</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> Y_N_UC = createField("Y_N_UC", org.jooq.impl.SQLDataType.CHAR.length(1), this);

	/**
	 * The column <code>T_BOOLEANS.VC_BOOLEAN</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> VC_BOOLEAN = createField("VC_BOOLEAN", org.jooq.impl.SQLDataType.VARCHAR.length(1), this);

	/**
	 * The column <code>T_BOOLEANS.C_BOOLEAN</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.String> C_BOOLEAN = createField("C_BOOLEAN", org.jooq.impl.SQLDataType.CHAR.length(1), this);

	/**
	 * The column <code>T_BOOLEANS.N_BOOLEAN</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS, java.lang.Integer> N_BOOLEAN = createField("N_BOOLEAN", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * Create a <code>T_BOOLEANS</code> table reference
	 */
	public T_BOOLEANS() {
		super("T_BOOLEANS", org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * Create an aliased <code>T_BOOLEANS</code> table reference
	 */
	public T_BOOLEANS(java.lang.String alias) {
		super(alias, org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS.T_BOOLEANS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS> getPrimaryKey() {
		return org.jooq.test.oracle3.generatedclasses.Keys.PK_T_BOOLEANS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_BOOLEANS>>asList(org.jooq.test.oracle3.generatedclasses.Keys.PK_T_BOOLEANS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS as(java.lang.String alias) {
		return new org.jooq.test.oracle3.generatedclasses.tables.T_BOOLEANS(alias);
	}
}
