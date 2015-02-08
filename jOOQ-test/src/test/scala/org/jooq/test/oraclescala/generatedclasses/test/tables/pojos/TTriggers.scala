/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.pojos


import java.io.Serializable
import java.lang.Integer

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull


/**
 * This class is generated by jOOQ.
 */
@Entity
@Table(name = "T_TRIGGERS", schema = "TEST")
class TTriggers(
	  private var idGenerated : Integer
	, private var id : Integer
	, private var counter : Integer
) extends Serializable {

	def this() = {
		this(null, null, null)
	}

	def this (value : TTriggers) = {
		this(
			  value.idGenerated
			, value.id
			, value.counter
		)
	}

	@Id
	@Column(name = "ID_GENERATED", unique = true, nullable = false, precision = 7)
	@NotNull
	def getIdGenerated : Integer = {
		this.idGenerated
	}

	def setIdGenerated(idGenerated : Integer) : Unit = {
		this.idGenerated = idGenerated
	}

	@Column(name = "ID", nullable = false, precision = 7)
	@NotNull
	def getId : Integer = {
		this.id
	}

	def setId(id : Integer) : Unit = {
		this.id = id
	}

	@Column(name = "COUNTER", nullable = false, precision = 7)
	@NotNull
	def getCounter : Integer = {
		this.counter
	}

	def setCounter(counter : Integer) : Unit = {
		this.counter = counter
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[TTriggers]
		if (idGenerated == null) {
			if (other.idGenerated != null)
				return false
		}
		else if (!idGenerated.equals(other.idGenerated))
			return false
		if (id == null) {
			if (other.id != null)
				return false
		}
		else if (!id.equals(other.id))
			return false
		if (counter == null) {
			if (other.counter != null)
				return false
		}
		else if (!counter.equals(other.counter))
			return false
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		result = prime * result + (if (idGenerated == null) 0 else idGenerated.hashCode())
		result = prime * result + (if (id == null) 0 else id.hashCode())
		result = prime * result + (if (counter == null) 0 else counter.hashCode())
		return result
	}
}
