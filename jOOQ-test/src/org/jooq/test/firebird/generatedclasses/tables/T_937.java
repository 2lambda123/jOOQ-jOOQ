/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class T_937 extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.firebird.generatedclasses.tables.records.T_937Record> {

	private static final long serialVersionUID = -1632357368;

	/**
	 * The singleton instance of <code>T_937</code>
	 */
	public static final org.jooq.test.firebird.generatedclasses.tables.T_937 T_937 = new org.jooq.test.firebird.generatedclasses.tables.T_937();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.firebird.generatedclasses.tables.records.T_937Record> getRecordType() {
		return org.jooq.test.firebird.generatedclasses.tables.records.T_937Record.class;
	}

	/**
	 * The column <code>T_937.T_937</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.T_937Record, java.lang.Integer> T_937_ = createField("T_937", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * Create a <code>T_937</code> table reference
	 */
	public T_937() {
		super("T_937", org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * Create an aliased <code>T_937</code> table reference
	 */
	public T_937(java.lang.String alias) {
		super(alias, org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.firebird.generatedclasses.tables.T_937.T_937);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.firebird.generatedclasses.tables.records.T_937Record> getMainKey() {
		return org.jooq.test.firebird.generatedclasses.Keys.T_937;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.firebird.generatedclasses.tables.records.T_937Record>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.firebird.generatedclasses.tables.records.T_937Record>>asList(org.jooq.test.firebird.generatedclasses.Keys.T_937);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.firebird.generatedclasses.tables.T_937 as(java.lang.String alias) {
		return new org.jooq.test.firebird.generatedclasses.tables.T_937(alias);
	}
}
