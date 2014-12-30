/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.packages.test_synonym_package;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.packages.TestSynonymPackage;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PublicTable extends AbstractRoutine<NumberTableRecord> {

	private static final long serialVersionUID = 1789758189;

	/**
	 * The parameter <code>TEST.TEST_SYNONYM_PACKAGE.PUBLIC_TABLE.RETURN_VALUE</code>.
	 */
	public static final Parameter<NumberTableRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public PublicTable() {
		super("PUBLIC_TABLE", Test.TEST, TestSynonymPackage.TEST_SYNONYM_PACKAGE, org.jooq.impl.SQLDataType.INTEGER.asArrayDataType(org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberTableRecord.class));

		setReturnParameter(RETURN_VALUE);
	}
}
