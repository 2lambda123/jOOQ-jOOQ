/**
 * This class is generated by jOOQ
 */
package org.jooq.test.jdbcoracle.generatedclasses.test.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_2025 implements org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.IXTestCase_2025 {

	private static final long serialVersionUID = -2340552;

	private java.lang.Integer refId;
	private java.lang.String  refName;

	public XTestCase_2025() {}

	public XTestCase_2025(
		java.lang.Integer refId,
		java.lang.String  refName
	) {
		this.refId = refId;
		this.refName = refName;
	}

	@Override
	public java.lang.Integer getRefId() {
		return this.refId;
	}

	@Override
	public void setRefId(java.lang.Integer refId) {
		this.refId = refId;
	}

	@Override
	public java.lang.String getRefName() {
		return this.refName;
	}

	@Override
	public void setRefName(java.lang.String refName) {
		this.refName = refName;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.IXTestCase_2025 from) {
		setRefId(from.getRefId());
		setRefName(from.getRefName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.IXTestCase_2025> E into(E into) {
		into.from(this);
		return into;
	}
}
