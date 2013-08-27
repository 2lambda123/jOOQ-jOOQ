/*
 * Copyright (c) 2013 by Data Geekery GmbH
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU Affero General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for 
 * more details.
 *
 * You should have received a copy of the GNU Affero General Public License 
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jooq.test.h2.generatedclasses.tables.daos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_85Dao extends org.jooq.impl.DAOImpl<org.jooq.test.h2.generatedclasses.tables.records.XTestCase_85Record, org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85, java.lang.Integer> {

	/**
	 * Create a new XTestCase_85Dao without any configuration
	 */
	public XTestCase_85Dao() {
		super(org.jooq.test.h2.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85, org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85.class);
	}

	/**
	 * Create a new XTestCase_85Dao with an attached configuration
	 */
	public XTestCase_85Dao(org.jooq.Configuration configuration) {
		super(org.jooq.test.h2.generatedclasses.tables.XTestCase_85.X_TEST_CASE_85, org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85.class, configuration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected java.lang.Integer getId(org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85 object) {
		return object.getId();
	}

	/**
	 * Fetch records that have <code>ID IN (values)</code>
	 */
	public java.util.List<org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85> fetchById(java.lang.Integer... values) {
		return fetch(org.jooq.test.h2.generatedclasses.tables.XTestCase_85.ID, values);
	}

	/**
	 * Fetch a unique record that has <code>ID = value</code>
	 */
	public org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85 fetchOneById(java.lang.Integer value) {
		return fetchOne(org.jooq.test.h2.generatedclasses.tables.XTestCase_85.ID, value);
	}

	/**
	 * Fetch records that have <code>X_UNUSED_ID IN (values)</code>
	 */
	public java.util.List<org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85> fetchByXUnusedId(java.lang.Integer... values) {
		return fetch(org.jooq.test.h2.generatedclasses.tables.XTestCase_85.X_UNUSED_ID, values);
	}

	/**
	 * Fetch records that have <code>X_UNUSED_NAME IN (values)</code>
	 */
	public java.util.List<org.jooq.test.h2.generatedclasses.tables.pojos.XTestCase_85> fetchByXUnusedName(java.lang.String... values) {
		return fetch(org.jooq.test.h2.generatedclasses.tables.XTestCase_85.X_UNUSED_NAME, values);
	}
}
