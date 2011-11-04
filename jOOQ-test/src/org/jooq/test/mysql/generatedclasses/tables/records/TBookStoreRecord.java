/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mysql.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 *
 * A book store
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.0"},
                            comments = "This class is generated by jOOQ")
public class TBookStoreRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.mysql.generatedclasses.tables.records.TBookStoreRecord> {

	private static final long serialVersionUID = 870242334;

	/**
	 * The books store name
	 */
	public void setName(java.lang.String value) {
		setValue(org.jooq.test.mysql.generatedclasses.tables.TBookStore.NAME, value);
	}

	/**
	 * The books store name
	 */
	public java.lang.String getName() {
		return getValue(org.jooq.test.mysql.generatedclasses.tables.TBookStore.NAME);
	}

	/**
	 * The books store name
	 */
	public java.util.List<org.jooq.test.mysql.generatedclasses.tables.records.TBookToBookStoreRecord> fetchTBookToBookStoreList() {
		return create()
			.selectFrom(org.jooq.test.mysql.generatedclasses.tables.TBookToBookStore.T_BOOK_TO_BOOK_STORE)
			.where(org.jooq.test.mysql.generatedclasses.tables.TBookToBookStore.BOOK_STORE_NAME.equal(getValue(org.jooq.test.mysql.generatedclasses.tables.TBookStore.NAME)))
			.fetch();
	}

	/**
	 * The book store ID
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.mysql.generatedclasses.tables.TBookStore.ID, value);
	}

	/**
	 * The book store ID
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.mysql.generatedclasses.tables.TBookStore.ID);
	}

	/**
	 * Create a detached TBookStoreRecord
	 */
	public TBookStoreRecord() {
		super(org.jooq.test.mysql.generatedclasses.tables.TBookStore.T_BOOK_STORE);
	}
}
