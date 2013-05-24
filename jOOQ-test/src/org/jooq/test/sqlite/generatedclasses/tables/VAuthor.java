/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlite.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VAuthor extends org.jooq.impl.TableImpl<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord> {

	private static final long serialVersionUID = -322598433;

	/**
	 * The singleton instance of <code>v_author</code>
	 */
	public static final org.jooq.test.sqlite.generatedclasses.tables.VAuthor V_AUTHOR = new org.jooq.test.sqlite.generatedclasses.tables.VAuthor();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord> getRecordType() {
		return org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord.class;
	}

	/**
	 * The column <code>v_author.ID</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, V_AUTHOR);

	/**
	 * The column <code>v_author.FIRST_NAME</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord, java.lang.String> FIRST_NAME = createField("FIRST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), V_AUTHOR);

	/**
	 * The column <code>v_author.LAST_NAME</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord, java.lang.String> LAST_NAME = createField("LAST_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(50), V_AUTHOR);

	/**
	 * The column <code>v_author.DATE_OF_BIRTH</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord, java.sql.Date> DATE_OF_BIRTH = createField("DATE_OF_BIRTH", org.jooq.impl.SQLDataType.DATE, V_AUTHOR);

	/**
	 * The column <code>v_author.YEAR_OF_BIRTH</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord, java.lang.Integer> YEAR_OF_BIRTH = createField("YEAR_OF_BIRTH", org.jooq.impl.SQLDataType.INTEGER, V_AUTHOR);

	/**
	 * The column <code>v_author.ADDRESS</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.sqlite.generatedclasses.tables.records.VAuthorRecord, java.lang.String> ADDRESS = createField("ADDRESS", org.jooq.impl.SQLDataType.VARCHAR.length(50), V_AUTHOR);

	/**
	 * No further instances allowed
	 */
	private VAuthor() {
		super("v_author", org.jooq.test.sqlite.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}
}
