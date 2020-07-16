package ru.todo.list.exception;

import static java.net.HttpURLConnection.HTTP_CONFLICT;

public class TaskAlreadyExistException extends BaseException {

    public TaskAlreadyExistException(String taskName){
        super(String.format("Task with name %s already exist", taskName), HTTP_CONFLICT);
    }
}
