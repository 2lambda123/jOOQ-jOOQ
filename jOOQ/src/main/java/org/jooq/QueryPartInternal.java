/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * This work is triple-licensed under ASL 2.0, AGPL 3.0, and jOOQ EULA
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   ASL 2.0 or jOOQ EULA.
 * - If you're using this work with at least one commercial database, you may
 *   choose AGPL 3.0 or jOOQ EULA.
 *
 * For more information, please visit http://www.jooq.org/licenses
 *
 * Apache Software License 2.0:
 * -----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * AGPL 3.0
 * -----------------------------------------------------------------------------
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this library.
 * If not, see http://www.gnu.org/licenses.
 *
 * jOOQ End User License Agreement:
 * -----------------------------------------------------------------------------
 * This library is commercial software; you may not redistribute it nor
 * modify it.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ End User
 * License Agreement for more details: http://www.jooq.org/eula
 */

package org.jooq;

import org.jooq.exception.DataAccessException;

/**
 * Base functionality declaration for all query objects
 * <p>
 * This interface is for JOOQ INTERNAL USE only. Do not reference directly
 *
 * @author Lukas Eder
 */
public interface QueryPartInternal extends QueryPart {

    /**
     * Render this {@link QueryPart} to a SQL string contained in
     * <code>context.sql()</code>. The <code>context</code> will contain
     * additional information about how to render this <code>QueryPart</code>,
     * e.g. whether this <code>QueryPart</code> should be rendered as a
     * declaration or reference, whether this <code>QueryPart</code>'s contained
     * bind variables should be inlined or replaced by <code>'?'</code>, etc.
     */
    void toSQL(RenderContext ctx);

    /**
     * Bind all parameters of this {@link QueryPart} to a PreparedStatement
     * <p>
     * This method is for JOOQ INTERNAL USE only. Do not reference directly
     *
     * @param ctx The context holding the next bind index and other information
     *            for variable binding
     * @throws DataAccessException If something went wrong while binding a
     *             variable
     */
    void bind(BindContext ctx) throws DataAccessException;

    /**
     * The {@link Clause}s that are represented by this query part.
     * <p>
     * {@link QueryPart}s can specify several <code>Clause</code>s for which an
     * event will be emitted {@link Context#start(Clause) before} (in forward
     * order) and {@link Context#end(Clause) after} (in reverse order) visiting
     * the the query part through {@link Context#visit(QueryPart)}
     * <p>
     * This method is for JOOQ INTERNAL USE only. Do not reference directly
     *
     * @return The <code>Clause</code>s represented by this query part or
     *         <code>null</code> or an empty array if this query part does not
     *         represent a clause.
     */
    Clause[] clauses(Context<?> ctx);

    /**
     * Check whether this {@link QueryPart} is able to declare fields in a
     * <code>SELECT</code> clause.
     * <p>
     * This method can be used by any {@link Context} to check how a certain SQL
     * clause should be rendered.
     * <p>
     * This method is for JOOQ INTERNAL USE only. Do not reference directly
     */
    boolean declaresFields();

    /**
     * Check whether this {@link QueryPart} is able to declare tables in a
     * <code>FROM</code> clause or <code>JOIN</code> clause.
     * <p>
     * This method can be used by any {@link Context} to check how a certain SQL
     * clause should be rendered.
     * <p>
     * This method is for JOOQ INTERNAL USE only. Do not reference directly
     */
    boolean declaresTables();
}
