/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.enums;

/**
 * This class is generated by jOOQ.
 */
public enum T_658_21 implements java.lang.Cloneable, org.jooq.MasterDataType<java.lang.Integer> {

	/**
	 * 1
	 */
	_1(1),

	/**
	 * 2
	 */
	_2(2),

	/**
	 * 3
	 */
	_3(3),
	;

	private final java.lang.Integer ID;

	private T_658_21(java.lang.Integer ID) {
		this.ID = ID;
	}

	@Override
	public java.lang.Integer getPrimaryKey() {
		return ID;
	}

	/**
	 * An uncommented item
	 * 
	 * PRIMARY KEY
	 */
	public final java.lang.Integer getID() {
		return ID;
	}
}
