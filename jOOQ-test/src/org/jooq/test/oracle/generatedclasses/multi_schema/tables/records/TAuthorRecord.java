/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.multi_schema.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "T_AUTHOR", schema = "MULTI_SCHEMA")
public class TAuthorRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TAuthorRecord> {

	private static final long serialVersionUID = 1182084110;

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.ID, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "ID", unique = true)
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.ID);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TBookRecord> fetchTBookListByAuthorId() {
		return create()
			.selectFrom(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TBook.T_BOOK)
			.where(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TBook.T_BOOK.AUTHOR_ID.equal(getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TBookRecord> fetchTBookListByCoAuthorId() {
		return create()
			.selectFrom(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TBook.T_BOOK)
			.where(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TBook.T_BOOK.CO_AUTHOR_ID.equal(getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 */
	public void setFirstName(java.lang.String value) {
		setValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.FIRST_NAME, value);
	}

	/**
	 * An uncommented item
	 */
	@javax.persistence.Column(name = "FIRST_NAME")
	public java.lang.String getFirstName() {
		return getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.FIRST_NAME);
	}

	/**
	 * An uncommented item
	 */
	public void setLastName(java.lang.String value) {
		setValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.LAST_NAME, value);
	}

	/**
	 * An uncommented item
	 */
	@javax.persistence.Column(name = "LAST_NAME")
	public java.lang.String getLastName() {
		return getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.LAST_NAME);
	}

	/**
	 * An uncommented item
	 */
	public void setDateOfBirth(java.sql.Date value) {
		setValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.DATE_OF_BIRTH, value);
	}

	/**
	 * An uncommented item
	 */
	@javax.persistence.Column(name = "DATE_OF_BIRTH")
	public java.sql.Date getDateOfBirth() {
		return getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.DATE_OF_BIRTH);
	}

	/**
	 * An uncommented item
	 */
	public void setYearOfBirth(java.lang.Integer value) {
		setValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.YEAR_OF_BIRTH, value);
	}

	/**
	 * An uncommented item
	 */
	@javax.persistence.Column(name = "YEAR_OF_BIRTH")
	public java.lang.Integer getYearOfBirth() {
		return getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.YEAR_OF_BIRTH);
	}

	/**
	 * An uncommented item
	 * 
	 * The SQL type of this item (U_ADDRESS_TYPE) could not be mapped.<br/>
	 * Deserialising this field might not work!
	 */
	public void setAddress(java.lang.Object value) {
		setValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.ADDRESS, value);
	}

	/**
	 * An uncommented item
	 * 
	 * The SQL type of this item (U_ADDRESS_TYPE) could not be mapped.<br/>
	 * Deserialising this field might not work!
	 */
	@javax.persistence.Column(name = "ADDRESS")
	public java.lang.Object getAddress() {
		return getValue(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR.ADDRESS);
	}

	/**
	 * Create a detached TAuthorRecord
	 */
	public TAuthorRecord() {
		super(org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor.T_AUTHOR);
	}
}
