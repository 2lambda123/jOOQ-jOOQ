/**
 * This class is generated by jOOQ
 */
package org.jooq.test.jdbcoracle.generatedclasses.test.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TTriggers implements org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.ITTriggers {

	private static final long serialVersionUID = 1709975189;

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
	public void setIdGenerated(java.lang.Integer idGenerated) {
		this.idGenerated = idGenerated;
	}

	@Override
	public java.lang.Integer getId() {
		return this.id;
	}

	@Override
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	@Override
	public java.lang.Integer getCounter() {
		return this.counter;
	}

	@Override
	public void setCounter(java.lang.Integer counter) {
		this.counter = counter;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.ITTriggers from) {
		setIdGenerated(from.getIdGenerated());
		setId(from.getId());
		setCounter(from.getCounter());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.ITTriggers> E into(E into) {
		into.from(this);
		return into;
	}
}
