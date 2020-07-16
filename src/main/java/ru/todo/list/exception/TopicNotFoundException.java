package ru.todo.list.exception;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class TopicNotFoundException extends BaseException {

    public TopicNotFoundException(String taskName){
        super(String.format("Topic with name %s not found", taskName), HTTP_NOT_FOUND);
    }

}
