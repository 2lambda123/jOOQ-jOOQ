/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.udt.pojos


import java.io.Serializable


/**
 * This class is generated by jOOQ.
 */
class UInvalidTable(
) extends Serializable {


	def this (value : UInvalidTable) = {
		this(
		)
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[UInvalidTable]
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		return result
	}
}
