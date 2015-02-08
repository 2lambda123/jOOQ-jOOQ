/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.pojos


import java.io.Serializable
import java.lang.Integer
import java.lang.String
import java.sql.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import org.jooq.test.oraclescala.generatedclasses.test.udt.pojos.UAddressType


/**
 * An entity holding authors of books
 */
@Entity
@Table(name = "T_AUTHOR", schema = "TEST")
class TAuthor(
	  private var id : Integer     
	, private var firstName : String      
	, private var lastName : String      
	, private var dateOfBirth : Date        
	, private var yearOfBirth : Integer     
	, private var address : UAddressType
) extends Serializable {

	def this() = {
		this(null, null, null, null, null, null)
	}

	def this (value : TAuthor) = {
		this(
			  value.id
			, value.firstName
			, value.lastName
			, value.dateOfBirth
			, value.yearOfBirth
			, value.address
		)
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@NotNull
	def getId : Integer = {
		this.id
	}

	def setId(id : Integer) : Unit = {
		this.id = id
	}

	@Column(name = "FIRST_NAME", length = 50)
	@Size(max = 50)
	def getFirstName : String = {
		this.firstName
	}

	def setFirstName(firstName : String) : Unit = {
		this.firstName = firstName
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	def getLastName : String = {
		this.lastName
	}

	def setLastName(lastName : String) : Unit = {
		this.lastName = lastName
	}

	@Column(name = "DATE_OF_BIRTH")
	def getDateOfBirth : Date = {
		this.dateOfBirth
	}

	def setDateOfBirth(dateOfBirth : Date) : Unit = {
		this.dateOfBirth = dateOfBirth
	}

	@Column(name = "YEAR_OF_BIRTH", precision = 7)
	def getYearOfBirth : Integer = {
		this.yearOfBirth
	}

	def setYearOfBirth(yearOfBirth : Integer) : Unit = {
		this.yearOfBirth = yearOfBirth
	}

	@Column(name = "ADDRESS")
	def getAddress : UAddressType = {
		this.address
	}

	def setAddress(address : UAddressType) : Unit = {
		this.address = address
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[TAuthor]
		if (id == null) {
			if (other.id != null)
				return false
		}
		else if (!id.equals(other.id))
			return false
		if (firstName == null) {
			if (other.firstName != null)
				return false
		}
		else if (!firstName.equals(other.firstName))
			return false
		if (lastName == null) {
			if (other.lastName != null)
				return false
		}
		else if (!lastName.equals(other.lastName))
			return false
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false
		}
		else if (!dateOfBirth.equals(other.dateOfBirth))
			return false
		if (yearOfBirth == null) {
			if (other.yearOfBirth != null)
				return false
		}
		else if (!yearOfBirth.equals(other.yearOfBirth))
			return false
		if (address == null) {
			if (other.address != null)
				return false
		}
		else if (!address.equals(other.address))
			return false
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		result = prime * result + (if (id == null) 0 else id.hashCode())
		result = prime * result + (if (firstName == null) 0 else firstName.hashCode())
		result = prime * result + (if (lastName == null) 0 else lastName.hashCode())
		result = prime * result + (if (dateOfBirth == null) 0 else dateOfBirth.hashCode())
		result = prime * result + (if (yearOfBirth == null) 0 else yearOfBirth.hashCode())
		result = prime * result + (if (address == null) 0 else address.hashCode())
		return result
	}
}
