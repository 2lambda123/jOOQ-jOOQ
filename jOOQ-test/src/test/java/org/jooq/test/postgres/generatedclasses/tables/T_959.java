/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables;


import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;
import org.jooq.test.postgres.generatedclasses.Public;
import org.jooq.test.postgres.generatedclasses.enums.U_959;
import org.jooq.test.postgres.generatedclasses.tables.records.T_959Record;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_959 extends TableImpl<T_959Record> {

	private static final long serialVersionUID = 1866712378;

	/**
	 * The reference instance of <code>public.t_959</code>
	 */
	public static final T_959 T_959 = new T_959();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<T_959Record> getRecordType() {
		return T_959Record.class;
	}

	/**
	 * The column <code>public.t_959.java_keywords</code>.
	 */
	public final TableField<T_959Record, U_959> JAVA_KEYWORDS = createField("java_keywords", org.jooq.util.postgres.PostgresDataType.VARCHAR.asEnumDataType(org.jooq.test.postgres.generatedclasses.enums.U_959.class), this, "");

	/**
	 * Create a <code>public.t_959</code> table reference
	 */
	public T_959() {
		this("t_959", null);
	}

	/**
	 * Create an aliased <code>public.t_959</code> table reference
	 */
	public T_959(String alias) {
		this(alias, T_959);
	}

	private T_959(String alias, Table<T_959Record> aliased) {
		this(alias, aliased, null);
	}

	private T_959(String alias, Table<T_959Record> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T_959 as(String alias) {
		return new T_959(alias, this);
	}

	/**
	 * Rename this table
	 */
	public T_959 rename(String name) {
		return new T_959(name, null);
	}
}
