/**
 * This class is generated by jOOQ
 */
package org.jooq.test.vertica.generatedclasses.tables.interfaces;


import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface ITExoticTypes extends Serializable {

	/**
	 * Setter for <code>public.t_exotic_types.ID</code>.
	 */
	public void setId(Long value);

	/**
	 * Getter for <code>public.t_exotic_types.ID</code>.
	 */
	public Long getId();

	/**
	 * Setter for <code>public.t_exotic_types.UU</code>.
	 */
	public void setUu(UUID value);

	/**
	 * Getter for <code>public.t_exotic_types.UU</code>.
	 */
	public UUID getUu();

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * Load data from another generated Record/POJO implementing the common interface ITExoticTypes
	 */
	public void from(org.jooq.test.vertica.generatedclasses.tables.interfaces.ITExoticTypes from);

	/**
	 * Copy data into another generated Record/POJO implementing the common interface ITExoticTypes
	 */
	public <E extends org.jooq.test.vertica.generatedclasses.tables.interfaces.ITExoticTypes> E into(E into);
}
