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
public interface ITDates extends java.io.Serializable {

	/**
	 * Setter for <code>PUBLIC.T_DATES.ID</code>. 
	 */
	public void setId(java.lang.Integer value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.ID</code>. 
	 */
	public java.lang.Integer getId();

	/**
	 * Setter for <code>PUBLIC.T_DATES.D</code>. 
	 */
	public void setD(java.sql.Date value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.D</code>. 
	 */
	public java.sql.Date getD();

	/**
	 * Setter for <code>PUBLIC.T_DATES.T</code>. 
	 */
	public void setT(java.sql.Time value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.T</code>. 
	 */
	public java.sql.Time getT();

	/**
	 * Setter for <code>PUBLIC.T_DATES.TS</code>. 
	 */
	public void setTs(java.sql.Timestamp value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.TS</code>. 
	 */
	public java.sql.Timestamp getTs();

	/**
	 * Setter for <code>PUBLIC.T_DATES.D_INT</code>. 
	 */
	public void setDInt(java.lang.Integer value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.D_INT</code>. 
	 */
	public java.lang.Integer getDInt();

	/**
	 * Setter for <code>PUBLIC.T_DATES.TS_BIGINT</code>. 
	 */
	public void setTsBigint(java.lang.Long value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.TS_BIGINT</code>. 
	 */
	public java.lang.Long getTsBigint();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface ITDates
	 */
	public void from(org.jooq.test.h2.generatedclasses.tables.interfaces.ITDates from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface ITDates
	 */
	public <E extends org.jooq.test.h2.generatedclasses.tables.interfaces.ITDates> E into(E into);
}
