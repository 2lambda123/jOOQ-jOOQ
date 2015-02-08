/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.udt.pojos


import java.io.Serializable
import java.lang.Integer
import java.lang.String

import javax.validation.constraints.Size


/**
 * This class is generated by jOOQ.
 */
class UBookType(
	  private var id : Integer
	, private var title : String 
) extends Serializable {

	def this() = {
		this(null, null)
	}

	def this (value : UBookType) = {
		this(
			  value.id
			, value.title
		)
	}

	def getId : Integer = {
		this.id
	}

	def setId(id : Integer) : Unit = {
		this.id = id
	}

	@Size(max = 400)
	def getTitle : String = {
		this.title
	}

	def setTitle(title : String) : Unit = {
		this.title = title
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[UBookType]
		if (id == null) {
			if (other.id != null)
				return false
		}
		else if (!id.equals(other.id))
			return false
		if (title == null) {
			if (other.title != null)
				return false
		}
		else if (!title.equals(other.title))
			return false
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		result = prime * result + (if (id == null) 0 else id.hashCode())
		result = prime * result + (if (title == null) 0 else title.hashCode())
		return result
	}
}
