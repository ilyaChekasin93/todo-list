package ru.todo.list.exception;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class TaskNotFoundException extends BaseException {

    public TaskNotFoundException(String taskName) {
        super(String.format("Task with name %s not found", taskName), HTTP_NOT_FOUND);
    }

    public TaskNotFoundException() {
        super(String.format("Task not found"), HTTP_NOT_FOUND);
    }
}
