/**
 * This class is generated by jOOQ
 */
package org.jooq.examples.sqlserver.adventureworks.person.tables;

/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings("all")
public class Contact extends org.jooq.impl.UpdatableTableImpl<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact> {

	private static final long serialVersionUID = 1988254605;

	/**
	 * The singleton instance of <code>Person.Contact</code>
	 */
	public static final org.jooq.examples.sqlserver.adventureworks.person.tables.Contact Contact = new org.jooq.examples.sqlserver.adventureworks.person.tables.Contact();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact> getRecordType() {
		return org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact.class;
	}

	/**
	 * The column <code>Person.Contact.ContactID</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.Integer> ContactID = createField("ContactID", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>Person.Contact.NameStyle</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.Boolean> NameStyle = createField("NameStyle", org.jooq.impl.SQLDataType.BIT, this);

	/**
	 * The column <code>Person.Contact.Title</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> Title = createField("Title", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.FirstName</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> FirstName = createField("FirstName", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.MiddleName</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> MiddleName = createField("MiddleName", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.LastName</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> LastName = createField("LastName", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.Suffix</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> Suffix = createField("Suffix", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.EmailAddress</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> EmailAddress = createField("EmailAddress", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.EmailPromotion</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.Integer> EmailPromotion = createField("EmailPromotion", org.jooq.impl.SQLDataType.INTEGER, this);

	/**
	 * The column <code>Person.Contact.Phone</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> Phone = createField("Phone", org.jooq.impl.SQLDataType.NVARCHAR, this);

	/**
	 * The column <code>Person.Contact.PasswordHash</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> PasswordHash = createField("PasswordHash", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * The column <code>Person.Contact.PasswordSalt</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> PasswordSalt = createField("PasswordSalt", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * The column <code>Person.Contact.AdditionalContactInfo</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.Object> AdditionalContactInfo = createField("AdditionalContactInfo", org.jooq.impl.DefaultDataType.getDefaultDataType("xml"), this);

	/**
	 * The column <code>Person.Contact.rowguid</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.String> rowguid = createField("rowguid", org.jooq.impl.SQLDataType.VARCHAR, this);

	/**
	 * The column <code>Person.Contact.ModifiedDate</code>. 
	 */
	public final org.jooq.TableField<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.sql.Timestamp> ModifiedDate = createField("ModifiedDate", org.jooq.impl.SQLDataType.TIMESTAMP, this);

	/**
	 * Create a <code>Person.Contact</code> table reference
	 */
	public Contact() {
		super("Contact", org.jooq.examples.sqlserver.adventureworks.person.Person.Person);
	}

	/**
	 * Create an aliased <code>Person.Contact</code> table reference
	 */
	public Contact(java.lang.String alias) {
		super(alias, org.jooq.examples.sqlserver.adventureworks.person.Person.Person, org.jooq.examples.sqlserver.adventureworks.person.tables.Contact.Contact);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact, java.lang.Integer> getIdentity() {
		return org.jooq.examples.sqlserver.adventureworks.person.Keys.IDENTITY_Contact;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact> getPrimaryKey() {
		return org.jooq.examples.sqlserver.adventureworks.person.Keys.PK_Contact_ContactID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<org.jooq.examples.sqlserver.adventureworks.person.tables.records.Contact>>asList(org.jooq.examples.sqlserver.adventureworks.person.Keys.PK_Contact_ContactID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.examples.sqlserver.adventureworks.person.tables.Contact as(java.lang.String alias) {
		return new org.jooq.examples.sqlserver.adventureworks.person.tables.Contact(alias);
	}
}
