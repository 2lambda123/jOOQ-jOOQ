/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.udt.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UAddressType implements java.io.Serializable {

	private static final long serialVersionUID = 267904537;

	private org.jooq.test.oracle5.generatedclasses.udt.pojos.UStreetType street;
	private java.lang.String                                             zip;
	private java.lang.String                                             city;
	private java.lang.String                                             country;
	private java.time.LocalDateTime                                      since;
	private java.math.BigDecimal                                         code;
	private byte[]                                                       f_1323;
	private java.lang.String                                             f_1326;

	public UAddressType() {}

	public UAddressType(
		org.jooq.test.oracle5.generatedclasses.udt.pojos.UStreetType street,
		java.lang.String                                             zip,
		java.lang.String                                             city,
		java.lang.String                                             country,
		java.time.LocalDateTime                                      since,
		java.math.BigDecimal                                         code,
		byte[]                                                       f_1323,
		java.lang.String                                             f_1326
	) {
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.since = since;
		this.code = code;
		this.f_1323 = f_1323;
		this.f_1326 = f_1326;
	}

	public org.jooq.test.oracle5.generatedclasses.udt.pojos.UStreetType getStreet() {
		return this.street;
	}

	public void setStreet(org.jooq.test.oracle5.generatedclasses.udt.pojos.UStreetType street) {
		this.street = street;
	}

	public java.lang.String getZip() {
		return this.zip;
	}

	public void setZip(java.lang.String zip) {
		this.zip = zip;
	}

	public java.lang.String getCity() {
		return this.city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getCountry() {
		return this.country;
	}

	public void setCountry(java.lang.String country) {
		this.country = country;
	}

	public java.time.LocalDateTime getSince() {
		return this.since;
	}

	public void setSince(java.time.LocalDateTime since) {
		this.since = since;
	}

	public java.math.BigDecimal getCode() {
		return this.code;
	}

	public void setCode(java.math.BigDecimal code) {
		this.code = code;
	}

	public byte[] getF_1323() {
		return this.f_1323;
	}

	public void setF_1323(byte[] f_1323) {
		this.f_1323 = f_1323;
	}

	public java.lang.String getF_1326() {
		return this.f_1326;
	}

	public void setF_1326(java.lang.String f_1326) {
		this.f_1326 = f_1326;
	}
}
