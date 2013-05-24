/**
 * This class is generated by jOOQ
 */
package org.jooq.test.hsqldb.generatedclasses.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TIdentity extends org.jooq.impl.TableImpl<org.jooq.test.hsqldb.generatedclasses.tables.records.TIdentityRecord> {

	private static final long serialVersionUID = 339638575;

	/**
	 * The singleton instance of <code>PUBLIC.T_IDENTITY</code>
	 */
	public static final org.jooq.test.hsqldb.generatedclasses.tables.TIdentity T_IDENTITY = new org.jooq.test.hsqldb.generatedclasses.tables.TIdentity();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.test.hsqldb.generatedclasses.tables.records.TIdentityRecord> getRecordType() {
		return org.jooq.test.hsqldb.generatedclasses.tables.records.TIdentityRecord.class;
	}

	/**
	 * The column <code>PUBLIC.T_IDENTITY.ID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TIdentityRecord, java.lang.Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>PUBLIC.T_IDENTITY.VAL</code>. 
	 */
	public final org.jooq.TableField<org.jooq.test.hsqldb.generatedclasses.tables.records.TIdentityRecord, java.lang.Integer> VAL = createField("VAL", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * Create a <code>PUBLIC.T_IDENTITY</code> table reference
	 */
	public TIdentity() {
		super("T_IDENTITY", org.jooq.test.hsqldb.generatedclasses.Public.PUBLIC);
	}

	/**
	 * Create an aliased <code>PUBLIC.T_IDENTITY</code> table reference
	 */
	public TIdentity(java.lang.String alias) {
		super(alias, org.jooq.test.hsqldb.generatedclasses.Public.PUBLIC, org.jooq.test.hsqldb.generatedclasses.tables.TIdentity.T_IDENTITY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.test.hsqldb.generatedclasses.tables.records.TIdentityRecord, java.lang.Integer> getIdentity() {
		return org.jooq.test.hsqldb.generatedclasses.Keys.IDENTITY_T_IDENTITY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.test.hsqldb.generatedclasses.tables.TIdentity as(java.lang.String alias) {
		return new org.jooq.test.hsqldb.generatedclasses.tables.TIdentity(alias);
	}
}
