/**
 * This class is generated by jOOQ
 */
package org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TAuthor extends org.jooq.impl.TableImpl<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord> {

	private static final long serialVersionUID = 1843905057;

	/**
	 * The singleton instance of <code>MULTI_SCHEMA.T_AUTHOR</code>
	 */
	public static final org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.TAuthor T_AUTHOR = new org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.TAuthor();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord> getRecordType() {
		return org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord.class;
	}

	/**
	 * The column <code>MULTI_SCHEMA.T_AUTHOR.ID</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord, java.math.BigDecimal> ID = createField("ID", org.jooq.impl.SQLDataType.NUMERIC, T_AUTHOR);

	/**
	 * The column <code>MULTI_SCHEMA.T_AUTHOR.FIRST_NAME</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord, java.lang.String> FIRST_NAME = createField("FIRST_NAME", org.jooq.impl.SQLDataType.VARCHAR, T_AUTHOR);

	/**
	 * The column <code>MULTI_SCHEMA.T_AUTHOR.LAST_NAME</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord, java.lang.String> LAST_NAME = createField("LAST_NAME", org.jooq.impl.SQLDataType.VARCHAR, T_AUTHOR);

	/**
	 * The column <code>MULTI_SCHEMA.T_AUTHOR.DATE_OF_BIRTH</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord, java.sql.Date> DATE_OF_BIRTH = createField("DATE_OF_BIRTH", org.jooq.impl.SQLDataType.DATE, T_AUTHOR);

	/**
	 * The column <code>MULTI_SCHEMA.T_AUTHOR.YEAR_OF_BIRTH</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord, java.math.BigDecimal> YEAR_OF_BIRTH = createField("YEAR_OF_BIRTH", org.jooq.impl.SQLDataType.NUMERIC, T_AUTHOR);

	/**
	 * The column <code>MULTI_SCHEMA.T_AUTHOR.ADDRESS</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.jdbcoracle.generatedclasses.multi_schema.tables.records.TAuthorRecord, java.lang.Object> ADDRESS = createField("ADDRESS", org.jooq.impl.SQLDataType.OTHER, T_AUTHOR);

	/**
	 * No further instances allowed
	 */
	private TAuthor() {
		super("T_AUTHOR", org.jooq.test.jdbcoracle.generatedclasses.multi_schema.MultiSchema.MULTI_SCHEMA);
	}
}
