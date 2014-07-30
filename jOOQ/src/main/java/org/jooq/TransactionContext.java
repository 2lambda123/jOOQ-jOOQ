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
package org.jooq;

/**
 * A context object that is used to pass arguments to the various methods of
 * {@link TransactionProvider}.
 *
 * @author Lukas Eder
 */
public interface TransactionContext {

    /**
     * The configuration scoped to this transaction and its nested transactions.
     */
    Configuration configuration();

    /**
     * The {@link SQLDialect} wrapped by this context.
     * <p>
     * This method is a convenient way of accessing
     * <code>configuration().dialect()</code>.
     */
    SQLDialect dialect();

    /**
     * The {@link SQLDialect#family()} wrapped by this context.
     * <p>
     * This method is a convenient way of accessing
     * <code>configuration().dialect().family()</code>.
     */
    SQLDialect family();

    /**
     * A user-defined transaction object, possibly obtained from
     * {@link TransactionProvider#begin(TransactionContext)}.
     *
     * @return The transaction object. May be <code>null</code>.
     */
    Transaction transaction();

    /**
     * Set the user-defined transaction object to the current transaction
     * context.
     */
    TransactionContext transaction(Transaction transaction);

    /**
     * The exception that has caused the rollback.
     *
     * @return The exception. May be <code>null</code>.
     */
    Exception cause();

    /**
     * Set the exception that has caused the rollback to the current transaction
     * context.
     */
    TransactionContext cause(Exception cause);
}
