/*
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
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.Internal.*;
import static org.jooq.impl.Keywords.*;
import static org.jooq.impl.Names.*;
import static org.jooq.impl.SQLDataType.*;
import static org.jooq.impl.Tools.*;
import static org.jooq.impl.Tools.BooleanDataKey.*;
import static org.jooq.impl.Tools.DataExtendedKey.*;
import static org.jooq.impl.Tools.DataKey.*;
import static org.jooq.SQLDialect.*;

import org.jooq.*;
import org.jooq.Function1;
import org.jooq.Record;
import org.jooq.conf.*;
import org.jooq.impl.*;
import org.jooq.impl.QOM.*;
import org.jooq.tools.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;


/**
 * The <code>RPAD</code> statement.
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
final class Rpad
extends
    AbstractField<String>
implements
    QOM.Rpad
{

    final Field<String>           string;
    final Field<? extends Number> length;
    final Field<String>           character;

    Rpad(
        Field<String> string,
        Field<? extends Number> length
    ) {
        super(
            N_RPAD,
            allNotNull(VARCHAR, string, length)
        );

        this.string = nullSafeNotNull(string, VARCHAR);
        this.length = nullSafeNotNull(length, INTEGER);
        this.character = null;
    }

    Rpad(
        Field<String> string,
        Field<? extends Number> length,
        Field<String> character
    ) {
        super(
            N_RPAD,
            allNotNull(VARCHAR, string, length, character)
        );

        this.string = nullSafeNotNull(string, VARCHAR);
        this.length = nullSafeNotNull(length, INTEGER);
        this.character = nullSafeNotNull(character, VARCHAR);
    }

    // -------------------------------------------------------------------------
    // XXX: QueryPart API
    // -------------------------------------------------------------------------



    private final Field<String> characterOrBlank() {
        return character == null ? inline(" ") : character;
    }

    @Override
    public final void accept(Context<?> ctx) {
        switch (ctx.family()) {













            // This beautiful expression was contributed by "Ludo", here:
            // http://stackoverflow.com/questions/6576343/how-to-simulate-lpad-rpad-with-sqlite
            case SQLITE:
                ctx.visit(string).sql(" || ").visit(N_SUBSTR).sql('(')
                    .visit(N_REPLACE).sql('(')
                        .visit(N_HEX).sql('(')
                            .visit(N_ZEROBLOB).sql('(')
                                .visit(length)
                        .sql(")), '00', ").visit(characterOrBlank())
                    .sql("), 1, ").visit(length).sql(" - ").visit(N_LENGTH).sql('(').visit(string).sql(')')
                .sql(')');
                break;

            default:
                ctx.visit(function(N_RPAD, getDataType(), string, length, characterOrBlank()));
                break;
        }
    }

















    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @Override
    public final Field<String> $string() {
        return string;
    }

    @Override
    public final Field<? extends Number> $length() {
        return length;
    }

    @Override
    public final Field<String> $character() {
        return character;
    }

    @Override
    public final QOM.Rpad $string(Field<String> newValue) {
        return constructor().apply(newValue, $length(), $character());
    }

    @Override
    public final QOM.Rpad $length(Field<? extends Number> newValue) {
        return constructor().apply($string(), newValue, $character());
    }

    @Override
    public final QOM.Rpad $character(Field<String> newValue) {
        return constructor().apply($string(), $length(), newValue);
    }

    public final Function3<? super Field<String>, ? super Field<? extends Number>, ? super Field<String>, ? extends QOM.Rpad> constructor() {
        return (a1, a2, a3) -> new Rpad(a1, a2, a3);
    }

    @Override
    public final QueryPart $replace(
        Predicate<? super QueryPart> recurse,
        Function1<? super QueryPart, ? extends QueryPart> replacement
    ) {
        return QOM.replace(
            this,
            $string(),
            $length(),
            $character(),
            constructor()::apply,
            recurse,
            replacement
        );
    }

    @Override
    public final <R> R $traverse(Traverser<?, R> traverser) {
        return QOM.traverse(traverser, this,
            $string(),
            $length(),
            $character()
        );
    }

    // -------------------------------------------------------------------------
    // XXX: The Object API
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object that) {
        if (that instanceof Rpad) { Rpad o = (Rpad) that;
            return
                StringUtils.equals($string(), o.$string()) &&
                StringUtils.equals($length(), o.$length()) &&
                StringUtils.equals($character(), o.$character())
            ;
        }
        else
            return super.equals(that);
    }
}
