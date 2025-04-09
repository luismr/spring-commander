package dev.luismachadoreis.blueprint.cqs.query;

/**
 * Interface for handling queries in the Command-Query Separation (CQS) pattern.
 * Query handlers are responsible for executing queries and returning their results.
 *
 * @param <Q> The type of query to handle, must extend Query<T>
 * @param <T> The type of the result returned by the query execution
 */
public interface QueryHandler<Q extends Query<T>, T> {
    /**
     * Handles the given query and returns its result.
     *
     * @param query The query to handle
     * @return The result of the query execution
     */
    T handle(Q query);
} 