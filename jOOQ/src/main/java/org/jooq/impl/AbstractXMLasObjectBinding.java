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

import static java.beans.Introspector.decapitalize;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLXML;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;

import org.jooq.Converter;

/**
 * A binding that binds JAXB-annotated {@link Object} types to {@link SQLXML}
 * types from your database.
 * <p>
 * Subtypes may extend this type to provide actual bound type.
 *
 * @author Lukas Eder
 */
public class AbstractXMLasObjectBinding<T> extends AbstractVarcharBinding<T> {

    /**
     * Generated UID
     */
    private static final long          serialVersionUID = -2153155338260706262L;

    private final Converter<Object, T> converter;

    protected AbstractXMLasObjectBinding(final Class<T> type) {
        final XmlRootElement root = type.getAnnotation(XmlRootElement.class);
        final JAXBContext ctx;

        try {
            ctx = JAXBContext.newInstance(type);
        }
        catch (JAXBException e) {
            throw new DataBindingException(e);
        }

        this.converter = new Converter<Object, T>() {

            /**
             * Generated UID
             */
            private static final long serialVersionUID = -2153155338260706262L;

            @Override
            public T from(Object t) {
                if (t == null)
                    return null;

                return JAXB.unmarshal(new StringReader("" + t), type);
            }

            @Override
            public Object to(T u) {
                if (u == null)
                    return null;

                try {
                    StringWriter s = new StringWriter();

                    Object o = u;
                    if (root == null) {
                        o = new JAXBElement<T>(new QName(decapitalize(type.getSimpleName())), type, u);
                    }

                    Marshaller m = ctx.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FRAGMENT, true);
                    m.marshal(o, s);
                    return s.toString();
                }
                catch (JAXBException e) {
                    throw new DataBindingException(e);
                }
            }

            @Override
            public Class<Object> fromType() {
                return Object.class;
            }

            @Override
            public Class<T> toType() {
                return type;
            }
        };
    }

    @Override
    public final Converter<Object, T> converter() {
        return converter;
    }

}
