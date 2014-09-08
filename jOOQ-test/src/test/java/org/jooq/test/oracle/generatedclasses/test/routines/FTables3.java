/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FTables3 extends org.jooq.impl.AbstractRoutine<org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord> {

	private static final long serialVersionUID = -1792485469;

	/**
	 * The parameter <code>TEST.F_TABLES3.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord.class), false);

	/**
	 * The parameter <code>TEST.F_TABLES3.IN_TABLE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord> IN_TABLE = createParameter("IN_TABLE", org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public FTables3() {
		super("F_TABLES3", org.jooq.test.oracle.generatedclasses.test.Test.TEST, org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord.class));

		setReturnParameter(RETURN_VALUE);
		addInParameter(IN_TABLE);
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter IN value to the routine
	 */
	public void setInTable(org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord value) {
		setValue(org.jooq.test.oracle.generatedclasses.test.routines.FTables3.IN_TABLE, value);
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setInTable(org.jooq.Field<org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord> field) {
		setField(IN_TABLE, field);
	}
}
