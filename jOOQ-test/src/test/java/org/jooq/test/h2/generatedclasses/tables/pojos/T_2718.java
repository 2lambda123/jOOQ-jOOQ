/**
 * This class is generated by jOOQ
 */
package org.jooq.test.h2.generatedclasses.tables.pojos;


import org.jooq.test.h2.generatedclasses.tables.interfaces.IT_2718;


/**
 * This is a POJO for table T_2718.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class T_2718 implements IT_2718 {

	private static final long serialVersionUID = -30931660;

	private Integer excludeMe;
	private Integer xx;

	public T_2718() {}

	public T_2718(T_2718 value) {
		this.excludeMe = value.excludeMe;
		this.xx = value.xx;
	}

	public T_2718(
		Integer excludeMe,
		Integer xx
	) {
		this.excludeMe = excludeMe;
		this.xx = xx;
	}

	@Override
	public Integer getExcludeMe() {
		return this.excludeMe;
	}

	@Override
	public T_2718 setExcludeMe(Integer excludeMe) {
		this.excludeMe = excludeMe;
		return this;
	}

	@Override
	public Integer getXx() {
		return this.xx;
	}

	@Override
	public T_2718 setXx(Integer xx) {
		this.xx = xx;
		return this;
	}

	// -------------------------------------------------------------------------
	// FROM and INTO
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void from(IT_2718 from) {
		setExcludeMe(from.getExcludeMe());
		setXx(from.getXx());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends IT_2718> E into(E into) {
		into.from(this);
		return into;
	}

	// Here, a toString() method could be generated
}
