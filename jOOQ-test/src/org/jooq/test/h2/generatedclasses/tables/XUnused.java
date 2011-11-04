/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.0"},
                            comments = "This class is generated by jOOQ")
public class XUnused extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord> {

	private static final long serialVersionUID = -1129210942;

	/**
	 * The singleton instance of X_UNUSED
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.XUnused X_UNUSED = new org.jooq.test.h2.generatedclasses.tables.XUnused();

	/**
	 * The class holding records for this type
	 */
	private static final java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord> __RECORD_TYPE = org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord.class;

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord> getRecordType() {
		return __RECORD_TYPE;
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> ID = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("ID", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.String> NAME = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.String>("NAME", org.jooq.impl.SQLDataType.VARCHAR, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.math.BigInteger> BIG_INTEGER = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.math.BigInteger>("BIG_INTEGER", org.jooq.impl.SQLDataType.DECIMAL_INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [PUBLIC.X_UNUSED.ID_REF, PUBLIC.X_UNUSED.ID_REF, PUBLIC.X_UNUSED.NAME_REF, PUBLIC.X_UNUSED.NAME_REF]
	 * REFERENCES X_UNUSED [PUBLIC.X_UNUSED.ID, PUBLIC.X_UNUSED.NAME]
	 * </pre></code>
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> ID_REF = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("ID_REF", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [PUBLIC.X_UNUSED.ID_REF, PUBLIC.X_UNUSED.ID_REF, PUBLIC.X_UNUSED.NAME_REF, PUBLIC.X_UNUSED.NAME_REF]
	 * REFERENCES X_UNUSED [PUBLIC.X_UNUSED.ID, PUBLIC.X_UNUSED.NAME]
	 * </pre></code>
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.String> NAME_REF = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.String>("NAME_REF", org.jooq.impl.SQLDataType.VARCHAR, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> CLASS = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("CLASS", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> FIELDS = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("FIELDS", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> TABLE = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("TABLE", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> CONFIGURATION = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("CONFIGURATION", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> U_D_T = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("U_D_T", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> META_DATA = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("META_DATA", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> VALUES = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("VALUES", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> TYPE0 = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("TYPE0", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> PRIMARY_KEY = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("PRIMARY_KEY", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer> PRIMARYKEY = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.lang.Integer>("PRIMARYKEY", org.jooq.impl.SQLDataType.INTEGER, X_UNUSED);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.math.BigDecimal> FIELD_737 = new org.jooq.impl.TableFieldImpl<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, java.math.BigDecimal>("FIELD 737", org.jooq.impl.SQLDataType.DECIMAL, X_UNUSED);

	/**
	 * No further instances allowed
	 */
	private XUnused() {
		super("X_UNUSED", org.jooq.test.h2.generatedclasses.Public.PUBLIC);
	}

	@Override
	public org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord> getMainKey() {
		return org.jooq.test.h2.generatedclasses.Keys.PK_X_UNUSED;
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord>>asList(org.jooq.test.h2.generatedclasses.Keys.PK_X_UNUSED, org.jooq.test.h2.generatedclasses.Keys.UK_X_UNUSED_ID);
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.h2.generatedclasses.tables.records.XUnusedRecord, ?>>asList(org.jooq.test.h2.generatedclasses.Keys.FK_X_UNUSED_SELF);
	}
}
