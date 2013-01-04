/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class T_785 extends org.jooq.impl.TableImpl<org.jooq.test.firebird.generatedclasses.tables.records.T_785Record> {

	private static final long serialVersionUID = -1271859848;

	/**
	 * The singleton instance of <code>T_785</code>
	 */
	public static final org.jooq.test.firebird.generatedclasses.tables.T_785 T_785 = new org.jooq.test.firebird.generatedclasses.tables.T_785();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.firebird.generatedclasses.tables.records.T_785Record> getRecordType() {
		return org.jooq.test.firebird.generatedclasses.tables.records.T_785Record.class;
	}

	/**
	 * The column <code>T_785.ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.T_785Record, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>T_785.NAME</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.T_785Record, java.lang.String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), this);

	/**
	 * The column <code>T_785.VALUE</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.T_785Record, java.lang.String> VALUE = createField("VALUE", org.jooq.impl.SQLDataType.VARCHAR.length(50), this);

	/**
	 * Create a <code>T_785</code> table reference
	 */
	public T_785() {
		super("T_785", org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * Create an aliased <code>T_785</code> table reference
	 */
	public T_785(java.lang.String alias) {
		super(alias, org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.firebird.generatedclasses.tables.T_785.T_785);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.firebird.generatedclasses.tables.T_785 as(java.lang.String alias) {
		return new org.jooq.test.firebird.generatedclasses.tables.T_785(alias);
	}
}
