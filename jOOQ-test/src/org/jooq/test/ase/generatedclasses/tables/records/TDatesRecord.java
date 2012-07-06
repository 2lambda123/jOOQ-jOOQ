/**
 * This class is generated by jOOQ
 */
package org.jooq.test.ase.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
public class TDatesRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.ase.generatedclasses.tables.records.TDatesRecord> {

	private static final long serialVersionUID = 969073507;

	/**
	 * The table column <code>dbo.t_dates.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public void setId(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TDates.ID, value);
	}

	/**
	 * The table column <code>dbo.t_dates.id</code>
	 * <p>
	 * This column is part of the table's PRIMARY KEY
	 */
	public java.lang.Integer getId() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TDates.ID);
	}

	/**
	 * The table column <code>dbo.t_dates.d</code>
	 */
	public void setD(java.sql.Date value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TDates.D, value);
	}

	/**
	 * The table column <code>dbo.t_dates.d</code>
	 */
	public java.sql.Date getD() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TDates.D);
	}

	/**
	 * The table column <code>dbo.t_dates.t</code>
	 */
	public void setT(java.sql.Time value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TDates.T, value);
	}

	/**
	 * The table column <code>dbo.t_dates.t</code>
	 */
	public java.sql.Time getT() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TDates.T);
	}

	/**
	 * The table column <code>dbo.t_dates.ts</code>
	 */
	public void setTs(java.sql.Timestamp value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TDates.TS, value);
	}

	/**
	 * The table column <code>dbo.t_dates.ts</code>
	 */
	public java.sql.Timestamp getTs() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TDates.TS);
	}

	/**
	 * The table column <code>dbo.t_dates.d_int</code>
	 */
	public void setDInt(java.lang.Integer value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TDates.D_INT, value);
	}

	/**
	 * The table column <code>dbo.t_dates.d_int</code>
	 */
	public java.lang.Integer getDInt() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TDates.D_INT);
	}

	/**
	 * The table column <code>dbo.t_dates.ts_bigint</code>
	 */
	public void setTsBigint(java.lang.Long value) {
		setValue(org.jooq.test.ase.generatedclasses.tables.TDates.TS_BIGINT, value);
	}

	/**
	 * The table column <code>dbo.t_dates.ts_bigint</code>
	 */
	public java.lang.Long getTsBigint() {
		return getValue(org.jooq.test.ase.generatedclasses.tables.TDates.TS_BIGINT);
	}

	/**
	 * Create a detached TDatesRecord
	 */
	public TDatesRecord() {
		super(org.jooq.test.ase.generatedclasses.tables.TDates.T_DATES);
	}
}
