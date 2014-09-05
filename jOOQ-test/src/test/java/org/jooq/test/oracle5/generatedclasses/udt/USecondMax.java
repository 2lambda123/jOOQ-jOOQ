/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.udt;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class USecondMax extends org.jooq.impl.UDTImpl<org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord> implements org.jooq.Package {

	private static final long serialVersionUID = -1766947220;

	/**
	 * The singleton instance of <code>TEST.U_SECOND_MAX</code>
	 */
	public static final org.jooq.test.oracle5.generatedclasses.udt.USecondMax U_SECOND_MAX = new org.jooq.test.oracle5.generatedclasses.udt.USecondMax();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord> getRecordType() {
		return org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord.class;
	}

	/**
	 * The attribute <code>TEST.U_SECOND_MAX.MAX</code>.
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord, java.math.BigDecimal> MAX = createField("MAX", org.jooq.impl.SQLDataType.NUMERIC, U_SECOND_MAX);

	/**
	 * The attribute <code>TEST.U_SECOND_MAX.SECMAX</code>.
	 */
	public static final org.jooq.UDTField<org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord, java.math.BigDecimal> SECMAX = createField("SECMAX", org.jooq.impl.SQLDataType.NUMERIC, U_SECOND_MAX);

	/**
	 * Call <code>TEST.U_SECOND_MAX.ODCIAGGREGATEINITIALIZE</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord odciaggregateinitialize(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord sctx) {
		org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateinitialize p = new org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateinitialize();
		p.setSctx(sctx);

		p.execute(configuration);
		return p.getSctx();
	}

	/**
	 * Call <code>TEST.U_SECOND_MAX.ODCIAGGREGATEITERATE</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord odciaggregateiterate(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord self, java.math.BigDecimal value) {
		org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateiterate p = new org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateiterate();
		p.setSelf(self);
		p.setValue(value);

		p.execute(configuration);
		return p.getSelf();
	}

	/**
	 * Call <code>TEST.U_SECOND_MAX.ODCIAGGREGATEMERGE</code>
	 */
	public static org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord odciaggregatemerge(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord self, org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord ctx2) {
		org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregatemerge p = new org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregatemerge();
		p.setSelf(self);
		p.setCtx2(ctx2);

		p.execute(configuration);
		return p.getSelf();
	}

	/**
	 * Call <code>TEST.U_SECOND_MAX.ODCIAGGREGATETERMINATE</code>
	 */
	public static java.math.BigDecimal odciaggregateterminate(org.jooq.Configuration configuration, org.jooq.test.oracle5.generatedclasses.udt.records.USecondMaxRecord self, java.math.BigDecimal flags) {
		org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateterminate p = new org.jooq.test.oracle5.generatedclasses.udt.u_second_max.Odciaggregateterminate();
		p.setSelf(self);
		p.setFlags(flags);

		p.execute(configuration);
		return p.getReturnvalue();
	}

	/**
	 * No further instances allowed
	 */
	private USecondMax() {
		super("U_SECOND_MAX", org.jooq.test.oracle5.generatedclasses.Test.TEST);

		// Initialise data type
		getDataType();
	}
}
