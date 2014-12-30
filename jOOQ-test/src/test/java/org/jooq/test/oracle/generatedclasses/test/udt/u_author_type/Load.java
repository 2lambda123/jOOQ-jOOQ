/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.u_author_type;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.UAuthorType;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UAuthorTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Load extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = 842015711;

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.LOAD.SELF</code>.
	 */
	public static final Parameter<UAuthorTypeRecord> SELF = createParameter("SELF", org.jooq.test.oracle.generatedclasses.test.udt.UAuthorType.U_AUTHOR_TYPE.getDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public Load() {
		super("LOAD", Test.TEST, UAuthorType.U_AUTHOR_TYPE);

		addInOutParameter(SELF);
	}

	/**
	 * Set the <code>SELF</code> parameter IN value to the routine
	 */
	public void setSelf(UAuthorTypeRecord value) {
		setValue(SELF, value);
	}

	/**
	 * Get the <code>SELF</code> parameter OUT value from the routine
	 */
	public UAuthorTypeRecord getSelf() {
		return getValue(SELF);
	}
}
