/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.multi_schema.tables.daos;


import java.sql.Date;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.TAuthor;
import org.jooq.test.oracle.generatedclasses.multi_schema.tables.records.TAuthorRecord;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UAddressTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TAuthorDao extends DAOImpl<TAuthorRecord, org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor, Integer> {

	/**
	 * Create a new TAuthorDao without any configuration
	 */
	public TAuthorDao() {
		super(TAuthor.T_AUTHOR, org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor.class);
	}

	/**
	 * Create a new TAuthorDao with an attached configuration
	 */
	public TAuthorDao(Configuration configuration) {
		super(TAuthor.T_AUTHOR, org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Integer getId(org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>ID IN (values)</code>
	 */
	public List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor> fetchById(Integer... values) {
		return fetch(TAuthor.T_AUTHOR.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>ID = value</code>
	 */
	public org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor fetchOneById(Integer value) {
		return fetchOne(TAuthor.T_AUTHOR.ID, value);
	}

	/**
	 * Fetch records that have <code>FIRST_NAME IN (values)</code>
	 */
	public List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor> fetchByFirstName(String... values) {
		return fetch(TAuthor.T_AUTHOR.FIRST_NAME, values);
	}

	/**
	 * Fetch records that have <code>LAST_NAME IN (values)</code>
	 */
	public List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor> fetchByLastName(String... values) {
		return fetch(TAuthor.T_AUTHOR.LAST_NAME, values);
	}

	/**
	 * Fetch records that have <code>DATE_OF_BIRTH IN (values)</code>
	 */
	public List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor> fetchByDateOfBirth(Date... values) {
		return fetch(TAuthor.T_AUTHOR.DATE_OF_BIRTH, values);
	}

	/**
	 * Fetch records that have <code>YEAR_OF_BIRTH IN (values)</code>
	 */
	public List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor> fetchByYearOfBirth(Integer... values) {
		return fetch(TAuthor.T_AUTHOR.YEAR_OF_BIRTH, values);
	}

	/**
	 * Fetch records that have <code>ADDRESS IN (values)</code>
	 */
	public List<org.jooq.test.oracle.generatedclasses.multi_schema.tables.pojos.TAuthor> fetchByAddress(UAddressTypeRecord... values) {
		return fetch(TAuthor.T_AUTHOR.ADDRESS, values);
	}
}
