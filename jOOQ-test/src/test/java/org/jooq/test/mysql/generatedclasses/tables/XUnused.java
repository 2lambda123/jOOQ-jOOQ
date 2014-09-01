/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mysql.generatedclasses.tables;

/**
 * An unused table in the same schema.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XUnused extends org.jooq.impl.TableImpl<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord> {

	private static final long serialVersionUID = -2077503303;

	/**
	 * The singleton instance of <code>test.x_unused</code>
	 */
	public static final org.jooq.test.mysql.generatedclasses.tables.XUnused X_UNUSED = new org.jooq.test.mysql.generatedclasses.tables.XUnused();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord> getRecordType() {
		return org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord.class;
	}

	/**
	 * The column <code>test.x_unused.ID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.NAME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.BIG_INTEGER</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.math.BigInteger> BIG_INTEGER = createField("BIG_INTEGER", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(25), X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.ID_REF</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> ID_REF = createField("ID_REF", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.NAME_REF</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.String> NAME_REF = createField("NAME_REF", org.jooq.impl.SQLDataType.VARCHAR.length(10), X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.CLASS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> CLASS = createField("CLASS", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.FIELDS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> FIELDS = createField("FIELDS", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.CONFIGURATION</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> CONFIGURATION = createField("CONFIGURATION", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.U_D_T</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> U_D_T = createField("U_D_T", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.META_DATA</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> META_DATA = createField("META_DATA", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.TYPE0</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> TYPE0 = createField("TYPE0", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.PRIMARY_KEY</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> PRIMARY_KEY = createField("PRIMARY_KEY", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.PRIMARYKEY</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> PRIMARYKEY = createField("PRIMARYKEY", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED, "");

	/**
	 * The column <code>test.x_unused.FIELD 737</code>.
	 */
	public static final org.jooq.TableField<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, java.math.BigDecimal> FIELD_737 = createField("FIELD 737", org.jooq.impl.SQLDataType.DECIMAL.precision(25, 2), X_UNUSED, "");

	/**
	 * No further instances allowed
	 */
	private XUnused() {
		this("x_unused", null);
	}

	private XUnused(java.lang.String alias, org.jooq.Table<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord> aliased) {
		this(alias, aliased, null);
	}

	private XUnused(java.lang.String alias, org.jooq.Table<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.mysql.generatedclasses.Test.TEST, aliased, parameters, "An unused table in the same schema.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord> getPrimaryKey() {
		return org.jooq.test.mysql.generatedclasses.Keys.KEY_X_UNUSED_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord>>asList(org.jooq.test.mysql.generatedclasses.Keys.KEY_X_UNUSED_PRIMARY, org.jooq.test.mysql.generatedclasses.Keys.KEY_X_UNUSED_UK_X_UNUSED_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.mysql.generatedclasses.tables.records.XUnusedRecord, ?>>asList(org.jooq.test.mysql.generatedclasses.Keys.FK_X_UNUSED_SELF);
	}
}
