/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.pojos;

/**
 * This is a POJO for table T_TRIGGERS.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TTriggers implements org.jooq.test.h2.generatedclasses.tables.interfaces.ITTriggers {

	private static final long serialVersionUID = 1770300003;

	private java.lang.Integer idGenerated;
	private java.lang.Integer id;
	private java.lang.Integer counter;

	public TTriggers() {}

	public TTriggers(
		java.lang.Integer idGenerated,
		java.lang.Integer id,
		java.lang.Integer counter
	) {
		this.idGenerated = idGenerated;
		this.id = id;
		this.counter = counter;
	}

	@Override
	public java.lang.Integer getIdGenerated() {
		return this.idGenerated;
	}

	@Override
	public TTriggers setIdGenerated(java.lang.Integer idGenerated) {
		this.idGenerated = idGenerated;
		return this;
	}

	@Override
	public java.lang.Integer getId() {
		return this.id;
	}

	@Override
	public TTriggers setId(java.lang.Integer id) {
		this.id = id;
		return this;
	}

	@Override
	public java.lang.Integer getCounter() {
		return this.counter;
	}

	@Override
	public TTriggers setCounter(java.lang.Integer counter) {
		this.counter = counter;
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.h2.generatedclasses.tables.interfaces.ITTriggers from) {
		setIdGenerated(from.getIdGenerated());
		setId(from.getId());
		setCounter(from.getCounter());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.h2.generatedclasses.tables.interfaces.ITTriggers> E into(E into) {
		into.from(this);
		return into;
	}

	// Here, a toString() method could be generated
}
