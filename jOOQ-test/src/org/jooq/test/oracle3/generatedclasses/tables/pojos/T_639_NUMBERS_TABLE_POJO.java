/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "T_639_NUMBERS_TABLE")
public class T_639_NUMBERS_TABLE_POJO extends java.lang.ThreadDeath implements java.lang.Cloneable, org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_639_NUMBERS_TABLE_INTERFACE {

	private static final long serialVersionUID = 1649074092;

	private java.lang.Integer    ID;
	private java.lang.Byte       BYTE;
	private java.lang.Short      SHORT;
	private java.lang.Integer    INTEGER;
	private java.lang.Long       LONG;
	private java.lang.Byte       BYTE_DECIMAL;
	private java.lang.Short      SHORT_DECIMAL;
	private java.lang.Integer    INTEGER_DECIMAL;
	private java.lang.Long       LONG_DECIMAL;
	private java.math.BigInteger BIG_INTEGER;
	private java.math.BigDecimal BIG_DECIMAL;

	@javax.persistence.Id
	@javax.persistence.Column(name = "ID", unique = true, nullable = false, precision = 7)
	@Override
	public java.lang.Integer getID() {
		return this.ID;
	}

	@Override
	public void setID(java.lang.Integer ID) {
		this.ID = ID;
	}

	@javax.persistence.Column(name = "BYTE", precision = 2)
	@Override
	public java.lang.Byte getBYTE() {
		return this.BYTE;
	}

	@Override
	public void setBYTE(java.lang.Byte BYTE) {
		this.BYTE = BYTE;
	}

	@javax.persistence.Column(name = "SHORT", precision = 4)
	@Override
	public java.lang.Short getSHORT() {
		return this.SHORT;
	}

	@Override
	public void setSHORT(java.lang.Short SHORT) {
		this.SHORT = SHORT;
	}

	@javax.persistence.Column(name = "INTEGER", precision = 7)
	@Override
	public java.lang.Integer getINTEGER() {
		return this.INTEGER;
	}

	@Override
	public void setINTEGER(java.lang.Integer INTEGER) {
		this.INTEGER = INTEGER;
	}

	@javax.persistence.Column(name = "LONG", precision = 18)
	@Override
	public java.lang.Long getLONG() {
		return this.LONG;
	}

	@Override
	public void setLONG(java.lang.Long LONG) {
		this.LONG = LONG;
	}

	@javax.persistence.Column(name = "BYTE_DECIMAL", precision = 2)
	@Override
	public java.lang.Byte getBYTE_DECIMAL() {
		return this.BYTE_DECIMAL;
	}

	@Override
	public void setBYTE_DECIMAL(java.lang.Byte BYTE_DECIMAL) {
		this.BYTE_DECIMAL = BYTE_DECIMAL;
	}

	@javax.persistence.Column(name = "SHORT_DECIMAL", precision = 4)
	@Override
	public java.lang.Short getSHORT_DECIMAL() {
		return this.SHORT_DECIMAL;
	}

	@Override
	public void setSHORT_DECIMAL(java.lang.Short SHORT_DECIMAL) {
		this.SHORT_DECIMAL = SHORT_DECIMAL;
	}

	@javax.persistence.Column(name = "INTEGER_DECIMAL", precision = 9)
	@Override
	public java.lang.Integer getINTEGER_DECIMAL() {
		return this.INTEGER_DECIMAL;
	}

	@Override
	public void setINTEGER_DECIMAL(java.lang.Integer INTEGER_DECIMAL) {
		this.INTEGER_DECIMAL = INTEGER_DECIMAL;
	}

	@javax.persistence.Column(name = "LONG_DECIMAL", precision = 18)
	@Override
	public java.lang.Long getLONG_DECIMAL() {
		return this.LONG_DECIMAL;
	}

	@Override
	public void setLONG_DECIMAL(java.lang.Long LONG_DECIMAL) {
		this.LONG_DECIMAL = LONG_DECIMAL;
	}

	@javax.persistence.Column(name = "BIG_INTEGER", precision = 22)
	@Override
	public java.math.BigInteger getBIG_INTEGER() {
		return this.BIG_INTEGER;
	}

	@Override
	public void setBIG_INTEGER(java.math.BigInteger BIG_INTEGER) {
		this.BIG_INTEGER = BIG_INTEGER;
	}

	@javax.persistence.Column(name = "BIG_DECIMAL", precision = 22, scale = 5)
	@Override
	public java.math.BigDecimal getBIG_DECIMAL() {
		return this.BIG_DECIMAL;
	}

	@Override
	public void setBIG_DECIMAL(java.math.BigDecimal BIG_DECIMAL) {
		this.BIG_DECIMAL = BIG_DECIMAL;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_639_NUMBERS_TABLE_INTERFACE from) {
		setID(from.getID());
		setBYTE(from.getBYTE());
		setSHORT(from.getSHORT());
		setINTEGER(from.getINTEGER());
		setLONG(from.getLONG());
		setBYTE_DECIMAL(from.getBYTE_DECIMAL());
		setSHORT_DECIMAL(from.getSHORT_DECIMAL());
		setINTEGER_DECIMAL(from.getINTEGER_DECIMAL());
		setLONG_DECIMAL(from.getLONG_DECIMAL());
		setBIG_INTEGER(from.getBIG_INTEGER());
		setBIG_DECIMAL(from.getBIG_DECIMAL());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_639_NUMBERS_TABLE_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}
}
