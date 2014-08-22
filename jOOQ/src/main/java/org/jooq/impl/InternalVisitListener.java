/**
 * Copyright (c) 2009-2014, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * This work is dual-licensed
 * - under the Apache Software License 2.0 (the "ASL")
 * - under the jOOQ License and Maintenance Agreement (the "jOOQ License")
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   either ASL or jOOQ License.
 * - If you're using this work with at least one commercial database, you must
 *   choose jOOQ License
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
 * jOOQ License and Maintenance Agreement:
 * -----------------------------------------------------------------------------
 * Data Geekery grants the Customer the non-exclusive, timely limited and
 * non-transferable license to install and use the Software under the terms of
 * the jOOQ License and Maintenance Agreement.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ License
 * and Maintenance Agreement for more details: http://www.jooq.org/licensing
 */
package org.jooq.impl;

import static java.lang.Boolean.TRUE;
import static org.jooq.Clause.SELECT;
import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.Utils.DATA_LOCALLY_SCOPED_DATA_MAP;
import static org.jooq.impl.Utils.DATA_OVERRIDE_ALIASES_IN_ORDER_BY;
import static org.jooq.impl.Utils.DATA_UNALIAS_ALIASES_IN_ORDER_BY;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import org.jooq.Field;
import org.jooq.QueryPart;
import org.jooq.VisitContext;
import org.jooq.VisitListener;

/**
 * A {@link VisitListener} used by jOOQ internally, to implement some useful
 * features.
 * <p>
 * Features implemented are:
 * <h3>[#2790] Keep a locally scoped data map, scoped for the current subquery</h3>
 * <p>
 * Sometimes, it is useful to have some information only available while
 * visiting QueryParts in the same context of the current subquery, e.g. when
 * communicating between SELECT and WINDOW clause, as is required to emulate
 * [#531].
 * </p>
 *
 * @author Lukas Eder
 */
class InternalVisitListener extends DefaultVisitListener {

    private Deque<Object> stack = new LinkedList<Object>();

    @Override
    public void clauseStart(VisitContext ctx) {
        if (ctx.clause() == SELECT) {
            stack.push(ctx.context().data(DATA_LOCALLY_SCOPED_DATA_MAP));
            ctx.context().data(DATA_LOCALLY_SCOPED_DATA_MAP, new HashMap<Object, Object>());
        }
    }

    @Override
    public void clauseEnd(VisitContext ctx) {
        if (ctx.clause() == SELECT) {
            ctx.context().data(DATA_LOCALLY_SCOPED_DATA_MAP, stack.pop());
        }
    }

    /* [pro] */
    @Override
    public void visitStart(VisitContext ctx) {
        QueryPart part = ctx.queryPart();

        // Apply this transformation only to fields that are not being declared in the SELECT clause
        if (part instanceof Field && !ctx.context().declareFields()) {

            // [#2080] Override the actual alias in case a synthetic alias is generated
            // in the SELECT clause
            Object[] object = (Object[]) ctx.data(DATA_OVERRIDE_ALIASES_IN_ORDER_BY);

            // Don't combine the effects of DATA_OVERRIDE_ALIASES_IN_ORDER_BY with DATA_UNALIAS_ALIASES_IN_ORDER_BY
            if (object != null && !TRUE.equals(ctx.data(DATA_UNALIAS_ALIASES_IN_ORDER_BY))) {
                Field<?>[] originalFields = (Field<?>[]) object[0];
                Field<?>[] aliasedFields = (Field<?>[]) object[1];

                for (int i = 0; i < originalFields.length; i++) {
                    if (part.equals(originalFields[i])) {
                        ctx.queryPart(fieldByName(aliasedFields[i].getName()));
                    }
                }
            }
        }
    }
    /* [/pro] */
}
