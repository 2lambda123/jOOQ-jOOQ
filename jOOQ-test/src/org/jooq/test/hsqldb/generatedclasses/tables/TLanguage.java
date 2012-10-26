/**
 * This class is generated by jOOQ
 */
package org.jooq.test.hsqldb.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TLanguage extends org.jooq.impl.UpdatableTableImpl<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord> {

	private static final long serialVersionUID = 77421189;

	/**
	 * The singleton instance of PUBLIC.T_LANGUAGE
	 */
	public static final org.jooq.test.hsqldb.generatedclasses.tables.TLanguage T_LANGUAGE = new org.jooq.test.hsqldb.generatedclasses.tables.TLanguage();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord> getRecordType() {
		return org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord.class;
	}

	/**
	 * The table column <code>PUBLIC.T_LANGUAGE.CD</code>
	 */
	public final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord, java.lang.String> CD = createField("CD", org.jooq.impl.SQLDataType.CHAR, this);

	/**
	 * The table column <code>PUBLIC.T_LANGUAGE.DESCRIPTION</code>
	 */
	public final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord, java.lang.String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * The table column <code>PUBLIC.T_LANGUAGE.DESCRIPTION_ENGLISH</code>
	 */
	public final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord, java.lang.String> DESCRIPTION_ENGLISH = createField("DESCRIPTION_ENGLISH", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * The table column <code>PUBLIC.T_LANGUAGE.ID</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	public TLanguage() {
		super("T_LANGUAGE", org.jooq.test.hsqldb.generatedclasses.Public.PUBLIC);
	}

	public TLanguage(java.lang.String alias) {
		super(alias, org.jooq.test.hsqldb.generatedclasses.Public.PUBLIC, org.jooq.test.hsqldb.generatedclasses.tables.TLanguage.T_LANGUAGE);
	}

	@Override
	public org.jooq.UniqueKey<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord> getMainKey() {
		return org.jooq.test.hsqldb.generatedclasses.Keys.PK_T_LANGUAGE;
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.hsqldb.generatedclasses.tables.records.TLanguageRecord>>asList(org.jooq.test.hsqldb.generatedclasses.Keys.PK_T_LANGUAGE);
	}

	@Override
	public org.jooq.test.hsqldb.generatedclasses.tables.TLanguage as(java.lang.String alias) {
		return new org.jooq.test.hsqldb.generatedclasses.tables.TLanguage(alias);
	}
}
