/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.packages.test_synonym_package


import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.test.oraclescala.generatedclasses.multi_schema.udt.records.NumberTableRecord
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.packages.TestSynonymPackage


object TestTable {

	/**
	 * The parameter <code>TEST.TEST_SYNONYM_PACKAGE.TEST_TABLE.RETURN_VALUE</code>.
	 */
	val RETURN_VALUE : Parameter[NumberTableRecord] = AbstractRoutine.createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.multi_schema.udt.records.NumberTableRecord]), false)
}

/**
 * This class is generated by jOOQ.
 */
class TestTable extends AbstractRoutine[NumberTableRecord]("TEST_TABLE", Test.TEST, TestSynonymPackage.TEST_SYNONYM_PACKAGE, org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(classOf[org.jooq.test.oraclescala.generatedclasses.multi_schema.udt.records.NumberTableRecord])) {
	{
		setReturnParameter(TestTable.RETURN_VALUE)
	}
}
