/**
 * This class is generated by jOOQ
 */
package org.jooq.test.ase.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class TLanguageRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.ase.generatedclasses.tables.records.TLanguageRecord> {

	private static final long serialVersionUID = 381731671;

	/**
	 * The table column <code>dbo.t_language.cd</code>
	 */
	public void setCd(java.lang.String value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.CD, value);
	}

	/**
	 * The table column <code>dbo.t_language.cd</code>
	 */
	public java.lang.String getCd() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.CD);
	}

	/**
	 * The table column <code>dbo.t_language.description</code>
	 */
	public void setDescription(java.lang.String value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.DESCRIPTION, value);
	}

	/**
	 * The table column <code>dbo.t_language.description</code>
	 */
	public java.lang.String getDescription() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.DESCRIPTION);
	}

	/**
	 * The table column <code>dbo.t_language.description_english</code>
	 */
	public void setDescriptionEnglish(java.lang.String value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.DESCRIPTION_ENGLISH, value);
	}

	/**
	 * The table column <code>dbo.t_language.description_english</code>
	 */
	public java.lang.String getDescriptionEnglish() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.DESCRIPTION_ENGLISH);
	}

	/**
	 * The table column <code>dbo.t_language.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.ID, value);
	}

	/**
	 * The table column <code>dbo.t_language.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.ID);
	}

	/**
	 * The table column <code>dbo.t_language.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.util.List<org.jooq.test.ase.generatedclasses.tables.records.TBookRecord> fetchTBookList() {
		return create()
			.selectFrom(org.jooq.test.ase.generatedclasses.tables.TBook.T_BOOK)
			.where(org.jooq.test.ase.generatedclasses.tables.TBook.LANGUAGE_ID.equal(getValue(org.jooq.test.ase.generatedclasses.tables.TLanguage.ID)))
			.fetch();
	}

	/**
	 * Create a detached TLanguageRecord
	 */
	public TLanguageRecord() {
		super(org.jooq.test.ase.generatedclasses.tables.TLanguage.T_LANGUAGE);
	}
}
