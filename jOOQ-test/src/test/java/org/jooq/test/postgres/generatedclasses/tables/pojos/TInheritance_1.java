/**
 * This class is generated by jOOQ
 */
package org.jooq.test.postgres.generatedclasses.tables.pojos;


import org.jooq.test.postgres.generatedclasses.tables.interfaces.ITInheritance_1;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TInheritance_1 implements ITInheritance_1 {

	private static final long serialVersionUID = 420551439;

	private final String text_1;

	public TInheritance_1(
		String text_1
	) {
		this.text_1 = text_1;
	}

	@Override
	public String getText_1() {
		return this.text_1;
	}
}
