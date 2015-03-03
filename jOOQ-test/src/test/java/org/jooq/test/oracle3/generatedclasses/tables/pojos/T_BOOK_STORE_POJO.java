/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.pojos;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_BOOK_STORE_INTERFACE;


/**
 * A book store
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "T_BOOK_STORE")
public class T_BOOK_STORE_POJO extends Object implements Serializable, T_BOOK_STORE_INTERFACE {

	private static final long serialVersionUID = 444344222;

	private String NAME;

	public T_BOOK_STORE_POJO() {}

	public T_BOOK_STORE_POJO(T_BOOK_STORE_POJO value) {
		this.NAME = value.NAME;
	}

	public T_BOOK_STORE_POJO(
		String NAME
	) {
		this.NAME = NAME;
	}

	@Id
	@Column(name = "NAME", unique = true, nullable = false, length = 400)
	@Override
	public String getNAME() {
		return this.NAME;
	}

	@Override
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(T_BOOK_STORE_INTERFACE from) {
		setNAME(from.getNAME());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends T_BOOK_STORE_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}
}
