/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.kotlin.db.h2.tables


import java.time.LocalDate

import kotlin.collections.List

import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.Name
import org.jooq.Record
import org.jooq.Row6
import org.jooq.Schema
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.example.kotlin.db.h2.Keys
import org.jooq.example.kotlin.db.h2.Public
import org.jooq.example.kotlin.db.h2.tables.records.AuthorRecord
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
class Author(
    alias: Name,
    child: Table<out Record>?,
    path: ForeignKey<out Record, AuthorRecord>?,
    aliased: Table<AuthorRecord>?,
    parameters: Array<Field<*>?>?
): TableImpl<AuthorRecord>(
    alias,
    Public.PUBLIC,
    child,
    path,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table()
) {
    companion object {

        /**
         * The reference instance of <code>PUBLIC.AUTHOR</code>
         */
        val AUTHOR = Author()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<AuthorRecord> = AuthorRecord::class.java

    /**
     * The column <code>PUBLIC.AUTHOR.ID</code>.
     */
    val ID: TableField<AuthorRecord, Int?> = createField(DSL.name("ID"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "")

    /**
     * The column <code>PUBLIC.AUTHOR.FIRST_NAME</code>.
     */
    val FIRST_NAME: TableField<AuthorRecord, String?> = createField(DSL.name("FIRST_NAME"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "")

    /**
     * The column <code>PUBLIC.AUTHOR.LAST_NAME</code>.
     */
    val LAST_NAME: TableField<AuthorRecord, String?> = createField(DSL.name("LAST_NAME"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "")

    /**
     * The column <code>PUBLIC.AUTHOR.DATE_OF_BIRTH</code>.
     */
    val DATE_OF_BIRTH: TableField<AuthorRecord, LocalDate?> = createField(DSL.name("DATE_OF_BIRTH"), org.jooq.impl.SQLDataType.LOCALDATE, this, "")

    /**
     * The column <code>PUBLIC.AUTHOR.YEAR_OF_BIRTH</code>.
     */
    val YEAR_OF_BIRTH: TableField<AuthorRecord, Int?> = createField(DSL.name("YEAR_OF_BIRTH"), org.jooq.impl.SQLDataType.INTEGER, this, "")

    /**
     * The column <code>PUBLIC.AUTHOR.ADDRESS</code>.
     */
    val ADDRESS: TableField<AuthorRecord, String?> = createField(DSL.name("ADDRESS"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "")

    private constructor(alias: Name, aliased: Table<AuthorRecord>?): this(alias, null, null, aliased, null)
    private constructor(alias: Name, aliased: Table<AuthorRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, aliased, parameters)

    /**
     * Create an aliased <code>PUBLIC.AUTHOR</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>PUBLIC.AUTHOR</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>PUBLIC.AUTHOR</code> table reference
     */
    constructor(): this(DSL.name("AUTHOR"))

    constructor(child: Table<out Record>, key: ForeignKey<out Record, AuthorRecord>): this(Internal.createPathAlias(child, key), child, key, AUTHOR, null)
    override fun getSchema(): Schema = Public.PUBLIC
    override fun getIdentity(): Identity<AuthorRecord, Int?> = super.getIdentity() as Identity<AuthorRecord, Int?>
    override fun getPrimaryKey(): UniqueKey<AuthorRecord> = Keys.PK_T_AUTHOR
    override fun getKeys(): List<UniqueKey<AuthorRecord>> = listOf(Keys.PK_T_AUTHOR)
    override fun `as`(alias: String): Author = Author(DSL.name(alias), this)
    override fun `as`(alias: Name): Author = Author(alias, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Author = Author(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Author = Author(name, null)

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------
    override fun fieldsRow(): Row6<Int?, String?, String?, LocalDate?, Int?, String?> = super.fieldsRow() as Row6<Int?, String?, String?, LocalDate?, Int?, String?>
}
