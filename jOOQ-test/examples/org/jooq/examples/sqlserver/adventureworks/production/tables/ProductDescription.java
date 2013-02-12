/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.sqlserver.adventureworks.production.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class ProductDescription extends org.jooq.impl.UpdatableTableImpl<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription> {

	private static final long serialVersionUID = 71638388;

	/**
	 * The singleton instance of <code>Production.ProductDescription</code>
	 */
	public static final org.jooq.examples.sqlserver.adventureworks.production.tables.ProductDescription ProductDescription = new org.jooq.examples.sqlserver.adventureworks.production.tables.ProductDescription();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription> getRecordType() {
		return org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription.class;
	}

	/**
	 * The column <code>Production.ProductDescription.ProductDescriptionID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription, java.lang.Integer> ProductDescriptionID = createField("ProductDescriptionID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>Production.ProductDescription.Description</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription, java.lang.String> Description = createField("Description", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Production.ProductDescription.rowguid</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription, java.lang.String> rowguid = createField("rowguid", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * The column <code>Production.ProductDescription.ModifiedDate</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription, java.sql.Timestamp> ModifiedDate = createField("ModifiedDate", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * Create a <code>Production.ProductDescription</code> table reference
	 */
	public ProductDescription() {
		super("ProductDescription", org.jooq.examples.sqlserver.adventureworks.production.Production.Production);
	}

	/**
	 * Create an aliased <code>Production.ProductDescription</code> table reference
	 */
	public ProductDescription(java.lang.String alias) {
		super(alias, org.jooq.examples.sqlserver.adventureworks.production.Production.Production, org.jooq.examples.sqlserver.adventureworks.production.tables.ProductDescription.ProductDescription);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription, java.lang.Integer> getIdentity() {
		return org.jooq.examples.sqlserver.adventureworks.production.Keys.IDENTITY_ProductDescription;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription> getPrimaryKey() {
		return org.jooq.examples.sqlserver.adventureworks.production.Keys.PK_ProductDescription_ProductDescriptionID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.production.tables.records.ProductDescription>>asList(org.jooq.examples.sqlserver.adventureworks.production.Keys.PK_ProductDescription_ProductDescriptionID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.examples.sqlserver.adventureworks.production.tables.ProductDescription as(java.lang.String alias) {
		return new org.jooq.examples.sqlserver.adventureworks.production.tables.ProductDescription(alias);
	}
}
