/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.records;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Row;
import org.jooq.Row3;
import org.jooq.impl.TableRecordImpl;
import org.jooq.test.oracle.generatedclasses.test.tables.T_785;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "T_785", schema = "TEST")
public class T_785Record extends TableRecordImpl<T_785Record> implements Record3<Integer, String, String> {

	private static final long serialVersionUID = 1935167609;

	/**
	 * Setter for <code>TEST.T_785.ID</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>TEST.T_785.ID</code>.
	 */
	@Column(name = "ID", precision = 7)
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>TEST.T_785.NAME</code>.
	 */
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>TEST.T_785.NAME</code>.
	 */
	@Column(name = "NAME", length = 50)
	@Size(max = 50)
	public String getName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>TEST.T_785.VALUE</code>.
	 */
	public void setValue(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>TEST.T_785.VALUE</code>.
	 */
	@Column(name = "VALUE", length = 50)
	@Size(max = 50)
	public String getValue() {
		return (String) getValue(2);
	}

	// -------------------------------------------------------------------------
	// Record3 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Integer, String, String> fieldsRow() {
		return (Row3) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row3<Integer, String, String> valuesRow() {
		return (Row3) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return T_785.T_785.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return T_785.T_785.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return T_785.T_785.VALUE;
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
	public String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_785Record value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_785Record value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_785Record value3(String value) {
		setValue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_785Record values(Integer value1, String value2, String value3) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached T_785Record
	 */
	public T_785Record() {
		super(T_785.T_785);
	}

	/**
	 * Create a detached, initialised T_785Record
	 */
	public T_785Record(Integer id, String name, String value) {
		super(T_785.T_785);

		setValue(0, id);
		setValue(1, name);
		setValue(2, value);
	}
}
