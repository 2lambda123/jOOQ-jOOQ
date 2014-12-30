/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UAddressTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PEnhanceAddress3 extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = -152976088;

	/**
	 * The parameter <code>TEST.P_ENHANCE_ADDRESS3.ADDRESS</code>.
	 */
	public static final Parameter<UAddressTypeRecord> ADDRESS = createParameter("ADDRESS", org.jooq.test.oracle.generatedclasses.test.udt.UAddressType.U_ADDRESS_TYPE.getDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public PEnhanceAddress3() {
		super("P_ENHANCE_ADDRESS3", Test.TEST);

		addInOutParameter(ADDRESS);
	}

	/**
	 * Set the <code>ADDRESS</code> parameter IN value to the routine
	 */
	public void setAddress(UAddressTypeRecord value) {
		setValue(ADDRESS, value);
	}

	/**
	 * Get the <code>ADDRESS</code> parameter OUT value from the routine
	 */
	public UAddressTypeRecord getAddress() {
		return getValue(ADDRESS);
	}
}
