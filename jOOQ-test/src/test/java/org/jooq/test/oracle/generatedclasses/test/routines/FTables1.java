/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FTables1 extends org.jooq.impl.AbstractRoutine<org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord> {

	private static final long serialVersionUID = -234667857;

	/**
	 * The parameter <code>TEST.F_TABLES1.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord.class), false);

	/**
	 * The parameter <code>TEST.F_TABLES1.IN_TABLE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord> IN_TABLE = createParameter("IN_TABLE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public FTables1() {
		super("F_TABLES1", org.jooq.test.oracle.generatedclasses.test.Test.TEST, org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord.class));

		setReturnParameter(RETURN_VALUE);
		addInParameter(IN_TABLE);
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter IN value to the routine
	 */
	public void setInTable(org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord value) {
		setValue(org.jooq.test.oracle.generatedclasses.test.routines.FTables1.IN_TABLE, value);
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter to the function to be used with a {@link org.jooq.Select} statement
	 */
	public void setInTable(org.jooq.Field<org.jooq.test.oracle.generatedclasses.test.udt.records.UNumberTableRecord> field) {
		setField(IN_TABLE, field);
	}
}
