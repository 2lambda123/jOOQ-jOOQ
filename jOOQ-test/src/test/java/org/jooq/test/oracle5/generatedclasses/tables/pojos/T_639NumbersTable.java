/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_639NumbersTable implements java.io.Serializable {

	private static final long serialVersionUID = -2063341401;

	private java.math.BigDecimal id;
	private java.math.BigDecimal byte_;
	private java.math.BigDecimal short_;
	private java.math.BigDecimal integer;
	private java.math.BigDecimal long_;
	private java.math.BigDecimal byteDecimal;
	private java.math.BigDecimal shortDecimal;
	private java.math.BigDecimal integerDecimal;
	private java.math.BigDecimal longDecimal;
	private java.math.BigDecimal bigInteger;
	private java.math.BigDecimal bigDecimal;

	public T_639NumbersTable() {}

	public T_639NumbersTable(
		java.math.BigDecimal id,
		java.math.BigDecimal byte_,
		java.math.BigDecimal short_,
		java.math.BigDecimal integer,
		java.math.BigDecimal long_,
		java.math.BigDecimal byteDecimal,
		java.math.BigDecimal shortDecimal,
		java.math.BigDecimal integerDecimal,
		java.math.BigDecimal longDecimal,
		java.math.BigDecimal bigInteger,
		java.math.BigDecimal bigDecimal
	) {
		this.id = id;
		this.byte_ = byte_;
		this.short_ = short_;
		this.integer = integer;
		this.long_ = long_;
		this.byteDecimal = byteDecimal;
		this.shortDecimal = shortDecimal;
		this.integerDecimal = integerDecimal;
		this.longDecimal = longDecimal;
		this.bigInteger = bigInteger;
		this.bigDecimal = bigDecimal;
	}

	public java.math.BigDecimal getId() {
		return this.id;
	}

	public void setId(java.math.BigDecimal id) {
		this.id = id;
	}

	public java.math.BigDecimal getByte() {
		return this.byte_;
	}

	public void setByte(java.math.BigDecimal byte_) {
		this.byte_ = byte_;
	}

	public java.math.BigDecimal getShort() {
		return this.short_;
	}

	public void setShort(java.math.BigDecimal short_) {
		this.short_ = short_;
	}

	public java.math.BigDecimal getInteger() {
		return this.integer;
	}

	public void setInteger(java.math.BigDecimal integer) {
		this.integer = integer;
	}

	public java.math.BigDecimal getLong() {
		return this.long_;
	}

	public void setLong(java.math.BigDecimal long_) {
		this.long_ = long_;
	}

	public java.math.BigDecimal getByteDecimal() {
		return this.byteDecimal;
	}

	public void setByteDecimal(java.math.BigDecimal byteDecimal) {
		this.byteDecimal = byteDecimal;
	}

	public java.math.BigDecimal getShortDecimal() {
		return this.shortDecimal;
	}

	public void setShortDecimal(java.math.BigDecimal shortDecimal) {
		this.shortDecimal = shortDecimal;
	}

	public java.math.BigDecimal getIntegerDecimal() {
		return this.integerDecimal;
	}

	public void setIntegerDecimal(java.math.BigDecimal integerDecimal) {
		this.integerDecimal = integerDecimal;
	}

	public java.math.BigDecimal getLongDecimal() {
		return this.longDecimal;
	}

	public void setLongDecimal(java.math.BigDecimal longDecimal) {
		this.longDecimal = longDecimal;
	}

	public java.math.BigDecimal getBigInteger() {
		return this.bigInteger;
	}

	public void setBigInteger(java.math.BigDecimal bigInteger) {
		this.bigInteger = bigInteger;
	}

	public java.math.BigDecimal getBigDecimal() {
		return this.bigDecimal;
	}

	public void setBigDecimal(java.math.BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}
}
