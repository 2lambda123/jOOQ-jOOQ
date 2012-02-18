/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle.generatedclasses.test.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "V_BOOK", schema = "TEST")
public class VBook implements java.io.Serializable {

	private static final long serialVersionUID = -1791159149;

	private java.lang.Integer id;
	private java.lang.Integer authorId;
	private java.lang.Integer coAuthorId;
	private java.lang.Integer detailsId;
	private java.lang.String  title;
	private java.lang.Integer publishedIn;
	private java.lang.Integer languageId;
	private java.lang.String  contentText;
	private byte[]            contentPdf;

	@javax.persistence.Column(name = "ID")
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	@javax.persistence.Column(name = "AUTHOR_ID")
	public java.lang.Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(java.lang.Integer authorId) {
		this.authorId = authorId;
	}

	@javax.persistence.Column(name = "CO_AUTHOR_ID")
	public java.lang.Integer getCoAuthorId() {
		return this.coAuthorId;
	}

	public void setCoAuthorId(java.lang.Integer coAuthorId) {
		this.coAuthorId = coAuthorId;
	}

	@javax.persistence.Column(name = "DETAILS_ID")
	public java.lang.Integer getDetailsId() {
		return this.detailsId;
	}

	public void setDetailsId(java.lang.Integer detailsId) {
		this.detailsId = detailsId;
	}

	@javax.persistence.Column(name = "TITLE")
	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	@javax.persistence.Column(name = "PUBLISHED_IN")
	public java.lang.Integer getPublishedIn() {
		return this.publishedIn;
	}

	public void setPublishedIn(java.lang.Integer publishedIn) {
		this.publishedIn = publishedIn;
	}

	@javax.persistence.Column(name = "LANGUAGE_ID")
	public java.lang.Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(java.lang.Integer languageId) {
		this.languageId = languageId;
	}

	@javax.persistence.Column(name = "CONTENT_TEXT")
	public java.lang.String getContentText() {
		return this.contentText;
	}

	public void setContentText(java.lang.String contentText) {
		this.contentText = contentText;
	}

	@javax.persistence.Column(name = "CONTENT_PDF")
	public byte[] getContentPdf() {
		return this.contentPdf;
	}

	public void setContentPdf(byte[] contentPdf) {
		this.contentPdf = contentPdf;
	}
}
