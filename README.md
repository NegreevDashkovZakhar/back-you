# Back-you application

It is a Spring application that allows client to manage and access their own database without any back-end development.
Analogue for Back4App (see https://www.back4app.com/)

## Project Purpose

Was developed as a project for the university and portfolio (is not monitized in any aspect)

## Structure

Application has 3 layers

1. Controllers
2. Services
3. Repositories

Each has its own purpose

### Controllers purpose

Controllers are mapped to respond to requests by passing them to service layer.  
Also, controllers decompose request arguments.

### Services purpose

Services standardize received data in order for it to be understandable, which users have access to which tables.  
Currently, concatenates users api key and table name to get resulting table name like this pattern
*api-key__table-name*.  
This ensures that tables belonging to different api keys do not overlap.  
Afterwards services pass arguments to repositories

### Repositories purpose

Repositories holds logic for constructing sql queries from requests and execute them using dedicated class
(QueryExecutor.java)

## Error handling and error codes

Errors are mostly thrown when sql query can not be executed (otherwise if error occurs it is classified as unknown)  
So query executor holds a map with database error codes and exceptions that must be thrown  
Every such exception is a ResponseStatusException which means that it holds data for http response (code and message
most of the time) and is an unchecked exception(that is why it is unnecessary to specify them throughout all application
layers).  
Throwing unchecked exceptions in such scenarios when specified table does not exist is simpler and exhaustive
(because we can not do anything to correct such error)