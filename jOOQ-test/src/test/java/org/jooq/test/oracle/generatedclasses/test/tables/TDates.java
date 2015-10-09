/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.test.oracle.generatedclasses.test.Keys;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.tables.records.TDatesRecord;
import org.jooq.types.DayToSecond;
import org.jooq.types.YearToMonth;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TDates extends TableImpl<TDatesRecord> {

	private static final long serialVersionUID = -326860156;

	/**
	 * The reference instance of <code>TEST.T_DATES</code>
	 */
	public static final TDates T_DATES = new TDates();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TDatesRecord> getRecordType() {
		return TDatesRecord.class;
	}

	/**
	 * The column <code>TEST.T_DATES.ID</code>.
	 */
	public final TableField<TDatesRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>TEST.T_DATES.D</code>.
	 */
	public final TableField<TDatesRecord, Date> D = createField("D", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>TEST.T_DATES.T</code>.
	 */
	public final TableField<TDatesRecord, Timestamp> T = createField("T", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>TEST.T_DATES.TS</code>.
	 */
	public final TableField<TDatesRecord, Timestamp> TS = createField("TS", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>TEST.T_DATES.T_TZ</code>.
	 */
	public final TableField<TDatesRecord, OffsetDateTime> T_TZ = createField("T_TZ", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

	/**
	 * The column <code>TEST.T_DATES.TS_TZ</code>.
	 */
	public final TableField<TDatesRecord, OffsetDateTime> TS_TZ = createField("TS_TZ", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

	/**
	 * The column <code>TEST.T_DATES.D_INT</code>.
	 */
	public final TableField<TDatesRecord, Integer> D_INT = createField("D_INT", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>TEST.T_DATES.TS_BIGINT</code>.
	 */
	public final TableField<TDatesRecord, Long> TS_BIGINT = createField("TS_BIGINT", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>TEST.T_DATES.I_Y</code>.
	 */
	public final TableField<TDatesRecord, YearToMonth> I_Y = createField("I_Y", org.jooq.impl.SQLDataType.INTERVALYEARTOMONTH, this, "");

	/**
	 * The column <code>TEST.T_DATES.I_D</code>.
	 */
	public final TableField<TDatesRecord, DayToSecond> I_D = createField("I_D", org.jooq.impl.SQLDataType.INTERVALDAYTOSECOND, this, "");

	/**
	 * Create a <code>TEST.T_DATES</code> table reference
	 */
	public TDates() {
		this("T_DATES", null);
	}

	/**
	 * Create an aliased <code>TEST.T_DATES</code> table reference
	 */
	public TDates(String alias) {
		this(alias, T_DATES);
	}

	private TDates(String alias, Table<TDatesRecord> aliased) {
		this(alias, aliased, null);
	}

	private TDates(String alias, Table<TDatesRecord> aliased, Field<?>[] parameters) {
		super(alias, Test.TEST, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TDatesRecord> getPrimaryKey() {
		return Keys.PK_T_DATES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TDatesRecord>> getKeys() {
		return Arrays.<UniqueKey<TDatesRecord>>asList(Keys.PK_T_DATES);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TDates as(String alias) {
		return new TDates(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TDates rename(String name) {
		return new TDates(name, null);
	}
}
