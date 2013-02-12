/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.sqlserver.adventureworks.humanresources.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class Shift extends org.jooq.impl.UpdatableTableImpl<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift> {

	private static final long serialVersionUID = 288135952;

	/**
	 * The singleton instance of <code>HumanResources.Shift</code>
	 */
	public static final org.jooq.examples.sqlserver.adventureworks.humanresources.tables.Shift Shift = new org.jooq.examples.sqlserver.adventureworks.humanresources.tables.Shift();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift> getRecordType() {
		return org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift.class;
	}

	/**
	 * The column <code>HumanResources.Shift.ShiftID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift, java.lang.Byte> ShiftID = createField("ShiftID", org.jooq.impl.SQLDataType.TINYINT, this);

	/**
	 * The column <code>HumanResources.Shift.Name</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift, java.lang.String> Name = createField("Name", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>HumanResources.Shift.StartTime</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift, java.sql.Timestamp> StartTime = createField("StartTime", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * The column <code>HumanResources.Shift.EndTime</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift, java.sql.Timestamp> EndTime = createField("EndTime", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * The column <code>HumanResources.Shift.ModifiedDate</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift, java.sql.Timestamp> ModifiedDate = createField("ModifiedDate", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * Create a <code>HumanResources.Shift</code> table reference
	 */
	public Shift() {
		super("Shift", org.jooq.examples.sqlserver.adventureworks.humanresources.HumanResources.HumanResources);
	}

	/**
	 * Create an aliased <code>HumanResources.Shift</code> table reference
	 */
	public Shift(java.lang.String alias) {
		super(alias, org.jooq.examples.sqlserver.adventureworks.humanresources.HumanResources.HumanResources, org.jooq.examples.sqlserver.adventureworks.humanresources.tables.Shift.Shift);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift, java.lang.Byte> getIdentity() {
		return org.jooq.examples.sqlserver.adventureworks.humanresources.Keys.IDENTITY_Shift;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift> getPrimaryKey() {
		return org.jooq.examples.sqlserver.adventureworks.humanresources.Keys.PK_Shift_ShiftID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.humanresources.tables.records.Shift>>asList(org.jooq.examples.sqlserver.adventureworks.humanresources.Keys.PK_Shift_ShiftID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.examples.sqlserver.adventureworks.humanresources.tables.Shift as(java.lang.String alias) {
		return new org.jooq.examples.sqlserver.adventureworks.humanresources.tables.Shift(alias);
	}
}
