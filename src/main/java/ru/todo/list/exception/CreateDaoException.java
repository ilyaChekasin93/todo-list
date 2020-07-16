package ru.todo.list.exception;

public class CreateDaoException extends DatabaseException {

    public CreateDaoException(String name, String error) {
        super(String.format("Error creating Dao %s with message: %s", name, error));
    }

}
