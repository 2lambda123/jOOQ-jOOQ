/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses;

/**
 * Convenience access to all sequences in TEST
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

	/**
	 * The sequence <code>TEST.S_AUTHOR_ID</code>
	 */
	public static final org.jooq.Sequence<java.lang.Integer> S_AUTHOR_ID = new org.jooq.impl.SequenceImpl<java.lang.Integer>("S_AUTHOR_ID", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

	/**
	 * The sequence <code>TEST.S_TRIGGERS_SEQUENCE</code>
	 */
	public static final org.jooq.Sequence<java.math.BigInteger> S_TRIGGERS_SEQUENCE = new org.jooq.impl.SequenceImpl<java.math.BigInteger>("S_TRIGGERS_SEQUENCE", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38).nullable(false));

	/**
	 * The sequence <code>TEST.S_961_BIG_INTEGER</code>
	 */
	public static final org.jooq.Sequence<java.math.BigInteger> S_961_BIG_INTEGER = new org.jooq.impl.SequenceImpl<java.math.BigInteger>("S_961_BIG_INTEGER", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.DECIMAL_INTEGER.precision(38).nullable(false));

	/**
	 * The sequence <code>TEST.S_961_BYTE</code>
	 */
	public static final org.jooq.Sequence<java.lang.Byte> S_961_BYTE = new org.jooq.impl.SequenceImpl<java.lang.Byte>("S_961_BYTE", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.TINYINT.nullable(false));

	/**
	 * The sequence <code>TEST.S_961_INT</code>
	 */
	public static final org.jooq.Sequence<java.lang.Integer> S_961_INT = new org.jooq.impl.SequenceImpl<java.lang.Integer>("S_961_INT", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.INTEGER.nullable(false));

	/**
	 * The sequence <code>TEST.S_961_LONG</code>
	 */
	public static final org.jooq.Sequence<java.lang.Long> S_961_LONG = new org.jooq.impl.SequenceImpl<java.lang.Long>("S_961_LONG", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

	/**
	 * The sequence <code>TEST.S_961_SHORT</code>
	 */
	public static final org.jooq.Sequence<java.lang.Short> S_961_SHORT = new org.jooq.impl.SequenceImpl<java.lang.Short>("S_961_SHORT", org.jooq.test.oracle5.generatedclasses.Test.TEST, org.jooq.impl.SQLDataType.SMALLINT.nullable(false));
}
