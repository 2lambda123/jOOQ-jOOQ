/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.udt.pojos;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class U_2155Object implements java.io.Serializable {

	private static final long serialVersionUID = 939003352;

	private java.sql.Date                                                            d;
	private org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord a;

	public U_2155Object() {}

	public U_2155Object(
		java.sql.Date                                                            d,
		org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord a
	) {
		this.d = d;
		this.a = a;
	}

	public java.sql.Date getD() {
		return this.d;
	}

	public void setD(java.sql.Date d) {
		this.d = d;
	}

	public org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord getA() {
		return this.a;
	}

	public void setA(org.jooq.test.oracle.generatedclasses.test.udt.records.U_2155ArrayRecord a) {
		this.a = a;
	}
}
