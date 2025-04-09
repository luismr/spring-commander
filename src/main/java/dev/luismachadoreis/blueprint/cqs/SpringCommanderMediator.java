package dev.luismachadoreis.blueprint.cqs;

import dev.luismachadoreis.blueprint.cqs.command.Command;
import dev.luismachadoreis.blueprint.cqs.command.CommandHandler;
import dev.luismachadoreis.blueprint.cqs.query.Query;
import dev.luismachadoreis.blueprint.cqs.query.QueryHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * SpringCommanderMediator is a mediator that uses Spring to find and execute command and query handlers.
 * It is a component that is used to send commands and queries to the system.
 */
@Component
public class SpringCommanderMediator {
 
    private final ApplicationContext applicationContext;

    public SpringCommanderMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Sends a command to the system.
     * @param command The command to send.
     * @return The result of the command.
     */
    public <T> T send(Command<T> command) {
        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }

        String[] beanNames = applicationContext.getBeanNamesForType(CommandHandler.class);
        if (beanNames.length == 0) {
            throw new IllegalStateException("No handler found for command");
        }

        @SuppressWarnings("unchecked")
        CommandHandler<Command<T>, T> handler = (CommandHandler<Command<T>, T>) applicationContext.getBean(beanNames[0]);
        return handler.handle(command);
    }

    /**
     * Sends a query to the system.
     * @param query The query to send.
     * @return The result of the query.
     */
    public <T> T send(Query<T> query) {
        if (query == null) {
            throw new NullPointerException("Query cannot be null");
        }

        String[] beanNames = applicationContext.getBeanNamesForType(QueryHandler.class);
        if (beanNames.length == 0) {
            throw new IllegalStateException("No handler found for query");
        }

        @SuppressWarnings("unchecked")
        QueryHandler<Query<T>, T> handler = (QueryHandler<Query<T>, T>) applicationContext.getBean(beanNames[0]);
        return handler.handle(query);
    }
} 