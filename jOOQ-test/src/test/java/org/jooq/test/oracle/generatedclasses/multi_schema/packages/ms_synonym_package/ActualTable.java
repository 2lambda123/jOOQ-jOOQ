/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.multi_schema.packages.ms_synonym_package;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActualTable extends org.jooq.impl.AbstractRoutine<org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord> {

	private static final long serialVersionUID = -210295180;

	/**
	 * The parameter <code>MULTI_SCHEMA.MS_SYNONYM_PACKAGE.ACTUAL_TABLE.RETURN_VALUE</code>.
	 */
	public static final org.jooq.Parameter<org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public ActualTable() {
		super("ACTUAL_TABLE", org.jooq.test.oracle.generatedclasses.multi_schema.MultiSchema.MULTI_SCHEMA, org.jooq.test.oracle.generatedclasses.multi_schema.packages.MsSynonymPackage.MS_SYNONYM_PACKAGE, org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord.class));

		setReturnParameter(RETURN_VALUE);
	}
}
