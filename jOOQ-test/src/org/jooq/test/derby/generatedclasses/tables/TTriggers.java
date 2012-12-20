/**
 * This class is generated by jOOQ
 */
package org.jooq.test.derby.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TTriggers extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord> {

	private static final long serialVersionUID = 1320733358;

	/**
	 * The singleton instance of <code>TEST.T_TRIGGERS</code>
	 */
	public static final org.jooq.test.derby.generatedclasses.tables.TTriggers T_TRIGGERS = new org.jooq.test.derby.generatedclasses.tables.TTriggers();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord> getRecordType() {
		return org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord.class;
	}

	/**
	 * The column <code>TEST.T_TRIGGERS.ID_GENERATED</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord, java.lang.Integer> ID_GENERATED = createField("ID_GENERATED", org.jooq.impl.SQLDataType.INTEGER, T_TRIGGERS);

	/**
	 * The column <code>TEST.T_TRIGGERS.ID</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, T_TRIGGERS);

	/**
	 * The column <code>TEST.T_TRIGGERS.COUNTER</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord, java.lang.Integer> COUNTER = createField("COUNTER", org.jooq.impl.SQLDataType.INTEGER, T_TRIGGERS);

	/**
	 * No further instances allowed
	 */
	private TTriggers() {
		super("T_TRIGGERS", org.jooq.test.derby.generatedclasses.Test.TEST);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord, java.lang.Integer> getIdentity() {
		return org.jooq.test.derby.generatedclasses.Keys.IDENTITY_T_TRIGGERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord> getMainKey() {
		return org.jooq.test.derby.generatedclasses.Keys.PK_T_TRIGGERS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.derby.generatedclasses.tables.records.TTriggersRecord>>asList(org.jooq.test.derby.generatedclasses.Keys.PK_T_TRIGGERS);
	}
}
