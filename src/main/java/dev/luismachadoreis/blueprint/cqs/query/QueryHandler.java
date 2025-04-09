package dev.luismachadoreis.blueprint.cqs.query;

/**
 * QueryHandler is a handler for queries.
 * It is used to handle queries in the system.
 */
public interface QueryHandler<Q extends Query<T>, T> {
    T handle(Q query);
} 