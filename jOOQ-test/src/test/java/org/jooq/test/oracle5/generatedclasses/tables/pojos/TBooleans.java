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
public class TBooleans implements java.io.Serializable {

	private static final long serialVersionUID = 2087337237;

	private java.math.BigDecimal id;
	private java.math.BigDecimal oneZero;
	private java.lang.String     trueFalseLc;
	private java.lang.String     trueFalseUc;
	private java.lang.String     yesNoLc;
	private java.lang.String     yesNoUc;
	private java.lang.String     yNLc;
	private java.lang.String     yNUc;
	private java.lang.String     vcBoolean;
	private java.lang.String     cBoolean;
	private java.math.BigDecimal nBoolean;

	public TBooleans() {}

	public TBooleans(
		java.math.BigDecimal id,
		java.math.BigDecimal oneZero,
		java.lang.String     trueFalseLc,
		java.lang.String     trueFalseUc,
		java.lang.String     yesNoLc,
		java.lang.String     yesNoUc,
		java.lang.String     yNLc,
		java.lang.String     yNUc,
		java.lang.String     vcBoolean,
		java.lang.String     cBoolean,
		java.math.BigDecimal nBoolean
	) {
		this.id = id;
		this.oneZero = oneZero;
		this.trueFalseLc = trueFalseLc;
		this.trueFalseUc = trueFalseUc;
		this.yesNoLc = yesNoLc;
		this.yesNoUc = yesNoUc;
		this.yNLc = yNLc;
		this.yNUc = yNUc;
		this.vcBoolean = vcBoolean;
		this.cBoolean = cBoolean;
		this.nBoolean = nBoolean;
	}

	public java.math.BigDecimal getId() {
		return this.id;
	}

	public void setId(java.math.BigDecimal id) {
		this.id = id;
	}

	public java.math.BigDecimal getOneZero() {
		return this.oneZero;
	}

	public void setOneZero(java.math.BigDecimal oneZero) {
		this.oneZero = oneZero;
	}

	public java.lang.String getTrueFalseLc() {
		return this.trueFalseLc;
	}

	public void setTrueFalseLc(java.lang.String trueFalseLc) {
		this.trueFalseLc = trueFalseLc;
	}

	public java.lang.String getTrueFalseUc() {
		return this.trueFalseUc;
	}

	public void setTrueFalseUc(java.lang.String trueFalseUc) {
		this.trueFalseUc = trueFalseUc;
	}

	public java.lang.String getYesNoLc() {
		return this.yesNoLc;
	}

	public void setYesNoLc(java.lang.String yesNoLc) {
		this.yesNoLc = yesNoLc;
	}

	public java.lang.String getYesNoUc() {
		return this.yesNoUc;
	}

	public void setYesNoUc(java.lang.String yesNoUc) {
		this.yesNoUc = yesNoUc;
	}

	public java.lang.String getYNLc() {
		return this.yNLc;
	}

	public void setYNLc(java.lang.String yNLc) {
		this.yNLc = yNLc;
	}

	public java.lang.String getYNUc() {
		return this.yNUc;
	}

	public void setYNUc(java.lang.String yNUc) {
		this.yNUc = yNUc;
	}

	public java.lang.String getVcBoolean() {
		return this.vcBoolean;
	}

	public void setVcBoolean(java.lang.String vcBoolean) {
		this.vcBoolean = vcBoolean;
	}

	public java.lang.String getCBoolean() {
		return this.cBoolean;
	}

	public void setCBoolean(java.lang.String cBoolean) {
		this.cBoolean = cBoolean;
	}

	public java.math.BigDecimal getNBoolean() {
		return this.nBoolean;
	}

	public void setNBoolean(java.math.BigDecimal nBoolean) {
		this.nBoolean = nBoolean;
	}
}
