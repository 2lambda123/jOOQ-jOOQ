/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.mysql.sakila.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.0"},
                            comments = "This class is generated by jOOQ")
public class Language extends org.jooq.impl.UpdatableTableImpl<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord> {

	private static final long serialVersionUID = -107238211;

	/**
	 * The singleton instance of language
	 */
	public static final org.jooq.examples.mysql.sakila.tables.Language LANGUAGE = new org.jooq.examples.mysql.sakila.tables.Language();

	/**
	 * The class holding records for this type
	 */
	private static final java.lang.Class<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord> __RECORD_TYPE = org.jooq.examples.mysql.sakila.tables.records.LanguageRecord.class;

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord> getRecordType() {
		return __RECORD_TYPE;
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public static final org.jooq.TableField<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.lang.Byte> LANGUAGE_ID = new org.jooq.impl.TableFieldImpl<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.lang.Byte>("language_id", org.jooq.impl.SQLDataType.TINYINT, LANGUAGE);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.lang.String> NAME = new org.jooq.impl.TableFieldImpl<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.lang.String>("name", org.jooq.impl.SQLDataType.CHAR, LANGUAGE);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.TableField<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.sql.Timestamp> LAST_UPDATE = new org.jooq.impl.TableFieldImpl<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.sql.Timestamp>("last_update", org.jooq.impl.SQLDataType.TIMESTAMP, LANGUAGE);

	/**
	 * No further instances allowed
	 */
	private Language() {
		super("language", org.jooq.examples.mysql.sakila.Sakila.SAKILA);
	}

	@Override
	public org.jooq.Identity<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord, java.lang.Byte> getIdentity() {
		return org.jooq.examples.mysql.sakila.Keys.IDENTITY_language;
	}

	@Override
	public org.jooq.UniqueKey<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord> getMainKey() {
		return org.jooq.examples.mysql.sakila.Keys.KEY_language_PRIMARY;
	}

	@Override
	@SuppressWarnings("unchecked")
	public java.util.List<org.jooq.UniqueKey<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.examples.mysql.sakila.tables.records.LanguageRecord>>asList(org.jooq.examples.mysql.sakila.Keys.KEY_language_PRIMARY);
	}
}
