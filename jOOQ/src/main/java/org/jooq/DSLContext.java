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
package org.jooq;

// ...
// ...
// ...
// ...
// ...
// ...
import static org.jooq.SQLDialect.CUBRID;
// ...
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.FIREBIRD;
// ...
import static org.jooq.SQLDialect.H2;
// ...
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.IGNITE;
// ...
// ...
import static org.jooq.SQLDialect.MARIADB;
// ...
// ...
import static org.jooq.SQLDialect.MYSQL;
// ...
// ...
import static org.jooq.SQLDialect.POSTGRES;
// ...
// ...
// ...
// ...
import static org.jooq.SQLDialect.SQLITE;
// ...
// ...
// ...
// ...
// ...
// ...

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;
import org.jooq.conf.StatementType;
import org.jooq.exception.ConfigurationException;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.DataDefinitionException;
import org.jooq.exception.InvalidResultException;
import org.jooq.exception.MappingException;
import org.jooq.exception.NoDataFoundException;
import org.jooq.exception.TooManyRowsException;
import org.jooq.impl.DSL;
import org.jooq.impl.ParserException;
import org.jooq.impl.ThreadLocalTransactionProvider;
import org.jooq.tools.jdbc.BatchedConnection;
import org.jooq.tools.jdbc.MockCallable;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockRunnable;
import org.jooq.util.xml.jaxb.InformationSchema;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A contextual DSL providing "attached" implementations to the
 * <code>org.jooq</code> interfaces.
 * <p>
 * Apart from the {@link DSL}, this contextual DSL is the main entry point
 * for client code, to access jOOQ classes and functionality that are related to
 * {@link Query} execution. Unlike objects created through the
 * <code>DSL</code> type, objects created from a <code>DSLContext</code> will be
 * "attached" to the <code>DSLContext</code>'s {@link #configuration()}, such
 * that they can be executed immediately in a fluent style. An example is given
 * here:
 * <p>
 * <code><pre>
 * DSLContext create = DSL.using(connection, dialect);
 *
 * // Immediately fetch results after constructing a query
 * create.selectFrom(MY_TABLE).where(MY_TABLE.ID.eq(1)).fetch();
 *
 * // The above is equivalent to this "non-fluent" style
 * create.fetch(DSL.selectFrom(MY_TABLE).where(MY_TABLE.ID.eq(1)));
 * </pre></code>
 * <p>
 * The <code>DSL</code> provides convenient constructors to create a
 * {@link Configuration}, which will be shared among all <code>Query</code>
 * objects thus created. Optionally, you can pass a reusable
 * <code>Configuration</code> to the {@link DSL#using(Configuration)}
 * constructor. Please consider thread-safety concerns documented in
 * {@link Configuration}, should you want to reuse the same
 * <code>Configuration</code> instance in various threads and / or transactions.
 *
 * @see DSL
 * @see Configuration
 * @author Lukas Eder
 */
public interface DSLContext extends Scope {

    // -------------------------------------------------------------------------
    // XXX Configuration API
    // -------------------------------------------------------------------------

    /**
     * Map a schema to another one.
     * <p>
     * This will map a schema onto another one, depending on configured schema
     * mapping in this <code>DSLContext</code>. If no applicable schema mapping
     * can be found, the schema itself is returned.
     *
     * @param schema A schema
     * @return The mapped schema
     */
    @Nullable
    Schema map(Schema schema);

    /**
     * Map a table to another one.
     * <p>
     * This will map a table onto another one, depending on configured table
     * mapping in this <code>DSLContext</code>. If no applicable table mapping can
     * be found, the table itself is returned.
     *
     * @param table A table
     * @return The mapped table
     */
    @Nullable
    <R extends Record> Table<R> map(Table<R> table);

    // -------------------------------------------------------------------------
    // XXX Convenience methods accessing the underlying Connection
    // -------------------------------------------------------------------------

    /**
     * Access the parser API.
     * <p>
     * This is experimental functionality.
     */
    @NotNull
    Parser parser();

    /**
     * A JDBC connection that runs each statement through the {@link #parser()}
     * first, prior to re-generating and running the SQL.
     * <p>
     * The resulting {@link Connection} wraps an underlying JDBC connection that
     * has been obtained from {@link ConnectionProvider#acquire()} and must be
     * released by calling {@link Connection#close()}.
     * <p>
     * <strong>This is experimental functionality:</strong>
     * <ul>
     * <li>While this works well for static {@link Statement} executions, bind
     * variables and their position calculation might cause issues when the
     * generated SQL output involves more complex SQL transformation. See also
     * <a href=
     * "https://github.com/jOOQ/jOOQ/issues/5759">https://github.com/jOOQ/jOOQ/issues/5759</a>.</li>
     * <li>Batching statements is currently not supported. See also <a href=
     * "https://github.com/jOOQ/jOOQ/issues/5757">https://github.com/jOOQ/jOOQ/issues/5757</a>.</li>
     * </ul>
     */
    @NotNull
    Connection parsingConnection();

    /**
     * A JDBC data source that runs each statement through the {@link #parser()}
     * first, prior to re-generating and running the SQL.
     * <p>
     * This simply wraps the {@link #parsingConnection()} in a {@link DataSource}.
     */
    @NotNull
    DataSource parsingDataSource();

    /**
     * A JDBC connection that proxies the underlying connection to run the jOOQ
     * Diagnostics Pack on executed queries.
     * <p>
     * <strong>This is experimental functionality.</strong>
     */
    @NotNull
    Connection diagnosticsConnection();

    /**
     * A JDBC connection that proxies the underlying connection to run the jOOQ
     * Diagnostics Pack on executed queries.
     * <p>
     * This simply wraps the {@link #diagnosticsConnection()} in a {@link DataSource}.
     */
    @NotNull
    DataSource diagnosticsDataSource();

    /**
     * Initialise a {@link Version}.
     * <p>
     * This is EXPERIMENTAL functionality and subject to change in future jOOQ
     * versions.
     */
    @Internal
    @NotNull
    Version version(String id);

    /**
     * Initialise a {@link Versions} graph.
     * <p>
     * This is EXPERIMENTAL functionality and subject to change in future jOOQ
     * versions.
     */
    @Internal
    @NotNull
    Versions versions();

    /**
     * Initialise a {@link Version}.
     * <p>
     * This is EXPERIMENTAL functionality and subject to change in future jOOQ
     * versions.
     */
    @Internal
    @NotNull
    Commit commit(String id);

    /**
     * Initialise a {@link Commits} graph.
     * <p>
     * This is EXPERIMENTAL functionality and subject to change in future jOOQ
     * versions.
     */
    @Internal
    @NotNull
    Commits commits();

    /**
     * Create a migration from the currently installed version to a new version.
     * <p>
     * This is EXPERIMENTAL functionality and subject to change in future jOOQ
     * versions.
     */
    @Internal
    @NotNull
    Migration migrateTo(Commit to);

    /**
     * Access the database meta data.
     * <p>
     * This method returns meta information provided by
     * {@link Configuration#metaProvider()}, which defaults to a wrapper type
     * that gives access to your JDBC connection's {@link DatabaseMetaData} as
     * obtained from your {@link ConnectionProvider}.
     *
     * @see #meta(DatabaseMetaData)
     */
    @NotNull
    Meta meta();

    /**
     * Access the database meta data from an explicit JDBC
     * {@link DatabaseMetaData}.
     */
    @NotNull
    Meta meta(DatabaseMetaData meta);

    /**
     * Access the database meta data from explicit catalog information.
     * <p>
     * This will not connect to your database to get live meta information,
     * unlike {@link #meta()} and {@link #meta(DatabaseMetaData)}.
     */
    @NotNull
    Meta meta(Catalog... catalogs);

    /**
     * Access the database meta data from explicit schema information.
     * <p>
     * This will not connect to your database to get live meta information,
     * unlike {@link #meta()} and {@link #meta(DatabaseMetaData)}.
     */
    @NotNull
    Meta meta(Schema... schemas);

    /**
     * Access the database meta data from explicit table information.
     * <p>
     * This will not connect to your database to get live meta information,
     * unlike {@link #meta()} and {@link #meta(DatabaseMetaData)}.
     */
    @NotNull
    Meta meta(Table<?>... tables);

    /**
     * Access the database meta data from an explicit JAXB-annotated meta model.
     * <p>
     * This will not connect to your database to get live meta information,
     * unlike {@link #meta()} and {@link #meta(DatabaseMetaData)}.
     */
    @NotNull
    Meta meta(InformationSchema schema);

    /**
     * Create meta data from a set of sources.
     * <p>
     * This is convenience for wrapping all argument {@link String}s in
     * {@link Source}. The same set of content types are supported as in
     * {@link #meta(Source...)}.
     *
     * @throws DataDefinitionException if the sources do not produce consistent
     *             meta data.
     * @throws ParserException if there is a parser error parsing the sources.
     */
    @NotNull
    Meta meta(String... sources) throws DataDefinitionException, ParserException;

    /**
     * Create meta data from a set of sources.
     * <p>
     * This method creates a {@link Meta} representation from a set of source
     * content, which can be any of:
     * <ul>
     * <li>A set of DDL scripts, which will be parsed using
     * {@link #parser()}.</li>
     * <li>A set of XML files, which will be unmarshalled into
     * {@link InformationSchema} objects.</li>
     * </ul>
     * <p>
     * This will not connect to your database to get live meta information,
     * unlike {@link #meta()} and {@link #meta(DatabaseMetaData)}.
     *
     * @throws DataDefinitionException if the sources do not produce consistent
     *             meta data.
     * @throws ParserException if there is a parser error parsing the sources.
     */
    @NotNull
    Meta meta(Source... sources) throws DataDefinitionException, ParserException;

    /**
     * Create meta data from a set of DDL queries.
     * <p>
     * This works the same way as {@link #meta(Source...)}, without the need of
     * parsing the DDL scripts.
     *
     * @throws DataDefinitionException if the sources do not produce consistent
     *             meta data.
     */
    @NotNull
    Meta meta(Query... queries) throws DataDefinitionException;

    /**
     * Convenience method for {@link Meta#informationSchema()}.
     *
     * @see #meta(Catalog...)
     * @see Meta#informationSchema()
     */
    @NotNull
    InformationSchema informationSchema(Catalog catalog);

    /**
     * Convenience method for {@link Meta#informationSchema()}.
     *
     * @see #meta(Catalog...)
     * @see Meta#informationSchema()
     */
    @NotNull
    InformationSchema informationSchema(Catalog... catalogs);

    /**
     * Convenience method for {@link Meta#informationSchema()}.
     *
     * @see #meta(Schema...)
     * @see Meta#informationSchema()
     */
    @NotNull
    InformationSchema informationSchema(Schema schema);

    /**
     * Convenience method for {@link Meta#informationSchema()}.
     *
     * @see #meta(Schema...)
     * @see Meta#informationSchema()
     */
    @NotNull
    InformationSchema informationSchema(Schema... schemas);

    /**
     * Convenience method for {@link Meta#informationSchema()}.
     *
     * @see #meta(Table...)
     * @see Meta#informationSchema()
     */
    @NotNull
    InformationSchema informationSchema(Table<?> table);

    /**
     * Convenience method for {@link Meta#informationSchema()}.
     *
     * @see #meta(Table...)
     * @see Meta#informationSchema()
     */
    @NotNull
    InformationSchema informationSchema(Table<?>... table);

    // -------------------------------------------------------------------------
    // XXX APIs related to query optimisation
    // -------------------------------------------------------------------------

    /**
     * Run an <code>EXPLAIN</code> statement in the database to estimate the
     * cardinality of the query.
     */
    @NotNull
    @Support({ H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    Explain explain(Query query);

    // -------------------------------------------------------------------------
    // XXX APIs for creating scope for transactions, mocking, batching, etc.
    // -------------------------------------------------------------------------

    /**
     * Run a {@link TransactionalCallable} in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}, and return the
     * <code>transactional</code>'s outcome.
     * <p>
     * The argument transactional code should not capture any scope but derive
     * its {@link Configuration} from the
     * {@link TransactionalCallable#run(Configuration)} argument in order to
     * create new statements.
     *
     * @param transactional The transactional code
     * @return The transactional outcome
     * @throws RuntimeException any runtime exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     * @throws DataAccessException any database problem that may have arised
     *             when executing the <code>transactional</code> logic, or a
     *             wrapper for any checked exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     */
    <T> T transactionResult(TransactionalCallable<T> transactional);

    /**
     * Run a {@link ContextTransactionalRunnable} in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}, and return the
     * <code>transactional</code>'s outcome.
     * <p>
     * The argument transactional code may capture scope to derive its
     * {@link Configuration} from the "context" in order to create new
     * statements. This context can be provided, for instance, by
     * {@link ThreadLocalTransactionProvider} automatically.
     *
     * @param transactional The transactional code
     * @return The transactional outcome
     * @throws ConfigurationException if the underlying
     *             {@link Configuration#transactionProvider()} is not able to
     *             provide context (i.e. currently, it is not a
     *             {@link ThreadLocalTransactionProvider}).
     * @throws RuntimeException any runtime exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     * @throws DataAccessException any database problem that may have arised
     *             when executing the <code>transactional</code> logic, or a
     *             wrapper for any checked exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     */
    <T> T transactionResult(ContextTransactionalCallable<T> transactional) throws ConfigurationException;

    /**
     * Run a {@link TransactionalRunnable} in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}.
     * <p>
     * The argument transactional code should not capture any scope but derive
     * its {@link Configuration} from the
     * {@link TransactionalCallable#run(Configuration)} argument in order to
     * create new statements.
     *
     * @param transactional The transactional code
     * @throws RuntimeException any runtime exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     * @throws DataAccessException any database problem that may have arised
     *             when executing the <code>transactional</code> logic, or a
     *             wrapper for any checked exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     */
    void transaction(TransactionalRunnable transactional);

    /**
     * Run a {@link ContextTransactionalRunnable} in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}.
     * <p>
     * The argument transactional code may capture scope to derive its
     * {@link Configuration} from the "context" in order to create new
     * statements. This context can be provided, for instance, by
     * {@link ThreadLocalTransactionProvider} automatically.
     *
     * @param transactional The transactional code
     * @throws ConfigurationException if the underlying
     *             {@link Configuration#transactionProvider()} is not able to
     *             provide context (i.e. currently, it is not a
     *             {@link ThreadLocalTransactionProvider}).
     * @throws RuntimeException any runtime exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     * @throws DataAccessException any database problem that may have arised
     *             when executing the <code>transactional</code> logic, or a
     *             wrapper for any checked exception thrown by the
     *             <code>transactional</code> logic, indicating that a rollback
     *             has occurred.
     */
    void transaction(ContextTransactionalRunnable transactional) throws ConfigurationException;

    /**
     * Run a {@link TransactionalCallable} asynchronously.
     * <p>
     * The <code>TransactionCallable</code> is run in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}, and returns the
     * <code>transactional</code>'s outcome in a new {@link CompletionStage}
     * that is asynchronously completed by a task run by an {@link Executor}
     * provided by the underlying {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     *
     * @param transactional The transactional code
     * @return The transactional outcome
     * @throws ConfigurationException If this is run with a
     *             {@link ThreadLocalTransactionProvider}.
     */
    @NotNull
    <T> CompletionStage<T> transactionResultAsync(TransactionalCallable<T> transactional) throws ConfigurationException;

    /**
     * Run a {@link TransactionalRunnable} asynchronously.
     * <p>
     * The <code>TransactionRunnable</code> is run in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}, and returns the
     * <code>transactional</code>'s outcome in a new {@link CompletionStage}
     * that is asynchronously completed by a task run by an {@link Executor}
     * provided by the underlying {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     *
     * @param transactional The transactional code
     * @throws ConfigurationException If this is run with a
     *             {@link ThreadLocalTransactionProvider}.
     */
    @NotNull
    CompletionStage<Void> transactionAsync(TransactionalRunnable transactional) throws ConfigurationException;

    /**
     * Run a {@link TransactionalCallable} asynchronously.
     * <p>
     * The <code>TransactionCallable</code> is run in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}, and returns the
     * <code>transactional</code>'s outcome in a new {@link CompletionStage}
     * that is asynchronously completed by a task run by a given
     * {@link Executor}.
     *
     * @param transactional The transactional code
     * @return The transactional outcome
     * @throws ConfigurationException If this is run with a
     *             {@link ThreadLocalTransactionProvider}.
     */
    @NotNull
    <T> CompletionStage<T> transactionResultAsync(Executor executor, TransactionalCallable<T> transactional) throws ConfigurationException;

    /**
     * Run a {@link TransactionalRunnable} asynchronously.
     * <p>
     * The <code>TransactionRunnable</code> is run in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#transactionProvider()}, and returns the
     * <code>transactional</code>'s outcome in a new {@link CompletionStage}
     * that is asynchronously completed by a task run by a given
     * {@link Executor}.
     *
     * @param transactional The transactional code
     * @throws ConfigurationException If this is run with a
     *             {@link ThreadLocalTransactionProvider}.
     */
    @NotNull
    CompletionStage<Void> transactionAsync(Executor executor, TransactionalRunnable transactional) throws ConfigurationException;

    /**
     * Run a {@link ConnectionCallable} in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#connectionProvider()}.
     *
     * @param callable The code running statements against the
     *            <code>connection</code>.
     * @return The outcome of the callable
     */
    <T> T connectionResult(ConnectionCallable<T> callable);

    /**
     * Run a {@link ConnectionRunnable} in the context of this
     * <code>DSLContext</code>'s underlying {@link #configuration()}'s
     * {@link Configuration#connectionProvider()}.
     *
     * @param runnable The code running statements against the
     *            <code>connection</code>.
     */
    void connection(ConnectionRunnable runnable);

    /**
     * Run a {@link MockRunnable} in the context of this <code>DSLContext</code>
     * 's underlying {@link #configuration()}'s, and of a
     * {@link MockDataProvider} and return the <code>mockable</code>'s outcome.
     */
    <T> T mockResult(MockDataProvider provider, MockCallable<T> mockable);

    /**
     * Run a {@link MockRunnable} in the context of this <code>DSLContext</code>
     * 's underlying {@link #configuration()}'s, and of a
     * {@link MockDataProvider}.
     */
    void mock(MockDataProvider provider, MockRunnable mockable);

    // -------------------------------------------------------------------------
    // XXX RenderContext and BindContext accessors
    // -------------------------------------------------------------------------

    /**
     * Get a new {@link RenderContext} for the context of this <code>DSLContext</code>.
     * <p>
     * This will return an initialised render context as such:
     * <ul>
     * <li>
     * <code>{@link RenderContext#castMode()} == {@link org.jooq.RenderContext.CastMode#DEFAULT DEFAULT}</code>
     * </li>
     * <li> <code>{@link RenderContext#declareFields()} == false</code></li>
     * <li> <code>{@link RenderContext#declareTables()} == false</code></li>
     * <li> <code>{@link RenderContext#format()} == false</code></li>
     * <li> <code>{@link RenderContext#paramType()} == {@link ParamType#INDEXED}</code></li>
     * <li> <code>{@link RenderContext#qualify()} == true</code></li>
     * <li> <code>{@link RenderContext#subquery()} == false</code></li>
     * </ul>
     *
     * @deprecated - [#6280] - 3.10 - Do not reuse this method. It will be
     *             completely internal with jOOQ 4.0
     */
    @Deprecated
    @Internal
    @NotNull
    RenderContext renderContext();

    /**
     * Render a QueryPart in the context of this <code>DSLContext</code>.
     * <p>
     * This is the same as calling <code>renderContext().render(part)</code>
     *
     * @param part The {@link QueryPart} to be rendered
     * @return The rendered SQL
     */
    @NotNull
    String render(QueryPart part);

    /**
     * Render a QueryPart in the context of this <code>DSLContext</code>, rendering bind
     * variables as named parameters.
     * <p>
     * This is the same as calling
     * <code>renderContext().paramType(NAMED).render(part)</code>
     *
     * @param part The {@link QueryPart} to be rendered
     * @return The rendered SQL
     */
    @NotNull
    String renderNamedParams(QueryPart part);

    /**
     * Render a QueryPart in the context of this <code>DSLContext</code>, rendering bind
     * variables as named parameters, or inlined parameters if they have no name.
     * <p>
     * This is the same as calling
     * <code>renderContext().paramType(NAMED_OR_INLINED).render(part)</code>
     *
     * @param part The {@link QueryPart} to be rendered
     * @return The rendered SQL
     */
    @NotNull
    String renderNamedOrInlinedParams(QueryPart part);

    /**
     * Render a QueryPart in the context of this <code>DSLContext</code>, inlining all bind
     * variables.
     * <p>
     * This is the same as calling
     * <code>renderContext().inline(true).render(part)</code>
     *
     * @param part The {@link QueryPart} to be rendered
     * @return The rendered SQL
     */
    @NotNull
    String renderInlined(QueryPart part);

    /**
     * Retrieve the bind values that will be bound by a given
     * <code>QueryPart</code>.
     * <p>
     * The returned <code>List</code> is immutable. To modify bind values, use
     * {@link #extractParams(QueryPart)} instead.
     * <p>
     * Unlike {@link #extractParams(QueryPart)}, which returns also inlined
     * parameters, this returns only actual bind values that will render an
     * actual bind value as a question mark <code>"?"</code>
     */
    @NotNull
    List<Object> extractBindValues(QueryPart part);

    /**
     * Get a <code>Map</code> of named parameters.
     * <p>
     * The <code>Map</code> itself is immutable, but the {@link Param} elements
     * allow for modifying bind values on an existing {@link Query} (or any
     * other {@link QueryPart}).
     * <p>
     * Bind values created with {@link DSL#val(Object)} will have their bind
     * index as name.
     *
     * @see Param
     * @see DSL#param(String, Object)
     */
    @NotNull
    Map<String, Param<?>> extractParams(QueryPart part);

    /**
     * Get a named parameter from a {@link QueryPart}, provided its name.
     * <p>
     * Bind values created with {@link DSL#val(Object)} will have their bind
     * index as name.
     *
     * @see Param
     * @see DSL#param(String, Object)
     */
    @Nullable
    Param<?> extractParam(QueryPart part, String name);

    /**
     * Get a new {@link BindContext} for the context of this
     * <code>DSLContext</code>.
     * <p>
     * This will return an initialised bind context as such:
     * <ul>
     * <li><code>{@link RenderContext#declareFields()} == false</code></li>
     * <li><code>{@link RenderContext#declareTables()} == false</code></li>
     * </ul>
     * <p>
     * BindContext for JOOQ INTERNAL USE only. Avoid referencing it directly
     *
     * @deprecated - [#6280] - 3.10 - Do not reuse this method. It will be
     *             completely internal with jOOQ 4.0
     */
    @Deprecated
    @Internal
    @NotNull
    BindContext bindContext(PreparedStatement stmt);

    /**
     * @deprecated - [#2662] - 3.2.0 - Do not reuse this method. It will be
     *             removed with jOOQ 4.0
     */
    @Deprecated
    int bind(QueryPart part, PreparedStatement stmt);

    // -------------------------------------------------------------------------
    // XXX Attachable and Serializable API
    // -------------------------------------------------------------------------

    /**
     * Attach this <code>DSLContext</code>'s underlying {@link #configuration()}
     * to some attachables.
     */
    void attach(Attachable... attachables);

    /**
     * Attach this <code>DSLContext</code>'s underlying {@link #configuration()}
     * to some attachables.
     */
    void attach(Collection<? extends Attachable> attachables);

    // -------------------------------------------------------------------------
    // XXX Access to the loader API
    // -------------------------------------------------------------------------

    /**
     * Create a new <code>Loader</code> object to load data from a CSV or XML
     * source.
     */
    @NotNull
    @Support
    <R extends Record> LoaderOptionsStep<R> loadInto(Table<R> table);

    // -------------------------------------------------------------------------
    // XXX: Queries
    // -------------------------------------------------------------------------

    /**
     * Wrap a collection of queries.
     *
     * @see DSL#queries(Query...)
     */
    @NotNull
    @Support
    Queries queries(Query... queries);

    /**
     * Wrap a collection of queries.
     *
     * @see DSL#queries(Collection)
     */
    @NotNull
    @Support
    Queries queries(Collection<? extends Query> queries);

    /**
     * Wrap a collection of statements in an anonymous procedural block.
     *
     * @see DSL#begin(Statement...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    Block begin(Statement... statements);

    /**
     * Wrap a collection of statements in an anoymous procedural block.
     *
     * @see DSL#begin(Collection)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    Block begin(Collection<? extends Statement> statements);



























    // -------------------------------------------------------------------------
    // XXX Plain SQL API
    // -------------------------------------------------------------------------

    /**
     * Create a new query holding plain SQL. There must not be any binding
     * variables contained in the SQL.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * String sql = "SET SCHEMA 'abc'";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return A query wrapping the plain SQL
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    RowCountQuery query(SQL sql);

    /**
     * Create a new query holding plain SQL. There must not be any binding
     * variables contained in the SQL.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * String sql = "SET SCHEMA 'abc'";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return A query wrapping the plain SQL
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    RowCountQuery query(String sql);

    /**
     * Create a new query holding plain SQL. There must be as many bind
     * variables contained in the SQL, as passed in the bindings parameter.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * String sql = "SET SCHEMA 'abc'";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return A query wrapping the plain SQL
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    RowCountQuery query(String sql, Object... bindings);

    /**
     * Create a new query holding plain SQL.
     * <p>
     * Unlike {@link #query(String, Object...)}, the SQL passed to this method
     * should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * query("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will render this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return A query wrapping the plain SQL
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    RowCountQuery query(String sql, QueryPart... parts);

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Result<Record> fetch(SQL sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Result<Record> fetch(String sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The results from the executed query. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Result<Record> fetch(String sql, Object... bindings) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetch(String, Object...)}, the SQL passed to this method
     * should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetch("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Result<Record> fetch(String sql, QueryPart... parts) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * The returned {@link Cursor} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Cursor<Record> fetchLazy(SQL sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * The returned {@link Cursor} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Cursor<Record> fetchLazy(String sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * The returned {@link Cursor} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Cursor<Record> fetchLazy(String sql, Object... bindings) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * The returned {@link Cursor} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Unlike {@link #fetchLazy(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchLazy("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Cursor<Record> fetchLazy(String sql, QueryPart... parts) throws DataAccessException;

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(SQL sql);

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(String sql);

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(String sql, Object... bindings);

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * Unlike {@link #fetchLazy(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchLazy("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(String sql, QueryPart... parts);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(Executor executor, SQL sql);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(Executor executor, String sql);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(Executor executor, String sql, Object... bindings);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * Unlike {@link #fetchLazy(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchLazy("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    CompletionStage<Result<Record>> fetchAsync(Executor executor, String sql, QueryPart... parts);

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * The returned {@link Stream} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query. This is never
     *         <code>null</code>, even if the database returns no
     *         {@link ResultSet}
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Stream<Record> fetchStream(SQL sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * The returned {@link Stream} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query. This is never
     *         <code>null</code>, even if the database returns no
     *         {@link ResultSet}
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Stream<Record> fetchStream(String sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * The returned {@link Stream} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The results from the executed query. This is never
     *         <code>null</code>, even if the database returns no
     *         {@link ResultSet}
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Stream<Record> fetchStream(String sql, Object... bindings) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL and "lazily" return the generated
     * result.
     * <p>
     * The returned {@link Stream} holds a reference to the executed
     * {@link PreparedStatement} and the associated {@link ResultSet}. Data can
     * be fetched (or iterated over) lazily, fetching records from the
     * {@link ResultSet} one by one.
     * <p>
     * Unlike {@link #fetchStream(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchLazy("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The results from the executed query. This is never
     *         <code>null</code>, even if the database returns no
     *         {@link ResultSet}
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Stream<Record> fetchStream(String sql, QueryPart... parts) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL, possibly returning several result
     * sets.
     * <p>
     * Example (Sybase ASE):
     * <p>
     * <code><pre>
     * String sql = "sp_help 'my_table'";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Results fetchMany(SQL sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL, possibly returning several result
     * sets.
     * <p>
     * Example (Sybase ASE):
     * <p>
     * <code><pre>
     * String sql = "sp_help 'my_table'";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Results fetchMany(String sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL, possibly returning several result
     * sets.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Sybase ASE):
     * <p>
     * <code><pre>
     * String sql = "sp_help 'my_table'";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The results. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Results fetchMany(String sql, Object... bindings) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL, possibly returning several result
     * sets.
     * <p>
     * Unlike {@link #fetchMany(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchMany("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The results. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Results fetchMany(String sql, QueryPart... parts) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     */
    @Nullable
    @Support
    @PlainSQL
    Record fetchOne(SQL sql) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     */
    @Nullable
    @Support
    @PlainSQL
    Record fetchOne(String sql) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @Nullable
    @Support
    @PlainSQL
    Record fetchOne(String sql, Object... bindings) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetchOne(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchOne("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @Nullable
    @Support
    @PlainSQL
    Record fetchOne(String sql, QueryPart... parts) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Record fetchSingle(SQL sql) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Record fetchSingle(String sql) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Record fetchSingle(String sql, Object... bindings) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetchOne(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchOne("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Record fetchSingle(String sql, QueryPart... parts) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<Record> fetchOptional(SQL sql) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<Record> fetchOptional(String sql) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<Record> fetchOptional(String sql, Object... bindings) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetchOne(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchOne("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<Record> fetchOptional(String sql, QueryPart... parts) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     */
    @Nullable
    @Support
    @PlainSQL
    Object fetchValue(SQL sql) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     */
    @Nullable
    @Support
    @PlainSQL
    Object fetchValue(String sql) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @Nullable
    @Support
    @PlainSQL
    Object fetchValue(String sql, Object... bindings) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetchValue(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchOne("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @Nullable
    @Support
    @PlainSQL
    Object fetchValue(String sql, QueryPart... parts) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The result value from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<?> fetchOptionalValue(SQL sql) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The result value from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<?> fetchOptionalValue(String sql) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<?> fetchOptionalValue(String sql, Object... bindings) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetchValue(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchOne("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    Optional<?> fetchOptionalValue(String sql, QueryPart... parts) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    List<?> fetchValues(SQL sql) throws DataAccessException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    List<?> fetchValues(String sql) throws DataAccessException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    List<?> fetchValues(String sql, Object... bindings) throws DataAccessException, InvalidResultException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #fetchValue(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * fetchOne("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    List<?> fetchValues(String sql, QueryPart... parts) throws DataAccessException, InvalidResultException;

    /**
     * Execute a query holding plain SQL.
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @Support
    @PlainSQL
    int execute(SQL sql) throws DataAccessException;

    /**
     * Execute a query holding plain SQL.
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     */
    @Support
    @PlainSQL
    int execute(String sql) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @Support
    @PlainSQL
    int execute(String sql, Object... bindings) throws DataAccessException;

    /**
     * Execute a new query holding plain SQL.
     * <p>
     * Unlike {@link #execute(String, Object...)}, the SQL passed to this method
     * should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * execute("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will execute this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return The results from the executed query
     * @throws DataAccessException if something went wrong executing the query
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @Support
    @PlainSQL
    int execute(String sql, QueryPart... parts) throws DataAccessException;

    /**
     * Create a new query holding plain SQL.
     * <p>
     * There must not be any bind variables contained in the SQL
     * <p>
     * Use this method, when you want to take advantage of the many ways to
     * fetch results in jOOQ, using {@link ResultQuery}. Some examples:
     * <p>
     * <table border="1">
     * <tr>
     * <td> {@link ResultQuery#fetchLazy()}</td>
     * <td>Open a cursor and fetch records one by one</td>
     * </tr>
     * <tr>
     * <td> {@link ResultQuery#fetchInto(Class)}</td>
     * <td>Fetch records into a custom POJO (optionally annotated with JPA
     * annotations)</td>
     * </tr>
     * <tr>
     * <td> {@link ResultQuery#fetchInto(RecordHandler)}</td>
     * <td>Fetch records into a custom callback (similar to Spring's RowMapper)</td>
     * </tr>
     * </table>
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return An executable query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    ResultQuery<Record> resultQuery(SQL sql);

    /**
     * Create a new query holding plain SQL.
     * <p>
     * There must not be any bind variables contained in the SQL
     * <p>
     * Use this method, when you want to take advantage of the many ways to
     * fetch results in jOOQ, using {@link ResultQuery}. Some examples:
     * <p>
     * <table border="1">
     * <tr>
     * <td> {@link ResultQuery#fetchLazy()}</td>
     * <td>Open a cursor and fetch records one by one</td>
     * </tr>
     * <tr>
     * <td> {@link ResultQuery#fetchInto(Class)}</td>
     * <td>Fetch records into a custom POJO (optionally annotated with JPA
     * annotations)</td>
     * </tr>
     * <tr>
     * <td> {@link ResultQuery#fetchInto(RecordHandler)}</td>
     * <td>Fetch records into a custom callback (similar to Spring's RowMapper)</td>
     * </tr>
     * </table>
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @return An executable query
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    ResultQuery<Record> resultQuery(String sql);

    /**
     * Create a new query holding plain SQL.
     * <p>
     * There must be as many bind variables contained in the SQL, as passed in
     * the bindings parameter
     * <p>
     * Use this method, when you want to take advantage of the many ways to
     * fetch results in jOOQ, using {@link ResultQuery}. Some examples:
     * <p>
     * <table border="1">
     * <tr>
     * <td> {@link ResultQuery#fetchLazy()}</td>
     * <td>Open a cursor and fetch records one by one</td>
     * </tr>
     * <tr>
     * <td> {@link ResultQuery#fetchInto(Class)}</td>
     * <td>Fetch records into a custom POJO (optionally annotated with JPA
     * annotations)</td>
     * </tr>
     * <tr>
     * <td> {@link ResultQuery#fetchInto(RecordHandler)}</td>
     * <td>Fetch records into a custom callback (similar to Spring's RowMapper)</td>
     * </tr>
     * </table>
     * <p>
     * Example (Postgres):
     * <p>
     * <code><pre>
     * String sql = "FETCH ALL IN \"&lt;unnamed cursor 1&gt;\"";</pre></code> Example
     * (SQLite):
     * <p>
     * <code><pre>
     * String sql = "pragma table_info('my_table')";</pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @param sql The SQL
     * @param bindings The bindings
     * @return A query wrapping the plain SQL
     * @see SQL
     * @see DSL#sql(String, Object...)
     */
    @NotNull
    @Support
    @PlainSQL
    ResultQuery<Record> resultQuery(String sql, Object... bindings);

    /**
     * Create a new query holding plain SQL.
     * <p>
     * Unlike {@link #resultQuery(String, Object...)}, the SQL passed to this
     * method should not contain any bind variables. Instead, you can pass
     * {@link QueryPart} objects to the method which will be rendered at indexed
     * locations of your SQL string as such: <code><pre>
     * // The following query
     * resultQuery("select {0}, {1} from {2}", val(1), inline("test"), name("DUAL"));
     *
     * // Will render this SQL by default, using SQLDialect.ORACLE:
     * select ?, 'test' from "DUAL"
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses! One way to escape
     * literals is to use {@link DSL#name(String...)} and similar methods
     *
     * @param sql The SQL clause, containing {numbered placeholders} where query
     *            parts can be injected
     * @param parts The {@link QueryPart} objects that are rendered at the
     *            {numbered placeholder} locations
     * @return A query wrapping the plain SQL
     * @see SQL
     * @see DSL#sql(String, QueryPart...)
     */
    @NotNull
    @Support
    @PlainSQL
    ResultQuery<Record> resultQuery(String sql, QueryPart... parts);

    // -------------------------------------------------------------------------
    // XXX JDBC convenience methods
    // -------------------------------------------------------------------------

    /**
     * Fetch all data from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Result}.
     * <p>
     * After fetching all data, the JDBC ResultSet will be closed.
     * <p>
     * Use {@link #fetchLazy(ResultSet)}, to fetch one <code>Record</code> at a
     * time, instead of load the entire <code>ResultSet</code> into a jOOQ
     * <code>Result</code> at once.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The resulting jOOQ Result. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Result<Record> fetch(ResultSet rs) throws DataAccessException;

    /**
     * Fetch all data from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Result}.
     * <p>
     * After fetching all data, the JDBC ResultSet will be closed.
     * <p>
     * Use {@link #fetchLazy(ResultSet)}, to fetch one <code>Record</code> at a
     * time, instead of load the entire <code>ResultSet</code> into a jOOQ
     * <code>Result</code> at once.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The resulting jOOQ Result. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Result<Record> fetch(ResultSet rs, Field<?>... fields) throws DataAccessException;

    /**
     * Fetch all data from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Result}.
     * <p>
     * After fetching all data, the JDBC ResultSet will be closed.
     * <p>
     * Use {@link #fetchLazy(ResultSet)}, to fetch one <code>Record</code> at a
     * time, instead of load the entire <code>ResultSet</code> into a jOOQ
     * <code>Result</code> at once.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The resulting jOOQ Result. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Result<Record> fetch(ResultSet rs, DataType<?>... types) throws DataAccessException;

    /**
     * Fetch all data from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Result}.
     * <p>
     * After fetching all data, the JDBC ResultSet will be closed.
     * <p>
     * Use {@link #fetchLazy(ResultSet)}, to fetch one <code>Record</code> at a
     * time, instead of load the entire <code>ResultSet</code> into a jOOQ
     * <code>Result</code> at once.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The resulting jOOQ Result. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Result<Record> fetch(ResultSet rs, Class<?>... types) throws DataAccessException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    Record fetchOne(ResultSet rs) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    Record fetchOne(ResultSet rs, Field<?>... fields) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    Record fetchOne(ResultSet rs, DataType<?>... types) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    Record fetchOne(ResultSet rs, Class<?>... types) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Record fetchSingle(ResultSet rs) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Record fetchSingle(ResultSet rs, Field<?>... fields) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Record fetchSingle(ResultSet rs, DataType<?>... types) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Record fetchSingle(ResultSet rs, Class<?>... types) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The resulting jOOQ record
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Optional<Record> fetchOptional(ResultSet rs) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The resulting jOOQ record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Optional<Record> fetchOptional(ResultSet rs, Field<?>... fields) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The resulting jOOQ record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Optional<Record> fetchOptional(ResultSet rs, DataType<?>... types) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and transform it to a jOOQ
     * {@link Record}.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The resulting jOOQ record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    Optional<Record> fetchOptional(ResultSet rs, Class<?>... types) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @Nullable
    @Support
    Object fetchValue(ResultSet rs) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>field</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param field The field to use in the desired output
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @Nullable
    @Support
    <T> T fetchValue(ResultSet rs, Field<T> field) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>type</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param type The data type to use in the desired output
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @Nullable
    @Support
    <T> T fetchValue(ResultSet rs, DataType<T> type) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>type</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param type The data types to use in the desired output
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @Nullable
    @Support
    <T> T fetchValue(ResultSet rs, Class<T> type) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The resulting value
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    Optional<?> fetchOptionalValue(ResultSet rs) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>field</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param field The field to use in the desired output
     * @return The resulting value
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> Optional<T> fetchOptionalValue(ResultSet rs, Field<T> field) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>type</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param type The data type to use in the desired output
     * @return The resulting value
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> Optional<T> fetchOptionalValue(ResultSet rs, DataType<T> type) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a record from a JDBC {@link ResultSet} and return the only
     * contained value.
     * <p>
     * This will internally fetch all records and throw an exception if there
     * was more than one resulting record.
     * <p>
     * The additional <code>type</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param type The data types to use in the desired output
     * @return The resulting value
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> Optional<T> fetchOptionalValue(ResultSet rs, Class<T> type) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch a result from a JDBC {@link ResultSet} and return the only
     * contained column's values.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    List<?> fetchValues(ResultSet rs) throws DataAccessException, InvalidResultException;

    /**
     * Fetch a result from a JDBC {@link ResultSet} and return the only
     * contained column's values.
     * <p>
     * The additional <code>field</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param field The field to use in the desired output
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> List<T> fetchValues(ResultSet rs, Field<T> field) throws DataAccessException, InvalidResultException;

    /**
     * Fetch a result from a JDBC {@link ResultSet} and return the only
     * contained column's values.
     * <p>
     * The additional <code>type</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param type The data type to use in the desired output
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> List<T> fetchValues(ResultSet rs, DataType<T> type) throws DataAccessException, InvalidResultException;

    /**
     * Fetch a result from a JDBC {@link ResultSet} and return the only
     * contained column's values.
     * <p>
     * The additional <code>type</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param type The data types to use in the desired output
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> List<T> fetchValues(ResultSet rs, Class<T> type) throws DataAccessException, InvalidResultException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Cursor}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Cursor<Record> fetchLazy(ResultSet rs) throws DataAccessException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Cursor}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Cursor<Record> fetchLazy(ResultSet rs, Field<?>... fields) throws DataAccessException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Cursor}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Cursor<Record> fetchLazy(ResultSet rs, DataType<?>... types) throws DataAccessException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Cursor}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Cursor<Record> fetchLazy(ResultSet rs, Class<?>... types) throws DataAccessException;

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(ResultSet rs);

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(ResultSet rs, Field<?>... fields);

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(ResultSet rs, DataType<?>... types);

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(ResultSet rs, Class<?>... types);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs, Field<?>... fields);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs, DataType<?>... types);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    CompletionStage<Result<Record>> fetchAsync(Executor executor, ResultSet rs, Class<?>... types);

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Stream}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @return The resulting stream
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Stream<Record> fetchStream(ResultSet rs) throws DataAccessException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Stream}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     * <p>
     * The additional <code>fields</code> argument is used by jOOQ to coerce
     * field names and data types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param fields The fields to use in the desired output
     * @return The resulting stream
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Stream<Record> fetchStream(ResultSet rs, Field<?>... fields) throws DataAccessException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Stream}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The resulting stream
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Stream<Record> fetchStream(ResultSet rs, DataType<?>... types) throws DataAccessException;

    /**
     * Wrap a JDBC {@link ResultSet} into a jOOQ {@link Stream}.
     * <p>
     * Use {@link #fetch(ResultSet)}, to load the entire <code>ResultSet</code>
     * into a jOOQ <code>Result</code> at once.
     * <p>
     * The additional <code>types</code> argument is used by jOOQ to coerce data
     * types to the desired output
     *
     * @param rs The JDBC ResultSet to fetch data from
     * @param types The data types to use in the desired output
     * @return The resulting stream
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Stream<Record> fetchStream(ResultSet rs, Class<?>... types) throws DataAccessException;

    /**
     * Fetch all data from a formatted string.
     * <p>
     * The supplied string is supposed to be formatted in a human-readable way.
     * This is the same as calling <code>fetchFromTXT(string, "{null}")</code>
     *
     * @param string The formatted string
     * @return The transformed result. This will never be <code>null</code>.
     * @see #fetchFromTXT(String, String)
     * @throws DataAccessException If the supplied string does not adhere to the
     *             above format rules.
     */
    @NotNull
    @Support
    Result<Record> fetchFromTXT(String string) throws DataAccessException;

    /**
     * Fetch all data from a formatted string.
     * <p>
     * This method supports parsing results from two types of human-readable
     * formats:
     * <h3>The jOOQ {@link Result#format()}</h3>
     * <p>
     * This format is recognised by the fact that the first line starts with a
     * "plus" sign: <code><pre>
     * +-----+-----+--------------------------+
     * |COL1 |COL2 |COL3 containing whitespace|
     * +-----+-----+--------------------------+
     * |val1 |1    |some text                 |
     * |val2 | 2   | more text                |
     * +-----+-----+--------------------------+
     * </pre></code> This method will decode the above formatted string
     * according to the following rules:
     * <ul>
     * <li>The number of columns is defined by the number of dash groups in the
     * first line. Groups are separated by exactly one "plus" sign</li>
     * <li>The column types are <code>VARCHAR(N)</code> where
     * <code>N = number of dashes per dash group</code></li>
     * <li>The column names are defined by the trimmed text contained in the
     * second row</li>
     * <li>The data is defined by the trimmed text contained in the subsequent
     * rows</li>
     * </ul>
     * <h3>The H2 database test data format</h3>
     * <p>
     * The supplied string is supposed to be formatted in the following,
     * human-readable way: <code><pre>
     * COL1  COL2   COL3 containing whitespace
     * ----- ----   --------------------------
     * val1  1      some text
     * val2   2      more text
     * </pre></code> This method will decode the above formatted string
     * according to the following rules:
     * <ul>
     * <li>The number of columns is defined by the number of dash groups in the
     * second line. Groups are separated by space(s)</li>
     * <li>The column types are <code>VARCHAR(N)</code> where
     * <code>N = number of dashes per dash group</code></li>
     * <li>The column names are defined by the trimmed text contained in the
     * first row</li>
     * <li>The data is defined by the trimmed text contained in the subsequent
     * rows</li>
     * </ul>
     * <h3>Both parsing methods</h3>
     * <p>
     * Both parsing methods make no assumption about the resulting data types.
     * Instead, all data is string-based.
     *
     * @param string The formatted string
     * @param nullLiteral The string literal to be used as <code>null</code>
     *            value.
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If the supplied string does not adhere to the
     *             above format rules.
     */
    @NotNull
    @Support
    Result<Record> fetchFromTXT(String string, String nullLiteral) throws DataAccessException;

    /**
     * Convert an HTML table into a jOOQ {@link Result}.
     * <p>
     * This is the inverse operation of {@link Result#formatHTML()}. It works
     * according to the following parsing rules:
     * <ul>
     * <li>The input is expected to be well-formed XML. XHTML conformance is not
     * required - i.e. unknown elements / attributes, or elements / attributes
     * not specified here, such as <code>&lt;caption&gt;</code>,
     * <code>&lt;thead&gt;</code>, <code>&lt;tbody&gt;</code> are simply ignored.</li>
     * <li>The surrounding <code>&lt;table&gt;</code> element is optional, but it
     * may appear only once</li>
     * <li>A single row containing table headings <code>&lt;th&gt;</code> is
     * allowed. Further rows containing table headings are ignored. Table
     * headings define field names. In the absence of table headings, field
     * names are generated.</li>
     * <li>The first row <code>&lt;tr&gt;</code> specifies the number of columns in
     * the table (regardless if it contains table headings or not). Subsequent
     * rows containing less columns will be padded. Subsequent rows containing
     * more columns will be truncated.</li>
     * <li>Comments are ignored</li>
     * <li>Nested tables are not supported</li>
     * </ul>
     * <p>
     * Ideal input looks like this: <code><pre>
     * &lt;table&gt;
     * &lt;tr&gt;&lt;th&gt;COL1&lt;/th&gt;&lt;th&gt;COL2&lt;/th&gt;&lt;/tr&gt;
     * &lt;tr&gt;&lt;td&gt;1&lt;/td&gt;&lt;td&gt;a&lt;/td&gt;&lt;/tr&gt;
     * &lt;tr&gt;&lt;td&gt;2&lt;/td&gt;&lt;td&gt;b&lt;/td&gt;&lt;/tr&gt;
     * &lt;/table&gt;
     * </code>
     * </pre>
     *
     * @param string The HTML-formatted string.
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If the supplied string does not adhere to the
     *             above format rules.
     */
    @NotNull
    @Support
    Result<Record> fetchFromHTML(String string) throws DataAccessException;

    /**
     * Fetch all data from a CSV string.
     * <p>
     * This is the same as calling <code>fetchFromCSV(string, ',')</code> and
     * the inverse of calling {@link Result#formatCSV()}. The first row of the
     * CSV data is required to hold field name information. Subsequent rows may
     * contain data, which is interpreted as {@link String}. Use the various
     * conversion methods to retrieve other data types from the
     * <code>Result</code>:
     * <ul>
     * <li>{@link Result#getValues(Field, Class)}</li>
     * <li>{@link Result#getValues(int, Class)}</li>
     * <li>{@link Result#getValues(String, Class)}</li>
     * <li>{@link Result#getValues(Field, Converter)}</li>
     * <li>{@link Result#getValues(int, Converter)}</li>
     * <li>{@link Result#getValues(String, Converter)}</li>
     * </ul>
     * <p>
     * Missing values result in <code>null</code>. Empty values result in empty
     * <code>Strings</code>
     *
     * @param string The CSV string
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If anything went wrong parsing the CSV file
     * @see #fetchFromCSV(String, char)
     */
    @NotNull
    @Support
    Result<Record> fetchFromCSV(String string) throws DataAccessException;

    /**
     * Fetch all data from a CSV string.
     * <p>
     * This is inverse of calling {@link Result#formatCSV(char)}. The first row
     * of the CSV data is required to hold field name information. Subsequent
     * rows may contain data, which is interpreted as {@link String}. Use the
     * various conversion methods to retrieve other data types from the
     * <code>Result</code>:
     * <ul>
     * <li>{@link Result#getValues(Field, Class)}</li>
     * <li>{@link Result#getValues(int, Class)}</li>
     * <li>{@link Result#getValues(String, Class)}</li>
     * <li>{@link Result#getValues(Field, Converter)}</li>
     * <li>{@link Result#getValues(int, Converter)}</li>
     * <li>{@link Result#getValues(String, Converter)}</li>
     * </ul>
     * <p>
     * Missing values result in <code>null</code>. Empty values result in empty
     * <code>Strings</code>
     *
     * @param string The CSV string
     * @param delimiter The delimiter to expect between records
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If anything went wrong parsing the CSV file
     * @see #fetchFromCSV(String)
     * @see #fetchFromStringData(List)
     */
    @NotNull
    @Support
    Result<Record> fetchFromCSV(String string, char delimiter) throws DataAccessException;

    /**
     * Fetch all data from a CSV string.
     * <p>
     * This is the same as calling <code>fetchFromCSV(string, ',')</code> and
     * the inverse of calling {@link Result#formatCSV(boolean)}. Rows may
     * contain data, which is interpreted as {@link String}. Use the various
     * conversion methods to retrieve other data types from the
     * <code>Result</code>:
     * <ul>
     * <li>{@link Result#getValues(Field, Class)}</li>
     * <li>{@link Result#getValues(int, Class)}</li>
     * <li>{@link Result#getValues(String, Class)}</li>
     * <li>{@link Result#getValues(Field, Converter)}</li>
     * <li>{@link Result#getValues(int, Converter)}</li>
     * <li>{@link Result#getValues(String, Converter)}</li>
     * </ul>
     * <p>
     * Missing values result in <code>null</code>. Empty values result in empty
     * <code>Strings</code>
     *
     * @param string The CSV string
     * @param header Whether to parse the first line as a CSV header line
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If anything went wrong parsing the CSV file
     * @see #fetchFromCSV(String, char)
     */
    @NotNull
    @Support
    Result<Record> fetchFromCSV(String string, boolean header) throws DataAccessException;

    /**
     * Fetch all data from a CSV string.
     * <p>
     * This is inverse of calling {@link Result#formatCSV(boolean, char)}. Rows
     * may contain data, which are interpreted as {@link String}. Use the
     * various conversion methods to retrieve other data types from the
     * <code>Result</code>:
     * <ul>
     * <li>{@link Result#getValues(Field, Class)}</li>
     * <li>{@link Result#getValues(int, Class)}</li>
     * <li>{@link Result#getValues(String, Class)}</li>
     * <li>{@link Result#getValues(Field, Converter)}</li>
     * <li>{@link Result#getValues(int, Converter)}</li>
     * <li>{@link Result#getValues(String, Converter)}</li>
     * </ul>
     * <p>
     * Missing values result in <code>null</code>. Empty values result in empty
     * <code>Strings</code>
     *
     * @param string The CSV string
     * @param header Whether to parse the first line as a CSV header line
     * @param delimiter The delimiter to expect between records
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If anything went wrong parsing the CSV file
     * @see #fetchFromCSV(String)
     * @see #fetchFromStringData(List)
     */
    @NotNull
    @Support
    Result<Record> fetchFromCSV(String string, boolean header, char delimiter) throws DataAccessException;

    /**
     * Fetch all data from a JSON string.
     * <p>
     * This is the inverse of calling {@link Result#formatJSON()}. Use the
     * various conversion methods to retrieve other data types from the
     * <code>Result</code>:
     * <ul>
     * <li> {@link Result#getValues(Field, Class)}</li>
     * <li> {@link Result#getValues(int, Class)}</li>
     * <li> {@link Result#getValues(String, Class)}</li>
     * <li> {@link Result#getValues(Field, Converter)}</li>
     * <li> {@link Result#getValues(int, Converter)}</li>
     * <li> {@link Result#getValues(String, Converter)}</li>
     * </ul>
     * <p>
     * Missing values result in <code>null</code>. Empty values result in empty
     * <code>Strings</code>
     *
     * @param string The JSON string
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If anything went wrong parsing the JSON file
     */
    @NotNull
    @Support
    Result<Record> fetchFromJSON(String string);

    /**
     * Fetch all data from an XML string.
     * <p>
     * This is the inverse of calling {@link Result#formatXML()}. Use the
     * various conversion methods to retrieve other data types from the
     * <code>Result</code>:
     * <ul>
     * <li> {@link Result#getValues(Field, Class)}</li>
     * <li> {@link Result#getValues(int, Class)}</li>
     * <li> {@link Result#getValues(String, Class)}</li>
     * <li> {@link Result#getValues(Field, Converter)}</li>
     * <li> {@link Result#getValues(int, Converter)}</li>
     * <li> {@link Result#getValues(String, Converter)}</li>
     * </ul>
     * <p>
     * Missing values result in <code>null</code>. Empty values result in empty
     * <code>Strings</code>
     *
     * @param string The XML string
     * @return The transformed result. This will never be <code>null</code>.
     * @throws DataAccessException If anything went wrong parsing the XML file
     */
    @NotNull
    @Support
    Result<Record> fetchFromXML(String string);

    /**
     * Fetch all data from a list of strings.
     * <p>
     * This is used by methods such as
     * <ul>
     * <li> {@link #fetchFromCSV(String)}</li>
     * <li> {@link #fetchFromTXT(String)}</li>
     * </ul>
     * The first element of the argument list should contain column names.
     * Subsequent elements contain actual data. The degree of all arrays
     * contained in the argument should be the same, although this is not a
     * requirement. jOOQ will ignore excess data, and fill missing data with
     * <code>null</code>.
     *
     * @param data The data to be transformed into a <code>Result</code>
     * @return The transformed result. This will never be <code>null</code>.
     * @see #fetchFromStringData(List)
     */
    @NotNull
    @Support
    Result<Record> fetchFromStringData(String[]... data);

    /**
     * Fetch all data from a list of strings.
     * <p>
     * This is used by methods such as
     * <ul>
     * <li> {@link #fetchFromCSV(String)}</li>
     * <li> {@link #fetchFromTXT(String)}</li>
     * </ul>
     * The first element of the argument list should contain column names.
     * Subsequent elements contain actual data. The degree of all arrays
     * contained in the argument should be the same, although this is not a
     * requirement. jOOQ will ignore excess data, and fill missing data with
     * <code>null</code>.
     *
     * @param data The data to be transformed into a <code>Result</code>
     * @return The transformed result. This will never be <code>null</code>.
     */
    @NotNull
    @Support
    Result<Record> fetchFromStringData(List<String[]> data);

    /**
     * Fetch all data from a list of strings.
     * <p>
     * This is used by methods such as
     * <ul>
     * <li>{@link #fetchFromCSV(String)}</li>
     * <li>{@link #fetchFromTXT(String)}</li>
     * </ul>
     * The degree of all arrays contained in the argument should be the same,
     * although this is not a requirement. jOOQ will ignore excess data, and
     * fill missing data with <code>null</code>.
     *
     * @param data The data to be transformed into a <code>Result</code>
     * @param header Whether to interpret the first line as a set of column
     *            names.
     * @return The transformed result. This will never be <code>null</code>.
     */
    @NotNull
    @Support
    Result<Record> fetchFromStringData(List<String[]> data, boolean header);

    // -------------------------------------------------------------------------
    // XXX Global Query factory
    // -------------------------------------------------------------------------

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String)} for strictly non-recursive CTE
     * and {@link #withRecursive(String)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(String alias);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(String alias, String... fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(String alias, Collection<String> fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(Name)} for strictly non-recursive CTE
     * and {@link #withRecursive(Name)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(Name alias);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(Name, Name...)} for strictly non-recursive CTE
     * and {@link #withRecursive(Name, Name...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(Name alias, Name... fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(Name, Name...)} for strictly non-recursive CTE
     * and {@link #withRecursive(Name, Name...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(Name alias, Collection<? extends Name> fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE and
     * {@link #withRecursive(String, String...)} for strictly recursive CTE.
     * <p>
     * This works in a similar way as {@link #with(String, String...)}, except
     * that all column names are produced by a function that receives the CTE's
     * {@link Select} columns as input.
     *
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(String alias, Function<? super Field<?>, ? extends String> fieldNameFunction);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE and
     * {@link #withRecursive(String, String...)} for strictly recursive CTE.
     * <p>
     * This works in a similar way as {@link #with(String, String...)}, except
     * that all column names are produced by a function that receives the CTE's
     * {@link Select} columns and their column indexes as input.
     *
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep with(String alias, BiFunction<? super Field<?>, ? super Integer, ? extends String> fieldNameFunction);



    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep1 with(String alias, String fieldAlias1);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep2 with(String alias, String fieldAlias1, String fieldAlias2);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep3 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep4 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep5 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep6 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep7 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep8 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep9 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep10 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep11 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep12 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep13 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep14 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep15 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep16 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep17 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep18 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep19 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep20 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19, String fieldAlias20);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep21 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19, String fieldAlias20, String fieldAlias21);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep22 with(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19, String fieldAlias20, String fieldAlias21, String fieldAlias22);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep1 with(Name alias, Name fieldAlias1);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep2 with(Name alias, Name fieldAlias1, Name fieldAlias2);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep3 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep4 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep5 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep6 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep7 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep8 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep9 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep10 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep11 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep12 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep13 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep14 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep15 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep16 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep17 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep18 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep19 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep20 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19, Name fieldAlias20);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep21 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19, Name fieldAlias20, Name fieldAlias21);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep22 with(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19, Name fieldAlias20, Name fieldAlias21, Name fieldAlias22);



    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * Reusable {@link CommonTableExpression} types can be constructed through
     * <ul>
     * <li>{@link DSL#name(String...)}</li>
     * <li>{@link Name#fields(String...)}</li>
     * <li>
     * {@link DerivedColumnList#as(Select)}</li>
     * </ul>
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(CommonTableExpression...)} for strictly non-recursive CTE
     * and {@link #withRecursive(CommonTableExpression...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithStep with(CommonTableExpression<?>... tables);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * Reusable {@link CommonTableExpression} types can be constructed through
     * <ul>
     * <li>{@link DSL#name(String...)}</li>
     * <li>{@link Name#fields(String...)}</li>
     * <li>
     * {@link DerivedColumnList#as(Select)}</li>
     * </ul>
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(CommonTableExpression...)} for strictly non-recursive CTE
     * and {@link #withRecursive(CommonTableExpression...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithStep with(Collection<? extends CommonTableExpression<?>> tables);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String)} for strictly non-recursive CTE
     * and {@link #withRecursive(String)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(String alias);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(String alias, String... fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(String alias, Collection<String> fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(Name)} for strictly non-recursive CTE
     * and {@link #withRecursive(Name)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(Name alias);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(Name, Name...)} for strictly non-recursive CTE
     * and {@link #withRecursive(Name, Name...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(Name alias, Name... fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(Name, Name...)} for strictly non-recursive CTE
     * and {@link #withRecursive(Name, Name...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(Name alias, Collection<? extends Name> fieldAliases);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     * <p>
     * This works in a similar way as {@link #with(String, String...)}, except
     * that all column names are produced by a function that receives the CTE's
     * {@link Select} columns as input.
     *
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(String alias, Function<? super Field<?>, ? extends String> fieldNameFunction);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     * <p>
     * This works in a similar way as {@link #with(String, String...)}, except
     * that all column names are produced by a function that receives the CTE's
     * {@link Select} columns and their column indexes as input.
     *
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep withRecursive(String alias, BiFunction<? super Field<?>, ? super Integer, ? extends String> fieldNameFunction);



    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep1 withRecursive(String alias, String fieldAlias1);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep2 withRecursive(String alias, String fieldAlias1, String fieldAlias2);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep3 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep4 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep5 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep6 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep7 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep8 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep9 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep10 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep11 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep12 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep13 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep14 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep15 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep16 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep17 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep18 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep19 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep20 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19, String fieldAlias20);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep21 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19, String fieldAlias20, String fieldAlias21);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep22 withRecursive(String alias, String fieldAlias1, String fieldAlias2, String fieldAlias3, String fieldAlias4, String fieldAlias5, String fieldAlias6, String fieldAlias7, String fieldAlias8, String fieldAlias9, String fieldAlias10, String fieldAlias11, String fieldAlias12, String fieldAlias13, String fieldAlias14, String fieldAlias15, String fieldAlias16, String fieldAlias17, String fieldAlias18, String fieldAlias19, String fieldAlias20, String fieldAlias21, String fieldAlias22);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep1 withRecursive(Name alias, Name fieldAlias1);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep2 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep3 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep4 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep5 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep6 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep7 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep8 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep9 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep10 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep11 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep12 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep13 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep14 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep15 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep16 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep17 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep18 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep19 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep20 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19, Name fieldAlias20);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep21 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19, Name fieldAlias20, Name fieldAlias21);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(String, String...)} for strictly non-recursive CTE
     * and {@link #withRecursive(String, String...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithAsStep22 withRecursive(Name alias, Name fieldAlias1, Name fieldAlias2, Name fieldAlias3, Name fieldAlias4, Name fieldAlias5, Name fieldAlias6, Name fieldAlias7, Name fieldAlias8, Name fieldAlias9, Name fieldAlias10, Name fieldAlias11, Name fieldAlias12, Name fieldAlias13, Name fieldAlias14, Name fieldAlias15, Name fieldAlias16, Name fieldAlias17, Name fieldAlias18, Name fieldAlias19, Name fieldAlias20, Name fieldAlias21, Name fieldAlias22);



    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * Reusable {@link CommonTableExpression} types can be constructed through
     * <ul>
     * <li>{@link DSL#name(String...)}</li>
     * <li>{@link Name#fields(String...)}</li>
     * <li>
     * {@link DerivedColumnList#as(Select)}</li>
     * </ul>
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(CommonTableExpression...)} for strictly non-recursive CTE
     * and {@link #withRecursive(CommonTableExpression...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithStep withRecursive(CommonTableExpression<?>... tables);

    /**
     * Create a <code>WITH</code> clause to supply subsequent
     * <code>SELECT</code>, <code>UPDATE</code>, <code>INSERT</code>,
     * <code>DELETE</code>, and <code>MERGE</code> statements with
     * {@link CommonTableExpression}s.
     * <p>
     * Reusable {@link CommonTableExpression} types can be constructed through
     * <ul>
     * <li>{@link DSL#name(String...)}</li>
     * <li>{@link Name#fields(String...)}</li>
     * <li>
     * {@link DerivedColumnList#as(Select)}</li>
     * </ul>
     * <p>
     * The <code>RECURSIVE</code> keyword may be optional or unsupported in some
     * databases, in case of which it will not be rendered. For optimal database
     * interoperability and readability, however, it is suggested that you use
     * {@link #with(CommonTableExpression...)} for strictly non-recursive CTE
     * and {@link #withRecursive(CommonTableExpression...)} for strictly
     * recursive CTE.
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    WithStep withRecursive(Collection<? extends CommonTableExpression<?>> tables);

    /**
     * Create a new DSL select statement, projecting the known columns from a
     * table.
     * <p>
     * This will project the known columns from the argument table querying
     * {@link Table#fields()}. If no known columns are available (e.g. because
     * the table has been created using {@link DSL#table(String)}), then
     * <code>SELECT *</code> is projected.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * SELECT table.col1, table.col2 FROM table
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record> SelectWhereStep<R> selectFrom(Table<R> table);

    /**
     * Create a new DSL select statement, projecting <code>*</code>.
     * <p>
     * Without knowing any columns from the argument table (see
     * {@link #selectFrom(Table)}), this will project <code>SELECT *</code>.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * </pre></code>
     *
     * @see DSL#table(Name)
     */
    @NotNull
    @Support
    SelectWhereStep<Record> selectFrom(Name table);

    /**
     * Create a new DSL select statement, projecting <code>*</code>.
     * <p>
     * Without knowing any columns from the argument table (see
     * {@link #selectFrom(Table)}), this will project <code>SELECT *</code>.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#table(SQL)
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    SelectWhereStep<Record> selectFrom(SQL sql);

    /**
     * Create a new DSL select statement, projecting <code>*</code>.
     * <p>
     * Without knowing any columns from the argument table (see
     * {@link #selectFrom(Table)}), this will project <code>SELECT *</code>.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#table(String)
     * @see DSL#sql(String)
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    SelectWhereStep<Record> selectFrom(String sql);

    /**
     * Create a new DSL select statement, projecting <code>*</code>.
     * <p>
     * Without knowing any columns from the argument table (see
     * {@link #selectFrom(Table)}), this will project <code>SELECT *</code>.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#table(String, Object...)
     * @see DSL#sql(String, Object...)
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    SelectWhereStep<Record> selectFrom(String sql, Object... bindings);

    /**
     * Create a new DSL select statement, projecting <code>*</code>.
     * <p>
     * Without knowing any columns from the argument table (see
     * {@link #selectFrom(Table)}), this will project <code>SELECT *</code>.
     * <p>
     * Example:
     * <p>
     * <code><pre>
     * SELECT * FROM table
     * </pre></code>
     * <p>
     * <b>NOTE</b>: When inserting plain SQL into jOOQ objects, you must
     * guarantee syntax integrity. You may also create the possibility of
     * malicious SQL injection. Be sure to properly use bind variables and/or
     * escape literals when concatenated into SQL clauses!
     *
     * @see DSL#table(String, QueryPart...)
     * @see DSL#sql(String, QueryPart...)
     * @see SQL
     */
    @NotNull
    @Support
    @PlainSQL
    SelectWhereStep<Record> selectFrom(String sql, QueryPart... parts);

    /**
     * Create a new DSL select statement.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(Collection)} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.select(fields)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     * <p>
     * Note that passing an empty collection conveniently produces
     * <code>SELECT *</code> semantics, i.e. it:
     * <ul>
     * <li>Renders <code>SELECT tab1.col1, tab1.col2, ..., tabN.colN</code> if
     * all columns are known</li>
     * <li>Renders <code>SELECT *</code> if not all columns are known, e.g. when
     * using plain SQL</li>
     * </ul>
     *
     * @see DSL#select(Collection)
     */
    @NotNull
    @Support
    SelectSelectStep<Record> select(Collection<? extends SelectFieldOrAsterisk> fields);

    /**
     * Create a new DSL select statement.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectFieldOrAsterisk...)} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.select(field1, field2)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2)
     *       .execute();
     * </pre></code>
     * <p>
     * Note that passing an empty collection conveniently produces
     * <code>SELECT *</code> semantics, i.e. it:
     * <ul>
     * <li>Renders <code>SELECT tab1.col1, tab1.col2, ..., tabN.colN</code> if
     * all columns are known</li>
     * <li>Renders <code>SELECT *</code> if not all columns are known, e.g. when
     * using plain SQL</li>
     * </ul>
     *
     * @see DSL#select(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    SelectSelectStep<Record> select(SelectFieldOrAsterisk... fields);



    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Field#in(Select)}, {@link Field#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1> SelectSelectStep<Record1<T1>> select(SelectField<T1> field1);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row2#in(Select)}, {@link Row2#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2> SelectSelectStep<Record2<T1, T2>> select(SelectField<T1> field1, SelectField<T2> field2);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row3#in(Select)}, {@link Row3#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3> SelectSelectStep<Record3<T1, T2, T3>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row4#in(Select)}, {@link Row4#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, field4)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4> SelectSelectStep<Record4<T1, T2, T3, T4>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row5#in(Select)}, {@link Row5#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, field4, field5)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5> SelectSelectStep<Record5<T1, T2, T3, T4, T5>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row6#in(Select)}, {@link Row6#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field5, field6)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6> SelectSelectStep<Record6<T1, T2, T3, T4, T5, T6>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row7#in(Select)}, {@link Row7#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field6, field7)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7> SelectSelectStep<Record7<T1, T2, T3, T4, T5, T6, T7>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row8#in(Select)}, {@link Row8#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field7, field8)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8> SelectSelectStep<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row9#in(Select)}, {@link Row9#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field8, field9)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9> SelectSelectStep<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row10#in(Select)}, {@link Row10#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field9, field10)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> SelectSelectStep<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row11#in(Select)}, {@link Row11#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field10, field11)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> SelectSelectStep<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row12#in(Select)}, {@link Row12#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field11, field12)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> SelectSelectStep<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row13#in(Select)}, {@link Row13#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field12, field13)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> SelectSelectStep<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row14#in(Select)}, {@link Row14#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field13, field14)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> SelectSelectStep<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row15#in(Select)}, {@link Row15#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field14, field15)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> SelectSelectStep<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row16#in(Select)}, {@link Row16#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field15, field16)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> SelectSelectStep<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row17#in(Select)}, {@link Row17#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field16, field17)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> SelectSelectStep<Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row18#in(Select)}, {@link Row18#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field17, field18)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> SelectSelectStep<Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row19#in(Select)}, {@link Row19#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field18, field19)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> SelectSelectStep<Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row20#in(Select)}, {@link Row20#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field19, field20)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> SelectSelectStep<Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row21#in(Select)}, {@link Row21#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field20, field21)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> SelectSelectStep<Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20, SelectField<T21> field21);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #select(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row22#in(Select)}, {@link Row22#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#select(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .select(field1, field2, field3, .., field21, field22)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> SelectSelectStep<Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>> select(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20, SelectField<T21> field21, SelectField<T22> field22);



    /**
     * Create a new DSL select statement.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(Collection)} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.selectDistinct(fields)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     * <p>
     * Note that passing an empty collection conveniently produces
     * <code>SELECT DISTINCT *</code> semantics, i.e. it:
     * <ul>
     * <li>Renders <code>SELECT DISTINCT tab1.col1, tab1.col2, ..., tabN.colN</code> if
     * all columns are known</li>
     * <li>Renders <code>SELECT DISTINCT *</code> if not all columns are known, e.g. when
     * using plain SQL</li>
     * </ul>
     *
     * @see DSL#selectDistinct(Collection)
     */
    @NotNull
    @Support
    SelectSelectStep<Record> selectDistinct(Collection<? extends SelectFieldOrAsterisk> fields);

    /**
     * Create a new DSL select statement.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectFieldOrAsterisk...)} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.selectDistinct(field1, field2)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     * <p>
     * Note that passing an empty collection conveniently produces
     * <code>SELECT DISTINCT *</code> semantics, i.e. it:
     * <ul>
     * <li>Renders <code>SELECT DISTINCT tab1.col1, tab1.col2, ..., tabN.colN</code> if
     * all columns are known</li>
     * <li>Renders <code>SELECT DISTINCT *</code> if not all columns are known, e.g. when
     * using plain SQL</li>
     * </ul>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    SelectSelectStep<Record> selectDistinct(SelectFieldOrAsterisk... fields);



    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Field#in(Select)}, {@link Field#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1> SelectSelectStep<Record1<T1>> selectDistinct(SelectField<T1> field1);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row2#in(Select)}, {@link Row2#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2> SelectSelectStep<Record2<T1, T2>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row3#in(Select)}, {@link Row3#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3> SelectSelectStep<Record3<T1, T2, T3>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row4#in(Select)}, {@link Row4#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, field4)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4> SelectSelectStep<Record4<T1, T2, T3, T4>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row5#in(Select)}, {@link Row5#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, field4, field5)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5> SelectSelectStep<Record5<T1, T2, T3, T4, T5>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row6#in(Select)}, {@link Row6#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field5, field6)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6> SelectSelectStep<Record6<T1, T2, T3, T4, T5, T6>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row7#in(Select)}, {@link Row7#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field6, field7)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7> SelectSelectStep<Record7<T1, T2, T3, T4, T5, T6, T7>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row8#in(Select)}, {@link Row8#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field7, field8)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8> SelectSelectStep<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row9#in(Select)}, {@link Row9#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field8, field9)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9> SelectSelectStep<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row10#in(Select)}, {@link Row10#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field9, field10)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> SelectSelectStep<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row11#in(Select)}, {@link Row11#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field10, field11)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> SelectSelectStep<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row12#in(Select)}, {@link Row12#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field11, field12)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> SelectSelectStep<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row13#in(Select)}, {@link Row13#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field12, field13)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> SelectSelectStep<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row14#in(Select)}, {@link Row14#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field13, field14)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> SelectSelectStep<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row15#in(Select)}, {@link Row15#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field14, field15)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> SelectSelectStep<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row16#in(Select)}, {@link Row16#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field15, field16)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> SelectSelectStep<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row17#in(Select)}, {@link Row17#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field16, field17)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> SelectSelectStep<Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row18#in(Select)}, {@link Row18#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field17, field18)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> SelectSelectStep<Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row19#in(Select)}, {@link Row19#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field18, field19)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> SelectSelectStep<Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row20#in(Select)}, {@link Row20#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field19, field20)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> SelectSelectStep<Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row21#in(Select)}, {@link Row21#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field20, field21)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> SelectSelectStep<Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20, SelectField<T21> field21);

    /**
     * Create a new DSL select statement.
     * <p>
     * This is the same as {@link #selectDistinct(SelectFieldOrAsterisk...)}, except that it
     * declares additional record-level typesafety, which is needed by
     * {@link Row22#in(Select)}, {@link Row22#equal(Select)} and other predicate
     * building methods taking subselect arguments.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectDistinct(SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField, SelectField)} instead.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .selectDistinct(field1, field2, field3, .., field21, field22)
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectDistinct(SelectFieldOrAsterisk...)
     * @see #selectDistinct(SelectFieldOrAsterisk...)
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> SelectSelectStep<Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>> selectDistinct(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20, SelectField<T21> field21, SelectField<T22> field22);



    /**
     * Create a new DSL select statement for a constant <code>0</code> literal.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectZero()} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.selectZero()
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#zero()
     * @see DSL#selectZero()
     */
    @NotNull
    @Support
    SelectSelectStep<Record1<Integer>> selectZero();

    /**
     * Create a new DSL select statement for a constant <code>1</code> literal.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectOne()} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.selectOne()
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#one()
     * @see DSL#selectOne()
     */
    @NotNull
    @Support
    SelectSelectStep<Record1<Integer>> selectOne();

    /**
     * Create a new DSL select statement for <code>COUNT(*)</code>.
     * <p>
     * This creates an attached, renderable and executable <code>SELECT</code>
     * statement from this {@link DSLContext}. If you don't need to render or
     * execute this <code>SELECT</code> statement (e.g. because you want to
     * create a subselect), consider using the static
     * {@link DSL#selectCount()} instead.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.selectCount()
     *       .from(table1)
     *       .join(table2).on(field1.equal(field2))
     *       .where(field1.greaterThan(100))
     *       .orderBy(field2);
     * </pre></code>
     *
     * @see DSL#selectCount()
     */
    @NotNull
    @Support
    SelectSelectStep<Record1<Integer>> selectCount();

    /**
     * Create a new {@link SelectQuery}
     */
    @NotNull
    @Support
    SelectQuery<Record> selectQuery();

    /**
     * Create a new {@link SelectQuery}
     *
     * @param table The table to select data from
     * @return The new {@link SelectQuery}
     */
    @NotNull
    @Support
    <R extends Record> SelectQuery<R> selectQuery(TableLike<R> table);

    /**
     * Create a new {@link InsertQuery}
     *
     * @param into The table to insert data into
     * @return The new {@link InsertQuery}
     */
    @NotNull
    @Support
    <R extends Record> InsertQuery<R> insertQuery(Table<R> into);

    /**
     * Create a new DSL insert statement.
     * <p>
     * This type of insert may feel more convenient to some users, as it uses
     * the <code>UPDATE</code> statement's <code>SET a = b</code> syntax.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.insertInto(table)
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .newRecord()
     *       .set(field1, value3)
     *       .set(field2, value4)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record> InsertSetStep<R> insertInto(Table<R> into);



    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1)
     *       .values(field1)
     *       .values(field1)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1> InsertValuesStep1<R, T1> insertInto(Table<R> into, Field<T1> field1);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2)
     *       .values(field1, field2)
     *       .values(field1, field2)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2> InsertValuesStep2<R, T1, T2> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3)
     *       .values(field1, field2, field3)
     *       .values(field1, field2, field3)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3> InsertValuesStep3<R, T1, T2, T3> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, field4)
     *       .values(field1, field2, field3, field4)
     *       .values(field1, field2, field3, field4)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4> InsertValuesStep4<R, T1, T2, T3, T4> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, field4, field5)
     *       .values(field1, field2, field3, field4, field5)
     *       .values(field1, field2, field3, field4, field5)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5> InsertValuesStep5<R, T1, T2, T3, T4, T5> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field5, field6)
     *       .values(valueA1, valueA2, valueA3, .., valueA5, valueA6)
     *       .values(valueB1, valueB2, valueB3, .., valueB5, valueB6)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6> InsertValuesStep6<R, T1, T2, T3, T4, T5, T6> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field6, field7)
     *       .values(valueA1, valueA2, valueA3, .., valueA6, valueA7)
     *       .values(valueB1, valueB2, valueB3, .., valueB6, valueB7)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7> InsertValuesStep7<R, T1, T2, T3, T4, T5, T6, T7> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field7, field8)
     *       .values(valueA1, valueA2, valueA3, .., valueA7, valueA8)
     *       .values(valueB1, valueB2, valueB3, .., valueB7, valueB8)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8> InsertValuesStep8<R, T1, T2, T3, T4, T5, T6, T7, T8> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field8, field9)
     *       .values(valueA1, valueA2, valueA3, .., valueA8, valueA9)
     *       .values(valueB1, valueB2, valueB3, .., valueB8, valueB9)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9> InsertValuesStep9<R, T1, T2, T3, T4, T5, T6, T7, T8, T9> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field9, field10)
     *       .values(valueA1, valueA2, valueA3, .., valueA9, valueA10)
     *       .values(valueB1, valueB2, valueB3, .., valueB9, valueB10)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> InsertValuesStep10<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field10, field11)
     *       .values(valueA1, valueA2, valueA3, .., valueA10, valueA11)
     *       .values(valueB1, valueB2, valueB3, .., valueB10, valueB11)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> InsertValuesStep11<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field11, field12)
     *       .values(valueA1, valueA2, valueA3, .., valueA11, valueA12)
     *       .values(valueB1, valueB2, valueB3, .., valueB11, valueB12)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> InsertValuesStep12<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field12, field13)
     *       .values(valueA1, valueA2, valueA3, .., valueA12, valueA13)
     *       .values(valueB1, valueB2, valueB3, .., valueB12, valueB13)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> InsertValuesStep13<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field13, field14)
     *       .values(valueA1, valueA2, valueA3, .., valueA13, valueA14)
     *       .values(valueB1, valueB2, valueB3, .., valueB13, valueB14)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> InsertValuesStep14<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field14, field15)
     *       .values(valueA1, valueA2, valueA3, .., valueA14, valueA15)
     *       .values(valueB1, valueB2, valueB3, .., valueB14, valueB15)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> InsertValuesStep15<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field15, field16)
     *       .values(valueA1, valueA2, valueA3, .., valueA15, valueA16)
     *       .values(valueB1, valueB2, valueB3, .., valueB15, valueB16)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> InsertValuesStep16<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field16, field17)
     *       .values(valueA1, valueA2, valueA3, .., valueA16, valueA17)
     *       .values(valueB1, valueB2, valueB3, .., valueB16, valueB17)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> InsertValuesStep17<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field17, field18)
     *       .values(valueA1, valueA2, valueA3, .., valueA17, valueA18)
     *       .values(valueB1, valueB2, valueB3, .., valueB17, valueB18)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> InsertValuesStep18<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field18, field19)
     *       .values(valueA1, valueA2, valueA3, .., valueA18, valueA19)
     *       .values(valueB1, valueB2, valueB3, .., valueB18, valueB19)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> InsertValuesStep19<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field19, field20)
     *       .values(valueA1, valueA2, valueA3, .., valueA19, valueA20)
     *       .values(valueB1, valueB2, valueB3, .., valueB19, valueB20)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> InsertValuesStep20<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field20, field21)
     *       .values(valueA1, valueA2, valueA3, .., valueA20, valueA21)
     *       .values(valueB1, valueB2, valueB3, .., valueB20, valueB21)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> InsertValuesStep21<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * using(configuration)
     *       .insertInto(table, field1, field2, field3, .., field21, field22)
     *       .values(valueA1, valueA2, valueA3, .., valueA21, valueA22)
     *       .values(valueB1, valueB2, valueB3, .., valueB21, valueB22)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> InsertValuesStep22<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> insertInto(Table<R> into, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21, Field<T22> field22);



    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.insertInto(table, field1, field2)
     *       .values(value1, value2)
     *       .values(value3, value4)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record> InsertValuesStepN<R> insertInto(Table<R> into, Field<?>... fields);

    /**
     * Create a new DSL insert statement.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.insertInto(table, field1, field2)
     *       .values(value1, value2)
     *       .values(value3, value4)
     *       .onDuplicateKeyUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record> InsertValuesStepN<R> insertInto(Table<R> into, Collection<? extends Field<?>> fields);

    /**
     * Create a new {@link UpdateQuery}
     *
     * @param table The table to update data into
     * @return The new {@link UpdateQuery}
     */
    @NotNull
    @Support
    <R extends Record> UpdateQuery<R> updateQuery(Table<R> table);

    /**
     * Create a new DSL update statement.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.update(table)
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .where(field1.greaterThan(100))
     *       .execute();
     * </pre></code>
     * <p>
     * Note that some databases support table expressions more complex than
     * simple table references. In MySQL, for instance, you can write
     * <code><pre>
     * create.update(t1.join(t2).on(t1.id.eq(t2.id)))
     *       .set(t1.value, value1)
     *       .set(t2.value, value2)
     *       .where(t1.id.eq(10))
     *       .execute();
     * </pre></code>
     */
    @NotNull
    @Support
    <R extends Record> UpdateSetFirstStep<R> update(Table<R> table);

    /**
     * Create a new DSL SQL standard MERGE statement.
     * <p>
     * This statement is available from DSL syntax only. It is known to be
     * supported in some way by any of these dialects:
     * <table border="1">
     * <tr>
     * <th>dialect</th>
     * <th>support type</th>
     * <th>documentation</th>
     * </tr>
     * <tr>
     * <td>DB2</td>
     * <td>SQL:2008 standard and major enhancements</td>
     * <td><a href=
     * "http://publib.boulder.ibm.com/infocenter/db2luw/v9/index.jsp?topic=/com.ibm.db2.udb.admin.doc/doc/r0010873.htm"
     * >http://publib.boulder.ibm.com/infocenter/db2luw/v9/index.jsp?topic=/com.
     * ibm.db2.udb.admin.doc/doc/r0010873.htm</a></td>
     * </tr>
     * <tr>
     * <td>HSQLDB</td>
     * <td>SQL:2008 standard</td>
     * <td><a
     * href="http://hsqldb.org/doc/2.0/guide/dataaccess-chapt.html#N129BA"
     * >http://hsqldb.org/doc/2.0/guide/dataaccess-chapt.html#N129BA</a></td>
     * </tr>
     * <tr>
     * <td>Oracle</td>
     * <td>SQL:2008 standard and minor enhancements</td>
     * <td><a href=
     * "http://download.oracle.com/docs/cd/B28359_01/server.111/b28286/statements_9016.htm"
     * >http://download.oracle.com/docs/cd/B28359_01/server.111/b28286/
     * statements_9016.htm</a></td>
     * </tr>
     * <tr>
     * <td>SQL Server</td>
     * <td>Similar to SQL:2008 standard with some major enhancements</td>
     * <td><a href= "http://msdn.microsoft.com/de-de/library/bb510625.aspx"
     * >http://msdn.microsoft.com/de-de/library/bb510625.aspx</a></td>
     * </tr>
     * <tr>
     * <td>Sybase</td>
     * <td>Similar to SQL:2008 standard with some major enhancements</td>
     * <td><a href=
     * "http://dcx.sybase.com/1100/en/dbreference_en11/merge-statement.html"
     * >http://dcx.sybase.com/1100/en/dbreference_en11/merge-statement.html</a></td>
     * </tr>
     * </table>
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.mergeInto(table)
     *       .using(select)
     *       .on(condition)
     *       .whenMatchedThenUpdate()
     *       .set(field1, value1)
     *       .set(field2, value2)
     *       .whenNotMatchedThenInsert(field1, field2)
     *       .values(value1, value2)
     *       .execute();
     * </pre></code>
     * <p>
     * Note: Using this method, you can also create an H2-specific MERGE
     * statement without field specification. See also
     * {@link #mergeInto(Table, Field...)}
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record> MergeUsingStep<R> mergeInto(Table<R> table);



    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1> MergeKeyStep1<R, T1> mergeInto(Table<R> table, Field<T1> field1);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2> MergeKeyStep2<R, T1, T2> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3> MergeKeyStep3<R, T1, T2, T3> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4> MergeKeyStep4<R, T1, T2, T3, T4> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5> MergeKeyStep5<R, T1, T2, T3, T4, T5> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6> MergeKeyStep6<R, T1, T2, T3, T4, T5, T6> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7> MergeKeyStep7<R, T1, T2, T3, T4, T5, T6, T7> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8> MergeKeyStep8<R, T1, T2, T3, T4, T5, T6, T7, T8> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9> MergeKeyStep9<R, T1, T2, T3, T4, T5, T6, T7, T8, T9> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> MergeKeyStep10<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> MergeKeyStep11<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> MergeKeyStep12<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> MergeKeyStep13<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> MergeKeyStep14<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> MergeKeyStep15<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> MergeKeyStep16<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> MergeKeyStep17<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> MergeKeyStep18<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> MergeKeyStep19<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> MergeKeyStep20<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> MergeKeyStep21<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21);

    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> MergeKeyStep22<R, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> mergeInto(Table<R> table, Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21, Field<T22> field22);



    /**
     * Create a new DSL UPSERT statement ({@link SQLDialect#H2}
     * <code>MERGE</code>) or {@link SQLDialect#HANA} <code>UPSERT</code>).
     * <p>
     * This statement is available from DSL syntax only. It is known to be
     * supported in some way by any of these dialects:
     * <table border="1">
     * <tr>
     * <td>H2</td>
     * <td>H2 natively supports this special syntax</td>
     * <td><a href="http://www.h2database.com/html/grammar.html#merge"
     * >http://www.h2database.com/html/grammar.html#merge</a></td>
     * </tr>
     * <tr>
     * <td>HANA</td>
     * <td>HANA natively supports this syntax</td>
     * <td><a href="http://help.sap.com/saphelp_hanaplatform/helpdata/en/20/fc06a7751910149892c0d09be21a38/content.htm">http://help.sap.com/saphelp_hanaplatform/helpdata/en/20/fc06a7751910149892c0d09be21a38/content.htm</a></td>
     * </tr>
     * <tr>
     * <td>PostgreSQL</td>
     * <td>This database can emulate the H2-specific MERGE statement via
     * <code>INSERT .. ON CONFLICT DO UPDATE</code></td>
     * <td><a href="http://www.postgresql.org/docs/9.5/static/sql-insert.html">http://www.postgresql.org/docs/9.5/static/sql-insert.html</a></td>
     * </tr>
     * <tr>
     * <td>DB2, HSQLDB, Oracle, SQL Server, Sybase SQL Anywhere</td>
     * <td>These databases can emulate the H2-specific MERGE statement using a
     * standard SQL MERGE statement, without restrictions</td>
     * <td>See {@link #mergeInto(Table)} for the standard MERGE statement</td>
     * </tr>
     * </table>
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record> MergeKeyStepN<R> mergeInto(Table<R> table, Field<?>... fields);

    /**
     * Create a new DSL merge statement (H2-specific syntax).
     *
     * @see #mergeInto(Table, Field...)
     * @deprecated - [#10045] - 3.14.0 - Use the standard SQL MERGE API instead, via {@link #mergeInto(Table)}
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    <R extends Record> MergeKeyStepN<R> mergeInto(Table<R> table, Collection<? extends Field<?>> fields);

    /**
     * Create a new {@link DeleteQuery}
     *
     * @param table The table to delete data from
     * @return The new {@link DeleteQuery}
     */
    @NotNull
    @Support
    <R extends Record> DeleteQuery<R> deleteQuery(Table<R> table);

    /**
     * Create a new DSL delete statement.
     * <p>
     * Example: <code><pre>
     * DSLContext create = DSL.using(configuration);
     *
     * create.deleteFrom(table)
     *       .where(field1.greaterThan(100))
     *       .execute();
     * </pre></code>
     * <p>
     * Some but not all databases support aliased tables in delete statements.
     */
    @NotNull
    @Support
    <R extends Record> DeleteUsingStep<R> deleteFrom(Table<R> table);

    /**
     * Create a new DSL delete statement.
     * <p>
     * This is an alias for {@link #deleteFrom(Table)}
     */
    @NotNull
    @Support
    <R extends Record> DeleteUsingStep<R> delete(Table<R> table);

    // -------------------------------------------------------------------------
    // XXX Batch query execution
    // -------------------------------------------------------------------------

    /**
     * Run a <code>BatchedRunnable</code> on a {@link BatchedConnection},
     * delaying execution as long as possible before batching.
     *
     * @see BatchedConnection BatchedConnection for details.
     */
    void batched(BatchedRunnable runnable);

    /**
     * Run a <code>BatchedRunnable</code> on a {@link BatchedConnection},
     * delaying execution as long as possible before batching.
     *
     * @see BatchedConnection BatchedConnection for details.
     */
    <T> T batchedResult(BatchedCallable<T> callable);

    /**
     * Create a batch statement to execute a set of queries in batch mode
     * (without bind values).
     * <p>
     * This essentially runs the following logic: <code><pre>
     * Statement s = connection.createStatement();
     *
     * for (Query query : queries) {
     *     s.addBatch(query.getSQL(true));
     * }
     *
     * s.execute();
     * </pre></code>
     *
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batch(Query... queries);

    /**
     * Create a batch statement to execute a set of queries in batch mode
     * (without bind values).
     * <p>
     * This essentially runs the following logic: <code><pre>
     * Statement s = connection.createStatement();
     *
     * for (Query query : queries) {
     *     s.addBatch(query.getSQL(true));
     * }
     *
     * s.execute();
     * </pre></code>
     *
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batch(Queries queries);

    /**
     * Create a batch statement to execute a set of queries in batch mode
     * (without bind values).
     * <p>
     * This is a convenience method for calling
     * <code><pre>batch(query(queries[0]), query(queries[1]), ...)</pre></code>.
     *
     * @see #query(String)
     * @see #batch(Query...)
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    @PlainSQL
    Batch batch(String... queries);

    /**
     * Create a batch statement to execute a set of queries in batch mode
     * (without bind values).
     * <p>
     * This essentially runs the following logic: <code><pre>
     * Statement s = connection.createStatement();
     *
     * for (Query query : queries) {
     *     s.addBatch(query.getSQL(true));
     * }
     *
     * s.execute();
     * </pre></code>
     *
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batch(Collection<? extends Query> queries);

    /**
     * Create a batch statement to execute a set of queries in batch mode (with
     * bind values).
     * <p>
     * When running <code><pre>
     * create.batch(query)
     *       .bind(valueA1, valueA2)
     *       .bind(valueB1, valueB2)
     *       .execute();
     * </pre></code>
     * <p>
     * This essentially runs the following logic: <code><pre>
     * Statement s = connection.prepareStatement(query.getSQL(false));
     *
     * for (Object[] bindValues : allBindValues) {
     *     for (Object bindValue : bindValues) {
     *         s.setXXX(bindValue);
     *     }
     *
     *     s.addBatch();
     * }
     *
     * s.execute();
     * </pre></code>
     * <p>
     * Note: bind values will be inlined to a static batch query as in
     * {@link #batch(Query...)}, if you choose to execute queries with
     * <code>{@link Settings#getStatementType()} == {@link StatementType#STATIC_STATEMENT}</code>
     *
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    BatchBindStep batch(Query query);

    /**
     * Create a batch statement to execute a set of queries in batch mode (with
     * bind values).
     * <p>
     * This is a convenience method for calling
     * <code><pre>batch(query(sql))</pre></code>.
     *
     * @see #query(String)
     * @see #batch(Query)
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    @PlainSQL
    BatchBindStep batch(String sql);

    /**
     * Create a batch statement to execute a set of queries in batch mode (with
     * bind values).
     * <p>
     * This is a convenience method for calling {@link #batch(Query)} and then
     * binding values one by one using {@link BatchBindStep#bind(Object...)}
     * <p>
     * Note: bind values will be inlined to a static batch query as in
     * {@link #batch(Query...)}, if you choose to execute queries with
     * <code>{@link Settings#getStatementType()} == {@link StatementType#STATIC_STATEMENT}</code>
     *
     * @see #batch(Query)
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batch(Query query, Object[]... bindings);

    /**
     * Create a batch statement to execute a set of queries in batch mode (with
     * bind values).
     * <p>
     * This is a convenience method for calling
     * <code><pre>batch(query(sql), bindings)</pre></code>.
     *
     * @see #query(String)
     * @see #batch(Query, Object[][])
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    @PlainSQL
    Batch batch(String sql, Object[]... bindings);

    /**
     * Create a batch statement to execute a set of <code>INSERT</code> and
     * <code>UPDATE</code> queries in batch mode (with bind values) according to
     * {@link UpdatableRecord#store()} semantics.
     * <p>
     * This batch operation can be executed in two modes:
     * <p>
     * <h5>With
     * <code>{@link Settings#getStatementType()} == {@link StatementType#PREPARED_STATEMENT}</code>
     * (the default)</h5>
     * <p>
     * In this mode, record order is preserved as much as possible, as long as
     * two subsequent records generate the same SQL (with bind variables). The
     * number of executed batch operations corresponds to
     * <code>[number of distinct rendered SQL statements]</code>. In the worst
     * case, this corresponds to the number of total records.
     * <p>
     * The record type order is preserved in the way they are passed to this
     * method. This is an example of how statements will be ordered: <code><pre>
     * // Let's assume, odd numbers result in INSERTs and even numbers in UPDATES
     * // Let's also assume a[n] are all of the same type, just as b[n], c[n]...
     * int[] result = create.batchStore(a1, a2, a3, b1, a4, c1, b3, a5)
     *                      .execute();
     * </pre></code> The above results in <code>result.length == 8</code> and
     * the following 4 separate batch statements:
     * <ol>
     * <li>INSERT a1, a3, a5</li>
     * <li>UPDATE a2, a4</li>
     * <li>INSERT b1, b3</li>
     * <li>INSERT c1</li>
     * </ol>
     * <p>
     * <h5>With
     * <code>{@link Settings#getStatementType()} == {@link StatementType#STATIC_STATEMENT}</code>
     * </h5>
     * <p>
     * This mode may be better for large and complex batch store operations, as
     * the order of records is preserved entirely, and jOOQ can guarantee that
     * only a single batch statement is serialised to the database.
     * <p>
     * <h5>A note on MERGE / UPSERT semantics</h5>
     * <p>
     * This method (just like {@link UpdatableRecord#store()}) does not
     * implement the semantics of an actual <code>UPSERT</code> or
     * <code>MERGE</code> statement, which delegates the decision of whether to
     * <code>INSERT</code> or <code>UPDATE</code> a record to the database. The
     * decision is made by the client (jOOQ) depending on whether each
     * individual record has been fetched from the database prior to storing it.
     *
     * @see UpdatableRecord#store()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchStore(UpdatableRecord<?>... records);

    /**
     * Create a batch statement to execute a set of <code>INSERT</code> and
     * <code>UPDATE</code> queries in batch mode (with bind values) according to
     * {@link UpdatableRecord#store()} semantics.
     *
     * @see #batchStore(UpdatableRecord...)
     * @see UpdatableRecord#store()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchStore(Collection<? extends UpdatableRecord<?>> records);

    /**
     * Create a batch statement to execute a set of <code>INSERT</code> queries
     * in batch mode (with bind values) according to
     * {@link TableRecord#insert()} semantics.
     *
     * @see #batchStore(UpdatableRecord...)
     * @see TableRecord#insert()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchInsert(TableRecord<?>... records);

    /**
     * Create a batch statement to execute a set of <code>INSERT</code> queries
     * in batch mode (with bind values) according to
     * {@link TableRecord#insert()} semantics.
     *
     * @see #batchStore(UpdatableRecord...)
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchInsert(Collection<? extends TableRecord<?>> records);

    /**
     * Create a batch statement to execute a set of <code>UPDATE</code> queries
     * in batch mode (with bind values) according to
     * {@link UpdatableRecord#update()} semantics.
     *
     * @see #batchStore(UpdatableRecord...)
     * @see UpdatableRecord#update()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchUpdate(UpdatableRecord<?>... records);

    /**
     * Create a batch statement to execute a set of <code>UPDATE</code> queries
     * in batch mode (with bind values) according to
     * {@link UpdatableRecord#update()} semantics.
     *
     * @see #batchStore(UpdatableRecord...)
     * @see UpdatableRecord#update()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchUpdate(Collection<? extends UpdatableRecord<?>> records);

    /**
     * Create a batch statement to execute a set of <code>MERGE</code> queries
     * in batch mode (with bind values) according to
     * {@link UpdatableRecord#merge()} semantics.
     *
     * @see UpdatableRecord#merge()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchMerge(UpdatableRecord<?>... records);

    /**
     * Create a batch statement to execute a set of <code>MERGE</code> queries
     * in batch mode (with bind values) according to
     * {@link UpdatableRecord#merge()} semantics.
     *
     * @see UpdatableRecord#merge()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchMerge(Collection<? extends UpdatableRecord<?>> records);

    /**
     * Create a batch statement to execute a set of <code>DELETE</code> queries
     * in batch mode (with bind values) according to
     * {@link UpdatableRecord#delete()} sematics.
     * <p>
     * This batch operation can be executed in two modes:
     * <p>
     * <h5>With
     * <code>{@link Settings#getStatementType()} == {@link StatementType#PREPARED_STATEMENT}</code>
     * (the default)</h5>
     * <p>
     * In this mode, record order is preserved as much as possible, as long as
     * two subsequent records generate the same SQL (with bind variables). The
     * number of executed batch operations corresponds to
     * <code>[number of distinct rendered SQL statements]</code>. In the worst
     * case, this corresponds to the number of total records.
     * <p>
     * The record type order is preserved in the way they are passed to this
     * method. This is an example of how statements will be ordered: <code><pre>
     * // Let's assume a[n] are all of the same type, just as b[n], c[n]...
     * int[] result = create.batchDelete(a1, a2, a3, b1, a4, c1, c2, a5)
     *                      .execute();
     * </pre></code> The above results in <code>result.length == 8</code> and
     * the following 5 separate batch statements:
     * <ol>
     * <li>DELETE a1, a2, a3</li>
     * <li>DELETE b1</li>
     * <li>DELETE a4</li>
     * <li>DELETE c1, c2</li>
     * <li>DELETE a5</li>
     * </ol>
     * <p>
     * <h5>With
     * <code>{@link Settings#getStatementType()} == {@link StatementType#STATIC_STATEMENT}</code>
     * </h5>
     * <p>
     * This mode may be better for large and complex batch delete operations, as
     * the order of records is preserved entirely, and jOOQ can guarantee that
     * only a single batch statement is serialised to the database.
     *
     * @see UpdatableRecord#delete()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchDelete(UpdatableRecord<?>... records);

    /**
     * Create a batch statement to execute a set of <code>DELETE</code> queries
     * in batch mode (with bind values) according to
     * {@link UpdatableRecord#delete()} sematics.
     *
     * @see #batchDelete(UpdatableRecord...)
     * @see UpdatableRecord#delete()
     * @see java.sql.Statement#executeBatch()
     */
    @NotNull
    @Support
    Batch batchDelete(Collection<? extends UpdatableRecord<?>> records);

    // -------------------------------------------------------------------------
    // XXX DDL Statements from existing meta data
    // -------------------------------------------------------------------------

    /**
     * Convenience method for {@link Meta#ddl()}.
     *
     * @see #meta(Catalog...)
     * @see Meta#ddl()
     */
    @NotNull
    Queries ddl(Catalog catalog);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Catalog...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Catalog schema, DDLExportConfiguration configuration);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Catalog...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Catalog schema, DDLFlag... flags);

    /**
     * Convenience method for {@link Meta#ddl()}.
     *
     * @see #meta(Schema...)
     * @see Meta#ddl()
     */
    @NotNull
    Queries ddl(Schema schema);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Schema...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Schema schema, DDLExportConfiguration configuration);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Schema...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Schema schema, DDLFlag... flags);

    /**
     * Convenience method for {@link Meta#ddl()}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl()
     */
    @NotNull
    Queries ddl(Table<?> table);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Table<?> table, DDLExportConfiguration configuration);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Table<?> table, DDLFlag... flags);

    /**
     * Convenience method for {@link Meta#ddl()}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl()
     */
    @NotNull
    Queries ddl(Table<?>... tables);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Table<?>[] tables, DDLExportConfiguration configuration);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Table<?>[] tables, DDLFlag... flags);

    /**
     * Convenience method for {@link Meta#ddl()}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl()
     */
    @NotNull
    Queries ddl(Collection<? extends Table<?>> tables);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Collection<? extends Table<?>> tables, DDLFlag... flags);

    /**
     * Convenience method for {@link Meta#ddl(DDLExportConfiguration)}.
     *
     * @see #meta(Table...)
     * @see Meta#ddl(DDLExportConfiguration)
     */
    @NotNull
    Queries ddl(Collection<? extends Table<?>> tables, DDLExportConfiguration configuration);

    // -------------------------------------------------------------------------
    // XXX DDL Statements
    // -------------------------------------------------------------------------



    /**
     * The <code>ALTER DATABASE</code> statement.
     *
     * @see DSL#alterDatabase(String)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterDatabaseStep alterDatabase(@Stringly.Name String database);

    /**
     * The <code>ALTER DATABASE</code> statement.
     *
     * @see DSL#alterDatabase(Name)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterDatabaseStep alterDatabase(Name database);

    /**
     * The <code>ALTER DATABASE</code> statement.
     *
     * @see DSL#alterDatabase(Catalog)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterDatabaseStep alterDatabase(Catalog database);

    /**
     * The <code>ALTER DATABASE IF EXISTS</code> statement.
     *
     * @see DSL#alterDatabaseIfExists(String)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterDatabaseStep alterDatabaseIfExists(@Stringly.Name String database);

    /**
     * The <code>ALTER DATABASE IF EXISTS</code> statement.
     *
     * @see DSL#alterDatabaseIfExists(Name)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterDatabaseStep alterDatabaseIfExists(Name database);

    /**
     * The <code>ALTER DATABASE IF EXISTS</code> statement.
     *
     * @see DSL#alterDatabaseIfExists(Catalog)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterDatabaseStep alterDatabaseIfExists(Catalog database);

    /**
     * The <code>ALTER DOMAIN</code> statement.
     *
     * @see DSL#alterDomain(String)
     */
    @NotNull
    @Support({ FIREBIRD, HSQLDB, POSTGRES })
    <T> AlterDomainStep<T> alterDomain(@Stringly.Name String domain);

    /**
     * The <code>ALTER DOMAIN</code> statement.
     *
     * @see DSL#alterDomain(Name)
     */
    @NotNull
    @Support({ FIREBIRD, HSQLDB, POSTGRES })
    <T> AlterDomainStep<T> alterDomain(Name domain);

    /**
     * The <code>ALTER DOMAIN</code> statement.
     *
     * @see DSL#alterDomain(Domain)
     */
    @NotNull
    @Support({ FIREBIRD, HSQLDB, POSTGRES })
    <T> AlterDomainStep<T> alterDomain(Domain<T> domain);

    /**
     * The <code>ALTER DOMAIN IF EXISTS</code> statement.
     *
     * @see DSL#alterDomainIfExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, HSQLDB, POSTGRES })
    <T> AlterDomainStep<T> alterDomainIfExists(@Stringly.Name String domain);

    /**
     * The <code>ALTER DOMAIN IF EXISTS</code> statement.
     *
     * @see DSL#alterDomainIfExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, HSQLDB, POSTGRES })
    <T> AlterDomainStep<T> alterDomainIfExists(Name domain);

    /**
     * The <code>ALTER DOMAIN IF EXISTS</code> statement.
     *
     * @see DSL#alterDomainIfExists(Domain)
     */
    @NotNull
    @Support({ FIREBIRD, HSQLDB, POSTGRES })
    <T> AlterDomainStep<T> alterDomainIfExists(Domain<T> domain);

    /**
     * The <code>ALTER INDEX</code> statement.
     *
     * @see DSL#alterIndex(String)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    AlterIndexOnStep alterIndex(@Stringly.Name String index);

    /**
     * The <code>ALTER INDEX</code> statement.
     *
     * @see DSL#alterIndex(Name)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    AlterIndexOnStep alterIndex(Name index);

    /**
     * The <code>ALTER INDEX</code> statement.
     *
     * @see DSL#alterIndex(Index)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    AlterIndexOnStep alterIndex(Index index);

    /**
     * The <code>ALTER INDEX IF EXISTS</code> statement.
     *
     * @see DSL#alterIndexIfExists(String)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    AlterIndexOnStep alterIndexIfExists(@Stringly.Name String index);

    /**
     * The <code>ALTER INDEX IF EXISTS</code> statement.
     *
     * @see DSL#alterIndexIfExists(Name)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    AlterIndexOnStep alterIndexIfExists(Name index);

    /**
     * The <code>ALTER INDEX IF EXISTS</code> statement.
     *
     * @see DSL#alterIndexIfExists(Index)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    AlterIndexOnStep alterIndexIfExists(Index index);

    /**
     * The <code>ALTER SCHEMA</code> statement.
     *
     * @see DSL#alterSchema(String)
     */
    @NotNull
    @Support({ H2, HSQLDB, POSTGRES })
    AlterSchemaStep alterSchema(@Stringly.Name String schema);

    /**
     * The <code>ALTER SCHEMA</code> statement.
     *
     * @see DSL#alterSchema(Name)
     */
    @NotNull
    @Support({ H2, HSQLDB, POSTGRES })
    AlterSchemaStep alterSchema(Name schema);

    /**
     * The <code>ALTER SCHEMA</code> statement.
     *
     * @see DSL#alterSchema(Schema)
     */
    @NotNull
    @Support({ H2, HSQLDB, POSTGRES })
    AlterSchemaStep alterSchema(Schema schema);

    /**
     * The <code>ALTER SCHEMA IF EXISTS</code> statement.
     *
     * @see DSL#alterSchemaIfExists(String)
     */
    @NotNull
    @Support({ H2 })
    AlterSchemaStep alterSchemaIfExists(@Stringly.Name String schema);

    /**
     * The <code>ALTER SCHEMA IF EXISTS</code> statement.
     *
     * @see DSL#alterSchemaIfExists(Name)
     */
    @NotNull
    @Support({ H2 })
    AlterSchemaStep alterSchemaIfExists(Name schema);

    /**
     * The <code>ALTER SCHEMA IF EXISTS</code> statement.
     *
     * @see DSL#alterSchemaIfExists(Schema)
     */
    @NotNull
    @Support({ H2 })
    AlterSchemaStep alterSchemaIfExists(Schema schema);

    /**
     * The <code>ALTER SEQUENCE</code> statement.
     *
     * @see DSL#alterSequence(String)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    AlterSequenceStep<Number> alterSequence(@Stringly.Name String sequence);

    /**
     * The <code>ALTER SEQUENCE</code> statement.
     *
     * @see DSL#alterSequence(Name)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    AlterSequenceStep<Number> alterSequence(Name sequence);

    /**
     * The <code>ALTER SEQUENCE</code> statement.
     *
     * @see DSL#alterSequence(Sequence)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    <T extends Number> AlterSequenceStep<T> alterSequence(Sequence<T> sequence);

    /**
     * The <code>ALTER SEQUENCE IF EXISTS</code> statement.
     *
     * @see DSL#alterSequenceIfExists(String)
     */
    @NotNull
    @Support({ H2, MARIADB, POSTGRES })
    AlterSequenceStep<Number> alterSequenceIfExists(@Stringly.Name String sequence);

    /**
     * The <code>ALTER SEQUENCE IF EXISTS</code> statement.
     *
     * @see DSL#alterSequenceIfExists(Name)
     */
    @NotNull
    @Support({ H2, MARIADB, POSTGRES })
    AlterSequenceStep<Number> alterSequenceIfExists(Name sequence);

    /**
     * The <code>ALTER SEQUENCE IF EXISTS</code> statement.
     *
     * @see DSL#alterSequenceIfExists(Sequence)
     */
    @NotNull
    @Support({ H2, MARIADB, POSTGRES })
    <T extends Number> AlterSequenceStep<T> alterSequenceIfExists(Sequence<T> sequence);

    /**
     * The <code>ALTER TYPE</code> statement.
     *
     * @see DSL#alterType(String)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterTypeStep alterType(@Stringly.Name String type);

    /**
     * The <code>ALTER TYPE</code> statement.
     *
     * @see DSL#alterType(Name)
     */
    @NotNull
    @Support({ POSTGRES })
    AlterTypeStep alterType(Name type);

    /**
     * The <code>ALTER VIEW</code> statement.
     *
     * @see DSL#alterView(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    AlterViewStep alterView(@Stringly.Name String view);

    /**
     * The <code>ALTER VIEW</code> statement.
     *
     * @see DSL#alterView(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    AlterViewStep alterView(Name view);

    /**
     * The <code>ALTER VIEW</code> statement.
     *
     * @see DSL#alterView(Table)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    AlterViewStep alterView(Table<?> view);

    /**
     * The <code>ALTER VIEW IF EXISTS</code> statement.
     *
     * @see DSL#alterViewIfExists(String)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    AlterViewStep alterViewIfExists(@Stringly.Name String view);

    /**
     * The <code>ALTER VIEW IF EXISTS</code> statement.
     *
     * @see DSL#alterViewIfExists(Name)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    AlterViewStep alterViewIfExists(Name view);

    /**
     * The <code>ALTER VIEW IF EXISTS</code> statement.
     *
     * @see DSL#alterViewIfExists(Table)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    AlterViewStep alterViewIfExists(Table<?> view);

    /**
     * The <code>COMMENT ON TABLE</code> statement.
     *
     * @see DSL#commentOnTable(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    CommentOnIsStep commentOnTable(@Stringly.Name String table);

    /**
     * The <code>COMMENT ON TABLE</code> statement.
     *
     * @see DSL#commentOnTable(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    CommentOnIsStep commentOnTable(Name table);

    /**
     * The <code>COMMENT ON TABLE</code> statement.
     *
     * @see DSL#commentOnTable(Table)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    CommentOnIsStep commentOnTable(Table<?> table);

    /**
     * The <code>COMMENT ON VIEW</code> statement.
     *
     * @see DSL#commentOnView(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    CommentOnIsStep commentOnView(@Stringly.Name String view);

    /**
     * The <code>COMMENT ON VIEW</code> statement.
     *
     * @see DSL#commentOnView(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    CommentOnIsStep commentOnView(Name view);

    /**
     * The <code>COMMENT ON VIEW</code> statement.
     *
     * @see DSL#commentOnView(Table)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    CommentOnIsStep commentOnView(Table<?> view);

    /**
     * The <code>COMMENT ON COLUMN</code> statement.
     *
     * @see DSL#commentOnColumn(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CommentOnIsStep commentOnColumn(@Stringly.Name String field);

    /**
     * The <code>COMMENT ON COLUMN</code> statement.
     *
     * @see DSL#commentOnColumn(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CommentOnIsStep commentOnColumn(Name field);

    /**
     * The <code>COMMENT ON COLUMN</code> statement.
     *
     * @see DSL#commentOnColumn(Field)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CommentOnIsStep commentOnColumn(Field<?> field);

    /**
     * The <code>CREATE DATABASE</code> statement.
     *
     * @see DSL#createDatabase(String)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    CreateDatabaseFinalStep createDatabase(@Stringly.Name String database);

    /**
     * The <code>CREATE DATABASE</code> statement.
     *
     * @see DSL#createDatabase(Name)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    CreateDatabaseFinalStep createDatabase(Name database);

    /**
     * The <code>CREATE DATABASE</code> statement.
     *
     * @see DSL#createDatabase(Catalog)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    CreateDatabaseFinalStep createDatabase(Catalog database);

    /**
     * The <code>CREATE DATABASE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createDatabaseIfNotExists(String)
     */
    @NotNull
    @Support({ MARIADB, MYSQL })
    CreateDatabaseFinalStep createDatabaseIfNotExists(@Stringly.Name String database);

    /**
     * The <code>CREATE DATABASE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createDatabaseIfNotExists(Name)
     */
    @NotNull
    @Support({ MARIADB, MYSQL })
    CreateDatabaseFinalStep createDatabaseIfNotExists(Name database);

    /**
     * The <code>CREATE DATABASE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createDatabaseIfNotExists(Catalog)
     */
    @NotNull
    @Support({ MARIADB, MYSQL })
    CreateDatabaseFinalStep createDatabaseIfNotExists(Catalog database);

    /**
     * The <code>CREATE DOMAIN</code> statement.
     *
     * @see DSL#createDomain(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    CreateDomainAsStep createDomain(@Stringly.Name String domain);

    /**
     * The <code>CREATE DOMAIN</code> statement.
     *
     * @see DSL#createDomain(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    CreateDomainAsStep createDomain(Name domain);

    /**
     * The <code>CREATE DOMAIN</code> statement.
     *
     * @see DSL#createDomain(Domain)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    CreateDomainAsStep createDomain(Domain<?> domain);

    /**
     * The <code>CREATE DOMAIN IF NOT EXISTS</code> statement.
     *
     * @see DSL#createDomainIfNotExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, POSTGRES })
    CreateDomainAsStep createDomainIfNotExists(@Stringly.Name String domain);

    /**
     * The <code>CREATE DOMAIN IF NOT EXISTS</code> statement.
     *
     * @see DSL#createDomainIfNotExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, POSTGRES })
    CreateDomainAsStep createDomainIfNotExists(Name domain);

    /**
     * The <code>CREATE DOMAIN IF NOT EXISTS</code> statement.
     *
     * @see DSL#createDomainIfNotExists(Domain)
     */
    @NotNull
    @Support({ FIREBIRD, H2, POSTGRES })
    CreateDomainAsStep createDomainIfNotExists(Domain<?> domain);













































    /**
     * The <code>CREATE INDEX</code> statement.
     *
     * @see DSL#createIndex(String)
     */
    @NotNull
    @Support
    CreateIndexStep createIndex(@Stringly.Name String index);

    /**
     * The <code>CREATE INDEX</code> statement.
     *
     * @see DSL#createIndex(Name)
     */
    @NotNull
    @Support
    CreateIndexStep createIndex(Name index);

    /**
     * The <code>CREATE INDEX</code> statement.
     *
     * @see DSL#createIndex(Index)
     */
    @NotNull
    @Support
    CreateIndexStep createIndex(Index index);

    /**
     * The <code>CREATE INDEX</code> statement.
     *
     * @see DSL#createIndex()
     */
    @NotNull
    @Support
    CreateIndexStep createIndex();

    /**
     * The <code>CREATE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createIndexIfNotExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    CreateIndexStep createIndexIfNotExists(@Stringly.Name String index);

    /**
     * The <code>CREATE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createIndexIfNotExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    CreateIndexStep createIndexIfNotExists(Name index);

    /**
     * The <code>CREATE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createIndexIfNotExists(Index)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    CreateIndexStep createIndexIfNotExists(Index index);

    /**
     * The <code>CREATE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createIndexIfNotExists()
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    CreateIndexStep createIndexIfNotExists();

    /**
     * The <code>CREATE UNIQUE INDEX</code> statement.
     *
     * @see DSL#createUniqueIndex(String)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndex(@Stringly.Name String index);

    /**
     * The <code>CREATE UNIQUE INDEX</code> statement.
     *
     * @see DSL#createUniqueIndex(Name)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndex(Name index);

    /**
     * The <code>CREATE UNIQUE INDEX</code> statement.
     *
     * @see DSL#createUniqueIndex(Index)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndex(Index index);

    /**
     * The <code>CREATE UNIQUE INDEX</code> statement.
     *
     * @see DSL#createUniqueIndex()
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndex();

    /**
     * The <code>CREATE UNIQUE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createUniqueIndexIfNotExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndexIfNotExists(@Stringly.Name String index);

    /**
     * The <code>CREATE UNIQUE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createUniqueIndexIfNotExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndexIfNotExists(Name index);

    /**
     * The <code>CREATE UNIQUE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createUniqueIndexIfNotExists(Index)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndexIfNotExists(Index index);

    /**
     * The <code>CREATE UNIQUE INDEX IF NOT EXISTS</code> statement.
     *
     * @see DSL#createUniqueIndexIfNotExists()
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES, SQLITE })
    CreateIndexStep createUniqueIndexIfNotExists();





















































































    /**
     * The <code>CREATE SCHEMA</code> statement.
     *
     * @see DSL#createSchema(String)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    CreateSchemaFinalStep createSchema(@Stringly.Name String schema);

    /**
     * The <code>CREATE SCHEMA</code> statement.
     *
     * @see DSL#createSchema(Name)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    CreateSchemaFinalStep createSchema(Name schema);

    /**
     * The <code>CREATE SCHEMA</code> statement.
     *
     * @see DSL#createSchema(Schema)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    CreateSchemaFinalStep createSchema(Schema schema);

    /**
     * The <code>CREATE SCHEMA IF NOT EXISTS</code> statement.
     *
     * @see DSL#createSchemaIfNotExists(String)
     */
    @NotNull
    @Support({ H2, MARIADB, MYSQL, POSTGRES })
    CreateSchemaFinalStep createSchemaIfNotExists(@Stringly.Name String schema);

    /**
     * The <code>CREATE SCHEMA IF NOT EXISTS</code> statement.
     *
     * @see DSL#createSchemaIfNotExists(Name)
     */
    @NotNull
    @Support({ H2, MARIADB, MYSQL, POSTGRES })
    CreateSchemaFinalStep createSchemaIfNotExists(Name schema);

    /**
     * The <code>CREATE SCHEMA IF NOT EXISTS</code> statement.
     *
     * @see DSL#createSchemaIfNotExists(Schema)
     */
    @NotNull
    @Support({ H2, MARIADB, MYSQL, POSTGRES })
    CreateSchemaFinalStep createSchemaIfNotExists(Schema schema);

    /**
     * The <code>CREATE SEQUENCE</code> statement.
     *
     * @see DSL#createSequence(String)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CreateSequenceFlagsStep createSequence(@Stringly.Name String sequence);

    /**
     * The <code>CREATE SEQUENCE</code> statement.
     *
     * @see DSL#createSequence(Name)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CreateSequenceFlagsStep createSequence(Name sequence);

    /**
     * The <code>CREATE SEQUENCE</code> statement.
     *
     * @see DSL#createSequence(Sequence)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CreateSequenceFlagsStep createSequence(Sequence<?> sequence);

    /**
     * The <code>CREATE SEQUENCE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createSequenceIfNotExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CreateSequenceFlagsStep createSequenceIfNotExists(@Stringly.Name String sequence);

    /**
     * The <code>CREATE SEQUENCE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createSequenceIfNotExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CreateSequenceFlagsStep createSequenceIfNotExists(Name sequence);

    /**
     * The <code>CREATE SEQUENCE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createSequenceIfNotExists(Sequence)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    CreateSequenceFlagsStep createSequenceIfNotExists(Sequence<?> sequence);

    /**
     * The <code>DROP DATABASE</code> statement.
     *
     * @see DSL#dropDatabase(String)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    DropDatabaseFinalStep dropDatabase(@Stringly.Name String database);

    /**
     * The <code>DROP DATABASE</code> statement.
     *
     * @see DSL#dropDatabase(Name)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    DropDatabaseFinalStep dropDatabase(Name database);

    /**
     * The <code>DROP DATABASE</code> statement.
     *
     * @see DSL#dropDatabase(Catalog)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    DropDatabaseFinalStep dropDatabase(Catalog database);

    /**
     * The <code>DROP DATABASE IF EXISTS</code> statement.
     *
     * @see DSL#dropDatabaseIfExists(String)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    DropDatabaseFinalStep dropDatabaseIfExists(@Stringly.Name String database);

    /**
     * The <code>DROP DATABASE IF EXISTS</code> statement.
     *
     * @see DSL#dropDatabaseIfExists(Name)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    DropDatabaseFinalStep dropDatabaseIfExists(Name database);

    /**
     * The <code>DROP DATABASE IF EXISTS</code> statement.
     *
     * @see DSL#dropDatabaseIfExists(Catalog)
     */
    @NotNull
    @Support({ MARIADB, MYSQL, POSTGRES })
    DropDatabaseFinalStep dropDatabaseIfExists(Catalog database);

    /**
     * The <code>DROP DOMAIN</code> statement.
     *
     * @see DSL#dropDomain(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    DropDomainCascadeStep dropDomain(@Stringly.Name String domain);

    /**
     * The <code>DROP DOMAIN</code> statement.
     *
     * @see DSL#dropDomain(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    DropDomainCascadeStep dropDomain(Name domain);

    /**
     * The <code>DROP DOMAIN</code> statement.
     *
     * @see DSL#dropDomain(Domain)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    DropDomainCascadeStep dropDomain(Domain<?> domain);

    /**
     * The <code>DROP DOMAIN IF EXISTS</code> statement.
     *
     * @see DSL#dropDomainIfExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    DropDomainCascadeStep dropDomainIfExists(@Stringly.Name String domain);

    /**
     * The <code>DROP DOMAIN IF EXISTS</code> statement.
     *
     * @see DSL#dropDomainIfExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    DropDomainCascadeStep dropDomainIfExists(Name domain);

    /**
     * The <code>DROP DOMAIN IF EXISTS</code> statement.
     *
     * @see DSL#dropDomainIfExists(Domain)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, POSTGRES })
    DropDomainCascadeStep dropDomainIfExists(Domain<?> domain);













































    /**
     * The <code>DROP INDEX</code> statement.
     *
     * @see DSL#dropIndex(String)
     */
    @NotNull
    @Support
    DropIndexOnStep dropIndex(@Stringly.Name String index);

    /**
     * The <code>DROP INDEX</code> statement.
     *
     * @see DSL#dropIndex(Name)
     */
    @NotNull
    @Support
    DropIndexOnStep dropIndex(Name index);

    /**
     * The <code>DROP INDEX</code> statement.
     *
     * @see DSL#dropIndex(Index)
     */
    @NotNull
    @Support
    DropIndexOnStep dropIndex(Index index);

    /**
     * The <code>DROP INDEX IF EXISTS</code> statement.
     *
     * @see DSL#dropIndexIfExists(String)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    DropIndexOnStep dropIndexIfExists(@Stringly.Name String index);

    /**
     * The <code>DROP INDEX IF EXISTS</code> statement.
     *
     * @see DSL#dropIndexIfExists(Name)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    DropIndexOnStep dropIndexIfExists(Name index);

    /**
     * The <code>DROP INDEX IF EXISTS</code> statement.
     *
     * @see DSL#dropIndexIfExists(Index)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE })
    DropIndexOnStep dropIndexIfExists(Index index);













































    /**
     * The <code>DROP SCHEMA</code> statement.
     *
     * @see DSL#dropSchema(String)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    DropSchemaStep dropSchema(@Stringly.Name String schema);

    /**
     * The <code>DROP SCHEMA</code> statement.
     *
     * @see DSL#dropSchema(Name)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    DropSchemaStep dropSchema(Name schema);

    /**
     * The <code>DROP SCHEMA</code> statement.
     *
     * @see DSL#dropSchema(Schema)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    DropSchemaStep dropSchema(Schema schema);

    /**
     * The <code>DROP SCHEMA IF EXISTS</code> statement.
     *
     * @see DSL#dropSchemaIfExists(String)
     */
    @NotNull
    @Support({ H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    DropSchemaStep dropSchemaIfExists(@Stringly.Name String schema);

    /**
     * The <code>DROP SCHEMA IF EXISTS</code> statement.
     *
     * @see DSL#dropSchemaIfExists(Name)
     */
    @NotNull
    @Support({ H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    DropSchemaStep dropSchemaIfExists(Name schema);

    /**
     * The <code>DROP SCHEMA IF EXISTS</code> statement.
     *
     * @see DSL#dropSchemaIfExists(Schema)
     */
    @NotNull
    @Support({ H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    DropSchemaStep dropSchemaIfExists(Schema schema);

    /**
     * The <code>DROP SEQUENCE</code> statement.
     *
     * @see DSL#dropSequence(String)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    DropSequenceFinalStep dropSequence(@Stringly.Name String sequence);

    /**
     * The <code>DROP SEQUENCE</code> statement.
     *
     * @see DSL#dropSequence(Name)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    DropSequenceFinalStep dropSequence(Name sequence);

    /**
     * The <code>DROP SEQUENCE</code> statement.
     *
     * @see DSL#dropSequence(Sequence)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    DropSequenceFinalStep dropSequence(Sequence<?> sequence);

    /**
     * The <code>DROP SEQUENCE IF EXISTS</code> statement.
     *
     * @see DSL#dropSequenceIfExists(String)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    DropSequenceFinalStep dropSequenceIfExists(@Stringly.Name String sequence);

    /**
     * The <code>DROP SEQUENCE IF EXISTS</code> statement.
     *
     * @see DSL#dropSequenceIfExists(Name)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    DropSequenceFinalStep dropSequenceIfExists(Name sequence);

    /**
     * The <code>DROP SEQUENCE IF EXISTS</code> statement.
     *
     * @see DSL#dropSequenceIfExists(Sequence)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    DropSequenceFinalStep dropSequenceIfExists(Sequence<?> sequence);

    /**
     * The <code>DROP TABLE</code> statement.
     *
     * @see DSL#dropTable(String)
     */
    @NotNull
    @Support
    DropTableStep dropTable(@Stringly.Name String table);

    /**
     * The <code>DROP TABLE</code> statement.
     *
     * @see DSL#dropTable(Name)
     */
    @NotNull
    @Support
    DropTableStep dropTable(Name table);

    /**
     * The <code>DROP TABLE</code> statement.
     *
     * @see DSL#dropTable(Table)
     */
    @NotNull
    @Support
    DropTableStep dropTable(Table<?> table);

    /**
     * The <code>DROP TABLE IF EXISTS</code> statement.
     *
     * @see DSL#dropTableIfExists(String)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    DropTableStep dropTableIfExists(@Stringly.Name String table);

    /**
     * The <code>DROP TABLE IF EXISTS</code> statement.
     *
     * @see DSL#dropTableIfExists(Name)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    DropTableStep dropTableIfExists(Name table);

    /**
     * The <code>DROP TABLE IF EXISTS</code> statement.
     *
     * @see DSL#dropTableIfExists(Table)
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    DropTableStep dropTableIfExists(Table<?> table);

    /**
     * The <code>DROP TEMPORARY TABLE</code> statement.
     *
     * @see DSL#dropTemporaryTable(String)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    DropTableStep dropTemporaryTable(@Stringly.Name String table);

    /**
     * The <code>DROP TEMPORARY TABLE</code> statement.
     *
     * @see DSL#dropTemporaryTable(Name)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    DropTableStep dropTemporaryTable(Name table);

    /**
     * The <code>DROP TEMPORARY TABLE</code> statement.
     *
     * @see DSL#dropTemporaryTable(Table)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    DropTableStep dropTemporaryTable(Table<?> table);

    /**
     * The <code>DROP TEMPORARY TABLE IF EXISTS</code> statement.
     *
     * @see DSL#dropTemporaryTableIfExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    DropTableStep dropTemporaryTableIfExists(@Stringly.Name String table);

    /**
     * The <code>DROP TEMPORARY TABLE IF EXISTS</code> statement.
     *
     * @see DSL#dropTemporaryTableIfExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    DropTableStep dropTemporaryTableIfExists(Name table);

    /**
     * The <code>DROP TEMPORARY TABLE IF EXISTS</code> statement.
     *
     * @see DSL#dropTemporaryTableIfExists(Table)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    DropTableStep dropTemporaryTableIfExists(Table<?> table);













































    /**
     * The <code>DROP VIEW</code> statement.
     *
     * @see DSL#dropView(String)
     */
    @NotNull
    @Support
    DropViewFinalStep dropView(@Stringly.Name String view);

    /**
     * The <code>DROP VIEW</code> statement.
     *
     * @see DSL#dropView(Name)
     */
    @NotNull
    @Support
    DropViewFinalStep dropView(Name view);

    /**
     * The <code>DROP VIEW</code> statement.
     *
     * @see DSL#dropView(Table)
     */
    @NotNull
    @Support
    DropViewFinalStep dropView(Table<?> view);

    /**
     * The <code>DROP VIEW IF EXISTS</code> statement.
     *
     * @see DSL#dropViewIfExists(String)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    DropViewFinalStep dropViewIfExists(@Stringly.Name String view);

    /**
     * The <code>DROP VIEW IF EXISTS</code> statement.
     *
     * @see DSL#dropViewIfExists(Name)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    DropViewFinalStep dropViewIfExists(Name view);

    /**
     * The <code>DROP VIEW IF EXISTS</code> statement.
     *
     * @see DSL#dropViewIfExists(Table)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    DropViewFinalStep dropViewIfExists(Table<?> view);

    /**
     * The <code>GRANT</code> statement.
     *
     * @see DSL#grant(Privilege)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    GrantOnStep grant(Privilege privileges);

    /**
     * The <code>GRANT</code> statement.
     *
     * @see DSL#grant(Privilege...)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    GrantOnStep grant(Privilege... privileges);

    /**
     * The <code>GRANT</code> statement.
     *
     * @see DSL#grant(Collection)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    GrantOnStep grant(Collection<? extends Privilege> privileges);

    /**
     * The <code>REVOKE</code> statement.
     *
     * @see DSL#revoke(Privilege)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    RevokeOnStep revoke(Privilege privileges);

    /**
     * The <code>REVOKE</code> statement.
     *
     * @see DSL#revoke(Privilege...)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    RevokeOnStep revoke(Privilege... privileges);

    /**
     * The <code>REVOKE</code> statement.
     *
     * @see DSL#revoke(Collection)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    RevokeOnStep revoke(Collection<? extends Privilege> privileges);

    /**
     * The <code>REVOKE GRANT OPTION FOR</code> statement.
     *
     * @see DSL#revokeGrantOptionFor(Privilege)
     */
    @NotNull
    @Support({ HSQLDB, POSTGRES })
    RevokeOnStep revokeGrantOptionFor(Privilege privileges);

    /**
     * The <code>REVOKE GRANT OPTION FOR</code> statement.
     *
     * @see DSL#revokeGrantOptionFor(Privilege...)
     */
    @NotNull
    @Support({ HSQLDB, POSTGRES })
    RevokeOnStep revokeGrantOptionFor(Privilege... privileges);

    /**
     * The <code>REVOKE GRANT OPTION FOR</code> statement.
     *
     * @see DSL#revokeGrantOptionFor(Collection)
     */
    @NotNull
    @Support({ HSQLDB, POSTGRES })
    RevokeOnStep revokeGrantOptionFor(Collection<? extends Privilege> privileges);

    /**
     * The <code>SET</code> statement.
     * <p>
     * Set a vendor specific session configuration to a new value.
     *
     * @see DSL#set(Name, Param)
     */
    @NotNull
    @Support({ MYSQL, POSTGRES })
    RowCountQuery set(Name name, Param<?> value);

    /**
     * The <code>SET LOCAL</code> statement.
     * <p>
     * Set a vendor specific transaction-local configuration to a new value.
     *
     * @see DSL#setLocal(Name, Param)
     */
    @NotNull
    @Support({ POSTGRES })
    RowCountQuery setLocal(Name name, Param<?> value);

    /**
     * The <code>SET CATALOG</code> statement.
     * <p>
     * Set the current catalog to a new value.
     *
     * @see DSL#setCatalog(String)
     */
    @NotNull
    @Support({ MARIADB, MYSQL })
    RowCountQuery setCatalog(@Stringly.Name String catalog);

    /**
     * The <code>SET CATALOG</code> statement.
     * <p>
     * Set the current catalog to a new value.
     *
     * @see DSL#setCatalog(Name)
     */
    @NotNull
    @Support({ MARIADB, MYSQL })
    RowCountQuery setCatalog(Name catalog);

    /**
     * The <code>SET CATALOG</code> statement.
     * <p>
     * Set the current catalog to a new value.
     *
     * @see DSL#setCatalog(Catalog)
     */
    @NotNull
    @Support({ MARIADB, MYSQL })
    RowCountQuery setCatalog(Catalog catalog);

    /**
     * The <code>SET SCHEMA</code> statement.
     * <p>
     * Set the current schema to a new value.
     *
     * @see DSL#setSchema(String)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    RowCountQuery setSchema(@Stringly.Name String schema);

    /**
     * The <code>SET SCHEMA</code> statement.
     * <p>
     * Set the current schema to a new value.
     *
     * @see DSL#setSchema(Name)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    RowCountQuery setSchema(Name schema);

    /**
     * The <code>SET SCHEMA</code> statement.
     * <p>
     * Set the current schema to a new value.
     *
     * @see DSL#setSchema(Schema)
     */
    @NotNull
    @Support({ DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES })
    RowCountQuery setSchema(Schema schema);

    /**
     * The <code>TRUNCATE</code> statement.
     *
     * @see DSL#truncate(String)
     */
    @NotNull
    @Support
    TruncateIdentityStep<Record> truncate(@Stringly.Name String table);

    /**
     * The <code>TRUNCATE</code> statement.
     *
     * @see DSL#truncate(Name)
     */
    @NotNull
    @Support
    TruncateIdentityStep<Record> truncate(Name table);

    /**
     * The <code>TRUNCATE</code> statement.
     *
     * @see DSL#truncate(Table)
     */
    @NotNull
    @Support
    <R extends Record> TruncateIdentityStep<R> truncate(Table<R> table);

    /**
     * The <code>TRUNCATE TABLE</code> statement.
     *
     * @see DSL#truncateTable(String)
     */
    @NotNull
    @Support
    TruncateIdentityStep<Record> truncateTable(@Stringly.Name String table);

    /**
     * The <code>TRUNCATE TABLE</code> statement.
     *
     * @see DSL#truncateTable(Name)
     */
    @NotNull
    @Support
    TruncateIdentityStep<Record> truncateTable(Name table);

    /**
     * The <code>TRUNCATE TABLE</code> statement.
     *
     * @see DSL#truncateTable(Table)
     */
    @NotNull
    @Support
    <R extends Record> TruncateIdentityStep<R> truncateTable(Table<R> table);



    /**
     * Create a new DSL <code>CREATE TABLE</code> statement.
     *
     * @see DSL#createTable(String)
     */
    @NotNull
    @Support
    CreateTableColumnStep createTable(String table);

    /**
     * Create a new DSL <code>CREATE TABLE</code> statement.
     *
     * @see DSL#createTable(Name)
     */
    @NotNull
    @Support
    CreateTableColumnStep createTable(Name table);

    /**
     * Create a new DSL <code>CREATE TABLE</code> statement.
     *
     * @see DSL#createTable(Table)
     */
    @NotNull
    @Support
    CreateTableColumnStep createTable(Table<?> table);

    /**
     * Create a new DSL <code>CREATE TABLE</code> statement.
     *
     * @see DSL#createTableIfNotExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateTableColumnStep createTableIfNotExists(String table);

    /**
     * Create a new DSL <code>CREATE TABLE</code> statement.
     *
     * @see DSL#createTableIfNotExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateTableColumnStep createTableIfNotExists(Name table);

    /**
     * Create a new DSL <code>CREATE TABLE</code> statement.
     *
     * @see DSL#createTableIfNotExists(Table)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateTableColumnStep createTableIfNotExists(Table<?> table);

    /**
     * Create a new DSL <code>CREATE TEMPORARY TABLE</code> statement.
     *
     * @see DSL#createTemporaryTable(String)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createTemporaryTable(String table);

    /**
     * Create a new DSL <code>CREATE TEMPORARY TABLE</code> statement.
     *
     * @see DSL#createTemporaryTable(Name)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createTemporaryTable(Name table);

    /**
     * Create a new DSL <code>CREATE TEMPORARY TABLE</code> statement.
     *
     * @see DSL#createTemporaryTable(Table)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createTemporaryTable(Table<?> table);

    /**
     * Create a new DSL <code>CREATE TEMPORARY TABLE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createTemporaryTableIfNotExists(String)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createTemporaryTableIfNotExists(String table);

    /**
     * Create a new DSL <code>CREATE TEMPORARY TABLE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createTemporaryTableIfNotExists(Name)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createTemporaryTableIfNotExists(Name table);

    /**
     * Create a new DSL <code>CREATE TEMPORARY TABLE IF NOT EXISTS</code> statement.
     *
     * @see DSL#createTemporaryTableIfNotExists(Table)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createTemporaryTableIfNotExists(Table<?> table);

    /**
     * Create a new DSL <code>CREATE GLOBAL TEMPORARY TABLE</code> statement.
     *
     * @see DSL#createGlobalTemporaryTable(String)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createGlobalTemporaryTable(String table);

    /**
     * Create a new DSL <code>CREATE GLOBAL TEMPORARY TABLE</code> statement.
     *
     * @see DSL#createGlobalTemporaryTable(Name)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createGlobalTemporaryTable(Name table);

    /**
     * Create a new DSL <code>CREATE GLOBAL TEMPORARY TABLE</code> statement.
     *
     * @see DSL#createGlobalTemporaryTable(Table)
     */
    @NotNull
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES })
    CreateTableColumnStep createGlobalTemporaryTable(Table<?> table);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     *
     * @see DSL#createView(String, String...)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(String view, String... fields);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     *
     * @see DSL#createView(Name, Name...)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(Name view, Name... fields);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     *
     * @see DSL#createView(Table, Field...)
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(Table<?> view, Field<?>... fields);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createView(String, String...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(String view, Function<? super Field<?>, ? extends String> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createView(String, String...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(String view, BiFunction<? super Field<?>, ? super Integer, ? extends String> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createView(Name, Name...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(Name view, Function<? super Field<?>, ? extends Name> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createView(Name, Name...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(Name view, BiFunction<? super Field<?>, ? super Integer, ? extends Name> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createView(Table, Field...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(Table<?> view, Function<? super Field<?>, ? extends Field<?>> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createView(Table, Field...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createView(Table<?> view, BiFunction<? super Field<?>, ? super Integer, ? extends Field<?>> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     *
     * @see DSL#createOrReplaceView(String, String...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(String view, String... fields);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     *
     * @see DSL#createOrReplaceView(Name, Name...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(Name view, Name... fields);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     *
     * @see DSL#createOrReplaceView(Table, Field...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(Table<?> view, Field<?>... fields);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     * <p>
     * This works like {@link #createOrReplaceView(String, String...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createOrReplaceView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(String view, Function<? super Field<?>, ? extends String> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     * <p>
     * This works like {@link #createOrReplaceView(String, String...)} except
     * that the view's field names are derived from the view's {@link Select}
     * statement using a function.
     *
     * @see DSL#createOrReplaceView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(String view, BiFunction<? super Field<?>, ? super Integer, ? extends String> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     * <p>
     * This works like {@link #createOrReplaceView(Name, Name...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createOrReplaceView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(Name view, Function<? super Field<?>, ? extends Name> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     * <p>
     * This works like {@link #createOrReplaceView(Name, Name...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createOrReplaceView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(Name view, BiFunction<? super Field<?>, ? super Integer, ? extends Name> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     * <p>
     * This works like {@link #createOrReplaceView(Table, Field...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createOrReplaceView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(Table<?> view, Function<? super Field<?>, ? extends Field<?>> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE OR REPLACE VIEW</code> statement.
     * <p>
     * This works like {@link #createOrReplaceView(Table, Field...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createOrReplaceView(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, MARIADB, MYSQL, POSTGRES })
    CreateViewAsStep<Record> createOrReplaceView(Table<?> view, BiFunction<? super Field<?>, ? super Integer, ? extends Field<?>> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(String view, String... fields);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     *
     * @see DSL#createViewIfNotExists(Name, Name...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(Name view, Name... fields);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     *
     * @see DSL#createViewIfNotExists(Table, Field...)
     */
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(Table<?> view, Field<?>... fields);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createViewIfNotExists(String, String...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(String view, Function<? super Field<?>, ? extends String> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createViewIfNotExists(String, String...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(String view, BiFunction<? super Field<?>, ? super Integer, ? extends String> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createViewIfNotExists(Name, Name...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(Name view, Function<? super Field<?>, ? extends Name> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createViewIfNotExists(Name, Name...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(Name view, BiFunction<? super Field<?>, ? super Integer, ? extends Name> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createViewIfNotExists(Table, Field...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(Table<?> view, Function<? super Field<?>, ? extends Field<?>> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE VIEW</code> statement.
     * <p>
     * This works like {@link #createViewIfNotExists(Table, Field...)} except that the
     * view's field names are derived from the view's {@link Select} statement
     * using a function.
     *
     * @see DSL#createViewIfNotExists(String, String...)
     * @deprecated - 3.14.0 - [#10156] - These methods will be removed without
     *             replacement from a future jOOQ. They offer convenience that
     *             is unidiomatic for jOOQ's DSL, without offering functionality
     *             that would not be possible otherwise - yet they add
     *             complexity in jOOQ's internals.
     */
    @Deprecated
    @NotNull
    @Support({ FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    CreateViewAsStep<Record> createViewIfNotExists(Table<?> view, BiFunction<? super Field<?>, ? super Integer, ? extends Field<?>> fieldNameFunction);

    /**
     * Create a new DSL <code>CREATE TYPE</code> statement.
     *
     * @see DSL#createType(String)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    CreateTypeStep createType(String type);

    /**
     * Create a new DSL <code>CREATE TYPE</code> statement.
     *
     * @see DSL#createType(Name)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    CreateTypeStep createType(Name type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropType(String)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropType(String type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropType(Name)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropType(Name type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropType(String...)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropType(String... type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropType(Name...)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropType(Name... type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropType(Collection)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropType(Collection<?> type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropTypeIfExists(String)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropTypeIfExists(String type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropTypeIfExists(Name)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropTypeIfExists(Name type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropTypeIfExists(String...)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropTypeIfExists(String... type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropTypeIfExists(Name...)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropTypeIfExists(Name... type);

    /**
     * Create a new DSL <code>DROP TYPE</code> statement.
     *
     * @see DSL#dropTypeIfExists(Collection)
     */
    @NotNull
    @Support({ H2, POSTGRES })
    DropTypeStep dropTypeIfExists(Collection<?> type);

    /**
     * Create a new DSL <code>ALTER TABLE</code> statement.
     *
     * @see DSL#alterTable(String)
     */
    @NotNull
    @Support
    AlterTableStep alterTable(String table);

    /**
     * Create a new DSL <code>ALTER TABLE</code> statement.
     *
     * @see DSL#alterTable(Name)
     */
    @NotNull
    @Support
    AlterTableStep alterTable(Name table);

    /**
     * Create a new DSL <code>ALTER TABLE</code> statement.
     *
     * @see DSL#alterTable(Table)
     */
    @NotNull
    @Support
    AlterTableStep alterTable(Table<?> table);

    /**
     * Create a new DSL <code>ALTER TABLE</code> statement.
     *
     * @see DSL#alterTableIfExists(String)
     */
    @NotNull
    @Support({ H2, IGNITE, MARIADB, POSTGRES })
    AlterTableStep alterTableIfExists(String table);

    /**
     * Create a new DSL <code>ALTER TABLE</code> statement.
     *
     * @see DSL#alterTableIfExists(Name)
     */
    @NotNull
    @Support({ H2, IGNITE, MARIADB, POSTGRES })
    AlterTableStep alterTableIfExists(Name table);

    /**
     * Create a new DSL <code>ALTER TABLE</code> statement.
     *
     * @see DSL#alterTableIfExists(Table)
     */
    @NotNull
    @Support({ H2, IGNITE, MARIADB, POSTGRES })
    AlterTableStep alterTableIfExists(Table<?> table);

    // -------------------------------------------------------------------------
    // XXX Other queries for identites and sequences
    // -------------------------------------------------------------------------

    /**
     * Retrieve the last inserted ID.
     * <p>
     * This is implemented for the following dialects:
     * <ul>
     * <li>{@link SQLDialect#ACCESS}: Using <code>@@identity</code></li>
     * <li>{@link SQLDialect#ASE}: Using <code>@@identity</code></li>
     * <li>{@link SQLDialect#DERBY}: Using <code>identity_val_local()</code></li>
     * <li>{@link SQLDialect#H2}: Using <code>identity()</code></li>
     * <li>{@link SQLDialect#HSQLDB}: Using <code>identity()</code></li>
     * <li>{@link SQLDialect#INFORMIX}: Using
     * <code>dbinfo('sqlca.sqlerrd1')</code></li>
     * <li>{@link SQLDialect#INGRES}: Using <code>last_identity()</code></li>
     * <li>{@link SQLDialect#MARIADB}: Using <code>last_insert_id()</code></li>
     * <li>{@link SQLDialect#MYSQL}: Using <code>last_insert_id()</code></li>
     * <li>{@link SQLDialect#POSTGRES}: Using <code>lastval()</code></li>
     * <li>{@link SQLDialect#SQLITE}: Using <code>last_insert_rowid()</code></li>
     * <li>{@link SQLDialect#SQLSERVER}: Using <code>@@identity</code></li>
     * <li>{@link SQLDialect#SYBASE}: Using <code>@@identity</code></li>
     * <li>{@link SQLDialect#VERTICA}: Using <code>last_insert_id()</code></li>
     * </ul>
     *
     * @return The last inserted ID. This may be <code>null</code> in some
     *         dialects, if no such number is available.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE })
    BigInteger lastID() throws DataAccessException;

    /**
     * Convenience method to fetch the NEXTVAL for a sequence directly from this
     * {@link DSLContext}'s underlying JDBC {@link Connection}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    BigInteger nextval(String sequence) throws DataAccessException;

    /**
     * Convenience method to fetch the NEXTVAL for a sequence directly from this
     * {@link DSLContext}'s underlying JDBC {@link Connection}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    BigInteger nextval(Name sequence) throws DataAccessException;

    /**
     * Convenience method to fetch the NEXTVAL for a sequence directly from this
     * {@link DSLContext}'s underlying JDBC {@link Connection}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    <T extends Number> T nextval(Sequence<T> sequence) throws DataAccessException;

    /**
     * Convenience method to fetch several NEXTVAL for a sequence directly from
     * this {@link DSLContext}'s underlying JDBC {@link Connection}.
     * <p>
     * This is done using {@link DSL#generateSeries(int, int)}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    <T extends Number> List<T> nextvals(Sequence<T> sequence, int size) throws DataAccessException;

    /**
     * Convenience method to fetch the CURRVAL for a sequence directly from this
     * {@link DSLContext}'s underlying JDBC {@link Connection}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    BigInteger currval(String sequence) throws DataAccessException;

    /**
     * Convenience method to fetch the CURRVAL for a sequence directly from this
     * {@link DSLContext}'s underlying JDBC {@link Connection}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    BigInteger currval(Name sequence) throws DataAccessException;

    /**
     * Convenience method to fetch the CURRVAL for a sequence directly from this
     * {@link DSLContext}'s underlying JDBC {@link Connection}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support({ CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, POSTGRES })
    <T extends Number> T currval(Sequence<T> sequence) throws DataAccessException;

    // -------------------------------------------------------------------------
    // XXX Global Record factory
    // -------------------------------------------------------------------------

    /**
     * Create a new {@link UDTRecord}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param <R> The generic record type
     * @param type The UDT describing records of type &lt;R&gt;
     * @return The new record
     */
    @NotNull
    <R extends UDTRecord<R>> R newRecord(UDT<R> type);

    /**
     * Create a new {@link Record} that can be inserted into the corresponding
     * table.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param <R> The generic record type
     * @param table The table holding records of type &lt;R&gt;
     * @return The new record
     */
    @NotNull
    <R extends Record> R newRecord(Table<R> table);

    /**
     * Create a new pre-filled {@link Record} that can be inserted into the
     * corresponding table.
     * <p>
     * This performs roughly the inverse operation of {@link Record#into(Class)}
     * <p>
     * The resulting record will have its internal "changed" flags set to true
     * for all values. This means that {@link UpdatableRecord#store()} will
     * perform an <code>INSERT</code> statement. If you wish to store the record
     * using an <code>UPDATE</code> statement, use
     * {@link #executeUpdate(UpdatableRecord)} instead.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param <R> The generic record type
     * @param table The table holding records of type &lt;R&gt;
     * @param source The source to be used to fill the new record
     * @return The new record
     * @throws MappingException wrapping any reflection or data type conversion
     *             exception that might have occurred while mapping records
     * @see Record#from(Object)
     * @see Record#into(Class)
     */
    @NotNull
    <R extends Record> R newRecord(Table<R> table, Object source);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param fields The fields defining the <code>Record</code> type
     * @return The new record
     */
    @NotNull
    Record newRecord(Field<?>... fields);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param fields The fields defining the <code>Record</code> type
     * @return The new record
     */
    @NotNull
    Record newRecord(Collection<? extends Field<?>> fields);



    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1> Record1<T1> newRecord(Field<T1> field1);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2> Record2<T1, T2> newRecord(Field<T1> field1, Field<T2> field2);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3> Record3<T1, T2, T3> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4> Record4<T1, T2, T3, T4> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5> Record5<T1, T2, T3, T4, T5> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6> Record6<T1, T2, T3, T4, T5, T6> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7> Record7<T1, T2, T3, T4, T5, T6, T7> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8> Record8<T1, T2, T3, T4, T5, T6, T7, T8> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9> Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new record
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> newRecord(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21, Field<T22> field22);



    /**
     * Create a new empty {@link Result}.
     * <p>
     * The result is attached to this {@link Configuration} by default. This
     * result can be used as a container for records.
     *
     * @param <R> The generic record type
     * @param table The table holding records of type &lt;R&gt;
     * @return The new result
     */
    @NotNull
    <R extends Record> Result<R> newResult(Table<R> table);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param fields The fields defining the <code>Record</code> type
     * @return The new record
     */
    @NotNull
    Result<Record> newResult(Field<?>... fields);

    /**
     * Create a new empty {@link Record}.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @param fields The fields defining the <code>Record</code> type
     * @return The new record
     */
    @NotNull
    Result<Record> newResult(Collection<? extends Field<?>> fields);



    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1> Result<Record1<T1>> newResult(Field<T1> field1);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2> Result<Record2<T1, T2>> newResult(Field<T1> field1, Field<T2> field2);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3> Result<Record3<T1, T2, T3>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4> Result<Record4<T1, T2, T3, T4>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5> Result<Record5<T1, T2, T3, T4, T5>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6> Result<Record6<T1, T2, T3, T4, T5, T6>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7> Result<Record7<T1, T2, T3, T4, T5, T6, T7>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8> Result<Record8<T1, T2, T3, T4, T5, T6, T7, T8>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9> Result<Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Result<Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Result<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Result<Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Result<Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Result<Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Result<Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Result<Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Result<Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Result<Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Result<Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Result<Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Result<Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21);

    /**
     * Create a new empty {@link Result}.
     * <p>
     * The resulting result is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The new result
     */
    @NotNull
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Result<Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>> newResult(Field<T1> field1, Field<T2> field2, Field<T3> field3, Field<T4> field4, Field<T5> field5, Field<T6> field6, Field<T7> field7, Field<T8> field8, Field<T9> field9, Field<T10> field10, Field<T11> field11, Field<T12> field12, Field<T13> field13, Field<T14> field14, Field<T15> field15, Field<T16> field16, Field<T17> field17, Field<T18> field18, Field<T19> field19, Field<T20> field20, Field<T21> field21, Field<T22> field22);



    // -------------------------------------------------------------------------
    // XXX Executing queries
    // -------------------------------------------------------------------------

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * results.
     *
     * @param query The query to execute
     * @return The result. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see ResultQuery#fetch()
     */
    @NotNull
    @Support
    <R extends Record> Result<R> fetch(ResultQuery<R> query) throws DataAccessException;

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * a cursor.
     *
     * @param query The query to execute
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see ResultQuery#fetchLazy()
     */
    @NotNull
    @Support
    <R extends Record> Cursor<R> fetchLazy(ResultQuery<R> query) throws DataAccessException;

    /**
     * Fetch results in a new {@link CompletionStage}.
     * <p>
     * The result is asynchronously completed by a task running in an
     * {@link Executor} provided by the {@link #configuration()}'s
     * {@link Configuration#executorProvider()}.
     *
     * @param query The query to execute
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see ResultQuery#fetchAsync()
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(ResultQuery<R> query);

    /**
     * Fetch results in a new {@link CompletionStage} that is asynchronously
     * completed by a task running in the given executor.
     *
     * @param query The query to execute
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     * @see ResultQuery#fetchAsync()
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, ResultQuery<R> query);

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * a stream.
     *
     * @param query The query to execute
     * @return The stream
     * @throws DataAccessException if something went wrong executing the query
     * @see ResultQuery#stream()
     */
    @NotNull
    @Support
    <R extends Record> Stream<R> fetchStream(ResultQuery<R> query) throws DataAccessException;

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * a cursor.
     *
     * @param query The query to execute
     * @return The results. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @see ResultQuery#fetchMany()
     */
    @NotNull
    @Support
    <R extends Record> Results fetchMany(ResultQuery<R> query) throws DataAccessException;

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * a record.
     *
     * @param query The query to execute
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see ResultQuery#fetchOne()
     */
    @Nullable
    @Support
    <R extends Record> R fetchOne(ResultQuery<R> query) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * a record.
     *
     * @param query The query to execute
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned no rows
     * @throws TooManyRowsException if the query returned more than one record
     * @see ResultQuery#fetchSingle()
     */
    @NotNull
    @Support
    <R extends Record> R fetchSingle(ResultQuery<R> query) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute a {@link ResultQuery} in the context of this <code>DSLContext</code> and return
     * a record.
     *
     * @param query The query to execute
     * @return The record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @see ResultQuery#fetchOptional()
     */
    @NotNull
    @Support
    <R extends Record> Optional<R> fetchOptional(ResultQuery<R> query) throws DataAccessException, TooManyRowsException;

    /**
     * Fetch a single value from a single column table.
     *
     * @param table The table from which to fetch a value
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <T> T fetchValue(Table<? extends Record1<T>> table) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a {@link ResultQuery} in the context of this
     * <code>DSLContext</code> and return a single value.
     *
     * @param query The query to execute
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <T, R extends Record1<T>> T fetchValue(ResultQuery<R> query) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a {@link ResultQuery} in the context of this
     * <code>DSLContext</code> and return a single value.
     *
     * @param field The field for which to fetch a single value.
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <T> T fetchValue(TableField<?, T> field) throws DataAccessException, TooManyRowsException;

    /**
     * Execute a {@link ResultQuery} in the context of this
     * <code>DSLContext</code> and return a single value.
     *
     * @param field The field for which to fetch a single value.
     * @return The value or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    @Nullable
    @Support
    <T> T fetchValue(Field<T> field) throws DataAccessException;

    /**
     * Execute a {@link ResultQuery} in the context of this
     * <code>DSLContext</code> and return a single value.
     *
     * @param query The query to execute
     * @return The value.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T, R extends Record1<T>> Optional<T> fetchOptionalValue(ResultQuery<R> query) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Execute a {@link ResultQuery} in the context of this
     * <code>DSLContext</code> and return a single value.
     *
     * @param field The field for which to fetch a single value.
     * @return The value.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     * @throws InvalidResultException if the query returned a record with more
     *             than one value
     */
    @NotNull
    @Support
    <T> Optional<T> fetchOptionalValue(TableField<?, T> field) throws DataAccessException, TooManyRowsException, InvalidResultException;

    /**
     * Fetch all values from a single column table.
     *
     * @param table The table from which to fetch values
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T> List<T> fetchValues(Table<? extends Record1<T>> table) throws DataAccessException;

    /**
     * Execute a {@link ResultQuery} in the context of this
     * <code>DSLContext</code> and return all values for the only column.
     *
     * @param query The query to execute
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T, R extends Record1<T>> List<T> fetchValues(ResultQuery<R> query) throws DataAccessException;

    /**
     * Fetch all values in a given {@link Table}'s {@link TableField}.
     *
     * @param field The field for which to fetch all values.
     * @return The values. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T> List<T> fetchValues(TableField<?, T> field) throws DataAccessException;

    /**
     * Execute a "Query by Example" (QBE) based on an example record.
     *
     * @param example The example record
     * @return The resulting records matching the example record.
     * @throws DataAccessException if something went wrong executing the query
     * @see DSL#condition(Record)
     */
    @NotNull
    @Support
    <R extends TableRecord<R>> Result<R> fetchByExample(R example) throws DataAccessException;

    /**
     * Execute a {@link Select} query in the context of this <code>DSLContext</code> and return
     * a <code>COUNT(*)</code> value.
     * <p>
     * This wraps a pre-existing <code>SELECT</code> query in another one to
     * calculate the <code>COUNT(*)</code> value, without modifying the original
     * <code>SELECT</code>. An example: <code><pre>
     * -- Original query:
     * SELECT id, title FROM book WHERE title LIKE '%a%'
     *
     * -- Wrapped query:
     * SELECT count(*) FROM (
     *   SELECT id, title FROM book WHERE title LIKE '%a%'
     * )
     * </pre></code> This is particularly useful for those databases that do not
     * support the <code>COUNT(*) OVER()</code> window function to calculate
     * total results in paged queries.
     *
     * @param query The wrapped query
     * @return The <code>COUNT(*)</code> result
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int fetchCount(Select<?> query) throws DataAccessException;

    /**
     * Count the number of records in a table.
     * <p>
     * This executes <code><pre>SELECT COUNT(*) FROM table</pre></code>
     *
     * @param table The table whose records to count
     * @return The number of records in the table
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int fetchCount(Table<?> table) throws DataAccessException;

    /**
     * Count the number of records in a table that satisfy a condition.
     * <p>
     * This executes <code><pre>SELECT COUNT(*) FROM table WHERE condition</pre></code>
     *
     * @param table The table whose records to count
     * @param condition The condition to apply
     * @return The number of records in the table that satisfy a condition
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int fetchCount(Table<?> table, Condition condition) throws DataAccessException;

    /**
     * Count the number of records in a table that satisfy a condition.
     * <p>
     * This executes
     * <code><pre>SELECT COUNT(*) FROM table WHERE condition</pre></code>
     * <p>
     * Convenience API for calling {@link #fetchCount(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @param table The table whose records to count
     * @param conditions The conditions to apply
     * @return The number of records in the table that satisfy a condition
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int fetchCount(Table<?> table, Condition... conditions) throws DataAccessException;

    /**
     * Count the number of records in a table that satisfy a condition.
     * <p>
     * This executes
     * <code><pre>SELECT COUNT(*) FROM table WHERE condition</pre></code>
     * <p>
     * Convenience API for calling {@link #fetchCount(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @param table The table whose records to count
     * @param conditions The conditions to apply
     * @return The number of records in the table that satisfy a condition
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int fetchCount(Table<?> table, Collection<? extends Condition> conditions) throws DataAccessException;

    /**
     * Check if a {@link Select} would return any records, if it were executed.
     * <p>
     * This wraps a pre-existing <code>SELECT</code> query in another one to
     * check for result existence, without modifying the original
     * <code>SELECT</code>. An example: <code><pre>
     * -- Original query:
     * SELECT id, title FROM book WHERE title LIKE '%a%'
     *
     * -- Wrapped query:
     * SELECT EXISTS (
     *   SELECT id, title FROM book WHERE title LIKE '%a%'
     * )
     * </pre></code>
     *
     * @param query The wrapped query
     * @return The <code>EXISTS(...)</code> result
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    boolean fetchExists(Select<?> query) throws DataAccessException;

    /**
     * Check if a table has any records.
     * <p>
     * This executes <code><pre>SELECT EXISTS(SELECT * FROM table)</pre></code>
     *
     * @param table The table whose records to count
     * @return Whether the table contains any records
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    boolean fetchExists(Table<?> table) throws DataAccessException;

    /**
     * Check if a table has any records that satisfy a condition.
     * <p>
     * This executes <code><pre>SELECT EXISTS(SELECT * FROM table WHERE condition)</pre></code>
     *
     * @param table The table whose records to count
     * @return Whether the table contains any records that satisfy a condition
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    boolean fetchExists(Table<?> table, Condition condition) throws DataAccessException;

    /**
     * Check if a table has any records that satisfy a condition.
     * <p>
     * This executes <code><pre>SELECT EXISTS(SELECT * FROM table WHERE condition)</pre></code>
     * <p>
     * Convenience API for calling {@link #fetchExists(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @param table The table whose records to count
     * @return Whether the table contains any records that satisfy a condition
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    boolean fetchExists(Table<?> table, Condition... conditions) throws DataAccessException;

    /**
     * Check if a table has any records that satisfy a condition.
     * <p>
     * This executes <code><pre>SELECT EXISTS(SELECT * FROM table WHERE condition)</pre></code>
     * <p>
     * Convenience API for calling {@link #fetchExists(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @param table The table whose records to count
     * @return Whether the table contains any records that satisfy a condition
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    boolean fetchExists(Table<?> table, Collection<? extends Condition> conditions) throws DataAccessException;

    /**
     * Execute a {@link Query} in the context of this <code>DSLContext</code>.
     *
     * @param query The query to execute
     * @return The number of affected rows
     * @throws DataAccessException if something went wrong executing the query
     * @see Query#execute()
     */
    @Support
    int execute(Query query) throws DataAccessException;

    // -------------------------------------------------------------------------
    // XXX Fast querying
    // -------------------------------------------------------------------------

    /**
     * Execute and return all records for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Result<R> fetch(Table<R> table) throws DataAccessException;

    /**
     * Execute and return all records for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Result<R> fetch(Table<R> table, Condition condition) throws DataAccessException;

    /**
     * Execute and return all records for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetch(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Result<R> fetch(Table<R> table, Condition... conditions) throws DataAccessException;

    /**
     * Execute and return all records for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetch(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The results from the executed query. This will never be
     *         <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Result<R> fetch(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <R extends Record> R fetchOne(Table<R> table) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <R extends Record> R fetchOne(Table<R> table, Condition condition) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchOne(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <R extends Record> R fetchOne(Table<R> table, Condition... conditions) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchOne(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The record or <code>null</code>, if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @Nullable
    @Support
    <R extends Record> R fetchOne(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned now rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> R fetchSingle(Table<R> table) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned now rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> R fetchSingle(Table<R> table, Condition condition) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned now rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> R fetchSingle(Table<R> table, Condition... conditions) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     * @throws NoDataFoundException if the query returned now rows
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> R fetchSingle(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException, NoDataFoundException, TooManyRowsException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Record fetchSingle(SelectField<?>... fields) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    Record fetchSingle(Collection<? extends SelectField<?>> fields) throws DataAccessException;



    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1> Record1<T1> fetchSingle(SelectField<T1> field1) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2> Record2<T1, T2> fetchSingle(SelectField<T1> field1, SelectField<T2> field2) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3> Record3<T1, T2, T3> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4> Record4<T1, T2, T3, T4> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5> Record5<T1, T2, T3, T4, T5> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6> Record6<T1, T2, T3, T4, T5, T6> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7> Record7<T1, T2, T3, T4, T5, T6, T7> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8> Record8<T1, T2, T3, T4, T5, T6, T7, T8> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9> Record9<T1, T2, T3, T4, T5, T6, T7, T8, T9> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Record10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Record12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> Record13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> Record14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> Record15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> Record16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> Record17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> Record18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> Record19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> Record20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> Record21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20, SelectField<T21> field21) throws DataAccessException;

    /**
     * Execute and return exactly one record for
     * <code><pre>SELECT F1, F2, ..., FN</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchSingle(ResultQuery)} with
     * {@link DSL#select(SelectFieldOrAsterisk...)}.
     *
     * @return The record. This is never <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> Record22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> fetchSingle(SelectField<T1> field1, SelectField<T2> field2, SelectField<T3> field3, SelectField<T4> field4, SelectField<T5> field5, SelectField<T6> field6, SelectField<T7> field7, SelectField<T8> field8, SelectField<T9> field9, SelectField<T10> field10, SelectField<T11> field11, SelectField<T12> field12, SelectField<T13> field13, SelectField<T14> field14, SelectField<T15> field15, SelectField<T16> field16, SelectField<T17> field17, SelectField<T18> field18, SelectField<T19> field19, SelectField<T20> field20, SelectField<T21> field21, SelectField<T22> field22) throws DataAccessException;



    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> Optional<R> fetchOptional(Table<R> table) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> Optional<R> fetchOptional(Table<R> table, Condition condition) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchOptional(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> Optional<R> fetchOptional(Table<R> table, Condition... conditions) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchOptional(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The record
     * @throws DataAccessException if something went wrong executing the query
     * @throws TooManyRowsException if the query returned more than one record
     */
    @NotNull
    @Support
    <R extends Record> Optional<R> fetchOptional(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException, TooManyRowsException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table LIMIT 1</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record or <code>null</code> if no record was returned
     * @throws DataAccessException if something went wrong executing the query
     */
    @Nullable
    @Support
    <R extends Record> R fetchAny(Table<R> table) throws DataAccessException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition LIMIT 1</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     *
     * @return The record or <code>null</code> if no record was returned
     * @throws DataAccessException if something went wrong executing the query
     */
    @Nullable
    @Support
    <R extends Record> R fetchAny(Table<R> table, Condition condition) throws DataAccessException;

    /**
     * Execute and return zero or on e record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition LIMIT 1</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchAny(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The record or <code>null</code> if no record was returned
     * @throws DataAccessException if something went wrong executing the query
     */
    @Nullable
    @Support
    <R extends Record> R fetchAny(Table<R> table, Condition... conditions) throws DataAccessException;

    /**
     * Execute and return zero or one record for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition LIMIT 1</pre></code>.
     * <p>
     * The resulting record is attached to this {@link Configuration} by
     * default. Use {@link Settings#isAttachRecords()} to override this
     * behaviour.
     * <p>
     * Convenience API for calling {@link #fetchAny(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The record or <code>null</code> if no record was returned
     * @throws DataAccessException if something went wrong executing the query
     */
    @Nullable
    @Support
    <R extends Record> R fetchAny(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Cursor<R> fetchLazy(Table<R> table) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Cursor<R> fetchLazy(Table<R> table, Condition condition) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchLazy(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Cursor<R> fetchLazy(Table<R> table, Condition... conditions) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchLazy(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The cursor. This will never be <code>null</code>.
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Cursor<R> fetchLazy(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException;

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Table<R> table);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Table<R> table, Condition condition);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchAsync(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Table<R> table, Condition... condition);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchAsync(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Table<R> table, Collection<? extends Condition> condition);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, Table<R> table);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, Table<R> table, Condition condition);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchAsync(Executor, Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, Table<R> table, Condition... conditions);

    /**
     * Execute and return all records asynchronously for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchAsync(Executor, Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @return The completion stage. The completed result will never be
     *         <code>null</code>.
     */
    @NotNull
    @Support
    <R extends Record> CompletionStage<Result<R>> fetchAsync(Executor executor, Table<R> table, Collection<? extends Condition> conditions);

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Stream<R> fetchStream(Table<R> table) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Stream<R> fetchStream(Table<R> table, Condition condition) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchStream(Table, Condition)} with
     * {@link DSL#and(Condition...)}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Stream<R> fetchStream(Table<R> table, Condition... conditions) throws DataAccessException;

    /**
     * Execute and return all records lazily for
     * <code><pre>SELECT table.col1, table.col2 FROM table WHERE condition</pre></code>.
     * <p>
     * The result and its contained records are attached to this
     * {@link Configuration} by default. Use {@link Settings#isAttachRecords()}
     * to override this behaviour.
     * <p>
     * Convenience API for calling {@link #fetchStream(Table, Condition)} with
     * {@link DSL#and(Collection)}.
     *
     * @throws DataAccessException if something went wrong executing the query
     */
    @NotNull
    @Support
    <R extends Record> Stream<R> fetchStream(Table<R> table, Collection<? extends Condition> conditions) throws DataAccessException;

    /**
     * Insert one record.
     * <p>
     * This executes something like the following statement:
     * <code><pre>INSERT INTO [table] ... VALUES [record] </pre></code>
     * <p>
     * Unlike {@link UpdatableRecord#store()}, this does not change any of the
     * argument <code>record</code>'s internal "changed" flags, such that a
     * subsequent call to {@link UpdatableRecord#store()} might lead to another
     * <code>INSERT</code> statement being executed.
     *
     * @return The number of inserted records
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int executeInsert(TableRecord<?> record) throws DataAccessException;

    /**
     * Update a table.
     * <code><pre>UPDATE [table] SET [modified values in record] WHERE [record is supplied record] </pre></code>
     *
     * @return The number of updated records
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int executeUpdate(UpdatableRecord<?> record) throws DataAccessException;

    /**
     * Update a table.
     * <code><pre>UPDATE [table] SET [modified values in record] WHERE [condition]</pre></code>
     *
     * @return The number of updated records
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int executeUpdate(TableRecord<?> record, Condition condition) throws DataAccessException;

    /**
     * Delete a record from a table.
     * <code><pre>DELETE FROM [table] WHERE [record is supplied record]</pre></code>
     *
     * @return The number of deleted records
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int executeDelete(UpdatableRecord<?> record) throws DataAccessException;

    /**
     * Delete a record from a table.
     * <code><pre>DELETE FROM [table] WHERE [condition]</pre></code>
     *
     * @return The number of deleted records
     * @throws DataAccessException if something went wrong executing the query
     */
    @Support
    int executeDelete(TableRecord<?> record, Condition condition) throws DataAccessException;
}
