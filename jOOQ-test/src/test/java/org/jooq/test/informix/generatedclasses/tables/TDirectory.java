/**
 * This class is generated by jOOQ
 */
package org.jooq.test.informix.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TDirectory extends org.jooq.impl.TableImpl<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord> {

	private static final long serialVersionUID = -2012333352;

	/**
	 * The singleton instance of <code>informix.t_directory</code>
	 */
	public static final org.jooq.test.informix.generatedclasses.tables.TDirectory T_DIRECTORY = new org.jooq.test.informix.generatedclasses.tables.TDirectory();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord> getRecordType() {
		return org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord.class;
	}

	/**
	 * The column <code>informix.t_directory.id</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>informix.t_directory.parent_id</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord, java.lang.Integer> PARENT_ID = createField("parent_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>informix.t_directory.is_directory</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord, java.lang.Integer> IS_DIRECTORY = createField("is_directory", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>informix.t_directory.name</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * Create a <code>informix.t_directory</code> table reference
	 */
	public TDirectory() {
		this("t_directory", null);
	}

	/**
	 * Create an aliased <code>informix.t_directory</code> table reference
	 */
	public TDirectory(java.lang.String alias) {
		this(alias, org.jooq.test.informix.generatedclasses.tables.TDirectory.T_DIRECTORY);
	}

	private TDirectory(java.lang.String alias, org.jooq.Table<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord> aliased) {
		this(alias, aliased, null);
	}

	private TDirectory(java.lang.String alias, org.jooq.Table<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.informix.generatedclasses.Informix.INFORMIX, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord> getPrimaryKey() {
		return org.jooq.test.informix.generatedclasses.Keys.PK_T_DIRECTORY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord>>asList(org.jooq.test.informix.generatedclasses.Keys.PK_T_DIRECTORY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.ForeignKey<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord, ?>> getReferences() {
		return java.util.Arrays.<org.jooq.ForeignKey<org.jooq.test.informix.generatedclasses.tables.records.TDirectoryRecord, ?>>asList(org.jooq.test.informix.generatedclasses.Keys.R19136_20535);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.informix.generatedclasses.tables.TDirectory as(java.lang.String alias) {
		return new org.jooq.test.informix.generatedclasses.tables.TDirectory(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.informix.generatedclasses.tables.TDirectory rename(java.lang.String name) {
		return new org.jooq.test.informix.generatedclasses.tables.TDirectory(name, null);
	}
}
