/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables.pojos;


import org.jooq.test.postgres.generatedclasses.tables.interfaces.IT_785;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_785 implements IT_785 {

	private static final long serialVersionUID = -1387697830;

	private final Integer id;
	private final String  name;
	private final String  value;

	public T_785(
		Integer id,
		String  name,
		String  value
	) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getValue() {
		return this.value;
	}
}
