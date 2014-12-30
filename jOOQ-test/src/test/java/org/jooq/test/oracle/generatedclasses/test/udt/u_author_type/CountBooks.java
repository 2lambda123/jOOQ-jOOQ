/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.u_author_type;


import java.math.BigDecimal;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.UAuthorType;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UAuthorTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CountBooks extends AbstractRoutine<BigDecimal> {

	private static final long serialVersionUID = -449719985;

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.COUNT_BOOKS.RETURN_VALUE</code>.
	 */
	public static final Parameter<BigDecimal> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.NUMERIC, false);

	/**
	 * The parameter <code>TEST.U_AUTHOR_TYPE.COUNT_BOOKS.SELF</code>.
	 */
	public static final Parameter<UAuthorTypeRecord> SELF = createParameter("SELF", org.jooq.test.oracle.generatedclasses.test.udt.UAuthorType.U_AUTHOR_TYPE.getDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public CountBooks() {
		super("COUNT_BOOKS", Test.TEST, UAuthorType.U_AUTHOR_TYPE, org.jooq.impl.SQLDataType.NUMERIC);

		setReturnParameter(RETURN_VALUE);
		addInParameter(SELF);
	}

	/**
	 * Set the <code>SELF</code> parameter IN value to the routine
	 */
	public void setSelf(UAuthorTypeRecord value) {
		setValue(SELF, value);
	}

	/**
	 * Set the <code>SELF</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setSelf(Field<UAuthorTypeRecord> field) {
		setField(SELF, field);
	}
}
