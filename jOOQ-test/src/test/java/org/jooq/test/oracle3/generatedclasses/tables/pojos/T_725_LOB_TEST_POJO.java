/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.pojos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_725_LOB_TEST_INTERFACE;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "T_725_LOB_TEST")
public class T_725_LOB_TEST_POJO extends ThreadDeath implements Cloneable, T_725_LOB_TEST_INTERFACE {

	private static final long serialVersionUID = 1586932162;

	private Integer ID;
	private byte[]  LOB;

	public T_725_LOB_TEST_POJO() {}

	public T_725_LOB_TEST_POJO(T_725_LOB_TEST_POJO value) {
		this.ID = value.ID;
		this.LOB = value.LOB;
	}

	public T_725_LOB_TEST_POJO(
		Integer ID,
		byte[]  LOB
	) {
		this.ID = ID;
		this.LOB = LOB;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@Override
	public Integer getID() {
		return this.ID;
	}

	@Override
	public void setID(Integer ID) {
		this.ID = ID;
	}

	@Column(name = "LOB")
	@Override
	public byte[] getLOB() {
		return this.LOB;
	}

	@Override
	public void setLOB(byte[] LOB) {
		this.LOB = LOB;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(T_725_LOB_TEST_INTERFACE from) {
		setID(from.getID());
		setLOB(from.getLOB());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends T_725_LOB_TEST_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}
}
