/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.pojos;


import java.io.Serializable;

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
@Table(name = "T_TRIGGERS", schema = "TEST")
public class TTriggers implements Serializable {

	private static final long serialVersionUID = -35778878;

	private Integer idGenerated;
	private Integer id;
	private Integer counter;

	public TTriggers() {}

	public TTriggers(
		Integer idGenerated,
		Integer id,
		Integer counter
	) {
		this.idGenerated = idGenerated;
		this.id = id;
		this.counter = counter;
	}

	@Id
	@Column(name = "ID_GENERATED", unique = true, nullable = false, precision = 7)
	@NotNull
	public Integer getIdGenerated() {
		return this.idGenerated;
	}

	public void setIdGenerated(Integer idGenerated) {
		this.idGenerated = idGenerated;
	}

	@Column(name = "ID", nullable = false, precision = 7)
	@NotNull
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "COUNTER", nullable = false, precision = 7)
	@NotNull
	public Integer getCounter() {
		return this.counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TTriggers other = (TTriggers) obj;
		if (idGenerated == null) {
			if (other.idGenerated != null)
				return false;
		}
		else if (!idGenerated.equals(other.idGenerated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (counter == null) {
			if (other.counter != null)
				return false;
		}
		else if (!counter.equals(other.counter))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGenerated == null) ? 0 : idGenerated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((counter == null) ? 0 : counter.hashCode());
		return result;
	}
}
