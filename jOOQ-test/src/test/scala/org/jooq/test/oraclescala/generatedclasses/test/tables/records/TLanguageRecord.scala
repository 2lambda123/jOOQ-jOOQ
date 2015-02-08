/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.records


import java.lang.Integer
import java.lang.String

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import org.jooq.Field
import org.jooq.Record1
import org.jooq.Record4
import org.jooq.Row4
import org.jooq.impl.UpdatableRecordImpl
import org.jooq.test.oraclescala.generatedclasses.test.tables.TLanguage


/**
 * An entity holding language master data
 * 
 * Oh oh. Newline character.
 * 
 * "Quotes". And \Escaping\That\Shouldn't\Be\Escaping
 * 
 * Oh, and beware of end-of-javadoc * /
 */
@Entity
@Table(name = "T_LANGUAGE", schema = "TEST")
class TLanguageRecord extends UpdatableRecordImpl[TLanguageRecord](TLanguage.T_LANGUAGE) with Record4[String, String, String, Integer] {

	/**
	 * Setter for <code>TEST.T_LANGUAGE.CD</code>. The language ISO code
	 */
	def setCd(value : String) : Unit = {
		setValue(0, value)
	}

	/**
	 * Getter for <code>TEST.T_LANGUAGE.CD</code>. The language ISO code
	 */
	@Column(name = "CD", nullable = false, length = 2)
	@NotNull
	@Size(max = 2)
	def getCd : String = {
		val r = getValue(0)
		if (r == null) null else r.asInstanceOf[String]
	}

	/**
	 * Setter for <code>TEST.T_LANGUAGE.DESCRIPTION</code>. The language description
	 */
	def setDescription(value : String) : Unit = {
		setValue(1, value)
	}

	/**
	 * Getter for <code>TEST.T_LANGUAGE.DESCRIPTION</code>. The language description
	 */
	@Column(name = "DESCRIPTION", length = 50)
	@Size(max = 50)
	def getDescription : String = {
		val r = getValue(1)
		if (r == null) null else r.asInstanceOf[String]
	}

	/**
	 * Setter for <code>TEST.T_LANGUAGE.DESCRIPTION_ENGLISH</code>.
	 */
	def setDescriptionEnglish(value : String) : Unit = {
		setValue(2, value)
	}

	/**
	 * Getter for <code>TEST.T_LANGUAGE.DESCRIPTION_ENGLISH</code>.
	 */
	@Column(name = "DESCRIPTION_ENGLISH", length = 50)
	@Size(max = 50)
	def getDescriptionEnglish : String = {
		val r = getValue(2)
		if (r == null) null else r.asInstanceOf[String]
	}

	/**
	 * Setter for <code>TEST.T_LANGUAGE.ID</code>. The language ID
	 */
	def setId(value : Integer) : Unit = {
		setValue(3, value)
	}

	/**
	 * Getter for <code>TEST.T_LANGUAGE.ID</code>. The language ID
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@NotNull
	def getId : Integer = {
		val r = getValue(3)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------
	override def key() : Record1[Integer] = {
		return super.key.asInstanceOf[ Record1[Integer] ]
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	override def fieldsRow : Row4[String, String, String, Integer] = {
		super.fieldsRow.asInstanceOf[ Row4[String, String, String, Integer] ]
	}

	override def valuesRow : Row4[String, String, String, Integer] = {
		super.valuesRow.asInstanceOf[ Row4[String, String, String, Integer] ]
	}
	override def field1 : Field[String] = TLanguage.T_LANGUAGE.CD
	override def field2 : Field[String] = TLanguage.T_LANGUAGE.DESCRIPTION
	override def field3 : Field[String] = TLanguage.T_LANGUAGE.DESCRIPTION_ENGLISH
	override def field4 : Field[Integer] = TLanguage.T_LANGUAGE.ID
	override def value1 : String = getCd
	override def value2 : String = getDescription
	override def value3 : String = getDescriptionEnglish
	override def value4 : Integer = getId

	override def value1(value : String) : TLanguageRecord = {
		setCd(value)
		this
	}

	override def value2(value : String) : TLanguageRecord = {
		setDescription(value)
		this
	}

	override def value3(value : String) : TLanguageRecord = {
		setDescriptionEnglish(value)
		this
	}

	override def value4(value : Integer) : TLanguageRecord = {
		setId(value)
		this
	}

	override def values(value1 : String, value2 : String, value3 : String, value4 : Integer) : TLanguageRecord = {
		this
	}

	/**
	 * Create a detached, initialised TLanguageRecord
	 */
	def this(cd : String, description : String, descriptionEnglish : String, id : Integer) = {
		this()

		setValue(0, cd)
		setValue(1, description)
		setValue(2, descriptionEnglish)
		setValue(3, id)
	}
}
