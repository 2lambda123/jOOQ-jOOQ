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
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_877 extends org.jooq.impl.TableImpl<org.jooq.test.h2.generatedclasses.tables.records.T_877Record> {

	private static final long serialVersionUID = 2085018942;

	/**
	 * The singleton instance of <code>PUBLIC.T_877</code>
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.T_877 T_877 = new org.jooq.test.h2.generatedclasses.tables.T_877();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.T_877Record> getRecordType() {
		return org.jooq.test.h2.generatedclasses.tables.records.T_877Record.class;
	}

	/**
	 * The column <code>PUBLIC.T_877.ID</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_877Record, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaulted(true), T_877);

	/**
	 * No further instances allowed
	 */
	private T_877() {
		super("T_877", org.jooq.test.h2.generatedclasses.Public.PUBLIC);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.test.h2.generatedclasses.tables.records.T_877Record, java.lang.Integer> getIdentity() {
		return org.jooq.test.h2.generatedclasses.Keys.IDENTITY_T_877;
	}
}
