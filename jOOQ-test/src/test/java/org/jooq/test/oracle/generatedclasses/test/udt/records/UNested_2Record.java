/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.records;


import java.util.Collection;
import java.util.List;

import org.jooq.Configuration;
import org.jooq.impl.ArrayRecordImpl;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UNested_1Record;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UNested_2Record extends ArrayRecordImpl<UNested_1Record> {

	private static final long serialVersionUID = 883109093;

	/**
	 * @deprecated - 3.4.0 - [#3126] - Use the {@link #UNested_2Record()} constructor instead
	 */
	@java.lang.Deprecated
	public UNested_2Record(Configuration configuration) {
		super(Test.TEST, "U_NESTED_2", org.jooq.test.oracle.generatedclasses.test.udt.UNested_1.U_NESTED_1.getDataType(), configuration);
	}

	/**
	 * @deprecated - 3.4.0 - [#3126] - Use the {@link #UNested_2Record()} constructor instead
	 */
	@java.lang.Deprecated
	public UNested_2Record(Configuration configuration, UNested_1Record... array) {
		this(configuration);
		set(array);
	}

	/**
	 * @deprecated - 3.4.0 - [#3126] - Use the {@link #UNested_2Record()} constructor instead
	 */
	@java.lang.Deprecated
	public UNested_2Record(Configuration configuration, List<? extends UNested_1Record> list) {
		this(configuration);
		setList(list);
	}

	/**
	 * Create a new <code>TEST.U_NESTED_2</code> record
	 */
	public UNested_2Record() {
		super(Test.TEST, "U_NESTED_2", org.jooq.test.oracle.generatedclasses.test.udt.UNested_1.U_NESTED_1.getDataType());
	}

	/**
	 * Create a new <code>TEST.U_NESTED_2</code> record
	 */
	public UNested_2Record(UNested_1Record... array) {
		this();
		set(array);
	}

	/**
	 * Create a new <code>TEST.U_NESTED_2</code> record
	 */
	public UNested_2Record(Collection<? extends UNested_1Record> collection) {
		this();
		set(collection);
	}
}
