/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.pojos;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * snapshot table for snapshot TEST.M_LIBRARY
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "M_LIBRARY", schema = "TEST")
public class MLibrary implements Serializable {

	private static final long serialVersionUID = 1600022882;

	private String author;
	private String title;

	public MLibrary() {}

	public MLibrary(
		String author,
		String title
	) {
		this.author = author;
		this.title = title;
	}

	@Column(name = "AUTHOR", length = 101)
	@Size(max = 101)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "TITLE", nullable = false, length = 400)
	@NotNull
	@Size(max = 400)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MLibrary other = (MLibrary) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		}
		else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		}
		else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
}
