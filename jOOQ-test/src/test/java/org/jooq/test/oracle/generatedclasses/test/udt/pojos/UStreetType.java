/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.pojos;


import java.io.Serializable;
import java.util.Arrays;

import javax.validation.constraints.Size;

import org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberArrayRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UStreetType implements Serializable {

	private static final long serialVersionUID = -1555982431;

	private String             street;
	private String             no;
	private UNumberArrayRecord floors;
	private byte[]             f_1323;
	private String             f_1326;

	public UStreetType() {}

	public UStreetType(
		String             street,
		String             no,
		UNumberArrayRecord floors,
		byte[]             f_1323,
		String             f_1326
	) {
		this.street = street;
		this.no = no;
		this.floors = floors;
		this.f_1323 = f_1323;
		this.f_1326 = f_1326;
	}

	@Size(max = 100)
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Size(max = 30)
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public UNumberArrayRecord getFloors() {
		return this.floors;
	}

	public void setFloors(UNumberArrayRecord floors) {
		this.floors = floors;
	}

	public byte[] getF_1323() {
		return this.f_1323;
	}

	public void setF_1323(byte[] f_1323) {
		this.f_1323 = f_1323;
	}

	public String getF_1326() {
		return this.f_1326;
	}

	public void setF_1326(String f_1326) {
		this.f_1326 = f_1326;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UStreetType other = (UStreetType) obj;
		if (street == null) {
			if (other.street != null)
				return false;
		}
		else if (!street.equals(other.street))
			return false;
		if (no == null) {
			if (other.no != null)
				return false;
		}
		else if (!no.equals(other.no))
			return false;
		if (floors == null) {
			if (other.floors != null)
				return false;
		}
		else if (!floors.equals(other.floors))
			return false;
		if (f_1323 == null) {
			if (other.f_1323 != null)
				return false;
		}
		else if (!Arrays.equals(f_1323, other.f_1323))
			return false;
		if (f_1326 == null) {
			if (other.f_1326 != null)
				return false;
		}
		else if (!f_1326.equals(other.f_1326))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((floors == null) ? 0 : floors.hashCode());
		result = prime * result + ((f_1323 == null) ? 0 : Arrays.hashCode(f_1323));
		result = prime * result + ((f_1326 == null) ? 0 : f_1326.hashCode());
		return result;
	}
}
