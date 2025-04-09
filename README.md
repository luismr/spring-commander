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
- Comprehensive test coverage
- GitHub Actions CI/CD pipeline

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

### Gradle

Add the dependency to your `build.gradle`:

```groovy
implementation 'dev.luismachadoreis.blueprint:spring-commander:0.0.1-SNAPSHOT'
```

## Usage

### Basic Setup

1. Add the dependency to your Spring Boot application's `pom.xml`:

```xml
<dependency>
    <groupId>dev.luismachadoreis.blueprint</groupId>
    <artifactId>spring-commander</artifactId>
    <version>0.0.1</version>
</dependency>
```

2. Create a command:

```java
public class CreateUserCommand implements Command<User> {
    private final String username;
    private final String email;

    public CreateUserCommand(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters
}
```

3. Create a command handler:

```java
@Component
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand, User> {
    @Override
    public User handle(CreateUserCommand command) {
        // Implementation
        return new User(command.getUsername(), command.getEmail());
    }
}
```

4. Use the mediator in your service:

```java
@Service
public class UserService {
    private final SpringCommanderMediator mediator;

    public UserService(SpringCommanderMediator mediator) {
        this.mediator = mediator;
    }

    public User createUser(String username, String email) {
        return mediator.send(new CreateUserCommand(username, email));
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

## Development

### Prerequisites

- Java 21 or later
- Maven 3.8 or later
- Git

### Building

```bash
git clone https://github.com/luismr/spring-commander-java.git
cd spring-commander-java
mvn clean install
```

### Testing

```bash
mvn test
```

### Code Coverage

Code coverage reports are generated automatically during the build process. You can find the reports in the `target/site/jacoco` directory.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## GitHub Actions

The project uses GitHub Actions for continuous integration and deployment. The workflow includes:

- Building and testing on multiple Java versions
- Code coverage reporting
- Automatic updates of code coverage badges
- Deployment to GitHub Packages

## Maven Release Process

### Prerequisites

1. Configure GitHub authentication in your Maven settings (`~/.m2/settings.xml`):

```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_TOKEN</password>
    </server>
  </servers>
</settings>
```

2. Create a GitHub Personal Access Token (PAT) with the following permissions:
   - `read:packages`
   - `write:packages`
   - `delete:packages`

### Performing a Release

1. Prepare the release:
```bash
mvn release:prepare
```
This will:
- Update version numbers
- Create a Git tag
- Update the SCM information

2. Perform the release:
```bash
mvn release:perform
```
This will:
- Checkout the tagged version
- Build and deploy to GitHub Packages
- Create source and Javadoc JARs

### Version Management

- SNAPSHOT versions are deployed to GitHub Packages automatically on each push to main
- Release versions are created manually using the release process
- Version format follows semantic versioning (MAJOR.MINOR.PATCH)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Author

- **Luis Machado Reis** - [luismr](https://github.com/luismr)

## Acknowledgments

- Spring Boot team for the amazing framework
- Martin Fowler for the CQS pattern
- All contributors and users of this project 