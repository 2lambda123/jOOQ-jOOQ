/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jooq.test.oracle3.generatedclasses.tables.interfaces.M_LIBRARY_INTERFACE;


/**
 * snapshot table for snapshot TEST.M_LIBRARY
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "M_LIBRARY")
public class M_LIBRARY_POJO extends ThreadDeath implements Cloneable, M_LIBRARY_INTERFACE {

	private static final long serialVersionUID = -191743344;

	private String AUTHOR;
	private String TITLE;

	public M_LIBRARY_POJO() {}

	public M_LIBRARY_POJO(M_LIBRARY_POJO value) {
		this.AUTHOR = value.AUTHOR;
		this.TITLE = value.TITLE;
	}

	public M_LIBRARY_POJO(
		String AUTHOR,
		String TITLE
	) {
		this.AUTHOR = AUTHOR;
		this.TITLE = TITLE;
	}

	@Column(name = "AUTHOR", length = 101)
	@Override
	public String getAUTHOR() {
		return this.AUTHOR;
	}

	@Override
	public void setAUTHOR(String AUTHOR) {
		this.AUTHOR = AUTHOR;
	}

	@Column(name = "TITLE", nullable = false, length = 400)
	@Override
	public String getTITLE() {
		return this.TITLE;
	}

	@Override
	public void setTITLE(String TITLE) {
		this.TITLE = TITLE;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(M_LIBRARY_INTERFACE from) {
		setAUTHOR(from.getAUTHOR());
		setTITLE(from.getTITLE());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends M_LIBRARY_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}
}
