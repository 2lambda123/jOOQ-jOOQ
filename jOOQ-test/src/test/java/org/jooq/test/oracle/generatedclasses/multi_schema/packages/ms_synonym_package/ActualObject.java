/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.multi_schema.packages.ms_synonym_package;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.multi_schema.MultiSchema;
import org.jooq.test.oracle.generatedclasses.multi_schema.packages.MsSynonymPackage;
import org.jooq.test.oracle.generatedclasses.multi_schema.udt.records.NumberObjectRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActualObject extends AbstractRoutine<NumberObjectRecord> {

	private static final long serialVersionUID = -321434371;

	/**
	 * The parameter <code>MULTI_SCHEMA.MS_SYNONYM_PACKAGE.ACTUAL_OBJECT.RETURN_VALUE</code>.
	 */
	public static final Parameter<NumberObjectRecord> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.test.oracle.generatedclasses.multi_schema.udt.NumberObject.NUMBER_OBJECT.getDataType(), false);

	/**
	 * Create a new routine call instance
	 */
	public ActualObject() {
		super("ACTUAL_OBJECT", MultiSchema.MULTI_SCHEMA, MsSynonymPackage.MS_SYNONYM_PACKAGE, org.jooq.test.oracle.generatedclasses.multi_schema.udt.NumberObject.NUMBER_OBJECT.getDataType());

		setReturnParameter(RETURN_VALUE);
	}
}
