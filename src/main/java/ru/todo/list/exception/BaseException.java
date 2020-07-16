package ru.todo.list.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    protected int statusCode;

    public BaseException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

}
