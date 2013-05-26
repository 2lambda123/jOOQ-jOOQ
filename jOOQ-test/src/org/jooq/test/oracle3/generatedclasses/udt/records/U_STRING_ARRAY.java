/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.udt.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class U_STRING_ARRAY extends org.jooq.impl.ArrayRecordImpl<java.lang.String> implements java.lang.Cloneable {

	private static final long serialVersionUID = 2089173366;

	/**
	 * Create a new <code>U_STRING_ARRAY</code> record
	 */
	public U_STRING_ARRAY(org.jooq.Configuration configuration) {
		super(org.jooq.test.oracle3.generatedclasses.DefaultSchema.DEFAULT_SCHEMA, "U_STRING_ARRAY", org.jooq.impl.SQLDataType.VARCHAR.length(20), configuration);
	}

	/**
	 * Create a new <code>U_STRING_ARRAY</code> record
	 */
	public U_STRING_ARRAY(org.jooq.Configuration configuration, java.lang.String... array) {
		this(configuration);
		set(array);
	}

	/**
	 * Create a new <code>U_STRING_ARRAY</code> record
	 */
	public U_STRING_ARRAY(org.jooq.Configuration configuration, java.util.List<? extends java.lang.String> list) {
		this(configuration);
		setList(list);
	}
}
