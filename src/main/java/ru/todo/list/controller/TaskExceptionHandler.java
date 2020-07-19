package ru.todo.list.controller;

import com.google.inject.Singleton;
import ru.todo.list.exception.BaseException;
import ru.todo.list.model.ErrorResponseModel;
import ru.todo.list.utils.DateUtils;
import ru.todo.list.utils.JsonUtils;
import spark.ExceptionHandler;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;


@Singleton
public class TaskExceptionHandler {

    public ExceptionHandler defaultExceptionHandler = (ex, req, resp) -> {
        ErrorResponseModel errorResponse = new ErrorResponseModel();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTime(DateUtils.getCurrentDate());

        String strBody = JsonUtils.jsonObj2String(errorResponse);
        int statusCode = getStatusCode(ex);

        resp.status(statusCode);
        resp.body(strBody);
    };

    private int getStatusCode(Exception ex){
        int statusCode;
        try {
            statusCode = ((BaseException) ex).getStatusCode();
        }catch (ClassCastException e){
            statusCode = HTTP_INTERNAL_ERROR;
        }

        return statusCode;
    }

}
