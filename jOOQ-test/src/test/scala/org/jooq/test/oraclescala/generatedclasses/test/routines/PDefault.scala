/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import java.lang.Number
import java.lang.String
import java.math.BigDecimal
import java.sql.Date

import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test


object PDefault {

	/**
	 * The parameter <code>TEST.P_DEFAULT.P_IN_NUMBER</code>.
	 */
	val P_IN_NUMBER : Parameter[BigDecimal] = AbstractRoutine.createParameter("P_IN_NUMBER", org.jooq.impl.SQLDataType.NUMERIC.defaulted(true), true)

	/**
	 * The parameter <code>TEST.P_DEFAULT.P_OUT_NUMBER</code>.
	 */
	val P_OUT_NUMBER : Parameter[BigDecimal] = AbstractRoutine.createParameter("P_OUT_NUMBER", org.jooq.impl.SQLDataType.NUMERIC, false)

	/**
	 * The parameter <code>TEST.P_DEFAULT.P_IN_VARCHAR</code>.
	 */
	val P_IN_VARCHAR : Parameter[String] = AbstractRoutine.createParameter("P_IN_VARCHAR", org.jooq.impl.SQLDataType.VARCHAR.defaulted(true), true)

	/**
	 * The parameter <code>TEST.P_DEFAULT.P_OUT_VARCHAR</code>.
	 */
	val P_OUT_VARCHAR : Parameter[String] = AbstractRoutine.createParameter("P_OUT_VARCHAR", org.jooq.impl.SQLDataType.VARCHAR, false)

	/**
	 * The parameter <code>TEST.P_DEFAULT.P_IN_DATE</code>.
	 */
	val P_IN_DATE : Parameter[Date] = AbstractRoutine.createParameter("P_IN_DATE", org.jooq.impl.SQLDataType.DATE.defaulted(true), true)

	/**
	 * The parameter <code>TEST.P_DEFAULT.P_OUT_DATE</code>.
	 */
	val P_OUT_DATE : Parameter[Date] = AbstractRoutine.createParameter("P_OUT_DATE", org.jooq.impl.SQLDataType.DATE, false)
}

/**
 * This class is generated by jOOQ.
 */
class PDefault extends AbstractRoutine[java.lang.Void]("P_DEFAULT", Test.TEST) {
	{
		addInParameter(PDefault.P_IN_NUMBER)
		addOutParameter(PDefault.P_OUT_NUMBER)
		addInParameter(PDefault.P_IN_VARCHAR)
		addOutParameter(PDefault.P_OUT_VARCHAR)
		addInParameter(PDefault.P_IN_DATE)
		addOutParameter(PDefault.P_OUT_DATE)
	}

	/**
	 * Set the <code>P_IN_NUMBER</code> parameter IN value to the routine
	 */
	def setPInNumber(value : Number) : Unit = {
		setNumber(PDefault.P_IN_NUMBER, value)
	}

	/**
	 * Set the <code>P_IN_VARCHAR</code> parameter IN value to the routine
	 */
	def setPInVarchar(value : String) : Unit = {
		setValue(PDefault.P_IN_VARCHAR, value)
	}

	/**
	 * Set the <code>P_IN_DATE</code> parameter IN value to the routine
	 */
	def setPInDate(value : Date) : Unit = {
		setValue(PDefault.P_IN_DATE, value)
	}

	/**
	 * Get the <code>P_OUT_NUMBER</code> parameter OUT value from the routine
	 */
	def getPOutNumber : BigDecimal = {
		getValue(PDefault.P_OUT_NUMBER)
	}

	/**
	 * Get the <code>P_OUT_VARCHAR</code> parameter OUT value from the routine
	 */
	def getPOutVarchar : String = {
		getValue(PDefault.P_OUT_VARCHAR)
	}

	/**
	 * Get the <code>P_OUT_DATE</code> parameter OUT value from the routine
	 */
	def getPOutDate : Date = {
		getValue(PDefault.P_OUT_DATE)
	}
}
