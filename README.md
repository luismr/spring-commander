# Spring Commander

![Java](https://img.shields.io/badge/Java-21-blue)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3.4.x-blue)
![JUnit](https://img.shields.io/badge/JUnit-5.x-blue)
![JaCoCo](https://img.shields.io/badge/JaCoCo-0.8.x-blue)

Spring Commander is a lightweight framework for implementing the Command-Query Separation (CQS) pattern in Java Spring Boot, powered by a clean Mediator architecture.

## Features

- Command-Query Separation (CQS) pattern implementation
- Clean Mediator architecture
- Spring Boot auto-configuration
- Type-safe command and query handling
- Easy integration with Spring Boot applications

## Build Status

[![Java CI with Maven](https://github.com/luismr/spring-commander/actions/workflows/maven.yml/badge.svg)](https://github.com/luismr/spring-commander/actions/workflows/maven.yml)
![Coverage](badges/jacoco.svg)
![Branches](badges/branches.svg)

## GitHub Actions Setup

To enable automatic updates of code coverage badges, you need to configure GitHub Actions permissions:

1. Go to your repository settings
2. Navigate to "Actions" → "General"
3. Under "Workflow permissions", select:
   - "Read and write permissions"
   - Check "Allow GitHub Actions to create and approve pull requests"
4. Save the changes

This will allow the GitHub Actions workflow to:
- Update code coverage badges
- Commit changes to the repository
- Create and update pull requests

## Getting Started

### Prerequisites

- Java 21
- Maven 3.6+
- Spring Boot 3.4.4+

### Installation

#### Using Git

```bash
git clone git@github.com:luismr/spring-commander.git
```

#### Using Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>dev.luismachadoreis.blueprint</groupId>
    <artifactId>spring-commander</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Usage

### Commands

1. Create a command:

```java
public class CreateUserCommand implements Command<User> {
    private String username;
    private String email;
    
    // Getters and setters
}
```

2. Create a command handler:

```java
@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {
    @Override
    public User handle(CreateUserCommand command) {
        // Implementation
    }
}
```

### Queries

1. Create a query:

```java
public class GetUserQuery implements Query<User> {
    private String username;
    
    // Getters and setters
}
```

2. Create a query handler:

```java
@Component
public class GetUserQueryHandler implements QueryHandler<GetUserQuery, User> {
    @Override
    public User handle(GetUserQuery query) {
        // Implementation
    }
}
```

### Using the Mediator

```java
@Autowired
private SpringMediator mediator;

public void example() {
    // Send a command
    User user = mediator.send(new CreateUserCommand("username", "email@example.com"));
    
    // Send a query
    User foundUser = mediator.send(new GetUserQuery("username"));
}
```

## Contributing

Contributions are welcome! If you'd like to contribute:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details. 