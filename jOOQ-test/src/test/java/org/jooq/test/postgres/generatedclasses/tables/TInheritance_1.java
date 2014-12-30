/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables;


import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;
import org.jooq.test.postgres.generatedclasses.Public;
import org.jooq.test.postgres.generatedclasses.tables.records.TInheritance_1Record;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TInheritance_1 extends TableImpl<TInheritance_1Record> {

	private static final long serialVersionUID = 1509360828;

	/**
	 * The reference instance of <code>public.t_inheritance_1</code>
	 */
	public static final TInheritance_1 T_INHERITANCE_1 = new TInheritance_1();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TInheritance_1Record> getRecordType() {
		return TInheritance_1Record.class;
	}

	/**
	 * The column <code>public.t_inheritance_1.text_1</code>.
	 */
	public final TableField<TInheritance_1Record, String> TEXT_1 = createField("text_1", org.jooq.impl.SQLDataType.CLOB, this, "");

	/**
	 * Create a <code>public.t_inheritance_1</code> table reference
	 */
	public TInheritance_1() {
		this("t_inheritance_1", null);
	}

	/**
	 * Create an aliased <code>public.t_inheritance_1</code> table reference
	 */
	public TInheritance_1(String alias) {
		this(alias, T_INHERITANCE_1);
	}

	private TInheritance_1(String alias, Table<TInheritance_1Record> aliased) {
		this(alias, aliased, null);
	}

	private TInheritance_1(String alias, Table<TInheritance_1Record> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TInheritance_1 as(String alias) {
		return new TInheritance_1(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TInheritance_1 rename(String name) {
		return new TInheritance_1(name, null);
	}
}
