/**
 * This class is generated by jOOQ
 */
package org.jooq.test.vertica.generatedclasses.tables.interfaces;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IT_2845CaseSensitivity extends Serializable {

	/**
	 * Setter for <code>public.T_2845_CASE_sensitivity.id</code>.
	 */
	public void setId(Long value);

	/**
	 * Getter for <code>public.T_2845_CASE_sensitivity.id</code>.
	 */
	public Long getId();

	/**
	 * Setter for <code>public.T_2845_CASE_sensitivity.insensitive</code>.
	 */
	public void setInsensitive(Long value);

	/**
	 * Getter for <code>public.T_2845_CASE_sensitivity.insensitive</code>.
	 */
	public Long getInsensitive();

	/**
	 * Setter for <code>public.T_2845_CASE_sensitivity.UPPER</code>.
	 */
	public void setUpper(Long value);

	/**
	 * Getter for <code>public.T_2845_CASE_sensitivity.UPPER</code>.
	 */
	public Long getUpper();

	/**
	 * Setter for <code>public.T_2845_CASE_sensitivity.lower</code>.
	 */
	public void setLower(Long value);

	/**
	 * Getter for <code>public.T_2845_CASE_sensitivity.lower</code>.
	 */
	public Long getLower();

	/**
	 * Setter for <code>public.T_2845_CASE_sensitivity.Mixed</code>.
	 */
	public void setMixed(Long value);

	/**
	 * Getter for <code>public.T_2845_CASE_sensitivity.Mixed</code>.
	 */
	public Long getMixed();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface IT_2845CaseSensitivity
	 */
	public void from(org.jooq.test.vertica.generatedclasses.tables.interfaces.IT_2845CaseSensitivity from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface IT_2845CaseSensitivity
	 */
	public <E extends org.jooq.test.vertica.generatedclasses.tables.interfaces.IT_2845CaseSensitivity> E into(E into);
}
