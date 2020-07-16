package ru.todo.list.exception;

public class DatabaseOperationException extends DatabaseException {

    public DatabaseOperationException(String error) {
        super(String.format("Error SQL operation with message: %s", error));
    }
}
