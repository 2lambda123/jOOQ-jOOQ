/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.records


import java.lang.Integer

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record2
import org.jooq.Row2
import org.jooq.impl.UpdatableRecordImpl
import org.jooq.test.oraclescala.generatedclasses.test.tables.XTestCase_64_69


/**
 * This class is generated by jOOQ.
 */
@Entity
@Table(name = "X_TEST_CASE_64_69", schema = "TEST")
class XTestCase_64_69Record extends UpdatableRecordImpl[XTestCase_64_69Record](XTestCase_64_69.X_TEST_CASE_64_69) with Record2[Integer, Integer] {

	/**
	 * Setter for <code>TEST.X_TEST_CASE_64_69.ID</code>.
	 */
	def setId(value : Integer) : Unit = {
		setValue(0, value)
	}

	/**
	 * Getter for <code>TEST.X_TEST_CASE_64_69.ID</code>.
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@NotNull
	def getId : Integer = {
		val r = getValue(0)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>TEST.X_TEST_CASE_64_69.UNUSED_ID</code>.
	 */
	def setUnusedId(value : Integer) : Unit = {
		setValue(1, value)
	}

	/**
	 * Getter for <code>TEST.X_TEST_CASE_64_69.UNUSED_ID</code>.
	 */
	@Column(name = "UNUSED_ID", precision = 7)
	def getUnusedId : Integer = {
		val r = getValue(1)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------
	override def key() : Record1[Integer] = {
		return super.key.asInstanceOf[ Record1[Integer] ]
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	override def fieldsRow : Row2[Integer, Integer] = {
		super.fieldsRow.asInstanceOf[ Row2[Integer, Integer] ]
	}

	override def valuesRow : Row2[Integer, Integer] = {
		super.valuesRow.asInstanceOf[ Row2[Integer, Integer] ]
	}
	override def field1 : Field[Integer] = XTestCase_64_69.X_TEST_CASE_64_69.ID
	override def field2 : Field[Integer] = XTestCase_64_69.X_TEST_CASE_64_69.UNUSED_ID
	override def value1 : Integer = getId
	override def value2 : Integer = getUnusedId

	override def value1(value : Integer) : XTestCase_64_69Record = {
		setId(value)
		this
	}

	override def value2(value : Integer) : XTestCase_64_69Record = {
		setUnusedId(value)
		this
	}

	override def values(value1 : Integer, value2 : Integer) : XTestCase_64_69Record = {
		this
	}

	/**
	 * Create a detached, initialised XTestCase_64_69Record
	 */
	def this(id : Integer, unusedId : Integer) = {
		this()

		setValue(0, id)
		setValue(1, unusedId)
	}
}
