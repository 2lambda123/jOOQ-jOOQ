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
 *
 * An entity holding language master data
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface ITLanguage extends java.io.Serializable {

	/**
	 * Setter for <code>PUBLIC.T_LANGUAGE.CD</code>. The language ISO code
	 */
	public void setCd(java.lang.String value);

	/**
	 * Getter for <code>PUBLIC.T_LANGUAGE.CD</code>. The language ISO code
	 */
	public java.lang.String getCd();

	/**
	 * Setter for <code>PUBLIC.T_LANGUAGE.DESCRIPTION</code>. The language description
	 */
	public void setDescription(java.lang.String value);

	/**
	 * Getter for <code>PUBLIC.T_LANGUAGE.DESCRIPTION</code>. The language description
	 */
	public java.lang.String getDescription();

	/**
	 * Setter for <code>PUBLIC.T_LANGUAGE.DESCRIPTION_ENGLISH</code>. 
	 */
	public void setDescriptionEnglish(java.lang.String value);

	/**
	 * Getter for <code>PUBLIC.T_LANGUAGE.DESCRIPTION_ENGLISH</code>. 
	 */
	public java.lang.String getDescriptionEnglish();

	/**
	 * Setter for <code>PUBLIC.T_LANGUAGE.ID</code>. The language ID
	 */
	public void setId(java.lang.Integer value);

	/**
	 * Getter for <code>PUBLIC.T_LANGUAGE.ID</code>. The language ID
	 */
	public java.lang.Integer getId();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface ITLanguage
	 */
	public void from(org.jooq.test.h2.generatedclasses.tables.interfaces.ITLanguage from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface ITLanguage
	 */
	public <E extends org.jooq.test.h2.generatedclasses.tables.interfaces.ITLanguage> E into(E into);
}
