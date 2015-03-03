/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.test.all.converters.LocalDateConverter;
import org.jooq.test.oracle.generatedclasses.test.Keys;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.tables.records.T_2155Record;
import org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord;
import org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ObjectRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_2155 extends TableImpl<T_2155Record> {

	private static final long serialVersionUID = 1990490962;

	/**
	 * The reference instance of <code>TEST.T_2155</code>
	 */
	public static final T_2155 T_2155 = new T_2155();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<T_2155Record> getRecordType() {
		return T_2155Record.class;
	}

	/**
	 * The column <code>TEST.T_2155.ID</code>.
	 */
	public final TableField<T_2155Record, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>TEST.T_2155.D1</code>.
	 */
	public final TableField<T_2155Record, LocalDate> D1 = createField("D1", org.jooq.impl.SQLDataType.DATE, this, "", new LocalDateConverter());

	/**
	 * The column <code>TEST.T_2155.D2</code>.
	 */
	public final TableField<T_2155Record, U_2155ObjectRecord> D2 = createField("D2", org.jooq.test.oracle.generatedclasses.test.udt.U_2155Object.U_2155_OBJECT.getDataType(), this, "");

	/**
	 * The column <code>TEST.T_2155.D3</code>.
	 */
	public final TableField<T_2155Record, U_2155ArrayRecord> D3 = createField("D3", org.jooq.impl.SQLDataType.DATE.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord.class), this, "");

	/**
	 * Create a <code>TEST.T_2155</code> table reference
	 */
	public T_2155() {
		this("T_2155", null);
	}

	/**
	 * Create an aliased <code>TEST.T_2155</code> table reference
	 */
	public T_2155(String alias) {
		this(alias, T_2155);
	}

	private T_2155(String alias, Table<T_2155Record> aliased) {
		this(alias, aliased, null);
	}

	private T_2155(String alias, Table<T_2155Record> aliased, Field<?>[] parameters) {
		super(alias, Test.TEST, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<T_2155Record> getPrimaryKey() {
		return Keys.PK_T_2155;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<T_2155Record>> getKeys() {
		return Arrays.<UniqueKey<T_2155Record>>asList(Keys.PK_T_2155);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_2155 as(String alias) {
		return new T_2155(alias, this);
	}

	/**
	 * Rename this table
	 */
	public T_2155 rename(String name) {
		return new T_2155(name, null);
	}
}
