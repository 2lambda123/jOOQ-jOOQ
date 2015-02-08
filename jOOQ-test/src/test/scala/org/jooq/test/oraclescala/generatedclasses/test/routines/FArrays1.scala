/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import org.jooq.Field
import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberArrayRecord


object FArrays1 {

	/**
	 * The parameter <code>TEST.F_ARRAYS1.RETURN_VALUE</code>.
	 */
	val RETURN_VALUE : Parameter[UNumberArrayRecord] = AbstractRoutine.createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberArrayRecord]), false)

	/**
	 * The parameter <code>TEST.F_ARRAYS1.IN_ARRAY</code>.
	 */
	val IN_ARRAY : Parameter[UNumberArrayRecord] = AbstractRoutine.createParameter("IN_ARRAY", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberArrayRecord]), false)
}

/**
 * This class is generated by jOOQ.
 */
class FArrays1 extends AbstractRoutine[UNumberArrayRecord]("F_ARRAYS1", Test.TEST, org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberArrayRecord])) {
	{
		setReturnParameter(FArrays1.RETURN_VALUE)
		addInParameter(FArrays1.IN_ARRAY)
	}

	/**
	 * Set the <code>IN_ARRAY</code> parameter IN value to the routine
	 */
	def setInArray(value : UNumberArrayRecord) : Unit = {
		setValue(FArrays1.IN_ARRAY, value)
	}

	/**
	 * Set the <code>IN_ARRAY</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	def setInArray(field : Field[UNumberArrayRecord]) : Unit = {
		setField(FArrays1.IN_ARRAY, field)
	}
}
