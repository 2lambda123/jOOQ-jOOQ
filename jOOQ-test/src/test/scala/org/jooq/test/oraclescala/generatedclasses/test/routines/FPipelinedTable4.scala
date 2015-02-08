/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.routines


import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.udt.records.UBookTableRecord


object FPipelinedTable4 {

	/**
	 * The parameter <code>TEST.F_PIPELINED_TABLE4.RETURN_VALUE</code>.
	 */
	val RETURN_VALUE : Parameter[UBookTableRecord] = AbstractRoutine.createParameter("RETURN_VALUE", org.jooq.test.oraclescala.generatedclasses.test.udt.UBookType.U_BOOK_TYPE.getDataType().asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UBookTableRecord]), false)
}

/**
 * This class is generated by jOOQ.
 */
class FPipelinedTable4 extends AbstractRoutine[UBookTableRecord]("F_PIPELINED_TABLE4", Test.TEST, org.jooq.test.oraclescala.generatedclasses.test.udt.UBookType.U_BOOK_TYPE.getDataType().asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.test.udt.records.UBookTableRecord])) {
	{
		setReturnParameter(FPipelinedTable4.RETURN_VALUE)
	}
}
