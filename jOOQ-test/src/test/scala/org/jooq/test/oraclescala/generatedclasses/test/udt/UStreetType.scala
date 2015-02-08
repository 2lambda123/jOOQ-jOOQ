/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.udt


import java.lang.Class
import java.lang.String

import org.jooq.UDTField
import org.jooq.impl.UDTImpl
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberArrayRecord
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UStreetTypeRecord

import scala.Array


object UStreetType {

	/**
	 * The reference instance of <code>TEST.U_STREET_TYPE</code>
	 */
	val U_STREET_TYPE = new UStreetType

	/**
	 * The attribute <code>TEST.U_STREET_TYPE.STREET</code>.
	 */
	val STREET = U_STREET_TYPE.STREET

	/**
	 * The attribute <code>TEST.U_STREET_TYPE.NO</code>.
	 */
	val NO = U_STREET_TYPE.NO

	/**
	 * The attribute <code>TEST.U_STREET_TYPE.FLOORS</code>.
	 */
	val FLOORS = U_STREET_TYPE.FLOORS

	/**
	 * The attribute <code>TEST.U_STREET_TYPE.F_1323</code>.
	 */
	val F_1323 = U_STREET_TYPE.F_1323

	/**
	 * The attribute <code>TEST.U_STREET_TYPE.F_1326</code>.
	 */
	val F_1326 = U_STREET_TYPE.F_1326
}

/**
 * This class is generated by jOOQ.
 */
class UStreetType extends UDTImpl[UStreetTypeRecord]("U_STREET_TYPE", Test.TEST) {

	/**
	 * The class holding records for this type
	 */
	override def getRecordType : Class[UStreetTypeRecord] = {
		classOf[UStreetTypeRecord]
	}
	private val STREET : UDTField[UStreetTypeRecord, String] = UDTImpl.createField("STREET", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "")
	private val NO : UDTField[UStreetTypeRecord, String] = UDTImpl.createField("NO", org.jooq.impl.SQLDataType.VARCHAR.length(30), this, "")
	private val FLOORS : UDTField[UStreetTypeRecord, UNumberArrayRecord] = UDTImpl.createField("FLOORS", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UNumberArrayRecord]), this, "")
	private val F_1323 : UDTField[UStreetTypeRecord, Array[scala.Byte]] = UDTImpl.createField("F_1323", org.jooq.impl.SQLDataType.BLOB, this, "")
	private val F_1326 : UDTField[UStreetTypeRecord, String] = UDTImpl.createField("F_1326", org.jooq.impl.SQLDataType.CLOB, this, "")

	{
		// Initialise data type
		getDataType
	}
}
