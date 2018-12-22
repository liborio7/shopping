# Sales taxes


A RESTful API to calculate sales taxes for an item list.
The items will be stored into an in-memory database.
On server startup, all data structures will be created if not already present.

The API were implemented in **Java 8** using **Gradle** for build automation and dependency management.

See [API documentation](/apidoc.md) for further details. 


### Libraries

- **Grizzly 2 Http Server Container**, an embedded server with **HK2** for dependency injection
- **Jersey JSON Jackson**, for JSON serialization and deserialization
- **H2 Database Engine**, an in-memory database
- **MyBatis SQL mapper framework**, a persistence framework with support for custom SQL
- **Logback classic module**, for logging
- **Apache Commons Lang**, helper utilities for the java.lang API
- **JUnit testing framework**, for unit tests
- **AssertJ core**, for fluent assertions
- **Mockito core**, a mocking framework for unit tests
- **Random Beans core implementation**, to generate random java beans for testing purpose
- **Jersey Test Framework**, for integration tests


### Features

- Get all stored items
- Calculate sales taxes for an item list
- Separation of persistence model, business logic, and HTTP resources layers
- RESTful HTTP response codes
- Unit tests
- Integration tests


### Requirements

- Java
- Gradle


### Get started

1. Clone GIT repository: ``git clone https://github.com/liborio7/shopping.git``
2. CD into the project directory to build the project: ``./gradlew build``
3. Run tests: ``./gradlew test``
4. Run server: ``./gradlew run``


### Future improvements

- Create new items
- Store created sales taxes
- Retrieve an already calculated sales taxes
- Add a new item to sales taxes
- Remove an item from sales taxes
- Edit item amount on sales taxes
- Multiple currency and exchange rate on sales taxes