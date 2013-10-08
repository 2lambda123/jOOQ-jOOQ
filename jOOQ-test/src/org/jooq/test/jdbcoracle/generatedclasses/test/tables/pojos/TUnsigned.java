/**
 * This class is generated by jOOQ
 */
package org.jooq.test.jdbcoracle.generatedclasses.test.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TUnsigned implements org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.ITUnsigned {

	private static final long serialVersionUID = 536241662;

	private java.lang.Short      uByte;
	private java.lang.Integer    uShort;
	private java.lang.Long       uInt;
	private java.math.BigInteger uLong;

	public TUnsigned() {}

	public TUnsigned(
		java.lang.Short      uByte,
		java.lang.Integer    uShort,
		java.lang.Long       uInt,
		java.math.BigInteger uLong
	) {
		this.uByte = uByte;
		this.uShort = uShort;
		this.uInt = uInt;
		this.uLong = uLong;
	}

	@Override
	public java.lang.Short getUByte() {
		return this.uByte;
	}

	@Override
	public void setUByte(java.lang.Short uByte) {
		this.uByte = uByte;
	}

	@Override
	public java.lang.Integer getUShort() {
		return this.uShort;
	}

	@Override
	public void setUShort(java.lang.Integer uShort) {
		this.uShort = uShort;
	}

	@Override
	public java.lang.Long getUInt() {
		return this.uInt;
	}

	@Override
	public void setUInt(java.lang.Long uInt) {
		this.uInt = uInt;
	}

	@Override
	public java.math.BigInteger getULong() {
		return this.uLong;
	}

	@Override
	public void setULong(java.math.BigInteger uLong) {
		this.uLong = uLong;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.ITUnsigned from) {
		setUByte(from.getUByte());
		setUShort(from.getUShort());
		setUInt(from.getUInt());
		setULong(from.getULong());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.ITUnsigned> E into(E into) {
		into.from(this);
		return into;
	}
}
