/**
 * This class is generated by jOOQ
 */
package org.jooq.test.access.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_639NumbersTable extends org.jooq.impl.TableImpl<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord> {

	private static final long serialVersionUID = -628793962;

	/**
	 * The singleton instance of <code>t_639_numbers_table</code>
	 */
	public static final org.jooq.test.access.generatedclasses.tables.T_639NumbersTable T_639_NUMBERS_TABLE = new org.jooq.test.access.generatedclasses.tables.T_639NumbersTable();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord> getRecordType() {
		return org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord.class;
	}

	/**
	 * The column <code>t_639_numbers_table.ID</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.BYTE</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Object> BYTE = createField("BYTE", org.jooq.impl.SQLDataType.OTHER, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.SHORT</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Short> SHORT = createField("SHORT", org.jooq.impl.SQLDataType.SMALLINT, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.INTEGER</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Integer> INTEGER = createField("INTEGER", org.jooq.impl.SQLDataType.INTEGER, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.LONG</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Object> LONG = createField("LONG", org.jooq.impl.SQLDataType.OTHER, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.BYTE_DECIMAL</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> BYTE_DECIMAL = createField("BYTE_DECIMAL", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.SHORT_DECIMAL</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> SHORT_DECIMAL = createField("SHORT_DECIMAL", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.INTEGER_DECIMAL</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> INTEGER_DECIMAL = createField("INTEGER_DECIMAL", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.LONG_DECIMAL</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> LONG_DECIMAL = createField("LONG_DECIMAL", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.BIG_INTEGER</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> BIG_INTEGER = createField("BIG_INTEGER", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.BIG_DECIMAL</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> BIG_DECIMAL = createField("BIG_DECIMAL", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.FLOAT</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Float> FLOAT = createField("FLOAT", org.jooq.impl.SQLDataType.REAL, T_639_NUMBERS_TABLE);

	/**
	 * The column <code>t_639_numbers_table.DOUBLE</code>. 
	 */
	public static final org.jooq.TableField<org.jooq.test.access.generatedclasses.tables.records.T_639NumbersTableRecord, java.lang.Double> DOUBLE = createField("DOUBLE", org.jooq.impl.SQLDataType.DOUBLE, T_639_NUMBERS_TABLE);

	/**
	 * No further instances allowed
	 */
	private T_639NumbersTable() {
		super("t_639_numbers_table", org.jooq.test.access.generatedclasses.DefaultSchema.DEFAULT_SCHEMA);
	}
}
