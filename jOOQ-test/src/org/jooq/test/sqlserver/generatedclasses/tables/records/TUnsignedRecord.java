/**
 * This class is generated by jOOQ
 */
package org.jooq.test.sqlserver.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TUnsignedRecord extends org.jooq.impl.TableRecordImpl<org.jooq.test.sqlserver.generatedclasses.tables.records.TUnsignedRecord> implements org.jooq.Record4<org.jooq.types.UByte, org.jooq.types.UShort, org.jooq.types.UInteger, org.jooq.types.ULong> {

	private static final long serialVersionUID = -1312167383;

	/**
	 * Setter for <code>dbo.t_unsigned.u_byte</code>. 
	 */
	public void setUByte(org.jooq.types.UByte value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>dbo.t_unsigned.u_byte</code>. 
	 */
	public org.jooq.types.UByte getUByte() {
		return (org.jooq.types.UByte) getValue(0);
	}

	/**
	 * Setter for <code>dbo.t_unsigned.u_short</code>. 
	 */
	public void setUShort(org.jooq.types.UShort value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>dbo.t_unsigned.u_short</code>. 
	 */
	public org.jooq.types.UShort getUShort() {
		return (org.jooq.types.UShort) getValue(1);
	}

	/**
	 * Setter for <code>dbo.t_unsigned.u_int</code>. 
	 */
	public void setUInt(org.jooq.types.UInteger value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>dbo.t_unsigned.u_int</code>. 
	 */
	public org.jooq.types.UInteger getUInt() {
		return (org.jooq.types.UInteger) getValue(2);
	}

	/**
	 * Setter for <code>dbo.t_unsigned.u_long</code>. 
	 */
	public void setULong(org.jooq.types.ULong value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>dbo.t_unsigned.u_long</code>. 
	 */
	public org.jooq.types.ULong getULong() {
		return (org.jooq.types.ULong) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<org.jooq.types.UByte, org.jooq.types.UShort, org.jooq.types.UInteger, org.jooq.types.ULong> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<org.jooq.types.UByte, org.jooq.types.UShort, org.jooq.types.UInteger, org.jooq.types.ULong> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.UByte> field1() {
		return org.jooq.test.sqlserver.generatedclasses.tables.TUnsigned.T_UNSIGNED.U_BYTE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.UShort> field2() {
		return org.jooq.test.sqlserver.generatedclasses.tables.TUnsigned.T_UNSIGNED.U_SHORT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.UInteger> field3() {
		return org.jooq.test.sqlserver.generatedclasses.tables.TUnsigned.T_UNSIGNED.U_INT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.ULong> field4() {
		return org.jooq.test.sqlserver.generatedclasses.tables.TUnsigned.T_UNSIGNED.U_LONG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.UByte value1() {
		return getUByte();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.UShort value2() {
		return getUShort();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.UInteger value3() {
		return getUInt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.ULong value4() {
		return getULong();
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TUnsignedRecord
	 */
	public TUnsignedRecord() {
		super(org.jooq.test.sqlserver.generatedclasses.tables.TUnsigned.T_UNSIGNED);
	}

	/**
	 * Create a detached, initialised TUnsignedRecord
	 */
	public TUnsignedRecord(org.jooq.types.UByte uByte, org.jooq.types.UShort uShort, org.jooq.types.UInteger uInt, org.jooq.types.ULong uLong) {
		super(org.jooq.test.sqlserver.generatedclasses.tables.TUnsigned.T_UNSIGNED);

		setValue(0, uByte);
		setValue(1, uShort);
		setValue(2, uInt);
		setValue(3, uLong);
	}
}
