/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;


import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FArrays1 extends AbstractRoutine<UNumberArrayRecord> {

	private static final long serialVersionUID = -2040433963;

	/**
	 * The parameter <code>TEST.F_ARRAYS1.RETURN_VALUE</code>.
	 */
	public static final Parameter<UNumberArrayRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord.class), false);

	/**
	 * The parameter <code>TEST.F_ARRAYS1.IN_ARRAY</code>.
	 */
	public static final Parameter<UNumberArrayRecord> IN_ARRAY = createParameter("IN_ARRAY", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public FArrays1() {
		super("F_ARRAYS1", Test.TEST, org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord.class));

		setReturnParameter(RETURN_VALUE);
		addInParameter(IN_ARRAY);
	}

	/**
	 * Set the <code>IN_ARRAY</code> parameter IN value to the routine
	 */
	public void setInArray(UNumberArrayRecord value) {
		setValue(IN_ARRAY, value);
	}

	/**
	 * Set the <code>IN_ARRAY</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setInArray(Field<UNumberArrayRecord> field) {
		setField(IN_ARRAY, field);
	}
}
