/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.packages.test_synonym_package;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberObjectRecord;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.packages.TestSynonymPackage;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PublicObject extends AbstractRoutine<NumberObjectRecord> {

	private static final long serialVersionUID = 350793726;

	/**
	 * The parameter <code>TEST.TEST_SYNONYM_PACKAGE.PUBLIC_OBJECT.RETURN_VALUE</code>.
	 */
	public static final Parameter<NumberObjectRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.test.oracle.generatedclasses.multi_schema.udt.NumberObject.NUMBER_OBJECT.getDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public PublicObject() {
		super("PUBLIC_OBJECT", Test.TEST, TestSynonymPackage.TEST_SYNONYM_PACKAGE, org.jooq.test.oracle.generatedclasses.multi_schema.udt.NumberObject.NUMBER_OBJECT.getDataType());

		setReturnParameter(RETURN_VALUE);
	}
}
