/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.pojos;


import java.io.Serializable;
import java.util.UUID;

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
@Table(name = "T_EXOTIC_TYPES", schema = "TEST")
public class TExoticTypes implements Serializable {

	private static final long serialVersionUID = 1697346402;

	private Integer      id;
	private UUID         uu;
	private Serializable javaIoSerializable;

	public TExoticTypes() {}

	public TExoticTypes(
		Integer      id,
		UUID         uu,
		Serializable javaIoSerializable
	) {
		this.id = id;
		this.uu = uu;
		this.javaIoSerializable = javaIoSerializable;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 7)
	@NotNull
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "UU")
	public UUID getUu() {
		return this.uu;
	}

	public void setUu(UUID uu) {
		this.uu = uu;
	}

	@Column(name = "JAVA_IO_SERIALIZABLE")
	public Serializable getJavaIoSerializable() {
		return this.javaIoSerializable;
	}

	public void setJavaIoSerializable(Serializable javaIoSerializable) {
		this.javaIoSerializable = javaIoSerializable;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TExoticTypes other = (TExoticTypes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (uu == null) {
			if (other.uu != null)
				return false;
		}
		else if (!uu.equals(other.uu))
			return false;
		if (javaIoSerializable == null) {
			if (other.javaIoSerializable != null)
				return false;
		}
		else if (!javaIoSerializable.equals(other.javaIoSerializable))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uu == null) ? 0 : uu.hashCode());
		result = prime * result + ((javaIoSerializable == null) ? 0 : javaIoSerializable.hashCode());
		return result;
	}
}
