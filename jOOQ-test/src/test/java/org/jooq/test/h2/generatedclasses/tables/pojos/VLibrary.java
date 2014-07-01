/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.pojos;

/**
 * This is a POJO for table V_LIBRARY.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VLibrary implements org.jooq.test.h2.generatedclasses.tables.interfaces.IVLibrary {

	private static final long serialVersionUID = 630382170;

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
	public VLibrary setAuthor(java.lang.String author) {
		this.author = author;
		return this;
	}

	@Override
	public java.lang.String getTitle() {
		return this.title;
	}

	@Override
	public VLibrary setTitle(java.lang.String title) {
		this.title = title;
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(org.jooq.test.h2.generatedclasses.tables.interfaces.IVLibrary from) {
		setAuthor(from.getAuthor());
		setTitle(from.getTitle());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends org.jooq.test.h2.generatedclasses.tables.interfaces.IVLibrary> E into(E into) {
		into.from(this);
		return into;
	}

	// Here, a toString() method could be generated
}
