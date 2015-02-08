/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oraclescala.generatedclasses.test.tables.pojos


import java.io.Serializable
import java.lang.Integer
import java.lang.String
import java.math.BigDecimal
import java.math.BigInteger

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.UniqueConstraint
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


/**
 * An unused table in the same schema.
 * 
 * "Its comments contain special characters"
 */
@Entity
@Table(name = "X_UNUSED", schema = "TEST", uniqueConstraints = Array(
	new UniqueConstraint(columnNames = Array("ID", "NAME"))
))
class XUnused(
	  private var id : Integer   
	, private var name : String    
	, private var bigInteger : BigInteger
	, private var idRef : Integer   
	, private var class_ : Integer   
	, private var fields : Integer   
	, private var configuration : Integer   
	, private var uDT : Integer   
	, private var metaData : Integer   
	, private var type0 : Integer   
	, private var primaryKey : Integer   
	, private var primarykey : Integer   
	, private var nameRef : String    
	, private var field_737 : BigDecimal
	, private var msUnusedIdRef : Integer   
	, private var msUnusedNameRef : String    
) extends Serializable {

	def this() = {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
	}

	def this (value : XUnused) = {
		this(
			  value.id
			, value.name
			, value.bigInteger
			, value.idRef
			, value.class_
			, value.fields
			, value.configuration
			, value.uDT
			, value.metaData
			, value.type0
			, value.primaryKey
			, value.primarykey
			, value.nameRef
			, value.field_737
			, value.msUnusedIdRef
			, value.msUnusedNameRef
		)
	}

	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@NotNull
	def getId : Integer = {
		this.id
	}

	def setId(id : Integer) : Unit = {
		this.id = id
	}

	@Column(name = "NAME", nullable = false, length = 10)
	@NotNull
	@Size(max = 10)
	def getName : String = {
		this.name
	}

	def setName(name : String) : Unit = {
		this.name = name
	}

	@Column(name = "BIG_INTEGER", precision = 38)
	def getBigInteger : BigInteger = {
		this.bigInteger
	}

	def setBigInteger(bigInteger : BigInteger) : Unit = {
		this.bigInteger = bigInteger
	}

	@Column(name = "ID_REF", precision = 7)
	def getIdRef : Integer = {
		this.idRef
	}

	def setIdRef(idRef : Integer) : Unit = {
		this.idRef = idRef
	}

	@Column(name = "CLASS", precision = 7)
	def getClass_ : Integer = {
		this.class_
	}

	def setClass_(class_ : Integer) : Unit = {
		this.class_ = class_
	}

	@Column(name = "FIELDS", precision = 7)
	def getFields : Integer = {
		this.fields
	}

	def setFields(fields : Integer) : Unit = {
		this.fields = fields
	}

	@Column(name = "CONFIGURATION", precision = 7)
	def getConfiguration : Integer = {
		this.configuration
	}

	def setConfiguration(configuration : Integer) : Unit = {
		this.configuration = configuration
	}

	@Column(name = "U_D_T", precision = 7)
	def getUDT : Integer = {
		this.uDT
	}

	def setUDT(uDT : Integer) : Unit = {
		this.uDT = uDT
	}

	@Column(name = "META_DATA", precision = 7)
	def getMetaData : Integer = {
		this.metaData
	}

	def setMetaData(metaData : Integer) : Unit = {
		this.metaData = metaData
	}

	@Column(name = "TYPE0", precision = 7)
	def getType0 : Integer = {
		this.type0
	}

	def setType0(type0 : Integer) : Unit = {
		this.type0 = type0
	}

	@Column(name = "PRIMARY_KEY", precision = 7)
	def getPrimaryKey_ : Integer = {
		this.primaryKey
	}

	def setPrimaryKey_(primaryKey : Integer) : Unit = {
		this.primaryKey = primaryKey
	}

	@Column(name = "PRIMARYKEY", precision = 7)
	def getPrimarykey : Integer = {
		this.primarykey
	}

	def setPrimarykey(primarykey : Integer) : Unit = {
		this.primarykey = primarykey
	}

	@Column(name = "NAME_REF", length = 10)
	@Size(max = 10)
	def getNameRef : String = {
		this.nameRef
	}

	def setNameRef(nameRef : String) : Unit = {
		this.nameRef = nameRef
	}

	@Column(name = "FIELD 737", precision = 25, scale = 2)
	def getField_737 : BigDecimal = {
		this.field_737
	}

	def setField_737(field_737 : BigDecimal) : Unit = {
		this.field_737 = field_737
	}

	@Column(name = "MS_UNUSED_ID_REF", precision = 7)
	def getMsUnusedIdRef : Integer = {
		this.msUnusedIdRef
	}

	def setMsUnusedIdRef(msUnusedIdRef : Integer) : Unit = {
		this.msUnusedIdRef = msUnusedIdRef
	}

	@Column(name = "MS_UNUSED_NAME_REF", length = 10)
	@Size(max = 10)
	def getMsUnusedNameRef : String = {
		this.msUnusedNameRef
	}

	def setMsUnusedNameRef(msUnusedNameRef : String) : Unit = {
		this.msUnusedNameRef = msUnusedNameRef
	}

	override def equals(obj : Any) : scala.Boolean = {
		if (this == obj)
			return true
		if (obj == null)
			return false
		if (getClass() != obj.getClass())
			return false
		val other = obj.asInstanceOf[XUnused]
		if (id == null) {
			if (other.id != null)
				return false
		}
		else if (!id.equals(other.id))
			return false
		if (name == null) {
			if (other.name != null)
				return false
		}
		else if (!name.equals(other.name))
			return false
		if (bigInteger == null) {
			if (other.bigInteger != null)
				return false
		}
		else if (!bigInteger.equals(other.bigInteger))
			return false
		if (idRef == null) {
			if (other.idRef != null)
				return false
		}
		else if (!idRef.equals(other.idRef))
			return false
		if (class_ == null) {
			if (other.class_ != null)
				return false
		}
		else if (!class_.equals(other.class_))
			return false
		if (fields == null) {
			if (other.fields != null)
				return false
		}
		else if (!fields.equals(other.fields))
			return false
		if (configuration == null) {
			if (other.configuration != null)
				return false
		}
		else if (!configuration.equals(other.configuration))
			return false
		if (uDT == null) {
			if (other.uDT != null)
				return false
		}
		else if (!uDT.equals(other.uDT))
			return false
		if (metaData == null) {
			if (other.metaData != null)
				return false
		}
		else if (!metaData.equals(other.metaData))
			return false
		if (type0 == null) {
			if (other.type0 != null)
				return false
		}
		else if (!type0.equals(other.type0))
			return false
		if (primaryKey == null) {
			if (other.primaryKey != null)
				return false
		}
		else if (!primaryKey.equals(other.primaryKey))
			return false
		if (primarykey == null) {
			if (other.primarykey != null)
				return false
		}
		else if (!primarykey.equals(other.primarykey))
			return false
		if (nameRef == null) {
			if (other.nameRef != null)
				return false
		}
		else if (!nameRef.equals(other.nameRef))
			return false
		if (field_737 == null) {
			if (other.field_737 != null)
				return false
		}
		else if (!field_737.equals(other.field_737))
			return false
		if (msUnusedIdRef == null) {
			if (other.msUnusedIdRef != null)
				return false
		}
		else if (!msUnusedIdRef.equals(other.msUnusedIdRef))
			return false
		if (msUnusedNameRef == null) {
			if (other.msUnusedNameRef != null)
				return false
		}
		else if (!msUnusedNameRef.equals(other.msUnusedNameRef))
			return false
		return true
	}

	override def hashCode : Int = {
		val prime = 31
		var result = 1
		result = prime * result + (if (id == null) 0 else id.hashCode())
		result = prime * result + (if (name == null) 0 else name.hashCode())
		result = prime * result + (if (bigInteger == null) 0 else bigInteger.hashCode())
		result = prime * result + (if (idRef == null) 0 else idRef.hashCode())
		result = prime * result + (if (class_ == null) 0 else class_.hashCode())
		result = prime * result + (if (fields == null) 0 else fields.hashCode())
		result = prime * result + (if (configuration == null) 0 else configuration.hashCode())
		result = prime * result + (if (uDT == null) 0 else uDT.hashCode())
		result = prime * result + (if (metaData == null) 0 else metaData.hashCode())
		result = prime * result + (if (type0 == null) 0 else type0.hashCode())
		result = prime * result + (if (primaryKey == null) 0 else primaryKey.hashCode())
		result = prime * result + (if (primarykey == null) 0 else primarykey.hashCode())
		result = prime * result + (if (nameRef == null) 0 else nameRef.hashCode())
		result = prime * result + (if (field_737 == null) 0 else field_737.hashCode())
		result = prime * result + (if (msUnusedIdRef == null) 0 else msUnusedIdRef.hashCode())
		result = prime * result + (if (msUnusedNameRef == null) 0 else msUnusedNameRef.hashCode())
		return result
	}
}
