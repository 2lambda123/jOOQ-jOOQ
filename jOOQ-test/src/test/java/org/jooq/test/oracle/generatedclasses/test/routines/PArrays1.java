/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PArrays1 extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = 798152839;

	/**
	 * The parameter <code>TEST.P_ARRAYS1.IN_ARRAY</code>.
	 */
	public static final Parameter<UNumberArrayRecord> IN_ARRAY = createParameter("IN_ARRAY", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord.class), false);

	/**
	 * The parameter <code>TEST.P_ARRAYS1.OUT_ARRAY</code>.
	 */
	public static final Parameter<UNumberArrayRecord> OUT_ARRAY = createParameter("OUT_ARRAY", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public PArrays1() {
		super("P_ARRAYS1", Test.TEST);

		addInParameter(IN_ARRAY);
		addOutParameter(OUT_ARRAY);
	}

	/**
	 * Set the <code>IN_ARRAY</code> parameter IN value to the routine
	 */
	public void setInArray(UNumberArrayRecord value) {
		setValue(IN_ARRAY, value);
	}

	/**
	 * Get the <code>OUT_ARRAY</code> parameter OUT value from the routine
	 */
	public UNumberArrayRecord getOutArray() {
		return getValue(OUT_ARRAY);
	}
}
