/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mysql.generatedclasses.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.test.mysql.generatedclasses.tables.TLanguage;


/**
 * An entity holding language master data
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TLanguageRecord extends UpdatableRecordImpl<TLanguageRecord> implements Record4<String, String, String, Integer> {

	private static final long serialVersionUID = -963502323;

	/**
	 * Setter for <code>test.t_language.CD</code>. The language ISO code
	 */
	public void setCd(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>test.t_language.CD</code>. The language ISO code
	 */
	public String getCd() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>test.t_language.DESCRIPTION</code>. The language description
	 */
	public void setDescription(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>test.t_language.DESCRIPTION</code>. The language description
	 */
	public String getDescription() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>test.t_language.description_english</code>.
	 */
	public void setDescriptionEnglish(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>test.t_language.description_english</code>.
	 */
	public String getDescriptionEnglish() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>test.t_language.ID</code>. The language ID
	 */
	public void setId(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>test.t_language.ID</code>. The language ID
	 */
	public Integer getId() {
		return (Integer) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, Integer> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, Integer> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return TLanguage.CD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return TLanguage.DESCRIPTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return TLanguage.DESCRIPTION_ENGLISH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return TLanguage.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getCd();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getDescription();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getDescriptionEnglish();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLanguageRecord value1(String value) {
		setCd(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLanguageRecord value2(String value) {
		setDescription(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLanguageRecord value3(String value) {
		setDescriptionEnglish(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLanguageRecord value4(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLanguageRecord values(String value1, String value2, String value3, Integer value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TLanguageRecord
	 */
	public TLanguageRecord() {
		super(TLanguage.T_LANGUAGE);
	}

	/**
	 * Create a detached, initialised TLanguageRecord
	 */
	public TLanguageRecord(String cd, String description, String descriptionEnglish, Integer id) {
		super(TLanguage.T_LANGUAGE);

		setValue(0, cd);
		setValue(1, description);
		setValue(2, descriptionEnglish);
		setValue(3, id);
	}
}
