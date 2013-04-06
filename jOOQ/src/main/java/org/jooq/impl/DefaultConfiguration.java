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
package org.jooq.impl;

import static org.jooq.SQLDialect.SQL99;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;

import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.ExecuteListener;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.conf.SettingsTools;

/**
 * A default implementation for configurations within a {@link DSLContext}, if no
 * custom configuration was supplied to {@link Factory#using(Configuration)}
 */
class DefaultConfiguration implements Configuration {

    /**
     * Serial version UID
     */
    private static final long            serialVersionUID = 8193158984283234708L;

    private transient ConnectionProvider connectionProvider;
    private final SQLDialect             dialect;

    @SuppressWarnings("deprecation")
    private final org.jooq.SchemaMapping mapping;
    private final Settings               settings;
    private final Map<Object, Object>    data;
    private List<ExecuteListener>        listeners;

    @SuppressWarnings("deprecation")
    DefaultConfiguration() {
        this(new NoConnectionProvider(), SQL99, SettingsTools.defaultSettings(), null);
    }

    @SuppressWarnings("deprecation")
    DefaultConfiguration(ConnectionProvider connectionProvider, SQLDialect dialect, Settings settings, Map<Object, Object> data) {
        this.connectionProvider = connectionProvider;
        this.dialect = dialect;
        this.settings = settings != null ? settings : SettingsTools.defaultSettings();
        this.mapping = new org.jooq.SchemaMapping(this);
        this.data = data != null ? data : new HashMap<Object, Object>();
        this.listeners = new ArrayList<ExecuteListener>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final SQLDialect getDialect() {
        return dialect;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ConnectionProvider getConnectionProvider() {
        return connectionProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public final org.jooq.SchemaMapping getSchemaMapping() {
        return mapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Settings getSettings() {
        return settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<Object, Object> getData() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object getData(Object key) {
        return data.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Object setData(Object key, Object value) {
        return data.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<ExecuteListener> getExecuteListeners() {
        return listeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setExecuteListeners(List<ExecuteListener> listeners) {
        this.listeners = listeners != null ? listeners : new ArrayList<ExecuteListener>();
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        JAXB.marshal(settings, writer);

        return "DefaultConfiguration [\n\tconnected=" + (connectionProvider != null && !(connectionProvider instanceof NoConnectionProvider)) +
            ",\n\tdialect=" + dialect +
            ",\n\tdata=" + data +
            ",\n\tsettings=\n\t\t" + writer.toString().trim().replace("\n", "\n\t\t") +
            "\n]";        }
}