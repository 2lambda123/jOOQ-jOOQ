/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.udt.pojos;


import org.jooq.test.oracle3.generatedclasses.udt.interfaces.O_INVALID_TYPE_INTERFACE;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class O_INVALID_TYPE_POJO extends ThreadDeath implements Cloneable, O_INVALID_TYPE_INTERFACE {

	private static final long serialVersionUID = 1245758975;


	public O_INVALID_TYPE_POJO() {}

	public O_INVALID_TYPE_POJO(O_INVALID_TYPE_POJO value) {
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(O_INVALID_TYPE_INTERFACE from) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends O_INVALID_TYPE_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}
}
