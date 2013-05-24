/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VLibrary extends org.jooq.impl.TableImpl<org.jooq.test.firebird.generatedclasses.tables.records.VLibraryRecord> {

	private static final long serialVersionUID = -2038278498;

	/**
	 * The singleton instance of <code>V_LIBRARY</code>
	 */
	public static final org.jooq.test.firebird.generatedclasses.tables.VLibrary V_LIBRARY = new org.jooq.test.firebird.generatedclasses.tables.VLibrary();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.firebird.generatedclasses.tables.records.VLibraryRecord> getRecordType() {
		return org.jooq.test.firebird.generatedclasses.tables.records.VLibraryRecord.class;
	}

	/**
	 * The column <code>V_LIBRARY.AUTHOR</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.VLibraryRecord, java.lang.String> AUTHOR = createField("AUTHOR", org.jooq.impl.SQLDataType.VARCHAR.length(101), this);

	/**
	 * The column <code>V_LIBRARY.TITLE</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.firebird.generatedclasses.tables.records.VLibraryRecord, java.lang.String> TITLE = createField("TITLE", org.jooq.impl.SQLDataType.VARCHAR.length(400), this);

	/**
	 * Create a <code>V_LIBRARY</code> table reference
	 */
	public VLibrary() {
		super("V_LIBRARY", org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}

	/**
	 * Create an aliased <code>V_LIBRARY</code> table reference
	 */
	public VLibrary(java.lang.String alias) {
		super(alias, org.jooq.test.firebird.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, org.jooq.test.firebird.generatedclasses.tables.VLibrary.V_LIBRARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.firebird.generatedclasses.tables.VLibrary as(java.lang.String alias) {
		return new org.jooq.test.firebird.generatedclasses.tables.VLibrary(alias);
	}
}
