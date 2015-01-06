/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.pojos;


import org.jooq.test.h2.generatedclasses.tables.interfaces.ITBookStore;


/**
 * This is a POJO for table T_BOOK_STORE.
 * <p>
 * A book store
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TBookStore implements ITBookStore {

	private static final long serialVersionUID = 1919794828;

	private String name;

	public TBookStore() {}

	public TBookStore(TBookStore value) {
		this.name = value.name;
	}

	public TBookStore(
		String name
	) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public TBookStore setName(String name) {
		this.name = name;
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(ITBookStore from) {
		setName(from.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends ITBookStore> E into(E into) {
		into.from(this);
		return into;
	}

	// Here, a toString() method could be generated
}
