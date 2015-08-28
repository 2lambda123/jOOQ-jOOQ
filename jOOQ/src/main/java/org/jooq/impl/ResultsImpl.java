/**
 * Copyright (c) 2009-2015, Data Geekery GmbH (http://www.datageekery.com)
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

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.jooq.AttachableInternal;
import org.jooq.Configuration;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultOrRows;
import org.jooq.Results;

/**
 * @author Lukas Eder
 */
class ResultsImpl extends AbstractList<Result<Record>> implements Results, AttachableInternal {

    /**
     * Generated UID
     */
    private static final long        serialVersionUID = 1744826140354980500L;

    private Configuration            configuration;
    private final List<ResultOrRows> results;

    ResultsImpl(Configuration configuration) {
        this.configuration = configuration;
        this.results = new ArrayList<ResultOrRows>();
    }

    // ------------------------------------------------------------------------
    // XXX: Additional, Results-specific methods
    // ------------------------------------------------------------------------

    @Override
    public final List<ResultOrRows> resultsOrRows() {
        return results;
    }

    // -------------------------------------------------------------------------
    // XXX: Attachable API
    // -------------------------------------------------------------------------

    @Override
    public final void attach(Configuration c) {
        this.configuration = c;

        for (Result<?> result : this)
            if (result != null)
                result.attach(c);
    }

    @Override
    public final void detach() {
        attach(null);
    }

    @Override
    public final Configuration configuration() {
        return configuration;
    }

    // -------------------------------------------------------------------------
    // XXX Object API
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String separator = "";

        for (ResultOrRows result : results) {
            if (result.result() == null)
                sb.append(separator).append("Update count: ").append(result.rows());
            else
                sb.append(separator).append("Result set:\n").append(result.result());

            separator = "\n";
        }

        return sb.toString();
    }

    @Override
    public int hashCode() {
        return results.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ResultsImpl) {
            ResultsImpl other = (ResultsImpl) obj;
            return results.equals(other.results);
        }

        return false;
    }

    // -------------------------------------------------------------------------
    // XXX: List API
    // -------------------------------------------------------------------------

    @Override
    public final int size() {
        return list().size();
    }

    @Override
    public final Result<Record> get(int index) {
        return list().get(index);
    }

    @Override
    public Result<Record> set(int index, Result<Record> element) {
        return results.set(translatedIndex(index), new ResultOrRowsImpl(element)).result();
    }

    @Override
    public void add(int index, Result<Record> element) {
        results.add(translatedIndex(index), new ResultOrRowsImpl(element));
    }

    @Override
    public Result<Record> remove(int index) {
        return results.remove(translatedIndex(index)).result();
    }

    private final List<Result<Record>> list() {
        List<Result<Record>> list = new ArrayList<Result<Record>>();

        for (ResultOrRows result : results)
            if (result.result() != null)
                list.add(result.result());

        return list;
    }

    private final int translatedIndex(int index) {
        int translated = 0;

        for (int i = 0; i < index; i++)
            while (results.get(translated++).result() == null);

        return translated;
    }

    static final class ResultOrRowsImpl implements ResultOrRows {

        private final Result<Record> result;
        private final int            rows;

        ResultOrRowsImpl(Result<Record> result) {
            this(result, result != null ? result.size() : 0);
        }

        ResultOrRowsImpl(int rows) {
            this(null, rows);
        }

        private ResultOrRowsImpl(Result<Record> result, int rows) {
            this.result = result;
            this.rows = rows;
        }

        @Override
        public final Result<Record> result() {
            return result;
        }

        @Override
        public final int rows() {
            return rows;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int r = 1;
            r = prime * r + ((this.result == null) ? 0 : this.result.hashCode());
            r = prime * r + rows;
            return r;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ResultOrRowsImpl other = (ResultOrRowsImpl) obj;
            if (result == null) {
                if (other.result != null)
                    return false;
            }
            else if (!result.equals(other.result))
                return false;
            if (rows != other.rows)
                return false;
            return true;
        }
    }
}
