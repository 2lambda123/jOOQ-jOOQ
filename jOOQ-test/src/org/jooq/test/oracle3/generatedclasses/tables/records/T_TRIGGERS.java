/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "T_TRIGGERS", schema = "TEST")
public class T_TRIGGERS extends org.jooq.impl.UpdatableRecordImpl<org.jooq.test.oracle3.generatedclasses.tables.records.T_TRIGGERS> {

	private static final long serialVersionUID = 40633938;

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public void setID_GENERATED(java.lang.Integer value) {
		setValue(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS.ID_GENERATED, value);
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	@javax.persistence.Id
	@javax.persistence.Column(name = "ID_GENERATED", unique = true)
	public java.lang.Integer getID_GENERATED() {
		return getValue(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS.ID_GENERATED);
	}

	/**
	 * An uncommented item
	 */
	public void setID(java.lang.Integer value) {
		setValue(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS.ID, value);
	}

	/**
	 * An uncommented item
	 */
	@javax.persistence.Column(name = "ID")
	public java.lang.Integer getID() {
		return getValue(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS.ID);
	}

	/**
	 * An uncommented item
	 */
	public void setCOUNTER(java.lang.Integer value) {
		setValue(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS.COUNTER, value);
	}

	/**
	 * An uncommented item
	 */
	@javax.persistence.Column(name = "COUNTER")
	public java.lang.Integer getCOUNTER() {
		return getValue(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS.COUNTER);
	}

	/**
	 * Create a detached T_TRIGGERS
	 */
	public T_TRIGGERS() {
		super(org.jooq.test.oracle3.generatedclasses.tables.T_TRIGGERS.T_TRIGGERS);
	}
}
