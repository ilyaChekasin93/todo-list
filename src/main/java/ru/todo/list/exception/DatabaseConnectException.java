package ru.todo.list.exception;

public class DatabaseConnectException extends DatabaseException {

    public DatabaseConnectException(String url, String error) {
        super(String.format("Error connection to database: %s with message: %s", url, error));
    }
}
