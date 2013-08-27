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
package org.jooq.test.h2.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 *
 * An entity holding language master data
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TLanguage extends org.jooq.impl.TableImpl<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> {

	private static final long serialVersionUID = -996992149;

	/**
	 * The singleton instance of <code>PUBLIC.T_LANGUAGE</code>
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.TLanguage T_LANGUAGE = new org.jooq.test.h2.generatedclasses.tables.TLanguage();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> getRecordType() {
		return org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord.class;
	}

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.CD</code>. The language ISO code
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.String> CD = createField("CD", org.jooq.impl.SQLDataType.CHAR.length(2).nullable(false), T_LANGUAGE);

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.DESCRIPTION</code>. The language description
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR.length(50), T_LANGUAGE);

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.DESCRIPTION_ENGLISH</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.String> DESCRIPTION_ENGLISH = createField("DESCRIPTION_ENGLISH", org.jooq.impl.SQLDataType.VARCHAR.length(50), T_LANGUAGE);

	/**
	 * The column <code>PUBLIC.T_LANGUAGE.ID</code>. The language ID
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), T_LANGUAGE);

	/**
	 * No further instances allowed
	 */
	private TLanguage() {
		super("T_LANGUAGE", org.jooq.test.h2.generatedclasses.Public.PUBLIC);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord> getPrimaryKey() {
		return org.jooq.test.h2.generatedclasses.Keys.PK_T_LANGUAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.test.h2.generatedclasses.tables.records.TLanguageRecord>>asList(org.jooq.test.h2.generatedclasses.Keys.PK_T_LANGUAGE);
	}
}
