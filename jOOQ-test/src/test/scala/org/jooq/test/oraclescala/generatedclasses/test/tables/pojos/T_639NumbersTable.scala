/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.pojos


import java.io.Serializable
import java.lang.Byte
import java.lang.Double
import java.lang.Integer
import java.lang.Long
import java.lang.Short
import java.math.BigDecimal
import java.math.BigInteger

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull


/**
 * This class is generated by jOOQ.
 */
@Entity
@Table(name = "T_639_NUMBERS_TABLE", schema = "TEST")
class T_639NumbersTable(
	  private var id : Integer   
	, private var byte_ : Byte      
	, private var short_ : Short     
	, private var integer : Integer   
	, private var long_ : Long      
	, private var byteDecimal : Byte      
	, private var shortDecimal : Short     
	, private var integerDecimal : Integer   
	, private var longDecimal : Long      
	, private var bigInteger : BigInteger
	, private var bigDecimal : BigDecimal
	, private var float_ : Double    
	, private var real : Double    
) extends Serializable {

	def this() = {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null)
	}

	def this (value : T_639NumbersTable) = {
		this(
			  value.id
			, value.byte_
			, value.short_
			, value.integer
			, value.long_
			, value.byteDecimal
			, value.shortDecimal
			, value.integerDecimal
			, value.longDecimal
			, value.bigInteger
			, value.bigDecimal
			, value.float_
			, value.real
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

	@Column(name = "BYTE", precision = 2)
	def getByte : Byte = {
		this.byte_
	}

	def setByte(byte_ : Byte) : Unit = {
		this.byte_ = byte_
	}

	@Column(name = "SHORT", precision = 4)
	def getShort : Short = {
		this.short_
	}

	def setShort(short_ : Short) : Unit = {
		this.short_ = short_
	}

	@Column(name = "INTEGER", precision = 7)
	def getInteger : Integer = {
		this.integer
	}

	def setInteger(integer : Integer) : Unit = {
		this.integer = integer
	}

	@Column(name = "LONG", precision = 18)
	def getLong : Long = {
		this.long_
	}

	def setLong(long_ : Long) : Unit = {
		this.long_ = long_
	}

	@Column(name = "BYTE_DECIMAL", precision = 2)
	def getByteDecimal : Byte = {
		this.byteDecimal
	}

	def setByteDecimal(byteDecimal : Byte) : Unit = {
		this.byteDecimal = byteDecimal
	}

	@Column(name = "SHORT_DECIMAL", precision = 4)
	def getShortDecimal : Short = {
		this.shortDecimal
	}

	def setShortDecimal(shortDecimal : Short) : Unit = {
		this.shortDecimal = shortDecimal
	}

	@Column(name = "INTEGER_DECIMAL", precision = 9)
	def getIntegerDecimal : Integer = {
		this.integerDecimal
	}

	def setIntegerDecimal(integerDecimal : Integer) : Unit = {
		this.integerDecimal = integerDecimal
	}

	@Column(name = "LONG_DECIMAL", precision = 18)
	def getLongDecimal : Long = {
		this.longDecimal
	}

	def setLongDecimal(longDecimal : Long) : Unit = {
		this.longDecimal = longDecimal
	}

	@Column(name = "BIG_INTEGER", precision = 22)
	def getBigInteger : BigInteger = {
		this.bigInteger
	}

	def setBigInteger(bigInteger : BigInteger) : Unit = {
		this.bigInteger = bigInteger
	}

	@Column(name = "BIG_DECIMAL", precision = 22, scale = 5)
	def getBigDecimal : BigDecimal = {
		this.bigDecimal
	}

	def setBigDecimal(bigDecimal : BigDecimal) : Unit = {
		this.bigDecimal = bigDecimal
	}

	@Column(name = "FLOAT", precision = 126)
	def getFloat : Double = {
		this.float_
	}

	def setFloat(float_ : Double) : Unit = {
		this.float_ = float_
	}

	@Column(name = "REAL", precision = 63)
	def getReal : Double = {
		this.real
	}

	def setReal(real : Double) : Unit = {
		this.real = real
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[T_639NumbersTable]
		if (id == null) {
			if (other.id != null)
				return false
		}
		else if (!id.equals(other.id))
			return false
		if (byte_ == null) {
			if (other.byte_ != null)
				return false
		}
		else if (!byte_.equals(other.byte_))
			return false
		if (short_ == null) {
			if (other.short_ != null)
				return false
		}
		else if (!short_.equals(other.short_))
			return false
		if (integer == null) {
			if (other.integer != null)
				return false
		}
		else if (!integer.equals(other.integer))
			return false
		if (long_ == null) {
			if (other.long_ != null)
				return false
		}
		else if (!long_.equals(other.long_))
			return false
		if (byteDecimal == null) {
			if (other.byteDecimal != null)
				return false
		}
		else if (!byteDecimal.equals(other.byteDecimal))
			return false
		if (shortDecimal == null) {
			if (other.shortDecimal != null)
				return false
		}
		else if (!shortDecimal.equals(other.shortDecimal))
			return false
		if (integerDecimal == null) {
			if (other.integerDecimal != null)
				return false
		}
		else if (!integerDecimal.equals(other.integerDecimal))
			return false
		if (longDecimal == null) {
			if (other.longDecimal != null)
				return false
		}
		else if (!longDecimal.equals(other.longDecimal))
			return false
		if (bigInteger == null) {
			if (other.bigInteger != null)
				return false
		}
		else if (!bigInteger.equals(other.bigInteger))
			return false
		if (bigDecimal == null) {
			if (other.bigDecimal != null)
				return false
		}
		else if (!bigDecimal.equals(other.bigDecimal))
			return false
		if (float_ == null) {
			if (other.float_ != null)
				return false
		}
		else if (!float_.equals(other.float_))
			return false
		if (real == null) {
			if (other.real != null)
				return false
		}
		else if (!real.equals(other.real))
			return false
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		result = prime * result + (if (id == null) 0 else id.hashCode())
		result = prime * result + (if (byte_ == null) 0 else byte_.hashCode())
		result = prime * result + (if (short_ == null) 0 else short_.hashCode())
		result = prime * result + (if (integer == null) 0 else integer.hashCode())
		result = prime * result + (if (long_ == null) 0 else long_.hashCode())
		result = prime * result + (if (byteDecimal == null) 0 else byteDecimal.hashCode())
		result = prime * result + (if (shortDecimal == null) 0 else shortDecimal.hashCode())
		result = prime * result + (if (integerDecimal == null) 0 else integerDecimal.hashCode())
		result = prime * result + (if (longDecimal == null) 0 else longDecimal.hashCode())
		result = prime * result + (if (bigInteger == null) 0 else bigInteger.hashCode())
		result = prime * result + (if (bigDecimal == null) 0 else bigDecimal.hashCode())
		result = prime * result + (if (float_ == null) 0 else float_.hashCode())
		result = prime * result + (if (real == null) 0 else real.hashCode())
		return result
	}
}
