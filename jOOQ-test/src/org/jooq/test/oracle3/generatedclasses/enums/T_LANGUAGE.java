/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle3.generatedclasses.enums;

/**
 * This class is generated by jOOQ.
 *
 * An entity holding language master data
 */
public enum T_LANGUAGE implements java.lang.Cloneable, org.jooq.MasterDataType<java.lang.Integer> {

	/**
	 * English
	 */
	en(1, "en", "English"),

	/**
	 * Deutsch
	 */
	de(2, "de", "Deutsch"),

	/**
	 * Français
	 */
	fr(3, "fr", "Français"),
	pt(4, "pt", null),
	;

	private final java.lang.Integer ID;
	private final java.lang.String CD;
	private final java.lang.String DESCRIPTION;

	private T_LANGUAGE(java.lang.Integer ID, java.lang.String CD, java.lang.String DESCRIPTION) {
		this.ID = ID;
		this.CD = CD;
		this.DESCRIPTION = DESCRIPTION;
	}

	@Override
	public java.lang.Integer getPrimaryKey() {
		return ID;
	}

	/**
	 * The language ID
	 * 
	 * PRIMARY KEY
	 */
	public final java.lang.Integer getID() {
		return ID;
	}

	/**
	 * The language ISO code
	 */
	public final java.lang.String getCD() {
		return CD;
	}

	/**
	 * The language description
	 */
	public final java.lang.String getDESCRIPTION() {
		return DESCRIPTION;
	}
}
