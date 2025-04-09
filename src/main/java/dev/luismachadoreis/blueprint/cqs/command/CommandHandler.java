package dev.luismachadoreis.blueprint.cqs.command;

/**
 * CommandHandler is a handler for commands.
 * It is used to handle commands in the system.
 */
public interface CommandHandler<C extends Command<T>, T> {
    T handle(C command);
} 