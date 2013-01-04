/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlite.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class VLibrary extends org.jooq.impl.TableImpl<org.jooq.test.sqlite.generatedclasses.tables.records.VLibraryRecord> {

	private static final long serialVersionUID = 750597272;

	/**
	 * The singleton instance of <code>V_LIBRARY</code>
	 */
	public static final org.jooq.test.sqlite.generatedclasses.tables.VLibrary V_LIBRARY = new org.jooq.test.sqlite.generatedclasses.tables.VLibrary();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.sqlite.generatedclasses.tables.records.VLibraryRecord> getRecordType() {
		return org.jooq.test.sqlite.generatedclasses.tables.records.VLibraryRecord.class;
	}

	/**
	 * The column <code>V_LIBRARY.AUTHOR</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VLibraryRecord, java.lang.String> AUTHOR = createField("AUTHOR", org.jooq.impl.SQLDataType.VARCHAR.length(50), V_LIBRARY);

	/**
	 * The column <code>V_LIBRARY.TITLE</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VLibraryRecord, java.lang.String> TITLE = createField("TITLE", org.jooq.impl.SQLDataType.VARCHAR.length(400), V_LIBRARY);

	/**
	 * No further instances allowed
	 */
	private VLibrary() {
		super("V_LIBRARY", org.jooq.test.sqlite.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}
}
