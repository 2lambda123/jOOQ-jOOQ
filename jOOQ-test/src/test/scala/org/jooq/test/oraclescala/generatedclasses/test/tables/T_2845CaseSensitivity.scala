/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables


import java.lang.Class
import java.lang.String
import java.math.BigDecimal
import java.util.Arrays
import java.util.List

import org.jooq.Field
import org.jooq.Table
import org.jooq.TableField
import org.jooq.UniqueKey
import org.jooq.impl.TableImpl
import org.jooq.test.oraclescala.generatedclasses.test.Keys
import org.jooq.test.oraclescala.generatedclasses.test.Test
import org.jooq.test.oraclescala.generatedclasses.test.tables.T_2845CaseSensitivity
import org.jooq.test.oraclescala.generatedclasses.test.tables.records.T_2845CaseSensitivityRecord


object T_2845CaseSensitivity {

	/**
	 * The reference instance of <code>TEST.T_2845_CASE_sensitivity</code>
	 */
	val T_2845_CASE_SENSITIVITY = new T_2845CaseSensitivity
}

/**
 * This class is generated by jOOQ.
 */
class T_2845CaseSensitivity(alias : String, aliased : Table[T_2845CaseSensitivityRecord], parameters : Array[ Field[_] ]) extends TableImpl[T_2845CaseSensitivityRecord](alias, Test.TEST, aliased, parameters, "") {

	/**
	 * The class holding records for this type
	 */
	override def getRecordType : Class[T_2845CaseSensitivityRecord] = {
		classOf[T_2845CaseSensitivityRecord]
	}

	/**
	 * The column <code>TEST.T_2845_CASE_sensitivity.ID</code>.
	 */
	val ID : TableField[T_2845CaseSensitivityRecord, BigDecimal] = createField("ID", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), "")

	/**
	 * The column <code>TEST.T_2845_CASE_sensitivity.INSENSITIVE</code>.
	 */
	val INSENSITIVE : TableField[T_2845CaseSensitivityRecord, BigDecimal] = createField("INSENSITIVE", org.jooq.impl.SQLDataType.NUMERIC, "")

	/**
	 * The column <code>TEST.T_2845_CASE_sensitivity.UPPER</code>.
	 */
	val UPPER : TableField[T_2845CaseSensitivityRecord, BigDecimal] = createField("UPPER", org.jooq.impl.SQLDataType.NUMERIC, "")

	/**
	 * The column <code>TEST.T_2845_CASE_sensitivity.lower</code>.
	 */
	val LOWER : TableField[T_2845CaseSensitivityRecord, BigDecimal] = createField("lower", org.jooq.impl.SQLDataType.NUMERIC, "")

	/**
	 * The column <code>TEST.T_2845_CASE_sensitivity.Mixed</code>.
	 */
	val MIXED : TableField[T_2845CaseSensitivityRecord, BigDecimal] = createField("Mixed", org.jooq.impl.SQLDataType.NUMERIC, "")

	/**
	 * Create a <code>TEST.T_2845_CASE_sensitivity</code> table reference
	 */
	def this() = {
		this("T_2845_CASE_sensitivity", null, null)
	}

	/**
	 * Create an aliased <code>TEST.T_2845_CASE_sensitivity</code> table reference
	 */
	def this(alias : String) = {
		this(alias, T_2845CaseSensitivity.T_2845_CASE_SENSITIVITY, null)
	}

	private def this(alias : String, aliased : Table[T_2845CaseSensitivityRecord]) = {
		this(alias, aliased, null)
	}

	override def getPrimaryKey : UniqueKey[T_2845CaseSensitivityRecord] = {
		Keys.PK_T_2845_CASE_SENSITIVITY
	}

	override def getKeys : List[ UniqueKey[T_2845CaseSensitivityRecord] ] = {
		return Arrays.asList[ UniqueKey[T_2845CaseSensitivityRecord] ](Keys.PK_T_2845_CASE_SENSITIVITY)
	}

	override def as(alias : String) : T_2845CaseSensitivity = {
		new T_2845CaseSensitivity(alias, this)
	}

	/**
	 * Rename this table
	 */
	def rename(name : String) : T_2845CaseSensitivity = {
		new T_2845CaseSensitivity(name, null)
	}
}
