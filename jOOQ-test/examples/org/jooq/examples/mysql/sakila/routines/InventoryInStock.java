/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.mysql.sakila.routines;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = {"http://www.jooq.org", "2.0.0"},
                            comments = "This class is generated by jOOQ")
public class InventoryInStock extends org.jooq.impl.AbstractRoutine<java.lang.Byte> {

	private static final long serialVersionUID = 1403674706;


	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.lang.Byte> RETURN_VALUE = new org.jooq.impl.ParameterImpl<java.lang.Byte>("RETURN_VALUE", org.jooq.impl.SQLDataType.TINYINT);

	/**
	 * An uncommented item
	 */
	public static final org.jooq.Parameter<java.lang.Integer> P_INVENTORY_ID = new org.jooq.impl.ParameterImpl<java.lang.Integer>("p_inventory_id", org.jooq.impl.SQLDataType.INTEGER);

	/**
	 * Create a new routine call instance
	 */
	public InventoryInStock() {
		super(org.jooq.SQLDialect.MYSQL, "inventory_in_stock", org.jooq.examples.mysql.sakila.Sakila.SAKILA, org.jooq.impl.SQLDataType.TINYINT);

		setReturnParameter(RETURN_VALUE);
		addInParameter(P_INVENTORY_ID);
	}

	/**
	 * Set the <code>p_inventory_id</code> parameter to the routine
	 */
	public void setPInventoryId(java.lang.Integer value) {
		setValue(P_INVENTORY_ID, value);
	}

	/**
	 * Set the <code>p_inventory_id</code> parameter to the function
	 * <p>
	 * Use this method only, if the function is called as a {@link org.jooq.Field} in a {@link org.jooq.Select} statement!
	 */
	public void setPInventoryId(org.jooq.Field<java.lang.Integer> field) {
		setField(P_INVENTORY_ID, field);
	}
}
