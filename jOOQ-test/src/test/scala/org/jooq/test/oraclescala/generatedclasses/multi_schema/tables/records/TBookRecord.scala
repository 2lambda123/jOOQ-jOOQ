/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.multi_schema.tables.records


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
import org.jooq.Record9
import org.jooq.Row9
import org.jooq.impl.UpdatableRecordImpl
import org.jooq.test.oraclescala.generatedclasses.multi_schema.tables.TBook

import scala.Array


/**
 * This class is generated by jOOQ.
 */
@Entity
@Table(name = "T_BOOK", schema = "MULTI_SCHEMA")
class TBookRecord extends UpdatableRecordImpl[TBookRecord](TBook.T_BOOK) with Record9[Integer, Integer, Integer, Integer, String, Integer, Integer, String, Array[scala.Byte]] {

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.ID</code>.
	 */
	def setId(value : Integer) : Unit = {
		setValue(0, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.ID</code>.
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@NotNull
	def getId : Integer = {
		val r = getValue(0)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.AUTHOR_ID</code>.
	 */
	def setAuthorId(value : Integer) : Unit = {
		setValue(1, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.AUTHOR_ID</code>.
	 */
	@Column(name = "AUTHOR_ID", nullable = false, precision = 7)
	@NotNull
	def getAuthorId : Integer = {
		val r = getValue(1)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.CO_AUTHOR_ID</code>.
	 */
	def setCoAuthorId(value : Integer) : Unit = {
		setValue(2, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.CO_AUTHOR_ID</code>.
	 */
	@Column(name = "CO_AUTHOR_ID", precision = 7)
	def getCoAuthorId : Integer = {
		val r = getValue(2)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.DETAILS_ID</code>.
	 */
	def setDetailsId(value : Integer) : Unit = {
		setValue(3, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.DETAILS_ID</code>.
	 */
	@Column(name = "DETAILS_ID", precision = 7)
	def getDetailsId : Integer = {
		val r = getValue(3)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.TITLE</code>.
	 */
	def setTitle(value : String) : Unit = {
		setValue(4, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.TITLE</code>.
	 */
	@Column(name = "TITLE", nullable = false, length = 400)
	@NotNull
	@Size(max = 400)
	def getTitle : String = {
		val r = getValue(4)
		if (r == null) null else r.asInstanceOf[String]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.PUBLISHED_IN</code>.
	 */
	def setPublishedIn(value : Integer) : Unit = {
		setValue(5, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.PUBLISHED_IN</code>.
	 */
	@Column(name = "PUBLISHED_IN", nullable = false, precision = 7)
	@NotNull
	def getPublishedIn : Integer = {
		val r = getValue(5)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.LANGUAGE_ID</code>.
	 */
	def setLanguageId(value : Integer) : Unit = {
		setValue(6, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.LANGUAGE_ID</code>.
	 */
	@Column(name = "LANGUAGE_ID", nullable = false, precision = 7)
	@NotNull
	def getLanguageId : Integer = {
		val r = getValue(6)
		if (r == null) null else r.asInstanceOf[Integer]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.CONTENT_TEXT</code>.
	 */
	def setContentText(value : String) : Unit = {
		setValue(7, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.CONTENT_TEXT</code>.
	 */
	@Column(name = "CONTENT_TEXT")
	def getContentText : String = {
		val r = getValue(7)
		if (r == null) null else r.asInstanceOf[String]
	}

	/**
	 * Setter for <code>MULTI_SCHEMA.T_BOOK.CONTENT_PDF</code>.
	 */
	def setContentPdf(value : Array[scala.Byte]) : Unit = {
		setValue(8, value)
	}

	/**
	 * Getter for <code>MULTI_SCHEMA.T_BOOK.CONTENT_PDF</code>.
	 */
	@Column(name = "CONTENT_PDF")
	def getContentPdf : Array[scala.Byte] = {
		val r = getValue(8)
		if (r == null) null else r.asInstanceOf[Array[scala.Byte]]
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------
	override def key() : Record1[Integer] = {
		return super.key.asInstanceOf[ Record1[Integer] ]
	}

	// -------------------------------------------------------------------------
	// Record9 type implementation
	// -------------------------------------------------------------------------

	override def fieldsRow : Row9[Integer, Integer, Integer, Integer, String, Integer, Integer, String, Array[scala.Byte]] = {
		super.fieldsRow.asInstanceOf[ Row9[Integer, Integer, Integer, Integer, String, Integer, Integer, String, Array[scala.Byte]] ]
	}

	override def valuesRow : Row9[Integer, Integer, Integer, Integer, String, Integer, Integer, String, Array[scala.Byte]] = {
		super.valuesRow.asInstanceOf[ Row9[Integer, Integer, Integer, Integer, String, Integer, Integer, String, Array[scala.Byte]] ]
	}
	override def field1 : Field[Integer] = TBook.T_BOOK.ID
	override def field2 : Field[Integer] = TBook.T_BOOK.AUTHOR_ID
	override def field3 : Field[Integer] = TBook.T_BOOK.CO_AUTHOR_ID
	override def field4 : Field[Integer] = TBook.T_BOOK.DETAILS_ID
	override def field5 : Field[String] = TBook.T_BOOK.TITLE
	override def field6 : Field[Integer] = TBook.T_BOOK.PUBLISHED_IN
	override def field7 : Field[Integer] = TBook.T_BOOK.LANGUAGE_ID
	override def field8 : Field[String] = TBook.T_BOOK.CONTENT_TEXT
	override def field9 : Field[Array[scala.Byte]] = TBook.T_BOOK.CONTENT_PDF
	override def value1 : Integer = getId
	override def value2 : Integer = getAuthorId
	override def value3 : Integer = getCoAuthorId
	override def value4 : Integer = getDetailsId
	override def value5 : String = getTitle
	override def value6 : Integer = getPublishedIn
	override def value7 : Integer = getLanguageId
	override def value8 : String = getContentText
	override def value9 : Array[scala.Byte] = getContentPdf

	override def value1(value : Integer) : TBookRecord = {
		setId(value)
		this
	}

	override def value2(value : Integer) : TBookRecord = {
		setAuthorId(value)
		this
	}

	override def value3(value : Integer) : TBookRecord = {
		setCoAuthorId(value)
		this
	}

	override def value4(value : Integer) : TBookRecord = {
		setDetailsId(value)
		this
	}

	override def value5(value : String) : TBookRecord = {
		setTitle(value)
		this
	}

	override def value6(value : Integer) : TBookRecord = {
		setPublishedIn(value)
		this
	}

	override def value7(value : Integer) : TBookRecord = {
		setLanguageId(value)
		this
	}

	override def value8(value : String) : TBookRecord = {
		setContentText(value)
		this
	}

	override def value9(value : Array[scala.Byte]) : TBookRecord = {
		setContentPdf(value)
		this
	}

	override def values(value1 : Integer, value2 : Integer, value3 : Integer, value4 : Integer, value5 : String, value6 : Integer, value7 : Integer, value8 : String, value9 : Array[scala.Byte]) : TBookRecord = {
		this
	}

	/**
	 * Create a detached, initialised TBookRecord
	 */
	def this(id : Integer, authorId : Integer, coAuthorId : Integer, detailsId : Integer, title : String, publishedIn : Integer, languageId : Integer, contentText : String, contentPdf : Array[scala.Byte]) = {
		this()

		setValue(0, id)
		setValue(1, authorId)
		setValue(2, coAuthorId)
		setValue(3, detailsId)
		setValue(4, title)
		setValue(5, publishedIn)
		setValue(6, languageId)
		setValue(7, contentText)
		setValue(8, contentPdf)
	}
}
