/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.tables.pojos;

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
public class T_785 implements java.io.Serializable {

	private static final long serialVersionUID = 496237873;

	private java.math.BigDecimal id;
	private java.lang.String     name;
	private java.lang.String     value;

	public T_785() {}

	public T_785(
		java.math.BigDecimal id,
		java.lang.String     name,
		java.lang.String     value
	) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public java.math.BigDecimal getId() {
		return this.id;
	}

	public void setId(java.math.BigDecimal id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getValue() {
		return this.value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}
}
