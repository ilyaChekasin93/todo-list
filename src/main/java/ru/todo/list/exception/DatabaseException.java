package ru.todo.list.exception;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

public class DatabaseException extends BaseException {

    public DatabaseException(String message){
        super(message, HTTP_INTERNAL_ERROR);
    }
}
