/**
 * This class is generated by jOOQ
 */
package org.jooq.util.db2.syscat.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.0" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables extends org.jooq.impl.TableImpl<org.jooq.Record> {

	private static final long serialVersionUID = 1751869210;

	/**
	 * The singleton instance of <code>SYSCAT.TABLES</code>
	 */
	public static final org.jooq.util.db2.syscat.tables.Tables TABLES = new org.jooq.util.db2.syscat.tables.Tables();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<org.jooq.Record> getRecordType() {
		return org.jooq.Record.class;
	}

	/**
	 * The column <code>SYSCAT.TABLES.TABSCHEMA</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> TABSCHEMA = createField("TABSCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TABNAME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> TABNAME = createField("TABNAME", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.OWNER</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> OWNER = createField("OWNER", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.OWNERTYPE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> OWNERTYPE = createField("OWNERTYPE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TYPE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> TYPE = createField("TYPE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.STATUS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> STATUS = createField("STATUS", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.BASE_TABSCHEMA</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> BASE_TABSCHEMA = createField("BASE_TABSCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.BASE_TABNAME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> BASE_TABNAME = createField("BASE_TABNAME", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ROWTYPESCHEMA</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ROWTYPESCHEMA = createField("ROWTYPESCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ROWTYPENAME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ROWTYPENAME = createField("ROWTYPENAME", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CREATE_TIME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Timestamp> CREATE_TIME = createField("CREATE_TIME", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ALTER_TIME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Timestamp> ALTER_TIME = createField("ALTER_TIME", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.INVALIDATE_TIME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Timestamp> INVALIDATE_TIME = createField("INVALIDATE_TIME", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.STATS_TIME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Timestamp> STATS_TIME = createField("STATS_TIME", org.jooq.impl.SQLDataType.TIMESTAMP, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.COLCOUNT</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> COLCOUNT = createField("COLCOUNT", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TABLEID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> TABLEID = createField("TABLEID", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TBSPACEID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> TBSPACEID = createField("TBSPACEID", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CARD</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Long> CARD = createField("CARD", org.jooq.impl.SQLDataType.BIGINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.NPAGES</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Long> NPAGES = createField("NPAGES", org.jooq.impl.SQLDataType.BIGINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.MPAGES</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Long> MPAGES = createField("MPAGES", org.jooq.impl.SQLDataType.BIGINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.FPAGES</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Long> FPAGES = createField("FPAGES", org.jooq.impl.SQLDataType.BIGINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.OVERFLOW</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Long> OVERFLOW = createField("OVERFLOW", org.jooq.impl.SQLDataType.BIGINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TBSPACE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> TBSPACE = createField("TBSPACE", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.INDEX_TBSPACE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> INDEX_TBSPACE = createField("INDEX_TBSPACE", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LONG_TBSPACE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> LONG_TBSPACE = createField("LONG_TBSPACE", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PARENTS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> PARENTS = createField("PARENTS", org.jooq.impl.SQLDataType.SMALLINT, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CHILDREN</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> CHILDREN = createField("CHILDREN", org.jooq.impl.SQLDataType.SMALLINT, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.SELFREFS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> SELFREFS = createField("SELFREFS", org.jooq.impl.SQLDataType.SMALLINT, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.KEYCOLUMNS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> KEYCOLUMNS = createField("KEYCOLUMNS", org.jooq.impl.SQLDataType.SMALLINT, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.KEYINDEXID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> KEYINDEXID = createField("KEYINDEXID", org.jooq.impl.SQLDataType.SMALLINT, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.KEYUNIQUE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> KEYUNIQUE = createField("KEYUNIQUE", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CHECKCOUNT</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> CHECKCOUNT = createField("CHECKCOUNT", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.DATACAPTURE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> DATACAPTURE = createField("DATACAPTURE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CONST_CHECKED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> CONST_CHECKED = createField("CONST_CHECKED", org.jooq.impl.SQLDataType.CHAR.length(32).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PMAP_ID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> PMAP_ID = createField("PMAP_ID", org.jooq.impl.SQLDataType.SMALLINT, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PARTITION_MODE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> PARTITION_MODE = createField("PARTITION_MODE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LOG_ATTRIBUTE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> LOG_ATTRIBUTE = createField("LOG_ATTRIBUTE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PCTFREE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> PCTFREE = createField("PCTFREE", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.APPEND_MODE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> APPEND_MODE = createField("APPEND_MODE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.REFRESH</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> REFRESH = createField("REFRESH", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.REFRESH_TIME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Timestamp> REFRESH_TIME = createField("REFRESH_TIME", org.jooq.impl.SQLDataType.TIMESTAMP, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LOCKSIZE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> LOCKSIZE = createField("LOCKSIZE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.VOLATILE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> VOLATILE = createField("VOLATILE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ROW_FORMAT</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ROW_FORMAT = createField("ROW_FORMAT", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PROPERTY</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> PROPERTY = createField("PROPERTY", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.STATISTICS_PROFILE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> STATISTICS_PROFILE = createField("STATISTICS_PROFILE", org.jooq.impl.SQLDataType.CLOB.length(10485760), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.COMPRESSION</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> COMPRESSION = createField("COMPRESSION", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ROWCOMPMODE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ROWCOMPMODE = createField("ROWCOMPMODE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ACCESS_MODE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ACCESS_MODE = createField("ACCESS_MODE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CLUSTERED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> CLUSTERED = createField("CLUSTERED", org.jooq.impl.SQLDataType.CHAR.length(1), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ACTIVE_BLOCKS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Long> ACTIVE_BLOCKS = createField("ACTIVE_BLOCKS", org.jooq.impl.SQLDataType.BIGINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.DROPRULE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> DROPRULE = createField("DROPRULE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.MAXFREESPACESEARCH</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> MAXFREESPACESEARCH = createField("MAXFREESPACESEARCH", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.AVGCOMPRESSEDROWSIZE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> AVGCOMPRESSEDROWSIZE = createField("AVGCOMPRESSEDROWSIZE", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.AVGROWCOMPRESSIONRATIO</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Float> AVGROWCOMPRESSIONRATIO = createField("AVGROWCOMPRESSIONRATIO", org.jooq.impl.SQLDataType.REAL.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.AVGROWSIZE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> AVGROWSIZE = createField("AVGROWSIZE", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PCTROWSCOMPRESSED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Float> PCTROWSCOMPRESSED = createField("PCTROWSCOMPRESSED", org.jooq.impl.SQLDataType.REAL.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LOGINDEXBUILD</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> LOGINDEXBUILD = createField("LOGINDEXBUILD", org.jooq.impl.SQLDataType.VARCHAR.length(3), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CODEPAGE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> CODEPAGE = createField("CODEPAGE", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.COLLATIONSCHEMA</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> COLLATIONSCHEMA = createField("COLLATIONSCHEMA", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.COLLATIONNAME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> COLLATIONNAME = createField("COLLATIONNAME", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.COLLATIONSCHEMA_ORDERBY</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> COLLATIONSCHEMA_ORDERBY = createField("COLLATIONSCHEMA_ORDERBY", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.COLLATIONNAME_ORDERBY</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> COLLATIONNAME_ORDERBY = createField("COLLATIONNAME_ORDERBY", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ENCODING_SCHEME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ENCODING_SCHEME = createField("ENCODING_SCHEME", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PCTPAGESSAVED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Short> PCTPAGESSAVED = createField("PCTPAGESSAVED", org.jooq.impl.SQLDataType.SMALLINT.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LAST_REGEN_TIME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Timestamp> LAST_REGEN_TIME = createField("LAST_REGEN_TIME", org.jooq.impl.SQLDataType.TIMESTAMP, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.SECPOLICYID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Integer> SECPOLICYID = createField("SECPOLICYID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PROTECTIONGRANULARITY</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> PROTECTIONGRANULARITY = createField("PROTECTIONGRANULARITY", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.AUDITPOLICYID</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Integer> AUDITPOLICYID = createField("AUDITPOLICYID", org.jooq.impl.SQLDataType.INTEGER, TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.AUDITPOLICYNAME</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> AUDITPOLICYNAME = createField("AUDITPOLICYNAME", org.jooq.impl.SQLDataType.VARCHAR.length(128), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.AUDITEXCEPTIONENABLED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> AUDITEXCEPTIONENABLED = createField("AUDITEXCEPTIONENABLED", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.DEFINER</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> DEFINER = createField("DEFINER", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ONCOMMIT</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ONCOMMIT = createField("ONCOMMIT", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LOGGED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> LOGGED = createField("LOGGED", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.ONROLLBACK</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> ONROLLBACK = createField("ONROLLBACK", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.LASTUSED</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.sql.Date> LASTUSED = createField("LASTUSED", org.jooq.impl.SQLDataType.DATE.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.CONTROL</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> CONTROL = createField("CONTROL", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TEMPORALTYPE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> TEMPORALTYPE = createField("TEMPORALTYPE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.TABLEORG</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> TABLEORG = createField("TABLEORG", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.EXTENDED_ROW_SIZE</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> EXTENDED_ROW_SIZE = createField("EXTENDED_ROW_SIZE", org.jooq.impl.SQLDataType.CHAR.length(1).nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.PCTEXTENDEDROWS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.Float> PCTEXTENDEDROWS = createField("PCTEXTENDEDROWS", org.jooq.impl.SQLDataType.REAL.nullable(false), TABLES, "");

	/**
	 * The column <code>SYSCAT.TABLES.REMARKS</code>.
	 */
	public static final org.jooq.TableField<org.jooq.Record, java.lang.String> REMARKS = createField("REMARKS", org.jooq.impl.SQLDataType.VARCHAR.length(254), TABLES, "");

	/**
	 * No further instances allowed
	 */
	private Tables() {
		this("TABLES", null);
	}

	private Tables(java.lang.String alias, org.jooq.Table<org.jooq.Record> aliased) {
		this(alias, aliased, null);
	}

	private Tables(java.lang.String alias, org.jooq.Table<org.jooq.Record> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, org.jooq.util.db2.syscat.Syscat.SYSCAT, aliased, parameters, "");
	}
}
