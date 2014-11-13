/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_3666 extends org.jooq.impl.TableImpl<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record> {

	private static final long serialVersionUID = 97113063;

	/**
	 * The singleton instance of <code>PUBLIC.T_3666</code>
	 */
	public static final org.jooq.test.h2.generatedclasses.tables.T_3666 T_3666 = new org.jooq.test.h2.generatedclasses.tables.T_3666();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record> getRecordType() {
		return org.jooq.test.h2.generatedclasses.tables.records.T_3666Record.class;
	}

	/**
	 * The column <code>PUBLIC.T_3666.E1</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record, java.lang.Long> E1 = createField("E1", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * The column <code>PUBLIC.T_3666.E2</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record, java.math.BigDecimal> E2 = createField("E2", org.jooq.impl.SQLDataType.DECIMAL.precision(20, 5), this, "");

	/**
	 * The column <code>PUBLIC.T_3666.E3</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record, java.lang.Short> E3 = createField("E3", org.jooq.impl.SQLDataType.SMALLINT, this, "");

	/**
	 * The column <code>PUBLIC.T_3666.E4</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record, java.lang.Short> E4 = createField("E4", org.jooq.impl.SQLDataType.SMALLINT, this, "");

	/**
	 * The column <code>PUBLIC.T_3666.E5</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record, java.lang.Byte> E5 = createField("E5", org.jooq.impl.SQLDataType.TINYINT, this, "");

	/**
	 * The column <code>PUBLIC.T_3666.E6</code>.
	 */
	public final org.jooq.TableField<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record, java.math.BigDecimal> E6 = createField("E6", org.jooq.impl.SQLDataType.DECIMAL.precision(20, 5), this, "");

	/**
	 * Create a <code>PUBLIC.T_3666</code> table reference
	 */
	public T_3666() {
		this("T_3666", null);
	}

	/**
	 * Create an aliased <code>PUBLIC.T_3666</code> table reference
	 */
	public T_3666(java.lang.String alias) {
		this(alias, org.jooq.test.h2.generatedclasses.tables.T_3666.T_3666);
	}

	private T_3666(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record> aliased) {
		this(alias, aliased, null);
	}

	private T_3666(java.lang.String alias, org.jooq.Table<org.jooq.test.h2.generatedclasses.tables.records.T_3666Record> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.test.h2.generatedclasses.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.h2.generatedclasses.tables.T_3666 as(java.lang.String alias) {
		return new org.jooq.test.h2.generatedclasses.tables.T_3666(alias, this);
	}

	/**
	 * Rename this table
	 */
	public org.jooq.test.h2.generatedclasses.tables.T_3666 rename(java.lang.String name) {
		return new org.jooq.test.h2.generatedclasses.tables.T_3666(name, null);
	}
}
