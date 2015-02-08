/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNested_3Record


object PNested {

	/**
	 * The parameter <code>TEST.P_NESTED.P1</code>.
	 */
	val P1 : Parameter[UNested_3Record] = AbstractRoutine.createParameter("P1", org.jooq.test.oraclescala.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false)

	/**
	 * The parameter <code>TEST.P_NESTED.P2</code>.
	 */
	val P2 : Parameter[UNested_3Record] = AbstractRoutine.createParameter("P2", org.jooq.test.oraclescala.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false)

	/**
	 * The parameter <code>TEST.P_NESTED.P3</code>.
	 */
	val P3 : Parameter[UNested_3Record] = AbstractRoutine.createParameter("P3", org.jooq.test.oraclescala.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false)

	/**
	 * The parameter <code>TEST.P_NESTED.P4</code>.
	 */
	val P4 : Parameter[UNested_3Record] = AbstractRoutine.createParameter("P4", org.jooq.test.oraclescala.generatedclasses.test.udt.UNested_3.U_NESTED_3.getDataType(), false)
}

/**
 * This class is generated by jOOQ.
 */
class PNested extends AbstractRoutine[java.lang.Void]("P_NESTED", Test.TEST) {
	{
		addInParameter(PNested.P1)
		addInParameter(PNested.P2)
		addOutParameter(PNested.P3)
		addOutParameter(PNested.P4)
	}

	/**
	 * Set the <code>P1</code> parameter IN value to the routine
	 */
	def setP1(value : UNested_3Record) : Unit = {
		setValue(PNested.P1, value)
	}

	/**
	 * Set the <code>P2</code> parameter IN value to the routine
	 */
	def setP2(value : UNested_3Record) : Unit = {
		setValue(PNested.P2, value)
	}

	/**
	 * Get the <code>P3</code> parameter OUT value from the routine
	 */
	def getP3 : UNested_3Record = {
		getValue(PNested.P3)
	}

	/**
	 * Get the <code>P4</code> parameter OUT value from the routine
	 */
	def getP4 : UNested_3Record = {
		getValue(PNested.P4)
	}
}
