/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberTableRecord


object PTables1 {

	/**
	 * The parameter <code>TEST.P_TABLES1.IN_TABLE</code>.
	 */
	val IN_TABLE : Parameter[UNumberTableRecord] = AbstractRoutine.createParameter("IN_TABLE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberTableRecord]), false)

	/**
	 * The parameter <code>TEST.P_TABLES1.OUT_TABLE</code>.
	 */
	val OUT_TABLE : Parameter[UNumberTableRecord] = AbstractRoutine.createParameter("OUT_TABLE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberTableRecord]), false)
}

/**
 * This class is generated by jOOQ.
 */
class PTables1 extends AbstractRoutine[java.lang.Void]("P_TABLES1", Test.TEST) {
	{
		addInParameter(PTables1.IN_TABLE)
		addOutParameter(PTables1.OUT_TABLE)
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter IN value to the routine
	 */
	def setInTable(value : UNumberTableRecord) : Unit = {
		setValue(PTables1.IN_TABLE, value)
	}

	/**
	 * Get the <code>OUT_TABLE</code> parameter OUT value from the routine
	 */
	def getOutTable : UNumberTableRecord = {
		getValue(PTables1.OUT_TABLE)
	}
}
