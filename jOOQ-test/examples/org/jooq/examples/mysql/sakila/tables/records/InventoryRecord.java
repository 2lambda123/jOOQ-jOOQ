/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.mysql.sakila.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = "http://jooq.sourceforge.net",
                            comments = "This class is generated by jOOQ")
public class InventoryRecord extends org.jooq.impl.UpdatableRecordImpl<org.jooq.examples.mysql.sakila.tables.records.InventoryRecord> {

	private static final long serialVersionUID = -964700144;

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setInventoryId(java.lang.Integer value) {
		setValue(org.jooq.examples.mysql.sakila.tables.Inventory.INVENTORY_ID, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.lang.Integer getInventoryId() {
		return getValue(org.jooq.examples.mysql.sakila.tables.Inventory.INVENTORY_ID);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public java.util.List<org.jooq.examples.mysql.sakila.tables.records.RentalRecord> fetchRentalList() throws java.sql.SQLException {
		return create()
			.selectFrom(org.jooq.examples.mysql.sakila.tables.Rental.RENTAL)
			.where(org.jooq.examples.mysql.sakila.tables.Rental.INVENTORY_ID.equal(getValue(org.jooq.examples.mysql.sakila.tables.Inventory.INVENTORY_ID)))
			.fetch();
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [sakila.inventory.film_id]
	 * REFERENCES film [sakila.film.film_id]
	 * </pre></code>
	 */
	public void setFilmId(java.lang.Short value) {
		setValue(org.jooq.examples.mysql.sakila.tables.Inventory.FILM_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [sakila.inventory.film_id]
	 * REFERENCES film [sakila.film.film_id]
	 * </pre></code>
	 */
	public java.lang.Short getFilmId() {
		return getValue(org.jooq.examples.mysql.sakila.tables.Inventory.FILM_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [sakila.inventory.film_id]
	 * REFERENCES film [sakila.film.film_id]
	 * </pre></code>
	 */
	public org.jooq.examples.mysql.sakila.tables.records.FilmRecord fetchFilm() throws java.sql.SQLException {
		return create()
			.selectFrom(org.jooq.examples.mysql.sakila.tables.Film.FILM)
			.where(org.jooq.examples.mysql.sakila.tables.Film.FILM_ID.equal(getValue(org.jooq.examples.mysql.sakila.tables.Inventory.FILM_ID)))
			.fetchOne();
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [sakila.inventory.store_id]
	 * REFERENCES store [sakila.store.store_id]
	 * </pre></code>
	 */
	public void setStoreId(java.lang.Byte value) {
		setValue(org.jooq.examples.mysql.sakila.tables.Inventory.STORE_ID, value);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [sakila.inventory.store_id]
	 * REFERENCES store [sakila.store.store_id]
	 * </pre></code>
	 */
	public java.lang.Byte getStoreId() {
		return getValue(org.jooq.examples.mysql.sakila.tables.Inventory.STORE_ID);
	}

	/**
	 * An uncommented item
	 * <p>
	 * <code><pre>
	 * FOREIGN KEY [sakila.inventory.store_id]
	 * REFERENCES store [sakila.store.store_id]
	 * </pre></code>
	 */
	public org.jooq.examples.mysql.sakila.tables.records.StoreRecord fetchStore() throws java.sql.SQLException {
		return create()
			.selectFrom(org.jooq.examples.mysql.sakila.tables.Store.STORE)
			.where(org.jooq.examples.mysql.sakila.tables.Store.STORE_ID.equal(getValue(org.jooq.examples.mysql.sakila.tables.Inventory.STORE_ID)))
			.fetchOne();
	}

	/**
	 * An uncommented item
	 */
	public void setLastUpdate(java.sql.Timestamp value) {
		setValue(org.jooq.examples.mysql.sakila.tables.Inventory.LAST_UPDATE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.sql.Timestamp getLastUpdate() {
		return getValue(org.jooq.examples.mysql.sakila.tables.Inventory.LAST_UPDATE);
	}

	/**
	 * Create a detached InventoryRecord
	 */
	public InventoryRecord() {
		super(org.jooq.examples.mysql.sakila.tables.Inventory.INVENTORY);
	}

	/**
	 * Create an attached InventoryRecord
	 * @deprecated - 1.6.4 [#363, #789] - use the other constructor instead for unattached
	 * records, or {@link org.jooq.impl.Factory#newRecord(org.jooq.Table)} for attached ones
	 */
	@Deprecated
	public InventoryRecord(org.jooq.Configuration configuration) {
		super(org.jooq.examples.mysql.sakila.tables.Inventory.INVENTORY, configuration);
	}
}
