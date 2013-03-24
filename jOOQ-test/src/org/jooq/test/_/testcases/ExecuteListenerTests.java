/**
 * Copyright (c) 2009-2013, Lukas Eder, lukas.eder@gmail.com
 * All rights reserved.
 *
 * This software is licensed to you under the Apache License, Version 2.0
 * (the "License"); You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name "jOOQ" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jooq.test._.testcases;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.jooq.conf.SettingsTools.executePreparedStatements;
import static org.jooq.impl.Factory.param;
import static org.jooq.impl.Factory.val;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jooq.Cursor;
import org.jooq.ExecuteContext;
import org.jooq.ExecuteListener;
import org.jooq.ExecuteType;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Record6;
import org.jooq.Result;
import org.jooq.TableRecord;
import org.jooq.UpdatableRecord;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DefaultExecuteListener;
import org.jooq.impl.Executor;
import org.jooq.test.BaseTest;
import org.jooq.test.jOOQAbstractTest;

import org.junit.Test;

public class ExecuteListenerTests<
    A    extends UpdatableRecord<A> & Record6<Integer, String, String, Date, Integer, ?>,
    AP,
    B    extends UpdatableRecord<B>,
    S    extends UpdatableRecord<S> & Record1<String>,
    B2S  extends UpdatableRecord<B2S> & Record3<String, Integer, Integer>,
    BS   extends UpdatableRecord<BS>,
    L    extends TableRecord<L> & Record2<String, String>,
    X    extends TableRecord<X>,
    DATE extends UpdatableRecord<DATE>,
    BOOL extends UpdatableRecord<BOOL>,
    D    extends UpdatableRecord<D>,
    T    extends UpdatableRecord<T>,
    U    extends TableRecord<U>,
    UU   extends UpdatableRecord<UU>,
    I    extends TableRecord<I>,
    IPK  extends UpdatableRecord<IPK>,
    T725 extends UpdatableRecord<T725>,
    T639 extends UpdatableRecord<T639>,
    T785 extends TableRecord<T785>>
extends BaseTest<A, AP, B, S, B2S, BS, L, X, DATE, BOOL, D, T, U, UU, I, IPK, T725, T639, T785> {

    public ExecuteListenerTests(jOOQAbstractTest<A, AP, B, S, B2S, BS, L, X, DATE, BOOL, D, T, U, UU, I, IPK, T725, T639, T785> delegate) {
        super(delegate);
    }

    @Test
    public void testExecuteListenerWithData() throws Exception {
        Executor create = create();
        create.getExecuteListeners().add(new DataListener());

        create.selectOne().fetch();
    }

    public static class DataListener extends DefaultExecuteListener {

        /**
         * Generated UID
         */
        private static final long serialVersionUID = 7399239846062763212L;

        @Override
        public void start(ExecuteContext ctx) {
            assertTrue(ctx.data().isEmpty());
            assertNull(ctx.data("X"));
            assertNull(ctx.data("X", "start"));
        }

        private void assertThings(ExecuteContext ctx, String oldValue, String newValue) {
            assertFalse(ctx.data().isEmpty());
            assertEquals(1, ctx.data().size());
            assertEquals(oldValue, ctx.data("X"));
            assertEquals(oldValue, ctx.data("X", newValue));
        }

        @Override
        public void renderStart(ExecuteContext ctx) {
            assertThings(ctx, "start", "renderStart");
        }

        @Override
        public void renderEnd(ExecuteContext ctx) {
            assertThings(ctx, "renderStart", "renderEnd");
        }

        @Override
        public void prepareStart(ExecuteContext ctx) {
            assertThings(ctx, "renderEnd", "prepareStart");
        }

        @Override
        public void prepareEnd(ExecuteContext ctx) {
            assertThings(ctx, "prepareStart", "prepareEnd");
        }

        @Override
        public void bindStart(ExecuteContext ctx) {
            assertThings(ctx, "prepareEnd", "bindStart");
        }

        @Override
        public void bindEnd(ExecuteContext ctx) {
            assertThings(ctx, "bindStart", "bindEnd");
        }

        @Override
        public void executeStart(ExecuteContext ctx) {
            assertThings(ctx, "bindEnd", "executeStart");
        }

        @Override
        public void executeEnd(ExecuteContext ctx) {
            assertThings(ctx, "executeStart", "executeEnd");
        }

        @Override
        public void fetchStart(ExecuteContext ctx) {
            assertThings(ctx, "executeEnd", "fetchStart");
        }

        @Override
        public void resultStart(ExecuteContext ctx) {
            assertThings(ctx, "fetchStart", "resultStart");
        }

        @Override
        public void recordStart(ExecuteContext ctx) {
            assertThings(ctx, "resultStart", "recordStart");
        }

        @Override
        public void recordEnd(ExecuteContext ctx) {
            assertThings(ctx, "recordStart", "recordEnd");
        }

        @Override
        public void resultEnd(ExecuteContext ctx) {
            assertThings(ctx, "recordEnd", "resultEnd");
        }

        @Override
        public void fetchEnd(ExecuteContext ctx) {
            assertThings(ctx, "resultEnd", "fetchEnd");
        }

        @Override
        public void end(ExecuteContext ctx) {
            assertThings(ctx, "fetchEnd", "end");
        }

        @Override
        public void exception(ExecuteContext ctx) {
            fail();
        }
    }

    @Test
    public void testExecuteListenerCustomException() throws Exception {
        Executor create = create();
        create.getExecuteListeners().add(new CustomExceptionListener());

        try {
            create.fetch("invalid sql");
        }
        catch (E e) {
            assertEquals("ERROR", e.getMessage());
        }
    }

    public static class CustomExceptionListener extends DefaultExecuteListener {

        /**
         * Generated UID
         */
        private static final long serialVersionUID = 7399239846062763212L;

        @Override
        public void exception(ExecuteContext ctx) {
            ctx.exception(new E("ERROR"));
        }
    }

    @Test
    public void testExecuteListenerOnResultQuery() throws Exception {
        Executor create = create();
        create.getExecuteListeners().add(new ResultQueryListener());

        create.setData("Foo", "Bar");
        create.setData("Bar", "Baz");

        Result<?> result =
        create.select(TBook_ID(), val("Hello"))
              .from(TBook())
              .where(TBook_ID().in(1, 2))
              .fetch();

        // [#1145] When inlining variables, no bind events are triggered
        int plus = (SettingsTools.executePreparedStatements(create.getSettings()) ? 2 : 0);

        // Check correct order of listener method invocation
        assertEquals(1, ResultQueryListener.start);
        assertEquals(2, ResultQueryListener.renderStart);
        assertEquals(3, ResultQueryListener.renderEnd);
        assertEquals(4, ResultQueryListener.prepareStart);
        assertEquals(5, ResultQueryListener.prepareEnd);
        assertEquals(plus > 0 ? 6 : 0, ResultQueryListener.bindStart);
        assertEquals(plus > 0 ? 7 : 0, ResultQueryListener.bindEnd);
        assertEquals(6 + plus, ResultQueryListener.executeStart);
        assertEquals(7 + plus, ResultQueryListener.executeEnd);
        assertEquals(8 + plus, ResultQueryListener.fetchStart);
        assertEquals(9 + plus, ResultQueryListener.resultStart);
        assertEquals(asList(10 + plus, 12 + plus), ResultQueryListener.recordStart);
        assertEquals(asList(11 + plus, 13 + plus), ResultQueryListener.recordEnd);
        assertEquals(14 + plus, ResultQueryListener.resultEnd);
        assertEquals(15 + plus, ResultQueryListener.fetchEnd);
        assertEquals(16 + plus, ResultQueryListener.end);
        assertEquals(2, result.size());
    }

    public static class ResultQueryListener extends DefaultExecuteListener {

        /**
         * Generated UID
         */
        private static final long serialVersionUID = 7399239846062763212L;

        // A counter that is incremented in callback methods
        private static int           callbackCount = 0;

        // Fields that are used to check whether callback methods were called
        // in the expected order
        public static int            start;
        public static int            renderStart;
        public static int            renderEnd;
        public static int            prepareStart;
        public static int            prepareEnd;
        public static int            bindStart;
        public static int            bindEnd;
        public static int            executeStart;
        public static int            executeEnd;
        public static int            fetchStart;
        public static int            resultStart;
        public static List<Integer>  recordStart   = new ArrayList<Integer>();
        public static List<Integer>  recordEnd     = new ArrayList<Integer>();
        public static int            resultEnd;
        public static int            fetchEnd;
        public static int            end;

        public static Queue<Integer> ids = new LinkedList<Integer>(asList(1, 2));

        @SuppressWarnings("serial")
        private void checkBase(ExecuteContext ctx) {
            assertNotNull(ctx.query());
            assertNotNull(ctx.batchQueries());
            assertTrue(ctx.query().toString().toLowerCase().contains("select"));
            assertTrue(ctx.batchQueries()[0].toString().toLowerCase().contains("select"));
            assertEquals(ctx.query(), ctx.batchQueries()[0]);
            assertEquals(1, ctx.batchSQL().length);

            assertEquals("Bar", ctx.configuration().getData("Foo"));
            assertEquals("Baz", ctx.configuration().getData("Bar"));
            assertEquals(new HashMap<String, String>() {{
                put("Foo", "Bar");
                put("Bar", "Baz");
            }}, ctx.configuration().getData());

            assertNull(ctx.routine());
            assertEquals(ExecuteType.READ, ctx.type());
        }

        private void checkSQL(ExecuteContext ctx, boolean patched) {
            assertTrue(ctx.batchSQL()[0].toLowerCase().contains("select"));
            assertTrue(ctx.sql().toLowerCase().contains("select"));
            assertEquals(ctx.sql(), ctx.batchSQL()[0]);

            if (patched) {
                assertTrue(ctx.sql().toLowerCase().contains("as my_field"));
            }
        }

        @SuppressWarnings("unused")
        private void checkStatement(ExecuteContext ctx, boolean patched) {
            assertNotNull(ctx.statement());
        }

        @SuppressWarnings("unused")
        private void checkResultSet(ExecuteContext ctx, boolean patched) {
            assertNotNull(ctx.resultSet());
        }

        @Override
        public void start(ExecuteContext ctx) {
            start = ++callbackCount;
            checkBase(ctx);

            assertNull(ctx.batchSQL()[0]);
            assertNull(ctx.sql());
            assertNull(ctx.statement());
            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void renderStart(ExecuteContext ctx) {
            renderStart = ++callbackCount;
            checkBase(ctx);

            assertNull(ctx.batchSQL()[0]);
            assertNull(ctx.sql());
            assertNull(ctx.statement());
            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void renderEnd(ExecuteContext ctx) {
            renderEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, false);

            assertNull(ctx.statement());
            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());

            ctx.sql(ctx.sql().replaceFirst("(?i:from)", "as my_field from"));
            checkSQL(ctx, true);
        }

        @Override
        public void prepareStart(ExecuteContext ctx) {
            prepareStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);

            assertNull(ctx.statement());
            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void prepareEnd(ExecuteContext ctx) {
            prepareEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);

            checkStatement(ctx, false);
            // TODO Patch statement
            checkStatement(ctx, true);

            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void bindStart(ExecuteContext ctx) {
            bindStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);

            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void bindEnd(ExecuteContext ctx) {
            bindEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);

            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void executeStart(ExecuteContext ctx) {
            executeStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);

            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void executeEnd(ExecuteContext ctx) {
            executeEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);

            checkResultSet(ctx, false);
            // TODO patch result set
            checkResultSet(ctx, true);

            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void fetchStart(ExecuteContext ctx) {
            fetchStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);

            assertNull(ctx.record());
            assertNull(ctx.result());
        }

        @Override
        public void resultStart(ExecuteContext ctx) {
            resultStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);

            assertNull(ctx.record());
            assertNotNull(ctx.result());
            assertTrue(ctx.result().isEmpty());
        }

        @Override
        public void recordStart(ExecuteContext ctx) {
            recordStart.add(++callbackCount);
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);

            assertNotNull(ctx.record());
            assertEquals(2, ctx.record().fieldsRow().size());

            assertNull(ctx.record().getValue(0));
            assertNull(ctx.record().getValue(1));
        }

        @Override
        public void recordEnd(ExecuteContext ctx) {
            recordEnd.add(++callbackCount);
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);

            assertNotNull(ctx.record());
            assertEquals(2, ctx.record().fieldsRow().size());

            assertEquals(ids.remove(), ctx.record().getValue(0));
            assertEquals("Hello", ctx.record().getValue(1));
        }

        @Override
        public void resultEnd(ExecuteContext ctx) {
            resultEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);
            assertNotNull(ctx.record());
            assertEquals(2, ctx.record().fieldsRow().size());

            assertNotNull(ctx.result());
            assertEquals(2, ctx.result().size());
        }

        @Override
        public void fetchEnd(ExecuteContext ctx) {
            fetchEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);
            assertNotNull(ctx.record());
            assertEquals(2, ctx.record().fieldsRow().size());

            assertNotNull(ctx.result());
            assertEquals(2, ctx.result().size());
        }

        @Override
        public void end(ExecuteContext ctx) {
            end = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
            checkResultSet(ctx, true);
            assertNotNull(ctx.record());
            assertEquals(2, ctx.record().fieldsRow().size());

            assertNotNull(ctx.result());
            assertEquals(2, ctx.result().size());
        }
    }

    static class E extends RuntimeException {

        /**
         * Generated UID
         */
        private static final long serialVersionUID = 594781555404278995L;

        public E(String message) {
            super(message);
        }
    }

    @Test
    public void testExecuteListenerOnBatchSingle() {
        if (!executePreparedStatements(create().getSettings())) {
            log.info("SKIPPINT", "Single batch tests with statement type = STATEMENT");
            return;
        }

        jOOQAbstractTest.reset = false;

        Executor create = create();
        create.getExecuteListeners().add(new BatchSingleListener());

        create.setData("Foo", "Bar");
        create.setData("Bar", "Baz");

        int[] result = create.batch(create().insertInto(TAuthor())
                                            .set(TAuthor_ID(), param("id", Integer.class))
                                            .set(TAuthor_LAST_NAME(), param("name", String.class)))
                             .bind(8, "Gamma")
                             .bind(9, "Helm")
                             .bind(10, "Johnson")
                             .execute();

        assertEquals(3, result.length);

        // Check correct order of listener method invocation
        assertEquals(1, BatchSingleListener.start);
        assertEquals(2, BatchSingleListener.renderStart);
        assertEquals(3, BatchSingleListener.renderEnd);
        assertEquals(4, BatchSingleListener.prepareStart);
        assertEquals(5, BatchSingleListener.prepareEnd);
        assertEquals(asList(6, 8, 10), BatchSingleListener.bindStart);
        assertEquals(asList(7, 9, 11), BatchSingleListener.bindEnd);
        assertEquals(12, BatchSingleListener.executeStart);
        assertEquals(13, BatchSingleListener.executeEnd);
        assertEquals(14, BatchSingleListener.end);
    }

    public static class BatchSingleListener extends DefaultExecuteListener {

        /**
         * Generated UID
         */
        private static final long   serialVersionUID = 7399239846062763212L;

        // A counter that is incremented in callback methods
        private static int          callbackCount    = 0;

        // Fields that are used to check whether callback methods were called
        // in the expected order
        public static int           start;
        public static int           renderStart;
        public static int           renderEnd;
        public static int           prepareStart;
        public static int           prepareEnd;
        public static List<Integer> bindStart        = new ArrayList<Integer>();
        public static List<Integer> bindEnd          = new ArrayList<Integer>();
        public static int           executeStart;
        public static int           executeEnd;
        public static int           end;

        @SuppressWarnings("serial")
        private void checkBase(ExecuteContext ctx) {
            assertNull(ctx.query());
            assertNotNull(ctx.batchQueries());
            assertTrue(ctx.batchQueries()[0].toString().toLowerCase().contains("insert"));
            assertEquals(1, ctx.batchSQL().length);

            assertEquals("Bar", ctx.configuration().getData("Foo"));
            assertEquals("Baz", ctx.configuration().getData("Bar"));
            assertEquals(new HashMap<String, String>() {{
                put("Foo", "Bar");
                put("Bar", "Baz");
            }}, ctx.configuration().getData());

            assertNull(ctx.routine());
            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());

            assertEquals(ExecuteType.BATCH, ctx.type());
        }

        private void checkSQL(ExecuteContext ctx, boolean patched) {
            assertTrue(ctx.batchSQL()[0].toLowerCase().contains("insert"));

            if (patched) {
                assertTrue(ctx.batchSQL()[0].toLowerCase().contains("values    ("));
            }
        }

        @SuppressWarnings("unused")
        private void checkStatement(ExecuteContext ctx, boolean patched) {
            assertNotNull(ctx.statement());
        }

        @Override
        public void start(ExecuteContext ctx) {
            start = ++callbackCount;
            checkBase(ctx);

            assertNull(ctx.batchSQL()[0]);
            assertNull(ctx.sql());
            assertNull(ctx.statement());
        }

        @Override
        public void renderStart(ExecuteContext ctx) {
            renderStart = ++callbackCount;
            checkBase(ctx);

            assertNull(ctx.batchSQL()[0]);
            assertNull(ctx.sql());
            assertNull(ctx.statement());
        }

        @Override
        public void renderEnd(ExecuteContext ctx) {
            renderEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, false);

            assertNull(ctx.statement());

            ctx.sql(ctx.sql().replaceFirst("(?i:values\\s+)", "values    "));
            checkSQL(ctx, true);
        }

        @Override
        public void prepareStart(ExecuteContext ctx) {
            prepareStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);

            assertNull(ctx.statement());
        }

        @Override
        public void prepareEnd(ExecuteContext ctx) {
            prepareEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);

            checkStatement(ctx, false);
            // TODO Patch statement
            checkStatement(ctx, true);
        }

        @Override
        public void bindStart(ExecuteContext ctx) {
            bindStart.add(++callbackCount);
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }

        @Override
        public void bindEnd(ExecuteContext ctx) {
            bindEnd.add(++callbackCount);
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }

        @Override
        public void executeStart(ExecuteContext ctx) {
            executeStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }

        @Override
        public void executeEnd(ExecuteContext ctx) {
            executeEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }

        @Override
        public void fetchStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void resultStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void recordStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void recordEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void resultEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void fetchEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void end(ExecuteContext ctx) {
            end = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }
    }

    @Test
    public void testExecuteListenerOnBatchMultiple() {
        jOOQAbstractTest.reset = false;

        Executor create = create();
        create.getExecuteListeners().add(new BatchMultipleListener());

        create.setData("Foo", "Bar");
        create.setData("Bar", "Baz");

        int[] result = create.batch(
            create().insertInto(TAuthor())
                    .set(TAuthor_ID(), 8)
                    .set(TAuthor_LAST_NAME(), "Gamma"),

            create().insertInto(TAuthor())
                    .set(TAuthor_ID(), 9)
                    .set(TAuthor_LAST_NAME(), "Helm"),

            create().insertInto(TBook())
                    .set(TBook_ID(), 6)
                    .set(TBook_AUTHOR_ID(), 8)
                    .set(TBook_PUBLISHED_IN(), 1994)
                    .set(TBook_LANGUAGE_ID(), 1)
                    .set(TBook_CONTENT_TEXT(), "Design Patterns are awesome")
                    .set(TBook_TITLE(), "Design Patterns"),

            create().insertInto(TAuthor())
                    .set(TAuthor_ID(), 10)
                    .set(TAuthor_LAST_NAME(), "Johnson")).execute();

        assertEquals(4, result.length);
        assertEquals(5, create().fetch(TBook()).size());
        assertEquals(1, create().fetch(TBook(), TBook_AUTHOR_ID().equal(8)).size());

        // Check correct order of listener method invocation
        assertEquals(1, BatchMultipleListener.start);
        assertEquals(asList(2, 4, 6, 8), BatchMultipleListener.renderStart);
        assertEquals(asList(3, 5, 7, 9), BatchMultipleListener.renderEnd);
        assertEquals(asList(10, 12, 14, 16), BatchMultipleListener.prepareStart);
        assertEquals(asList(11, 13, 15, 17), BatchMultipleListener.prepareEnd);
        assertEquals(18, BatchMultipleListener.executeStart);
        assertEquals(19, BatchMultipleListener.executeEnd);
        assertEquals(20, BatchMultipleListener.end);
    }

    public static class BatchMultipleListener extends DefaultExecuteListener {

        /**
         * Generated UID
         */
        private static final long serialVersionUID = 7399239846062763212L;

        // A counter that is incremented in callback methods
        private static int           callbackCount = 0;
        private static int           rendered      = 0;
        private static int           prepared      = 0;

        // Fields that are used to check whether callback methods were called
        // in the expected order
        public static int            start;
        public static List<Integer>  renderStart   = new ArrayList<Integer>();
        public static List<Integer>  renderEnd     = new ArrayList<Integer>();
        public static List<Integer>  prepareStart  = new ArrayList<Integer>();
        public static List<Integer>  prepareEnd    = new ArrayList<Integer>();
        public static int            executeStart;
        public static int            executeEnd;
        public static int            end;

        public static Queue<Integer> ids = new LinkedList<Integer>(asList(1, 2));

        @SuppressWarnings("serial")
        private void checkBase(ExecuteContext ctx) {
            assertNull(ctx.query());
            assertNotNull(ctx.batchQueries());
            assertTrue(ctx.batchQueries()[0].toString().toLowerCase().contains("insert"));
            assertTrue(ctx.batchQueries()[1].toString().toLowerCase().contains("insert"));
            assertTrue(ctx.batchQueries()[2].toString().toLowerCase().contains("insert"));
            assertTrue(ctx.batchQueries()[3].toString().toLowerCase().contains("insert"));
            assertEquals(4, ctx.batchSQL().length);

            assertEquals("Bar", ctx.configuration().getData("Foo"));
            assertEquals("Baz", ctx.configuration().getData("Bar"));
            assertEquals(new HashMap<String, String>() {{
                put("Foo", "Bar");
                put("Bar", "Baz");
            }}, ctx.configuration().getData());

            assertNull(ctx.routine());
            assertNull(ctx.resultSet());
            assertNull(ctx.record());
            assertNull(ctx.result());

            assertEquals(ExecuteType.BATCH, ctx.type());
        }

        private void checkSQL(ExecuteContext ctx, boolean patched) {
            for (int i = 0; i < rendered; i++) {
                assertTrue(ctx.batchQueries()[i].toString().toLowerCase().contains("insert"));

                if (patched) {
                    assertTrue(ctx.batchSQL()[i].toLowerCase().contains("values    ("));
                }
            }
        }

        @SuppressWarnings("unused")
        private void checkStatement(ExecuteContext ctx, boolean patched) {
            assertNotNull(ctx.statement());
        }

        @Override
        public void start(ExecuteContext ctx) {
            start = ++callbackCount;
            checkBase(ctx);

            assertNull(ctx.batchSQL()[0]);
            assertNull(ctx.batchSQL()[1]);
            assertNull(ctx.batchSQL()[2]);
            assertNull(ctx.batchSQL()[3]);
            assertNull(ctx.sql());
            assertNull(ctx.statement());
        }

        @Override
        public void renderStart(ExecuteContext ctx) {
            renderStart.add(++callbackCount);
            checkBase(ctx);
            checkStatement(ctx, false);
            checkSQL(ctx, false);

            assertNull(ctx.sql());
        }

        @Override
        public void renderEnd(ExecuteContext ctx) {
            renderEnd.add(++callbackCount);
            rendered++;
            checkBase(ctx);
            checkStatement(ctx, false);
            checkSQL(ctx, false);

            ctx.batchSQL()[rendered - 1] = ctx.batchSQL()[rendered - 1].replaceFirst("(?i:values\\s+)", "values    ");
            checkSQL(ctx, true);
        }

        @Override
        public void prepareStart(ExecuteContext ctx) {
            prepareStart.add(++callbackCount);
            checkBase(ctx);
            checkStatement(ctx, false);
            checkSQL(ctx, true);
        }

        @Override
        public void prepareEnd(ExecuteContext ctx) {
            prepareEnd.add(++callbackCount);
            prepared++;
            checkBase(ctx);
            checkStatement(ctx, false);
            checkSQL(ctx, true);
        }

        @Override
        public void bindStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void bindEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void executeStart(ExecuteContext ctx) {
            executeStart = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }

        @Override
        public void executeEnd(ExecuteContext ctx) {
            executeEnd = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }

        @Override
        public void fetchStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void resultStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void recordStart(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void recordEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void resultEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void fetchEnd(ExecuteContext ctx) {
            fail();
        }

        @Override
        public void end(ExecuteContext ctx) {
            end = ++callbackCount;
            checkBase(ctx);
            checkSQL(ctx, true);
            checkStatement(ctx, true);
        }
    }

    @Test
    public void testExecuteListenerFetchLazyTest() throws Exception {
        Executor create = create();
        create.getExecuteListeners().add(new FetchLazyListener());
        FetchLazyListener.reset();

        create.selectFrom(TAuthor()).fetch();
        assertEquals(1, FetchLazyListener.countStart);
        assertEquals(1, FetchLazyListener.countRenderStart);
        assertEquals(1, FetchLazyListener.countRenderEnd);
        assertEquals(1, FetchLazyListener.countPrepareStart);
        assertEquals(1, FetchLazyListener.countPrepareEnd);
        assertEquals(1, FetchLazyListener.countBindStart);
        assertEquals(1, FetchLazyListener.countBindEnd);
        assertEquals(1, FetchLazyListener.countExecuteStart);
        assertEquals(1, FetchLazyListener.countExecuteEnd);
        assertEquals(1, FetchLazyListener.countFetchStart);
        assertEquals(1, FetchLazyListener.countResultStart);
        assertEquals(2, FetchLazyListener.countRecordStart);
        assertEquals(2, FetchLazyListener.countRecordEnd);
        assertEquals(1, FetchLazyListener.countResultEnd);
        assertEquals(1, FetchLazyListener.countFetchEnd);
        assertEquals(1, FetchLazyListener.countEnd);
        assertEquals(0, FetchLazyListener.countException);

        // [#1868] fetchLazy should behave almost the same as fetch
        FetchLazyListener.reset();
        Cursor<A> cursor = create.selectFrom(TAuthor()).fetchLazy();
        assertEquals(1, FetchLazyListener.countStart);
        assertEquals(1, FetchLazyListener.countRenderStart);
        assertEquals(1, FetchLazyListener.countRenderEnd);
        assertEquals(1, FetchLazyListener.countPrepareStart);
        assertEquals(1, FetchLazyListener.countPrepareEnd);
        assertEquals(1, FetchLazyListener.countBindStart);
        assertEquals(1, FetchLazyListener.countBindEnd);
        assertEquals(1, FetchLazyListener.countExecuteStart);
        assertEquals(1, FetchLazyListener.countExecuteEnd);
        assertEquals(0, FetchLazyListener.countFetchStart);
        assertEquals(0, FetchLazyListener.countResultStart);
        assertEquals(0, FetchLazyListener.countRecordStart);
        assertEquals(0, FetchLazyListener.countRecordEnd);
        assertEquals(0, FetchLazyListener.countResultEnd);
        assertEquals(0, FetchLazyListener.countFetchEnd);
        assertEquals(0, FetchLazyListener.countEnd);
        assertEquals(0, FetchLazyListener.countException);

        cursor.fetchOne();
        assertEquals(1, FetchLazyListener.countStart);
        assertEquals(1, FetchLazyListener.countRenderStart);
        assertEquals(1, FetchLazyListener.countRenderEnd);
        assertEquals(1, FetchLazyListener.countPrepareStart);
        assertEquals(1, FetchLazyListener.countPrepareEnd);
        assertEquals(1, FetchLazyListener.countBindStart);
        assertEquals(1, FetchLazyListener.countBindEnd);
        assertEquals(1, FetchLazyListener.countExecuteStart);
        assertEquals(1, FetchLazyListener.countExecuteEnd);
        assertEquals(1, FetchLazyListener.countFetchStart);
        assertEquals(1, FetchLazyListener.countResultStart);
        assertEquals(1, FetchLazyListener.countRecordStart);
        assertEquals(1, FetchLazyListener.countRecordEnd);
        assertEquals(1, FetchLazyListener.countResultEnd);
        assertEquals(0, FetchLazyListener.countFetchEnd);
        assertEquals(0, FetchLazyListener.countEnd);
        assertEquals(0, FetchLazyListener.countException);

        cursor.fetchOne();
        assertEquals(1, FetchLazyListener.countStart);
        assertEquals(1, FetchLazyListener.countRenderStart);
        assertEquals(1, FetchLazyListener.countRenderEnd);
        assertEquals(1, FetchLazyListener.countPrepareStart);
        assertEquals(1, FetchLazyListener.countPrepareEnd);
        assertEquals(1, FetchLazyListener.countBindStart);
        assertEquals(1, FetchLazyListener.countBindEnd);
        assertEquals(1, FetchLazyListener.countExecuteStart);
        assertEquals(1, FetchLazyListener.countExecuteEnd);
        assertEquals(1, FetchLazyListener.countFetchStart);
        assertEquals(2, FetchLazyListener.countResultStart);
        assertEquals(2, FetchLazyListener.countRecordStart);
        assertEquals(2, FetchLazyListener.countRecordEnd);
        assertEquals(2, FetchLazyListener.countResultEnd);
        assertEquals(0, FetchLazyListener.countFetchEnd);
        assertEquals(0, FetchLazyListener.countEnd);
        assertEquals(0, FetchLazyListener.countException);

        cursor.fetchOne();
        assertEquals(1, FetchLazyListener.countStart);
        assertEquals(1, FetchLazyListener.countRenderStart);
        assertEquals(1, FetchLazyListener.countRenderEnd);
        assertEquals(1, FetchLazyListener.countPrepareStart);
        assertEquals(1, FetchLazyListener.countPrepareEnd);
        assertEquals(1, FetchLazyListener.countBindStart);
        assertEquals(1, FetchLazyListener.countBindEnd);
        assertEquals(1, FetchLazyListener.countExecuteStart);
        assertEquals(1, FetchLazyListener.countExecuteEnd);
        assertEquals(1, FetchLazyListener.countFetchStart);
        assertEquals(3, FetchLazyListener.countResultStart);
        assertEquals(2, FetchLazyListener.countRecordStart);
        assertEquals(2, FetchLazyListener.countRecordEnd);
        assertEquals(3, FetchLazyListener.countResultEnd);
        assertEquals(1, FetchLazyListener.countFetchEnd);
        assertEquals(1, FetchLazyListener.countEnd);
        assertEquals(0, FetchLazyListener.countException);
    }

    public static class FetchLazyListener implements ExecuteListener {

        /**
         * Generated UID
         */
        private static final long serialVersionUID = 7399239846062763212L;

        static int countStart;
        static int countRenderStart;
        static int countRenderEnd;
        static int countPrepareStart;
        static int countPrepareEnd;
        static int countBindStart;
        static int countBindEnd;
        static int countExecuteStart;
        static int countExecuteEnd;
        static int countFetchStart;
        static int countResultStart;
        static int countRecordStart;
        static int countRecordEnd;
        static int countResultEnd;
        static int countFetchEnd;
        static int countEnd;
        static int countException;

        static void reset() {
            for (java.lang.reflect.Field f : FetchLazyListener.class.getDeclaredFields()) {
                f.setAccessible(true);

                try {
                    f.set(FetchLazyListener.class, 0);
                }
                catch (Exception ignore) {}
            }
        }

        @Override
        public void start(ExecuteContext ctx) {
            countStart++;
        }

        @Override
        public void renderStart(ExecuteContext ctx) {
            countRenderStart++;
        }

        @Override
        public void renderEnd(ExecuteContext ctx) {
            countRenderEnd++;
        }

        @Override
        public void prepareStart(ExecuteContext ctx) {
            countPrepareStart++;
        }

        @Override
        public void prepareEnd(ExecuteContext ctx) {
            countPrepareEnd++;
        }

        @Override
        public void bindStart(ExecuteContext ctx) {
            countBindStart++;
        }

        @Override
        public void bindEnd(ExecuteContext ctx) {
            countBindEnd++;
        }

        @Override
        public void executeStart(ExecuteContext ctx) {
            countExecuteStart++;
        }

        @Override
        public void executeEnd(ExecuteContext ctx) {
            countExecuteEnd++;
        }

        @Override
        public void fetchStart(ExecuteContext ctx) {
            countFetchStart++;
        }

        @Override
        public void resultStart(ExecuteContext ctx) {
            countResultStart++;
        }

        @Override
        public void recordStart(ExecuteContext ctx) {
            countRecordStart++;
        }

        @Override
        public void recordEnd(ExecuteContext ctx) {
            countRecordEnd++;
        }

        @Override
        public void resultEnd(ExecuteContext ctx) {
            countResultEnd++;
        }

        @Override
        public void fetchEnd(ExecuteContext ctx) {
            countFetchEnd++;
        }

        @Override
        public void exception(ExecuteContext ctx) {
            countException++;
        }

        @Override
        public void end(ExecuteContext ctx) {
            countEnd++;
        }
    }
}
