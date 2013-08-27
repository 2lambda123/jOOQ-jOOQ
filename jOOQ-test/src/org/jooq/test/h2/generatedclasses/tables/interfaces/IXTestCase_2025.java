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
package org.jooq.test.h2.generatedclasses.tables.interfaces;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IXTestCase_2025 extends java.io.Serializable {

	/**
	 * Setter for <code>PUBLIC.X_TEST_CASE_2025.REF_ID</code>. 
	 */
	public void setRefId(java.lang.Integer value);

	/**
	 * Getter for <code>PUBLIC.X_TEST_CASE_2025.REF_ID</code>. 
	 */
	public java.lang.Integer getRefId();

	/**
	 * Setter for <code>PUBLIC.X_TEST_CASE_2025.REF_NAME</code>. 
	 */
	public void setRefName(java.lang.String value);

	/**
	 * Getter for <code>PUBLIC.X_TEST_CASE_2025.REF_NAME</code>. 
	 */
	public java.lang.String getRefName();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface IXTestCase_2025
	 */
	public void from(org.jooq.test.h2.generatedclasses.tables.interfaces.IXTestCase_2025 from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface IXTestCase_2025
	 */
	public <E extends org.jooq.test.h2.generatedclasses.tables.interfaces.IXTestCase_2025> E into(E into);
}
