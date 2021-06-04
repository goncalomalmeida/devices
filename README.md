# How to build
This app is built on Java 11 so it needs to be installed locally.

I'm using maven as a built tool so just run the following command at the root:

```./mvnw -q clean install -DskipTests=true```

# How to run tests
To test the project just run the following maven command at the root:

```./mvnw -q clean install test```

# How to run
After building the project, run the following command at the root of the project in order to have it running:

```docker-compose up```

This will create two containers:
* One *devices* database
* One backend server running on port *8080*

# How to try it out
The backend is exposing the API on the following endpoint (using OpenAPI v3):

``` http://localhost:8080/api/swagger-ui.html ```


# How is it organized
This project is structured in a [Hexagonal Architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
 * ports (APIs/Interfaces for the domain to use)
 * adapters (implementations for the above APIs)
 
This clear separation of duties prevents developers from adding project dependencies that shouldn't be there (i.e. one cannot access the database directly from a REST controller).
The major downside of following the *Dependency Inversion Principle* to the limit is the amount of converters that the application needs 
to maintain.
  
## Technical decisions
* Followed [this RFC](https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html) for deciding the best HTTP verbs for each operation
* Added pagination to the device listing API  
* Using flywaydb for database migrations
* Using an in-memory database for tests (ideally we would set up a docker)

# Possible improvements
* More error handling on API calls
* Add full test coverage
* Javadoc
* More & better OpenAPI documentation
* Authentication/authorization
* Add an index to the 'brand' column for better performance


