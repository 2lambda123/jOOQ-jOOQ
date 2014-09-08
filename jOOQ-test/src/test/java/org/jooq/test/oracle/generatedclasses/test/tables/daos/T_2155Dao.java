/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.daos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_2155Dao extends org.jooq.impl.DAOImpl<org.jooq.test.oracle.generatedclasses.test.tables.records.T_2155Record, org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155, java.lang.Integer> {

	/**
	 * Create a new T_2155Dao without any configuration
	 */
	public T_2155Dao() {
		super(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155, org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155.class);
	}

	/**
	 * Create a new T_2155Dao with an attached configuration
	 */
	public T_2155Dao(org.jooq.Configuration configuration) {
		super(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155, org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Integer getId(org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155 object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>ID IN (values)</code>
	 */
	public java.util.List<org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155> fetchById(java.lang.Integer... values) {
		return fetch(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>ID = value</code>
	 */
	public org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155 fetchOneById(java.lang.Integer value) {
		return fetchOne(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155.ID, value);
	}

	/**
	 * Fetch records that have <code>D1 IN (values)</code>
	 */
	public java.util.List<org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155> fetchByD1(java.time.LocalDateTime... values) {
		return fetch(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155.D1, values);
	}

	/**
	 * Fetch records that have <code>D2 IN (values)</code>
	 */
	public java.util.List<org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155> fetchByD2(org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ObjectRecord... values) {
		return fetch(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155.D2, values);
	}

	/**
	 * Fetch records that have <code>D3 IN (values)</code>
	 */
	public java.util.List<org.jooq.test.oracle.generatedclasses.test.tables.pojos.T_2155> fetchByD3(org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord... values) {
		return fetch(org.jooq.test.oracle.generatedclasses.test.tables.T_2155.T_2155.D3, values);
	}
}
