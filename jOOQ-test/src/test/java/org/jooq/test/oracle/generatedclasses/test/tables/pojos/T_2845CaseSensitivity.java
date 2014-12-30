/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "T_2845_CASE_sensitivity", schema = "TEST")
public class T_2845CaseSensitivity implements Serializable {

	private static final long serialVersionUID = 201779414;

	private BigDecimal id;
	private BigDecimal insensitive;
	private BigDecimal upper;
	private BigDecimal lower;
	private BigDecimal mixed;

	public T_2845CaseSensitivity() {}

	public T_2845CaseSensitivity(
		BigDecimal id,
		BigDecimal insensitive,
		BigDecimal upper,
		BigDecimal lower,
		BigDecimal mixed
	) {
		this.id = id;
		this.insensitive = insensitive;
		this.upper = upper;
		this.lower = lower;
		this.mixed = mixed;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@NotNull
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "INSENSITIVE")
	public BigDecimal getInsensitive() {
		return this.insensitive;
	}

	public void setInsensitive(BigDecimal insensitive) {
		this.insensitive = insensitive;
	}

	@Column(name = "UPPER")
	public BigDecimal getUpper() {
		return this.upper;
	}

	public void setUpper(BigDecimal upper) {
		this.upper = upper;
	}

	@Column(name = "lower")
	public BigDecimal getLower() {
		return this.lower;
	}

	public void setLower(BigDecimal lower) {
		this.lower = lower;
	}

	@Column(name = "Mixed")
	public BigDecimal getMixed() {
		return this.mixed;
	}

	public void setMixed(BigDecimal mixed) {
		this.mixed = mixed;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final T_2845CaseSensitivity other = (T_2845CaseSensitivity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (insensitive == null) {
			if (other.insensitive != null)
				return false;
		}
		else if (!insensitive.equals(other.insensitive))
			return false;
		if (upper == null) {
			if (other.upper != null)
				return false;
		}
		else if (!upper.equals(other.upper))
			return false;
		if (lower == null) {
			if (other.lower != null)
				return false;
		}
		else if (!lower.equals(other.lower))
			return false;
		if (mixed == null) {
			if (other.mixed != null)
				return false;
		}
		else if (!mixed.equals(other.mixed))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((insensitive == null) ? 0 : insensitive.hashCode());
		result = prime * result + ((upper == null) ? 0 : upper.hashCode());
		result = prime * result + ((lower == null) ? 0 : lower.hashCode());
		result = prime * result + ((mixed == null) ? 0 : mixed.hashCode());
		return result;
	}
}
