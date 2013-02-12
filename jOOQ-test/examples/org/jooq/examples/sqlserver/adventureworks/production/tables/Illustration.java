/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.sqlserver.adventureworks.production.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class Illustration extends org.jooq.impl.UpdatableTableImpl<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration> {

	private static final long serialVersionUID = -243094093;

	/**
	 * The singleton instance of <code>Production.Illustration</code>
	 */
	public static final org.jooq.examples.sqlserver.adventureworks.production.tables.Illustration Illustration = new org.jooq.examples.sqlserver.adventureworks.production.tables.Illustration();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration> getRecordType() {
		return org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration.class;
	}

	/**
	 * The column <code>Production.Illustration.IllustrationID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration, java.lang.Integer> IllustrationID = createField("IllustrationID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>Production.Illustration.Diagram</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration, java.lang.Object> Diagram = createField("Diagram", org.jooq.impl.DefaultDataType.getDefaultDataType("xml"), this);

	/**
	 * The column <code>Production.Illustration.ModifiedDate</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration, java.sql.Timestamp> ModifiedDate = createField("ModifiedDate", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * Create a <code>Production.Illustration</code> table reference
	 */
	public Illustration() {
		super("Illustration", org.jooq.examples.sqlserver.adventureworks.production.Production.Production);
	}

	/**
	 * Create an aliased <code>Production.Illustration</code> table reference
	 */
	public Illustration(java.lang.String alias) {
		super(alias, org.jooq.examples.sqlserver.adventureworks.production.Production.Production, org.jooq.examples.sqlserver.adventureworks.production.tables.Illustration.Illustration);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration, java.lang.Integer> getIdentity() {
		return org.jooq.examples.sqlserver.adventureworks.production.Keys.IDENTITY_Illustration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration> getPrimaryKey() {
		return org.jooq.examples.sqlserver.adventureworks.production.Keys.PK_Illustration_IllustrationID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.production.tables.records.Illustration>>asList(org.jooq.examples.sqlserver.adventureworks.production.Keys.PK_Illustration_IllustrationID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.examples.sqlserver.adventureworks.production.tables.Illustration as(java.lang.String alias) {
		return new org.jooq.examples.sqlserver.adventureworks.production.tables.Illustration(alias);
	}
}
