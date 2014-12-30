/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.postgres.generatedclasses.Public;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PArrays1 extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -864271563;

	/**
	 * The parameter <code>public.p_arrays.in_array</code>.
	 */
	public static final Parameter<Integer[]> IN_ARRAY = createParameter("in_array", org.jooq.impl.SQLDataType.INTEGER.getArrayDataType(), false);

	/**
	 * The parameter <code>public.p_arrays.out_array</code>.
	 */
	public static final Parameter<Integer[]> OUT_ARRAY = createParameter("out_array", org.jooq.impl.SQLDataType.INTEGER.getArrayDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public PArrays1() {
		super("p_arrays", Public.PUBLIC);

		addInParameter(IN_ARRAY);
		addOutParameter(OUT_ARRAY);
		setOverloaded(true);
	}

	/**
	 * Set the <code>in_array</code> parameter IN value to the routine
	 */
	public void setInArray(Integer[] value) {
		setValue(IN_ARRAY, value);
	}

	/**
	 * Get the <code>out_array</code> parameter OUT value from the routine
	 */
	public Integer[] getOutArray() {
		return getValue(OUT_ARRAY);
	}
}
