/**
 * This class is generated by jOOQ
 */
package org.jooq.test.jdbcoracle.generatedclasses.test.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VLibrary implements org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.IVLibrary {

	private static final long serialVersionUID = 445508730;

	private java.lang.String author;
	private java.lang.String title;

	public VLibrary() {}

	public VLibrary(
		java.lang.String author,
		java.lang.String title
	) {
		this.author = author;
		this.title = title;
	}

	@Override
	public java.lang.String getAuthor() {
		return this.author;
	}

	@Override
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}

	@Override
	public java.lang.String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.IVLibrary from) {
		setAuthor(from.getAuthor());
		setTitle(from.getTitle());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.jdbcoracle.generatedclasses.test.tables.interfaces.IVLibrary> E into(E into) {
		into.from(this);
		return into;
	}
}
