/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.records;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record11;
import org.jooq.Row;
import org.jooq.Row11;
import org.jooq.impl.TableRecordImpl;
import org.jooq.test.oracle.generatedclasses.test.tables.VIncomplete;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "V_INCOMPLETE", schema = "TEST")
public class VIncompleteRecord extends TableRecordImpl<VIncompleteRecord> implements Record11<Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object> {

	private static final long serialVersionUID = -1137516581;

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.ID</code>.
	 */
	public void setId(Object value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.ID</code>.
	 */
	@Column(name = "ID")
	public Object getId() {
		return (Object) getValue(0);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.AUTHOR_ID</code>.
	 */
	public void setAuthorId(Object value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.AUTHOR_ID</code>.
	 */
	@Column(name = "AUTHOR_ID")
	public Object getAuthorId() {
		return (Object) getValue(1);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.CO_AUTHOR_ID</code>.
	 */
	public void setCoAuthorId(Object value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.CO_AUTHOR_ID</code>.
	 */
	@Column(name = "CO_AUTHOR_ID")
	public Object getCoAuthorId() {
		return (Object) getValue(2);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.DETAILS_ID</code>.
	 */
	public void setDetailsId(Object value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.DETAILS_ID</code>.
	 */
	@Column(name = "DETAILS_ID")
	public Object getDetailsId() {
		return (Object) getValue(3);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.TITLE</code>.
	 */
	public void setTitle(Object value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.TITLE</code>.
	 */
	@Column(name = "TITLE")
	public Object getTitle() {
		return (Object) getValue(4);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.PUBLISHED_IN</code>.
	 */
	public void setPublishedIn(Object value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.PUBLISHED_IN</code>.
	 */
	@Column(name = "PUBLISHED_IN")
	public Object getPublishedIn() {
		return (Object) getValue(5);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.LANGUAGE_ID</code>.
	 */
	public void setLanguageId(Object value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.LANGUAGE_ID</code>.
	 */
	@Column(name = "LANGUAGE_ID")
	public Object getLanguageId() {
		return (Object) getValue(6);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.CONTENT_TEXT</code>.
	 */
	public void setContentText(Object value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.CONTENT_TEXT</code>.
	 */
	@Column(name = "CONTENT_TEXT")
	public Object getContentText() {
		return (Object) getValue(7);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.CONTENT_PDF</code>.
	 */
	public void setContentPdf(Object value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.CONTENT_PDF</code>.
	 */
	@Column(name = "CONTENT_PDF")
	public Object getContentPdf() {
		return (Object) getValue(8);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.REC_VERSION</code>.
	 */
	public void setRecVersion(Object value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.REC_VERSION</code>.
	 */
	@Column(name = "REC_VERSION")
	public Object getRecVersion() {
		return (Object) getValue(9);
	}

	/**
	 * Setter for <code>TEST.V_INCOMPLETE.REC_TIMESTAMP</code>.
	 */
	public void setRecTimestamp(Object value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>TEST.V_INCOMPLETE.REC_TIMESTAMP</code>.
	 */
	@Column(name = "REC_TIMESTAMP")
	public Object getRecTimestamp() {
		return (Object) getValue(10);
	}

	// -------------------------------------------------------------------------
	// Record11 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row11<Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object> fieldsRow() {
		return (Row11) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row11<Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object> valuesRow() {
		return (Row11) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field1() {
		return VIncomplete.V_INCOMPLETE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field2() {
		return VIncomplete.V_INCOMPLETE.AUTHOR_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field3() {
		return VIncomplete.V_INCOMPLETE.CO_AUTHOR_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field4() {
		return VIncomplete.V_INCOMPLETE.DETAILS_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field5() {
		return VIncomplete.V_INCOMPLETE.TITLE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field6() {
		return VIncomplete.V_INCOMPLETE.PUBLISHED_IN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field7() {
		return VIncomplete.V_INCOMPLETE.LANGUAGE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field8() {
		return VIncomplete.V_INCOMPLETE.CONTENT_TEXT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field9() {
		return VIncomplete.V_INCOMPLETE.CONTENT_PDF;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field10() {
		return VIncomplete.V_INCOMPLETE.REC_VERSION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Object> field11() {
		return VIncomplete.V_INCOMPLETE.REC_TIMESTAMP;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value2() {
		return getAuthorId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value3() {
		return getCoAuthorId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value4() {
		return getDetailsId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value5() {
		return getTitle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value6() {
		return getPublishedIn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value7() {
		return getLanguageId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value8() {
		return getContentText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value9() {
		return getContentPdf();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value10() {
		return getRecVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object value11() {
		return getRecTimestamp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value1(Object value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value2(Object value) {
		setAuthorId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value3(Object value) {
		setCoAuthorId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value4(Object value) {
		setDetailsId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value5(Object value) {
		setTitle(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value6(Object value) {
		setPublishedIn(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value7(Object value) {
		setLanguageId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value8(Object value) {
		setContentText(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value9(Object value) {
		setContentPdf(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value10(Object value) {
		setRecVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord value11(Object value) {
		setRecTimestamp(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VIncompleteRecord values(Object value1, Object value2, Object value3, Object value4, Object value5, Object value6, Object value7, Object value8, Object value9, Object value10, Object value11) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached VIncompleteRecord
	 */
	public VIncompleteRecord() {
		super(VIncomplete.V_INCOMPLETE);
	}

	/**
	 * Create a detached, initialised VIncompleteRecord
	 */
	public VIncompleteRecord(Object id, Object authorId, Object coAuthorId, Object detailsId, Object title, Object publishedIn, Object languageId, Object contentText, Object contentPdf, Object recVersion, Object recTimestamp) {
		super(VIncomplete.V_INCOMPLETE);

		setValue(0, id);
		setValue(1, authorId);
		setValue(2, coAuthorId);
		setValue(3, detailsId);
		setValue(4, title);
		setValue(5, publishedIn);
		setValue(6, languageId);
		setValue(7, contentText);
		setValue(8, contentPdf);
		setValue(9, recVersion);
		setValue(10, recTimestamp);
	}
}
