/**
 * This class is generated by jOOQ
 */
package org.jooq.test.firebird.generatedclasses.tables.interfaces;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "F_TABLES5")
public interface IFTables5 extends java.io.Serializable {

	/**
	 * Setter for <code>F_TABLES5.V</code>.
	 */
	public void setV(java.lang.Integer value);

	/**
	 * Getter for <code>F_TABLES5.V</code>.
	 */
	@javax.persistence.Column(name = "V")
	public java.lang.Integer getV();

	/**
	 * Setter for <code>F_TABLES5.S</code>.
	 */
	public void setS(java.lang.Integer value);

	/**
	 * Getter for <code>F_TABLES5.S</code>.
	 */
	@javax.persistence.Column(name = "S")
	public java.lang.Integer getS();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface IFTables5
	 */
	public void from(org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables5 from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface IFTables5
	 */
	public <E extends org.jooq.test.firebird.generatedclasses.tables.interfaces.IFTables5> E into(E into);
}
