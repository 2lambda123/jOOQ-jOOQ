/**
 * This class is generated by jOOQ
 */
package org.jooq.util.sybase.sys.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = "http://jooq.sourceforge.net",
                            comments = "This class is generated by jOOQ")
public class SysprocparmRecord extends org.jooq.impl.TableRecordImpl<org.jooq.util.sybase.sys.tables.records.SysprocparmRecord> {

	private static final long serialVersionUID = -1117724127;

	/**
	 * An uncommented item
	 */
	public void setProcId(java.lang.Integer value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PROC_ID, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Integer getProcId() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PROC_ID);
	}

	/**
	 * An uncommented item
	 */
	public void setParmId(java.lang.Short value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_ID, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Short getParmId() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_ID);
	}

	/**
	 * An uncommented item
	 */
	public void setParmType(java.lang.Short value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_TYPE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Short getParmType() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_TYPE);
	}

	/**
	 * An uncommented item
	 */
	public void setParmModeIn(java.lang.String value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_MODE_IN, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getParmModeIn() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_MODE_IN);
	}

	/**
	 * An uncommented item
	 */
	public void setParmModeOut(java.lang.String value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_MODE_OUT, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getParmModeOut() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_MODE_OUT);
	}

	/**
	 * An uncommented item
	 */
	public void setDomainId(java.lang.Short value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.DOMAIN_ID, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Short getDomainId() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.DOMAIN_ID);
	}

	/**
	 * An uncommented item
	 */
	public void setWidth(java.lang.Long value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.WIDTH, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Long getWidth() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.WIDTH);
	}

	/**
	 * An uncommented item
	 */
	public void setScale(java.lang.Short value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.SCALE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Short getScale() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.SCALE);
	}

	/**
	 * An uncommented item
	 */
	public void setUserType(java.lang.Short value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.USER_TYPE, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.Short getUserType() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.USER_TYPE);
	}

	/**
	 * An uncommented item
	 */
	public void setParmName(java.lang.String value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_NAME, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getParmName() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.PARM_NAME);
	}

	/**
	 * An uncommented item
	 */
	public void setDefault(java.lang.String value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.DEFAULT, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getDefault() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.DEFAULT);
	}

	/**
	 * An uncommented item
	 */
	public void setRemarks(java.lang.String value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.REMARKS, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getRemarks() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.REMARKS);
	}

	/**
	 * An uncommented item
	 */
	public void setBaseTypeStr(java.lang.String value) {
		setValue(org.jooq.util.sybase.sys.tables.Sysprocparm.BASE_TYPE_STR, value);
	}

	/**
	 * An uncommented item
	 */
	public java.lang.String getBaseTypeStr() {
		return getValue(org.jooq.util.sybase.sys.tables.Sysprocparm.BASE_TYPE_STR);
	}

	/**
	 * Create a detached SysprocparmRecord
	 */
	public SysprocparmRecord() {
		super(org.jooq.util.sybase.sys.tables.Sysprocparm.SYSPROCPARM);
	}
}
