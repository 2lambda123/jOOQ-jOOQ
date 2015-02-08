/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import java.lang.Number
import java.math.BigDecimal

import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test


object P1490 {

	/**
	 * The parameter <code>TEST.P1490.value</code>.
	 */
	val VALUE : Parameter[BigDecimal] = AbstractRoutine.createParameter("value", org.jooq.impl.SQLDataType.NUMERIC, false)
}

/**
 * This class is generated by jOOQ.
 */
class P1490 extends AbstractRoutine[java.lang.Void]("P1490", Test.TEST) {
	{
		addInParameter(P1490.VALUE)
	}

	/**
	 * Set the <code>value</code> parameter IN value to the routine
	 */
	def setValue(value : Number) : Unit = {
		setNumber(P1490.VALUE, value)
	}
}
