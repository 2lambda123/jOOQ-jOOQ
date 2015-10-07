/**
 * This class is generated by jOOQ
 */
package org.jooq.test.vertica.generatedclasses.tables.records;


import java.math.BigDecimal;
import java.math.BigInteger;

import org.jooq.Field;
import org.jooq.Record16;
import org.jooq.Record2;
import org.jooq.Row16;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.test.vertica.generatedclasses.tables.XUnused;
import org.jooq.test.vertica.generatedclasses.tables.interfaces.IXUnused;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XUnusedRecord extends UpdatableRecordImpl<XUnusedRecord> implements Record16<Long, String, BigInteger, Long, Long, Long, Long, Long, Long, Long, Long, Long, String, BigDecimal, Long, Long>, IXUnused {

	private static final long serialVersionUID = 9258363;

	/**
	 * Setter for <code>public.x_unused.ID</code>.
	 */
	@Override
	public void setId(Long value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.x_unused.ID</code>.
	 */
	@Override
	public Long getId() {
		return (Long) getValue(0);
	}

	/**
	 * Setter for <code>public.x_unused.NAME</code>.
	 */
	@Override
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.x_unused.NAME</code>.
	 */
	@Override
	public String getName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>public.x_unused.BIG_INTEGER</code>.
	 */
	@Override
	public void setBigInteger(BigInteger value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>public.x_unused.BIG_INTEGER</code>.
	 */
	@Override
	public BigInteger getBigInteger() {
		return (BigInteger) getValue(2);
	}

	/**
	 * Setter for <code>public.x_unused.ID_REF</code>.
	 */
	@Override
	public void setIdRef(Long value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>public.x_unused.ID_REF</code>.
	 */
	@Override
	public Long getIdRef() {
		return (Long) getValue(3);
	}

	/**
	 * Setter for <code>public.x_unused.CLASS</code>.
	 */
	@Override
	public void setClass_(Long value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>public.x_unused.CLASS</code>.
	 */
	@Override
	public Long getClass_() {
		return (Long) getValue(4);
	}

	/**
	 * Setter for <code>public.x_unused.FIELDS</code>.
	 */
	@Override
	public void setFields(Long value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>public.x_unused.FIELDS</code>.
	 */
	@Override
	public Long getFields() {
		return (Long) getValue(5);
	}

	/**
	 * Setter for <code>public.x_unused.CONFIGURATION</code>.
	 */
	@Override
	public void setConfiguration(Long value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>public.x_unused.CONFIGURATION</code>.
	 */
	@Override
	public Long getConfiguration() {
		return (Long) getValue(6);
	}

	/**
	 * Setter for <code>public.x_unused.U_D_T</code>.
	 */
	@Override
	public void setUDT(Long value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>public.x_unused.U_D_T</code>.
	 */
	@Override
	public Long getUDT() {
		return (Long) getValue(7);
	}

	/**
	 * Setter for <code>public.x_unused.META_DATA</code>.
	 */
	@Override
	public void setMetaData(Long value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>public.x_unused.META_DATA</code>.
	 */
	@Override
	public Long getMetaData() {
		return (Long) getValue(8);
	}

	/**
	 * Setter for <code>public.x_unused.TYPE0</code>.
	 */
	@Override
	public void setType0(Long value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>public.x_unused.TYPE0</code>.
	 */
	@Override
	public Long getType0() {
		return (Long) getValue(9);
	}

	/**
	 * Setter for <code>public.x_unused.PRIMARY_KEY</code>.
	 */
	@Override
	public void setPrimaryKey_(Long value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>public.x_unused.PRIMARY_KEY</code>.
	 */
	@Override
	public Long getPrimaryKey_() {
		return (Long) getValue(10);
	}

	/**
	 * Setter for <code>public.x_unused.PRIMARYKEY</code>.
	 */
	@Override
	public void setPrimarykey(Long value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>public.x_unused.PRIMARYKEY</code>.
	 */
	@Override
	public Long getPrimarykey() {
		return (Long) getValue(11);
	}

	/**
	 * Setter for <code>public.x_unused.NAME_REF</code>.
	 */
	@Override
	public void setNameRef(String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>public.x_unused.NAME_REF</code>.
	 */
	@Override
	public String getNameRef() {
		return (String) getValue(12);
	}

	/**
	 * Setter for <code>public.x_unused.FIELD 737</code>.
	 */
	@Override
	public void setField_737(BigDecimal value) {
		setValue(13, value);
	}

	/**
	 * Getter for <code>public.x_unused.FIELD 737</code>.
	 */
	@Override
	public BigDecimal getField_737() {
		return (BigDecimal) getValue(13);
	}

	/**
	 * Setter for <code>public.x_unused.CONNECTION</code>.
	 */
	@Override
	public void setConnection(Long value) {
		setValue(14, value);
	}

	/**
	 * Getter for <code>public.x_unused.CONNECTION</code>.
	 */
	@Override
	public Long getConnection() {
		return (Long) getValue(14);
	}

	/**
	 * Setter for <code>public.x_unused.PREPARED_STATEMENT</code>.
	 */
	@Override
	public void setPreparedStatement(Long value) {
		setValue(15, value);
	}

	/**
	 * Getter for <code>public.x_unused.PREPARED_STATEMENT</code>.
	 */
	@Override
	public Long getPreparedStatement() {
		return (Long) getValue(15);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record2<Long, String> key() {
		return (Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record16 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row16<Long, String, BigInteger, Long, Long, Long, Long, Long, Long, Long, Long, Long, String, BigDecimal, Long, Long> fieldsRow() {
		return (Row16) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row16<Long, String, BigInteger, Long, Long, Long, Long, Long, Long, Long, Long, Long, String, BigDecimal, Long, Long> valuesRow() {
		return (Row16) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field1() {
		return XUnused.X_UNUSED.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return XUnused.X_UNUSED.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<BigInteger> field3() {
		return XUnused.X_UNUSED.BIG_INTEGER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field4() {
		return XUnused.X_UNUSED.ID_REF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field5() {
		return XUnused.X_UNUSED.CLASS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field6() {
		return XUnused.X_UNUSED.FIELDS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field7() {
		return XUnused.X_UNUSED.CONFIGURATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field8() {
		return XUnused.X_UNUSED.U_D_T;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field9() {
		return XUnused.X_UNUSED.META_DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field10() {
		return XUnused.X_UNUSED.TYPE0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field11() {
		return XUnused.X_UNUSED.PRIMARY_KEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field12() {
		return XUnused.X_UNUSED.PRIMARYKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field13() {
		return XUnused.X_UNUSED.NAME_REF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<BigDecimal> field14() {
		return XUnused.X_UNUSED.FIELD_737;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field15() {
		return XUnused.X_UNUSED.CONNECTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field16() {
		return XUnused.X_UNUSED.PREPARED_STATEMENT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger value3() {
		return getBigInteger();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value4() {
		return getIdRef();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value5() {
		return getClass_();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value6() {
		return getFields();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value7() {
		return getConfiguration();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value8() {
		return getUDT();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value9() {
		return getMetaData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value10() {
		return getType0();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value11() {
		return getPrimaryKey_();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value12() {
		return getPrimarykey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value13() {
		return getNameRef();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal value14() {
		return getField_737();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value15() {
		return getConnection();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value16() {
		return getPreparedStatement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value1(Long value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value3(BigInteger value) {
		setBigInteger(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value4(Long value) {
		setIdRef(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value5(Long value) {
		setClass_(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value6(Long value) {
		setFields(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value7(Long value) {
		setConfiguration(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value8(Long value) {
		setUDT(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value9(Long value) {
		setMetaData(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value10(Long value) {
		setType0(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value11(Long value) {
		setPrimaryKey_(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value12(Long value) {
		setPrimarykey(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value13(String value) {
		setNameRef(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value14(BigDecimal value) {
		setField_737(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value15(Long value) {
		setConnection(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord value16(Long value) {
		setPreparedStatement(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XUnusedRecord values(Long value1, String value2, BigInteger value3, Long value4, Long value5, Long value6, Long value7, Long value8, Long value9, Long value10, Long value11, Long value12, String value13, BigDecimal value14, Long value15, Long value16) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		value13(value13);
		value14(value14);
		value15(value15);
		value16(value16);
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(IXUnused from) {
		setId(from.getId());
		setName(from.getName());
		setBigInteger(from.getBigInteger());
		setIdRef(from.getIdRef());
		setClass_(from.getClass_());
		setFields(from.getFields());
		setConfiguration(from.getConfiguration());
		setUDT(from.getUDT());
		setMetaData(from.getMetaData());
		setType0(from.getType0());
		setPrimaryKey_(from.getPrimaryKey_());
		setPrimarykey(from.getPrimarykey());
		setNameRef(from.getNameRef());
		setField_737(from.getField_737());
		setConnection(from.getConnection());
		setPreparedStatement(from.getPreparedStatement());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends IXUnused> E into(E into) {
		into.from(this);
		return into;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached XUnusedRecord
	 */
	public XUnusedRecord() {
		super(XUnused.X_UNUSED);
	}

	/**
	 * Create a detached, initialised XUnusedRecord
	 */
	public XUnusedRecord(Long id, String name, BigInteger bigInteger, Long idRef, Long class_, Long fields, Long configuration, Long uDT, Long metaData, Long type0, Long primaryKey, Long primarykey, String nameRef, BigDecimal field_737, Long connection, Long preparedStatement) {
		super(XUnused.X_UNUSED);

		setValue(0, id);
		setValue(1, name);
		setValue(2, bigInteger);
		setValue(3, idRef);
		setValue(4, class_);
		setValue(5, fields);
		setValue(6, configuration);
		setValue(7, uDT);
		setValue(8, metaData);
		setValue(9, type0);
		setValue(10, primaryKey);
		setValue(11, primarykey);
		setValue(12, nameRef);
		setValue(13, field_737);
		setValue(14, connection);
		setValue(15, preparedStatement);
	}
}
