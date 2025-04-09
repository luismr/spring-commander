package dev.luismachadoreis.blueprint.cqs;

import dev.luismachadoreis.blueprint.cqs.command.Command;
import dev.luismachadoreis.blueprint.cqs.command.CommandHandler;
import dev.luismachadoreis.blueprint.cqs.query.Query;
import dev.luismachadoreis.blueprint.cqs.query.QueryHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Mediator implementation for handling commands and queries in a Spring Boot application.
 * This class is responsible for finding and executing the appropriate handlers for commands and queries.
 */
@Component
public class SpringCommanderMediator {
 
    private final ApplicationContext applicationContext;

    /**
     * Creates a new SpringCommanderMediator instance.
     *
     * @param applicationContext The Spring application context used to find command and query handlers
     */
    public SpringCommanderMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Sends a command to its handler and returns the result.
     *
     * @param <T> The type of the result returned by the command execution
     * @param command The command to execute
     * @return The result of the command execution
     * @throws IllegalStateException if no handler is found for the command
     */
    @SuppressWarnings("unchecked")
    public <T> T send(Command<T> command) {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }

        CommandHandler<Command<T>, T> handler = applicationContext.getBean(CommandHandler.class);
        if (handler == null) {
            throw new IllegalStateException("No handler found for command: " + command.getClass().getName());
        }

        return handler.handle(command);
    }

    /**
     * Sends a query to its handler and returns the result.
     *
     * @param <T> The type of the result returned by the query execution
     * @param query The query to execute
     * @return The result of the query execution
     * @throws IllegalStateException if no handler is found for the query
     */
    @SuppressWarnings("unchecked")
    public <T> T send(Query<T> query) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }

        QueryHandler<Query<T>, T> handler = applicationContext.getBean(QueryHandler.class);
        if (handler == null) {
            throw new IllegalStateException("No handler found for query: " + query.getClass().getName());
        }

        return handler.handle(query);
    }
} 