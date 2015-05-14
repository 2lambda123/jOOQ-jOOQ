/**
 * This class is generated by jOOQ
 */
package org.jooq.test.mysql.generatedclasses.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.test.mysql.generatedclasses.tables.XTestCase_85;


/**
 * An unused table in the same schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_85Record extends UpdatableRecordImpl<XTestCase_85Record> implements Record3<Integer, Integer, String> {

	private static final long serialVersionUID = -1218924698;

	/**
	 * Setter for <code>test.x_test_case_85.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>test.x_test_case_85.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>test.x_test_case_85.x_unused_id</code>.
	 */
	public void setXUnusedId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>test.x_test_case_85.x_unused_id</code>.
	 */
	public Integer getXUnusedId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>test.x_test_case_85.x_unused_name</code>.
	 */
	public void setXUnusedName(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>test.x_test_case_85.x_unused_name</code>.
	 */
	public String getXUnusedName() {
		return (String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Integer, Integer, String> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Integer, Integer, String> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return XTestCase_85.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return XTestCase_85.X_UNUSED_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return XTestCase_85.X_UNUSED_NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getXUnusedId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getXUnusedName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XTestCase_85Record value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XTestCase_85Record value2(Integer value) {
		setXUnusedId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XTestCase_85Record value3(String value) {
		setXUnusedName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XTestCase_85Record values(Integer value1, Integer value2, String value3) {
		value1(value1);
		value2(value2);
		value3(value3);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached XTestCase_85Record
	 */
	public XTestCase_85Record() {
		super(XTestCase_85.X_TEST_CASE_85);
	}

	/**
	 * Create a detached, initialised XTestCase_85Record
	 */
	public XTestCase_85Record(Integer id, Integer xUnusedId, String xUnusedName) {
		super(XTestCase_85.X_TEST_CASE_85);

		setValue(0, id);
		setValue(1, xUnusedId);
		setValue(2, xUnusedName);
	}
}
