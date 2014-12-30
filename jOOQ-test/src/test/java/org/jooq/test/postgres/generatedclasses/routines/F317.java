/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.routines;


import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.postgres.generatedclasses.Public;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class F317 extends AbstractRoutine<Integer> {

	private static final long serialVersionUID = -1868378578;

	/**
	 * The parameter <code>public.f317.RETURN_VALUE</code>.
	 */
	public static final Parameter<Integer> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER, false);

	/**
	 * The parameter <code>public.f317.p1</code>.
	 */
	public static final Parameter<Integer> P1 = createParameter("p1", org.jooq.impl.SQLDataType.INTEGER, false);

	/**
	 * The parameter <code>public.f317.p2</code>.
	 */
	public static final Parameter<Integer> P2 = createParameter("p2", org.jooq.impl.SQLDataType.INTEGER, false);

	/**
	 * The parameter <code>public.f317.p3</code>.
	 */
	public static final Parameter<Integer> P3 = createParameter("p3", org.jooq.impl.SQLDataType.INTEGER, false);

	/**
	 * The parameter <code>public.f317.p4</code>.
	 */
	public static final Parameter<Integer> P4 = createParameter("p4", org.jooq.impl.SQLDataType.INTEGER, false);

	/**
	 * Create a new routine call instance
	 */
	public F317() {
		super("f317", Public.PUBLIC, org.jooq.impl.SQLDataType.INTEGER);

		setReturnParameter(RETURN_VALUE);
		addInParameter(P1);
		addInParameter(P2);
		addInParameter(P3);
		addInParameter(P4);
	}

	/**
	 * Set the <code>p1</code> parameter IN value to the routine
	 */
	public void setP1(Integer value) {
		setValue(P1, value);
	}

	/**
	 * Set the <code>p1</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public F317 setP1(Field<Integer> field) {
		setField(P1, field);
		return this;
	}

	/**
	 * Set the <code>p2</code> parameter IN value to the routine
	 */
	public void setP2(Integer value) {
		setValue(P2, value);
	}

	/**
	 * Set the <code>p2</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public F317 setP2(Field<Integer> field) {
		setField(P2, field);
		return this;
	}

	/**
	 * Set the <code>p3</code> parameter IN value to the routine
	 */
	public void setP3(Integer value) {
		setValue(P3, value);
	}

	/**
	 * Set the <code>p3</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public F317 setP3(Field<Integer> field) {
		setField(P3, field);
		return this;
	}

	/**
	 * Set the <code>p4</code> parameter IN value to the routine
	 */
	public void setP4(Integer value) {
		setValue(P4, value);
	}

	/**
	 * Set the <code>p4</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public F317 setP4(Field<Integer> field) {
		setField(P4, field);
		return this;
	}
}
