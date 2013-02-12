/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class T_DIRECTORY extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY> implements java.lang.Cloneable {

	private static final long serialVersionUID = 722346098;

	/**
	 * The singleton instance of <code>T_DIRECTORY</code>
	 */
	public static final org.jooq.test.oracle3.generatedclasses.tables.T_DIRECTORY T_DIRECTORY = new org.jooq.test.oracle3.generatedclasses.tables.T_DIRECTORY();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY> getRecordType() {
		return org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY.class;
	}

	/**
	 * The column <code>T_DIRECTORY.ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>T_DIRECTORY.PARENT_ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY, java.lang.Integer> PARENT_ID = createField("PARENT_ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>T_DIRECTORY.IS_DIRECTORY</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY, java.lang.Integer> IS_DIRECTORY = createField("IS_DIRECTORY", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>T_DIRECTORY.name</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY, java.lang.String> name = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(50), this);

	/**
	 * Create a <code>T_DIRECTORY</code> table reference
	 */
	public T_DIRECTORY() {
		super("T_DIRECTORY", org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * Create an aliased <code>T_DIRECTORY</code> table reference
	 */
	public T_DIRECTORY(java.lang.String alias) {
		super(alias, org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.oracle3.generatedclasses.tables.T_DIRECTORY.T_DIRECTORY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY> getPrimaryKey() {
		return org.jooq.test.oracle3.generatedclasses.Keys.PK_T_DIRECTORY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY>>asList(org.jooq.test.oracle3.generatedclasses.Keys.PK_T_DIRECTORY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.oracle3.generatedclasses.tables.records.T_DIRECTORY, ?>>asList(org.jooq.test.oracle3.generatedclasses.Keys.PK_T_DIRECTORY_SELF);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.oracle3.generatedclasses.tables.T_DIRECTORY as(java.lang.String alias) {
		return new org.jooq.test.oracle3.generatedclasses.tables.T_DIRECTORY(alias);
	}
}
