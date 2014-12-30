/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.routines;


import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.test.oracle.generatedclasses.test.Test;
import org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PTables3 extends AbstractRoutine<java.lang.Void> {

	private static final long serialVersionUID = 109637385;

	/**
	 * The parameter <code>TEST.P_TABLES3.IN_TABLE</code>.
	 */
	public static final Parameter<UStringTableRecord> IN_TABLE = createParameter("IN_TABLE", org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord.class), false);

	/**
	 * The parameter <code>TEST.P_TABLES3.OUT_TABLE</code>.
	 */
	public static final Parameter<UStringTableRecord> OUT_TABLE = createParameter("OUT_TABLE", org.jooq.impl.SQLDataType.VARCHAR.length(20).asArrayDataType(org.jooq.test.oracle.generatedclasses.test.udt.records.UStringTableRecord.class), false);

	/**
	 * Create a new routine call instance
	 */
	public PTables3() {
		super("P_TABLES3", Test.TEST);

		addInParameter(IN_TABLE);
		addOutParameter(OUT_TABLE);
	}

	/**
	 * Set the <code>IN_TABLE</code> parameter IN value to the routine
	 */
	public void setInTable(UStringTableRecord value) {
		setValue(IN_TABLE, value);
	}

	/**
	 * Get the <code>OUT_TABLE</code> parameter OUT value from the routine
	 */
	public UStringTableRecord getOutTable() {
		return getValue(OUT_TABLE);
	}
}
