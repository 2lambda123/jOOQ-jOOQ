/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.records;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.ArrayRecordImpl;
import org.jooq.test.all.converters.LocalDateTimeConverter;
import org.jooq.test.oracle.generatedclasses.test.Test;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class U_2155ArrayRecord extends ArrayRecordImpl<LocalDateTime> {

	private static final long serialVersionUID = -2127557951;

	/**
	 * @deprecated - 3.4.0 - [#3126] - Use the {@link #U_2155ArrayRecord()} constructor instead
	 */
	@java.lang.Deprecated
	public U_2155ArrayRecord(Configuration configuration) {
		super(Test.TEST, "U_2155_ARRAY", org.jooq.impl.SQLDataType.DATE, configuration, new LocalDateTimeConverter());
	}

	/**
	 * @deprecated - 3.4.0 - [#3126] - Use the {@link #U_2155ArrayRecord()} constructor instead
	 */
	@java.lang.Deprecated
	public U_2155ArrayRecord(Configuration configuration, LocalDateTime... array) {
		this(configuration);
		set(array);
	}

	/**
	 * @deprecated - 3.4.0 - [#3126] - Use the {@link #U_2155ArrayRecord()} constructor instead
	 */
	@java.lang.Deprecated
	public U_2155ArrayRecord(Configuration configuration, List<? extends LocalDateTime> list) {
		this(configuration);
		setList(list);
	}

	/**
	 * Create a new <code>TEST.U_2155_ARRAY</code> record
	 */
	public U_2155ArrayRecord() {
		super(Test.TEST, "U_2155_ARRAY", org.jooq.impl.SQLDataType.DATE, new LocalDateTimeConverter());
	}

	/**
	 * Create a new <code>TEST.U_2155_ARRAY</code> record
	 */
	public U_2155ArrayRecord(LocalDateTime... array) {
		this();
		set(array);
	}

	/**
	 * Create a new <code>TEST.U_2155_ARRAY</code> record
	 */
	public U_2155ArrayRecord(Collection<? extends LocalDateTime> collection) {
		this();
		set(collection);
	}
}
