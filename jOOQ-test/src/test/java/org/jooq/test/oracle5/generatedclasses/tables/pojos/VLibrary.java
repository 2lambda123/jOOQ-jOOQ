/**
 * This class is generated by jOOQ
 */
package org.jooq.test.oracle5.generatedclasses.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VLibrary implements java.io.Serializable {

	private static final long serialVersionUID = -1053470599;

	private java.lang.String author;
	private java.lang.String title;

	public VLibrary() {}

	public VLibrary(
		java.lang.String author,
		java.lang.String title
	) {
		this.author = author;
		this.title = title;
	}

	public java.lang.String getAuthor() {
		return this.author;
	}

	public void setAuthor(java.lang.String author) {
		this.author = author;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}
}
