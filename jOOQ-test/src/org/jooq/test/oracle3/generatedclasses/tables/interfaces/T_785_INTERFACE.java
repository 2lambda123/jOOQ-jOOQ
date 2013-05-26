/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.interfaces;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
@javax.persistence.Entity
@javax.persistence.Table(name = "T_785")
public interface T_785_INTERFACE extends java.lang.Cloneable, java.io.Serializable {

	/**
	 * Setter for <code>T_785.ID</code>. 
	 */
	public void setID(java.lang.Integer value);

	/**
	 * Getter for <code>T_785.ID</code>. 
	 */
	@javax.persistence.Column(name = "ID", precision = 7)
	public java.lang.Integer getID();

	/**
	 * Setter for <code>T_785.NAME</code>. 
	 */
	public void setNAME(java.lang.String value);

	/**
	 * Getter for <code>T_785.NAME</code>. 
	 */
	@javax.persistence.Column(name = "NAME", length = 50)
	public java.lang.String getNAME();

	/**
	 * Setter for <code>T_785.VALUE</code>. 
	 */
	public void setVALUE(java.lang.String value);

	/**
	 * Getter for <code>T_785.VALUE</code>. 
	 */
	@javax.persistence.Column(name = "VALUE", length = 50)
	public java.lang.String getVALUE();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface T_785_INTERFACE
	 */
	public void from(org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_785_INTERFACE from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface T_785_INTERFACE
	 */
	public <E extends org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_785_INTERFACE> E into(E into);
}
