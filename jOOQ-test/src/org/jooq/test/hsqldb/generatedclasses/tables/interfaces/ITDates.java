/**
 * This class is generated by jOOQ
 */
package org.jooq.test.hsqldb.generatedclasses.tables.interfaces;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface ITDates extends java.io.Serializable {

	/**
	 * Setter for <code>PUBLIC.T_DATES.ID</code>. 
	 */
	public void setId(java.lang.Integer value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.ID</code>. 
	 */
	public java.lang.Integer getId();

	/**
	 * Setter for <code>PUBLIC.T_DATES.D</code>. 
	 */
	public void setD(java.sql.Date value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.D</code>. 
	 */
	public java.sql.Date getD();

	/**
	 * Setter for <code>PUBLIC.T_DATES.T</code>. 
	 */
	public void setT(java.sql.Time value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.T</code>. 
	 */
	public java.sql.Time getT();

	/**
	 * Setter for <code>PUBLIC.T_DATES.TS</code>. 
	 */
	public void setTs(java.sql.Timestamp value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.TS</code>. 
	 */
	public java.sql.Timestamp getTs();

	/**
	 * Setter for <code>PUBLIC.T_DATES.D_INT</code>. 
	 */
	public void setDInt(java.lang.Integer value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.D_INT</code>. 
	 */
	public java.lang.Integer getDInt();

	/**
	 * Setter for <code>PUBLIC.T_DATES.TS_BIGINT</code>. 
	 */
	public void setTsBigint(java.lang.Long value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.TS_BIGINT</code>. 
	 */
	public java.lang.Long getTsBigint();

	/**
	 * Setter for <code>PUBLIC.T_DATES.I_Y</code>. 
	 */
	public void setIY(org.jooq.types.YearToMonth value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.I_Y</code>. 
	 */
	public org.jooq.types.YearToMonth getIY();

	/**
	 * Setter for <code>PUBLIC.T_DATES.I_D</code>. 
	 */
	public void setID(org.jooq.types.DayToSecond value);

	/**
	 * Getter for <code>PUBLIC.T_DATES.I_D</code>. 
	 */
	public org.jooq.types.DayToSecond getID();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface ITDates
	 */
	public void from(org.jooq.test.hsqldb.generatedclasses.tables.interfaces.ITDates from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface ITDates
	 */
	public <E extends org.jooq.test.hsqldb.generatedclasses.tables.interfaces.ITDates> E into(E into);
}
