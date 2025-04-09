package dev.luismachadoreis.blueprint.cqs;

import dev.luismachadoreis.blueprint.cqs.command.Command;
import dev.luismachadoreis.blueprint.cqs.command.CommandHandler;
import dev.luismachadoreis.blueprint.cqs.query.Query;
import dev.luismachadoreis.blueprint.cqs.query.QueryHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpringCommanderMediatorTest {

    @Mock
    private ApplicationContext applicationContext;

    private SpringCommanderMediator mediator;

    // Test Command and Handler
    record TestCommand(String data) implements Command<String> {}

    @org.springframework.stereotype.Component
    static class TestCommandHandler implements CommandHandler<TestCommand, String> {
        @Override
        public String handle(TestCommand command) {
            return "Handled: " + command.data();
        }
    }

    // Test Query and Handler
    record TestQuery(int limit) implements Query<Integer> {}

    @org.springframework.stereotype.Component
    static class TestQueryHandler implements QueryHandler<TestQuery, Integer> {
        @Override
        public Integer handle(TestQuery query) {
            return query.limit() * 2;
        }
    }

    @BeforeEach
    void setUp() {
        mediator = new SpringCommanderMediator(applicationContext);
    }

    @Test
    void shouldHandleCommand() {
        // Given
        TestCommand command = new TestCommand("test");
        TestCommandHandler handler = new TestCommandHandler();
        when(applicationContext.getBean(CommandHandler.class))
                .thenReturn(handler);

        // When
        String result = mediator.send(command);

        // Then
        assertThat(result).isEqualTo("Handled: test");
    }

    @Test
    void shouldHandleQuery() {
        // Given
        TestQuery query = new TestQuery(5);
        TestQueryHandler handler = new TestQueryHandler();
        when(applicationContext.getBean(QueryHandler.class))
                .thenReturn(handler);

        // When
        Integer result = mediator.send(query);

        // Then
        assertThat(result).isEqualTo(10);
    }

    @Test
    void shouldThrowExceptionWhenNoCommandHandlerFound() {
        // Given
        TestCommand command = new TestCommand("test");
        when(applicationContext.getBean(CommandHandler.class))
                .thenReturn(null);

        // When/Then
        assertThatThrownBy(() -> mediator.send(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No handler found for command");
    }

    @Test
    void shouldThrowExceptionWhenNoQueryHandlerFound() {
        // Given
        TestQuery query = new TestQuery(5);
        when(applicationContext.getBean(QueryHandler.class))
                .thenReturn(null);

        // When/Then
        assertThatThrownBy(() -> mediator.send(query))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No handler found for query");
    }

    @Test
    void shouldThrowExceptionWhenCommandIsNull() {
        // When/Then
        assertThatThrownBy(() -> mediator.send((Command<String>) null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Command cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenQueryIsNull() {
        // When/Then
        assertThatThrownBy(() -> mediator.send((Query<String>) null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Query cannot be null");
    }
} 