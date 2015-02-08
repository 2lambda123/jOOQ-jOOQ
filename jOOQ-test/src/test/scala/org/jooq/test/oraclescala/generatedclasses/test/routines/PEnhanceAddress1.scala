/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import java.lang.String

import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UAddressTypeRecord


object PEnhanceAddress1 {

	/**
	 * The parameter <code>TEST.P_ENHANCE_ADDRESS1.ADDRESS</code>.
	 */
	val ADDRESS : Parameter[UAddressTypeRecord] = AbstractRoutine.createParameter("ADDRESS", org.jooq.test.oraclescala.generatedclasses.test.udt.UAddressType.U_ADDRESS_TYPE.getDataType(), false)

	/**
	 * The parameter <code>TEST.P_ENHANCE_ADDRESS1.NO</code>.
	 */
	val NO : Parameter[String] = AbstractRoutine.createParameter("NO", org.jooq.impl.SQLDataType.VARCHAR, false)
}

/**
 * This class is generated by jOOQ.
 */
class PEnhanceAddress1 extends AbstractRoutine[java.lang.Void]("P_ENHANCE_ADDRESS1", Test.TEST) {
	{
		addInParameter(PEnhanceAddress1.ADDRESS)
		addOutParameter(PEnhanceAddress1.NO)
	}

	/**
	 * Set the <code>ADDRESS</code> parameter IN value to the routine
	 */
	def setAddress(value : UAddressTypeRecord) : Unit = {
		setValue(PEnhanceAddress1.ADDRESS, value)
	}

	/**
	 * Get the <code>NO</code> parameter OUT value from the routine
	 */
	def getNo : String = {
		getValue(PEnhanceAddress1.NO)
	}
}
