/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.test.oracle.generatedclasses.test.Keys;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.tables.records.XTestCase_85Record;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_85 extends TableImpl<XTestCase_85Record> {

	private static final long serialVersionUID = 153093888;

	/**
	 * The reference instance of <code>TEST.X_TEST_CASE_85</code>
	 */
	public static final XTestCase_85 X_TEST_CASE_85 = new XTestCase_85();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<XTestCase_85Record> getRecordType() {
		return XTestCase_85Record.class;
	}

	/**
	 * The column <code>TEST.X_TEST_CASE_85.ID</code>.
	 */
	public final TableField<XTestCase_85Record, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>TEST.X_TEST_CASE_85.X_UNUSED_ID</code>.
	 */
	public final TableField<XTestCase_85Record, Integer> X_UNUSED_ID = createField("X_UNUSED_ID", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>TEST.X_TEST_CASE_85.X_UNUSED_NAME</code>.
	 */
	public final TableField<XTestCase_85Record, String> X_UNUSED_NAME = createField("X_UNUSED_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(10), this, "");

	/**
	 * Create a <code>TEST.X_TEST_CASE_85</code> table reference
	 */
	public XTestCase_85() {
		this("X_TEST_CASE_85", null);
	}

	/**
	 * Create an aliased <code>TEST.X_TEST_CASE_85</code> table reference
	 */
	public XTestCase_85(String alias) {
		this(alias, X_TEST_CASE_85);
	}

	private XTestCase_85(String alias, Table<XTestCase_85Record> aliased) {
		this(alias, aliased, null);
	}

	private XTestCase_85(String alias, Table<XTestCase_85Record> aliased, Field<?>[] parameters) {
		super(alias, Test.TEST, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<XTestCase_85Record> getPrimaryKey() {
		return Keys.PK_X_TEST_CASE_85;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<XTestCase_85Record>> getKeys() {
		return Arrays.<UniqueKey<XTestCase_85Record>>asList(Keys.PK_X_TEST_CASE_85);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<XTestCase_85Record, ?>> getReferences() {
		return Arrays.<ForeignKey<XTestCase_85Record, ?>>asList(Keys.FK_X_TEST_CASE_85);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XTestCase_85 as(String alias) {
		return new XTestCase_85(alias, this);
	}

	/**
	 * Rename this table
	 */
	public XTestCase_85 rename(String name) {
		return new XTestCase_85(name, null);
	}
}
