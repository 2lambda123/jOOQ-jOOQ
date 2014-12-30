/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;
import org.jooq.test.postgres.generatedclasses.Keys;
import org.jooq.test.postgres.generatedclasses.Public;
import org.jooq.test.postgres.generatedclasses.tables.records.XTestCase_64_69Record;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class XTestCase_64_69 extends TableImpl<XTestCase_64_69Record> {

	private static final long serialVersionUID = 154513493;

	/**
	 * The reference instance of <code>public.x_test_case_64_69</code>
	 */
	public static final XTestCase_64_69 X_TEST_CASE_64_69 = new XTestCase_64_69();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<XTestCase_64_69Record> getRecordType() {
		return XTestCase_64_69Record.class;
	}

	/**
	 * The column <code>public.x_test_case_64_69.id</code>.
	 */
	public final TableField<XTestCase_64_69Record, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>public.x_test_case_64_69.unused_id</code>.
	 */
	public final TableField<XTestCase_64_69Record, Integer> UNUSED_ID = createField("unused_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>public.x_test_case_64_69</code> table reference
	 */
	public XTestCase_64_69() {
		this("x_test_case_64_69", null);
	}

	/**
	 * Create an aliased <code>public.x_test_case_64_69</code> table reference
	 */
	public XTestCase_64_69(String alias) {
		this(alias, X_TEST_CASE_64_69);
	}

	private XTestCase_64_69(String alias, Table<XTestCase_64_69Record> aliased) {
		this(alias, aliased, null);
	}

	private XTestCase_64_69(String alias, Table<XTestCase_64_69Record> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<XTestCase_64_69Record> getPrimaryKey() {
		return Keys.PK_X_TEST_CASE_64_69;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<XTestCase_64_69Record>> getKeys() {
		return Arrays.<UniqueKey<XTestCase_64_69Record>>asList(Keys.PK_X_TEST_CASE_64_69);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<XTestCase_64_69Record, ?>> getReferences() {
		return Arrays.<ForeignKey<XTestCase_64_69Record, ?>>asList(Keys.X_TEST_CASE_64_69__FK_X_TEST_CASE_64_69A, Keys.X_TEST_CASE_64_69__FK_X_TEST_CASE_64_69B);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public XTestCase_64_69 as(String alias) {
		return new XTestCase_64_69(alias, this);
	}

	/**
	 * Rename this table
	 */
	public XTestCase_64_69 rename(String name) {
		return new XTestCase_64_69(name, null);
	}
}
