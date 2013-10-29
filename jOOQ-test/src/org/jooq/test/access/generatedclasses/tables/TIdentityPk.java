/**
 * This class is generated by jOOQ
 */
package org.jooq.test.access.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TIdentityPk extends org.jooq.impl.TableImpl<org.jooq.test.access.generatedclasses.tables.records.TIdentityPkRecord> {

	private static final long serialVersionUID = 434205291;

	/**
	 * The singleton instance of <code>t_identity_pk</code>
	 */
	public static final org.jooq.test.access.generatedclasses.tables.TIdentityPk T_IDENTITY_PK = new org.jooq.test.access.generatedclasses.tables.TIdentityPk();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.access.generatedclasses.tables.records.TIdentityPkRecord> getRecordType() {
		return org.jooq.test.access.generatedclasses.tables.records.TIdentityPkRecord.class;
	}

	/**
	 * The column <code>t_identity_pk.id</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.TIdentityPkRecord, java.lang.Object> ID = createField("id", org.jooq.impl.SQLDataType.OTHER, T_IDENTITY_PK);

	/**
	 * The column <code>t_identity_pk.val</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.TIdentityPkRecord, java.lang.Integer> VAL = createField("val", org.jooq.impl.SQLDataType.INTEGER, T_IDENTITY_PK);

	/**
	 * No further instances allowed
	 */
	private TIdentityPk() {
		super("t_identity_pk", org.jooq.test.access.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}
}
