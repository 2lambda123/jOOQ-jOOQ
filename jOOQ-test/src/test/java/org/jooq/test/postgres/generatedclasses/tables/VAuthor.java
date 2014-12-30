/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables;


import java.sql.Date;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;
import org.jooq.test.postgres.generatedclasses.Public;
import org.jooq.test.postgres.generatedclasses.tables.records.VAuthorRecord;
import org.jooq.test.postgres.generatedclasses.udt.records.UAddressTypeRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VAuthor extends TableImpl<VAuthorRecord> {

	private static final long serialVersionUID = 358475667;

	/**
	 * The reference instance of <code>public.v_author</code>
	 */
	public static final VAuthor V_AUTHOR = new VAuthor();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<VAuthorRecord> getRecordType() {
		return VAuthorRecord.class;
	}

	/**
	 * The column <code>public.v_author.id</code>.
	 */
	public final TableField<VAuthorRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.v_author.first_name</code>.
	 */
	public final TableField<VAuthorRecord, String> FIRST_NAME = createField("first_name", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>public.v_author.last_name</code>.
	 */
	public final TableField<VAuthorRecord, String> LAST_NAME = createField("last_name", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

	/**
	 * The column <code>public.v_author.date_of_birth</code>.
	 */
	public final TableField<VAuthorRecord, Date> DATE_OF_BIRTH = createField("date_of_birth", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>public.v_author.year_of_birth</code>.
	 */
	public final TableField<VAuthorRecord, Integer> YEAR_OF_BIRTH = createField("year_of_birth", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>public.v_author.address</code>.
	 */
	public final TableField<VAuthorRecord, UAddressTypeRecord> ADDRESS = createField("address", org.jooq.test.postgres.generatedclasses.udt.UAddressType.U_ADDRESS_TYPE.getDataType(), this, "");

	/**
	 * Create a <code>public.v_author</code> table reference
	 */
	public VAuthor() {
		this("v_author", null);
	}

	/**
	 * Create an aliased <code>public.v_author</code> table reference
	 */
	public VAuthor(String alias) {
		this(alias, V_AUTHOR);
	}

	private VAuthor(String alias, Table<VAuthorRecord> aliased) {
		this(alias, aliased, null);
	}

	private VAuthor(String alias, Table<VAuthorRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VAuthor as(String alias) {
		return new VAuthor(alias, this);
	}

	/**
	 * Rename this table
	 */
	public VAuthor rename(String name) {
		return new VAuthor(name, null);
	}
}
