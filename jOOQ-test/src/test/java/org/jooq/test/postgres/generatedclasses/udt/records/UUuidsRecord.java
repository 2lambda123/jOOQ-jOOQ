/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.udt.records;


import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row;
import org.jooq.Row2;
import org.jooq.impl.UDTRecordImpl;
import org.jooq.test.postgres.generatedclasses.udt.UUuids;
import org.jooq.test.postgres.generatedclasses.udt.interfaces.IUUuids;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UUuidsRecord extends UDTRecordImpl<UUuidsRecord> implements Record2<UUID, UUID[]>, IUUuids {

	private static final long serialVersionUID = -949400522;

	/**
	 * Setter for <code>public.u_uuids.u1</code>.
	 */
	public UUuidsRecord setU1(UUID value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>public.u_uuids.u1</code>.
	 */
	@Override
	public UUID getU1() {
		return (UUID) getValue(0);
	}

	/**
	 * Setter for <code>public.u_uuids.u2</code>.
	 */
	public UUuidsRecord setU2(UUID[] value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>public.u_uuids.u2</code>.
	 */
	@Override
	public UUID[] getU2() {
		return (UUID[]) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<UUID, UUID[]> fieldsRow() {
		return (Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row2<UUID, UUID[]> valuesRow() {
		return (Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID> field1() {
		return UUuids.U1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<UUID[]> field2() {
		return UUuids.U2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID value1() {
		return getU1();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUID[] value2() {
		return getU2();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUuidsRecord value1(UUID value) {
		setU1(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUuidsRecord value2(UUID[] value) {
		setU2(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UUuidsRecord values(UUID value1, UUID[] value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached UUuidsRecord
	 */
	public UUuidsRecord() {
		super(UUuids.U_UUIDS);
	}

	/**
	 * Create a detached, initialised UUuidsRecord
	 */
	public UUuidsRecord(UUID u1, UUID[] u2) {
		super(UUuids.U_UUIDS);

		setValue(0, u1);
		setValue(1, u2);
	}
}
