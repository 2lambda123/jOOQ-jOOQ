/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.U_3005Record


object P3005 {

	/**
	 * The parameter <code>TEST.P3005.P1</code>.
	 */
	val P1 : Parameter[U_3005Record] = AbstractRoutine.createParameter("P1", org.jooq.test.oraclescala.generatedclasses.test.udt.U_3005.U_3005.getDataType(), false)
}

/**
 * This class is generated by jOOQ.
 */
class P3005 extends AbstractRoutine[java.lang.Void]("P3005", Test.TEST) {
	{
		addOutParameter(P3005.P1)
	}

	/**
	 * Get the <code>P1</code> parameter OUT value from the routine
	 */
	def getP1 : U_3005Record = {
		getValue(P3005.P1)
	}
}
