/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.udt.records;

/**
 * This class is generated by jOOQ.
 */
public class U_DATE_TABLE extends org.jooq.impl.ArrayRecordImpl<java.sql.Date> {

	private static final long serialVersionUID = -1479595152;

	public U_DATE_TABLE(org.jooq.Configuration configuration) {
		super("TEST.U_DATE_TABLE", org.jooq.impl.SQLDataType.DATE, configuration);
	}

	public U_DATE_TABLE(org.jooq.Configuration configuration, java.sql.Date... array) {
		this(configuration);
		set(array);
	}

	public U_DATE_TABLE(org.jooq.Configuration configuration, java.util.List<? extends java.sql.Date> list) {
		this(configuration);
		setList(list);
	}
}
