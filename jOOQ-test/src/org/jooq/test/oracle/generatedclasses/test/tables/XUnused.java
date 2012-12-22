/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables;

/**
 * This class is generated by jOOQ.
 *
 * An unused table in the same schema.
 */
@java.lang.SuppressWarnings("all")
public class XUnused extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord> {

	private static final long serialVersionUID = -678837737;

	/**
	 * The singleton instance of <code>TEST.X_UNUSED</code>
	 */
	public static final org.jooq.test.oracle.generatedclasses.test.tables.XUnused X_UNUSED = new org.jooq.test.oracle.generatedclasses.test.tables.XUnused();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord> getRecordType() {
		return org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord.class;
	}

	/**
	 * The column <code>TEST.X_UNUSED.ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.NAME</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(10), this);

	/**
	 * The column <code>TEST.X_UNUSED.BIG_INTEGER</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.math.BigInteger> BIG_INTEGER = createField("BIG_INTEGER", org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38), this);

	/**
	 * The column <code>TEST.X_UNUSED.ID_REF</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> ID_REF = createField("ID_REF", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.CLASS</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> CLASS = createField("CLASS", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.FIELDS</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> FIELDS = createField("FIELDS", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.CONFIGURATION</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> CONFIGURATION = createField("CONFIGURATION", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.U_D_T</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> U_D_T = createField("U_D_T", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.META_DATA</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> META_DATA = createField("META_DATA", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.TYPE0</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> TYPE0 = createField("TYPE0", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.PRIMARY_KEY</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> PRIMARY_KEY = createField("PRIMARY_KEY", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.PRIMARYKEY</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> PRIMARYKEY = createField("PRIMARYKEY", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.NAME_REF</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.String> NAME_REF = createField("NAME_REF", org.jooq.impl.SQLDataType.VARCHAR.length(10), this);

	/**
	 * The column <code>TEST.X_UNUSED.FIELD 737</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.math.BigDecimal> FIELD_737 = createField("FIELD 737", org.jooq.impl.SQLDataType.NUMERIC.precision(25, 2), this);

	/**
	 * The column <code>TEST.X_UNUSED.MS_UNUSED_ID_REF</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.Integer> MS_UNUSED_ID_REF = createField("MS_UNUSED_ID_REF", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>TEST.X_UNUSED.MS_UNUSED_NAME_REF</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, java.lang.String> MS_UNUSED_NAME_REF = createField("MS_UNUSED_NAME_REF", org.jooq.impl.SQLDataType.VARCHAR.length(10), this);

	/**
	 * Create a <code>TEST.X_UNUSED</code> table reference
	 */
	public XUnused() {
		super("X_UNUSED", org.jooq.test.oracle.generatedclasses.test.Test.TEST);
	}

	/**
	 * Create an aliased <code>TEST.X_UNUSED</code> table reference
	 */
	public XUnused(java.lang.String alias) {
		super(alias, org.jooq.test.oracle.generatedclasses.test.Test.TEST, org.jooq.test.oracle.generatedclasses.test.tables.XUnused.X_UNUSED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord> getMainKey() {
		return org.jooq.test.oracle.generatedclasses.test.Keys.PK_X_UNUSED;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord>>asList(org.jooq.test.oracle.generatedclasses.test.Keys.PK_X_UNUSED, org.jooq.test.oracle.generatedclasses.test.Keys.UK_X_UNUSED_ID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.oracle.generatedclasses.test.tables.records.XUnusedRecord, ?>>asList(org.jooq.test.oracle.generatedclasses.test.Keys.FK_X_UNUSED_SELF);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.oracle.generatedclasses.test.tables.XUnused as(java.lang.String alias) {
		return new org.jooq.test.oracle.generatedclasses.test.tables.XUnused(alias);
	}
}
