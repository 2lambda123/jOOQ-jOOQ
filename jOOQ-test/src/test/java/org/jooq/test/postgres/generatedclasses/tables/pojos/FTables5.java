/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables.pojos;


import org.jooq.test.postgres.generatedclasses.tables.interfaces.IFTables5;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FTables5 implements IFTables5 {

	private static final long serialVersionUID = 1424461687;

	private final Integer v;
	private final Integer s;

	public FTables5(
		Integer v,
		Integer s
	) {
		this.v = v;
		this.s = s;
	}

	@Override
	public Integer getV() {
		return this.v;
	}

	@Override
	public Integer getS() {
		return this.s;
	}
}
