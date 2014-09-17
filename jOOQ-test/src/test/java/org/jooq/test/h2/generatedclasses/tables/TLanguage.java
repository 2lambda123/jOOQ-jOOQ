/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables;

/**
 * An entity holding language master data
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TLanguage extends org.jooq.impl.TableImpl<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> {

	private static final long serialVersionUID = 989870623;

	/**
	 * The singleton instance of <code>PUBLIC.T_LANGUAGE</code>
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.TLanguage T_LANGUAGE = new org.jooq.test.h2.generatedclasses.tables.TLanguage();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> getRecordType() {
		return org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord.class;
	}

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.CD</code>. The language ISO code
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.String> CD = createField("CD", org.jooq.impl.SQLDataType.CHAR.length(2).nullable(false), this, "The language ISO code");

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.DESCRIPTION</code>. The language description
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "The language description");

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.DESCRIPTION_ENGLISH</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.String> DESCRIPTION_ENGLISH = createField("DESCRIPTION_ENGLISH", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.ID</code>. The language ID
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "The language ID");

	/**
	 * Create a <code>PUBLIC.T_LANGUAGE</code> table reference
	 */
	public TLanguage() {
		this("T_LANGUAGE", null);
	}

	/**
	 * Create an aliased <code>PUBLIC.T_LANGUAGE</code> table reference
	 */
	public TLanguage(java.lang.String alias) {
		this(alias, org.jooq.test.h2.generatedclasses.tables.TLanguage.T_LANGUAGE);
	}

	private TLanguage(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> aliased) {
		this(alias, aliased, null);
	}

	private TLanguage(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.h2.generatedclasses.Public.PUBLIC, aliased, parameters, "An entity holding language master data");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> getPrimaryKey() {
		return org.jooq.test.h2.generatedclasses.Keys.PK_T_LANGUAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord>>asList(org.jooq.test.h2.generatedclasses.Keys.PK_T_LANGUAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.h2.generatedclasses.tables.TLanguage as(java.lang.String alias) {
		return new org.jooq.test.h2.generatedclasses.tables.TLanguage(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.h2.generatedclasses.tables.TLanguage rename(java.lang.String name) {
		return new org.jooq.test.h2.generatedclasses.tables.TLanguage(name, null);
	}
}
