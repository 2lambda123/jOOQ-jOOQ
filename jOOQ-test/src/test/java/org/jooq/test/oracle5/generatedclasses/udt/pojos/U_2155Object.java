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
public class U_2155Object implements java.io.Serializable {

	private static final long serialVersionUID = -1039830800;

	private java.time.LocalDateTime                                              d;
	private org.jooq.test.oracle5.generatedclasses.udt.records.U_2155ArrayRecord a;

	public U_2155Object() {}

	public U_2155Object(
		java.time.LocalDateTime                                              d,
		org.jooq.test.oracle5.generatedclasses.udt.records.U_2155ArrayRecord a
	) {
		this.d = d;
		this.a = a;
	}

	public java.time.LocalDateTime getD() {
		return this.d;
	}

	public void setD(java.time.LocalDateTime d) {
		this.d = d;
	}

	public org.jooq.test.oracle5.generatedclasses.udt.records.U_2155ArrayRecord getA() {
		return this.a;
	}

	public void setA(org.jooq.test.oracle5.generatedclasses.udt.records.U_2155ArrayRecord a) {
		this.a = a;
	}
}
