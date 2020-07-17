package ru.todo.list.exception;

public class CreateTableException extends DatabaseException {

    public CreateTableException(String tableName, String error) {
        super(String.format("Error creating table %s with message: %s", tableName, error));
    }

}
