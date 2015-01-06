/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.pojos;


import java.sql.Date;

import org.jooq.test.h2.generatedclasses.tables.interfaces.IVAuthor;


/**
 * This is a POJO for table V_AUTHOR.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VAuthor implements IVAuthor {

	private static final long serialVersionUID = 1557526647;

	private Integer id;
	private String  firstName;
	private String  lastName;
	private Date    dateOfBirth;
	private Integer yearOfBirth;
	private String  address;

	public VAuthor() {}

	public VAuthor(VAuthor value) {
		this.id = value.id;
		this.firstName = value.firstName;
		this.lastName = value.lastName;
		this.dateOfBirth = value.dateOfBirth;
		this.yearOfBirth = value.yearOfBirth;
		this.address = value.address;
	}

	public VAuthor(
		Integer id,
		String  firstName,
		String  lastName,
		Date    dateOfBirth,
		Integer yearOfBirth,
		String  address
	) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.yearOfBirth = yearOfBirth;
		this.address = address;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public VAuthor setId(Integer id) {
		this.id = id;
		return this;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public VAuthor setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public VAuthor setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	@Override
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	@Override
	public VAuthor setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	@Override
	public Integer getYearOfBirth() {
		return this.yearOfBirth;
	}

	@Override
	public VAuthor setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
		return this;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public VAuthor setAddress(String address) {
		this.address = address;
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(IVAuthor from) {
		setId(from.getId());
		setFirstName(from.getFirstName());
		setLastName(from.getLastName());
		setDateOfBirth(from.getDateOfBirth());
		setYearOfBirth(from.getYearOfBirth());
		setAddress(from.getAddress());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends IVAuthor> E into(E into) {
		into.from(this);
		return into;
	}

	// Here, a toString() method could be generated
}
