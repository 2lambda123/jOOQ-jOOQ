/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UNested_3Record;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PNested extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = 1474950446;

	/**
	 * The parameter <code>TEST.P_NESTED.P1</code>.
	 */
	public static final Parameter<UNested_3Record> P1 = createParameter("P1", org.jooq.test.oracle.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false);

	/**
	 * The parameter <code>TEST.P_NESTED.P2</code>.
	 */
	public static final Parameter<UNested_3Record> P2 = createParameter("P2", org.jooq.test.oracle.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false);

	/**
	 * The parameter <code>TEST.P_NESTED.P3</code>.
	 */
	public static final Parameter<UNested_3Record> P3 = createParameter("P3", org.jooq.test.oracle.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false);

	/**
	 * The parameter <code>TEST.P_NESTED.P4</code>.
	 */
	public static final Parameter<UNested_3Record> P4 = createParameter("P4", org.jooq.test.oracle.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public PNested() {
		super("P_NESTED", Test.TEST);

		addInParameter(P1);
		addInParameter(P2);
		addOutParameter(P3);
		addOutParameter(P4);
	}

	/**
	 * Set the <code>P1</code> parameter IN value to the routine
	 */
	public void setP1(UNested_3Record value) {
		setValue(P1, value);
	}

	/**
	 * Set the <code>P2</code> parameter IN value to the routine
	 */
	public void setP2(UNested_3Record value) {
		setValue(P2, value);
	}

	/**
	 * Get the <code>P3</code> parameter OUT value from the routine
	 */
	public UNested_3Record getP3() {
		return getValue(P3);
	}

	/**
	 * Get the <code>P4</code> parameter OUT value from the routine
	 */
	public UNested_3Record getP4() {
		return getValue(P4);
	}
}
