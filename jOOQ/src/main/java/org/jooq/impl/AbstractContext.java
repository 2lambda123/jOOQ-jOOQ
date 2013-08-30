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
package org.jooq.impl;

import java.util.HashMap;
import java.util.Map;

import org.jooq.Clause;
import org.jooq.Configuration;
import org.jooq.Context;
import org.jooq.QueryPart;
import org.jooq.QueryPartInternal;

/**
 * @author Lukas Eder
 */
@SuppressWarnings("unchecked")
abstract class AbstractContext<C extends Context<C>> implements Context<C> {

    final Configuration       configuration;
    final Map<Object, Object> data;

    boolean                   declareFields;
    boolean                   declareTables;
    boolean                   subquery;
    int                       index;

    AbstractContext(Configuration configuration) {
        this.configuration = configuration;
        this.data = new HashMap<Object, Object>();
    }

    // ------------------------------------------------------------------------
    // XXX Context API
    // ------------------------------------------------------------------------

    @Override
    public final Configuration configuration() {
        return configuration;
    }

    @Override
    public final Map<Object, Object> data() {
        return data;
    }

    @Override
    public final Object data(Object key) {
        return data.get(key);
    }

    @Override
    public final Object data(Object key, Object value) {
        return data.put(key, value);
    }

    @Override
    public /* non-final */ C start(Clause clause) {
        return (C) this;
    }

    @Override
    public /* non-final */ C end(Clause clause) {
        return (C) this;
    }

    @Override
    public /* non-final */ C visit(QueryPart part) {
        if (part != null) {
            QueryPartInternal internal = (QueryPartInternal) part;

            // If this is supposed to be a declaration section and the part isn't
            // able to declare anything, then disable declaration temporarily

            // We're declaring fields, but "part" does not declare fields
            if (declareFields() && !internal.declaresFields()) {
                declareFields(false);
                visit0(internal);
                declareFields(true);
            }

            // We're declaring tables, but "part" does not declare tables
            else if (declareTables() && !internal.declaresTables()) {
                declareTables(false);
                visit0(internal);
                declareTables(true);
            }

            // We're not declaring, or "part" can declare
            else {
                visit0(internal);
            }
        }

        return (C) this;
    }

    protected abstract void visit0(QueryPartInternal internal);

    @Override
    public final boolean declareFields() {
        return declareFields;
    }

    @Override
    public final C declareFields(boolean d) {
        this.declareFields = d;
        return (C) this;
    }

    @Override
    public final boolean declareTables() {
        return declareTables;
    }

    @Override
    public final C declareTables(boolean d) {
        this.declareTables = d;
        return (C) this;
    }

    @Override
    public final boolean subquery() {
        return subquery;
    }

    @Override
    public final C subquery(boolean s) {
        this.subquery = s;
        return (C) this;
    }

    @Override
    public final int nextIndex() {
        return ++index;
    }

    @Override
    public final int peekIndex() {
        return index + 1;
    }

    // ------------------------------------------------------------------------
    // XXX Object API
    // ------------------------------------------------------------------------

    void toString(StringBuilder sb) {
        sb.append(  "bind index   [");
        sb.append(index);
        sb.append("]");
        sb.append("\ndeclaring    [");

        if (declareFields) {
            sb.append("fields");
        }
        else if (declareTables) {
            sb.append("tables");
        }
        else {
            sb.append("-");
        }

        sb.append("]\nsubquery     [");
        sb.append(subquery);
        sb.append("]");
    }
}
