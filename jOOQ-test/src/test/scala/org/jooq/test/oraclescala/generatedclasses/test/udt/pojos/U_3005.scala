/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.udt.pojos


import java.io.Serializable
import java.lang.Integer


/**
 * This class is generated by jOOQ.
 */
class U_3005(
	  private var id : Integer
) extends Serializable {

	def this() = {
		this(null : Integer)
	}

	def this (value : U_3005) = {
		this(
			  value.id
		)
	}

	def getId : Integer = {
		this.id
	}

	def setId(id : Integer) : Unit = {
		this.id = id
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[U_3005]
		if (id == null) {
			if (other.id != null)
				return false
		}
		else if (!id.equals(other.id))
			return false
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		result = prime * result + (if (id == null) 0 else id.hashCode())
		return result
	}
}
