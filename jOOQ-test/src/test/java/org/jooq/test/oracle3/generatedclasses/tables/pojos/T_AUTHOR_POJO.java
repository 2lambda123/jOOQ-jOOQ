/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.pojos;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.test.oracle3.generatedclasses.tables.interfaces.T_AUTHOR_INTERFACE;
import org.jooq.test.oracle3.generatedclasses.udt.interfaces.U_ADDRESS_TYPE_INTERFACE;
import org.jooq.test.oracle3.generatedclasses.udt.pojos.U_ADDRESS_TYPE_POJO;


/**
 * An entity holding authors of books
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "T_AUTHOR")
public class T_AUTHOR_POJO extends ThreadDeath implements Cloneable, T_AUTHOR_INTERFACE {

	private static final long serialVersionUID = -446414258;

	private Integer             ID;
	private String              FIRST_NAME;
	private String              LAST_NAME;
	private Date                DATE_OF_BIRTH;
	private Integer             YEAR_OF_BIRTH;
	private U_ADDRESS_TYPE_POJO ADDRESS;

	public T_AUTHOR_POJO() {}

	public T_AUTHOR_POJO(T_AUTHOR_POJO value) {
		this.ID = value.ID;
		this.FIRST_NAME = value.FIRST_NAME;
		this.LAST_NAME = value.LAST_NAME;
		this.DATE_OF_BIRTH = value.DATE_OF_BIRTH;
		this.YEAR_OF_BIRTH = value.YEAR_OF_BIRTH;
		this.ADDRESS = value.ADDRESS;
	}

	public T_AUTHOR_POJO(
		Integer             ID,
		String              FIRST_NAME,
		String              LAST_NAME,
		Date                DATE_OF_BIRTH,
		Integer             YEAR_OF_BIRTH,
		U_ADDRESS_TYPE_POJO ADDRESS
	) {
		this.ID = ID;
		this.FIRST_NAME = FIRST_NAME;
		this.LAST_NAME = LAST_NAME;
		this.DATE_OF_BIRTH = DATE_OF_BIRTH;
		this.YEAR_OF_BIRTH = YEAR_OF_BIRTH;
		this.ADDRESS = ADDRESS;
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

	@Column(name = "FIRST_NAME", length = 50)
	@Override
	public String getFIRST_NAME() {
		return this.FIRST_NAME;
	}

	@Override
	public void setFIRST_NAME(String FIRST_NAME) {
		this.FIRST_NAME = FIRST_NAME;
	}

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@Override
	public String getLAST_NAME() {
		return this.LAST_NAME;
	}

	@Override
	public void setLAST_NAME(String LAST_NAME) {
		this.LAST_NAME = LAST_NAME;
	}

	@Column(name = "DATE_OF_BIRTH")
	@Override
	public Date getDATE_OF_BIRTH() {
		return this.DATE_OF_BIRTH;
	}

	@Override
	public void setDATE_OF_BIRTH(Date DATE_OF_BIRTH) {
		this.DATE_OF_BIRTH = DATE_OF_BIRTH;
	}

	@Column(name = "YEAR_OF_BIRTH", precision = 7)
	@Override
	public Integer getYEAR_OF_BIRTH() {
		return this.YEAR_OF_BIRTH;
	}

	@Override
	public void setYEAR_OF_BIRTH(Integer YEAR_OF_BIRTH) {
		this.YEAR_OF_BIRTH = YEAR_OF_BIRTH;
	}

	@Column(name = "ADDRESS")
	@Override
	public U_ADDRESS_TYPE_POJO getADDRESS() {
		return this.ADDRESS;
	}

	public void setADDRESS(U_ADDRESS_TYPE_POJO ADDRESS) {
		this.ADDRESS = ADDRESS;
	}

	@Override
	public void setADDRESS(U_ADDRESS_TYPE_INTERFACE ADDRESS) {
		if (ADDRESS == null)
			this.ADDRESS = null;
		else
			this.ADDRESS = ADDRESS.into(new U_ADDRESS_TYPE_POJO());
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(T_AUTHOR_INTERFACE from) {
		setID(from.getID());
		setFIRST_NAME(from.getFIRST_NAME());
		setLAST_NAME(from.getLAST_NAME());
		setDATE_OF_BIRTH(from.getDATE_OF_BIRTH());
		setYEAR_OF_BIRTH(from.getYEAR_OF_BIRTH());
		setADDRESS(from.getADDRESS());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends T_AUTHOR_INTERFACE> E into(E into) {
		into.from(this);
		return into;
	}
}
