package ru.todo.list.utils;

import ru.todo.list.model.ResponseModel;
import spark.Response;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;


public class ResponseUtils {

    public static final String SUCCESS_MESSAGE = "Success";
    public static final String CREATED_MESSAGE = "Successfully created";
    public static final String UPDATED_MESSAGE = "Successfully updated";
    public static final String DELETED_MESSAGE = "Successfully deleted";


    public static ResponseModel success(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        String responseBody = response.body();
        responseModel.setContent(responseBody);
        responseModel.setMessage(SUCCESS_MESSAGE);
        return responseModel;
    }

    public static ResponseModel created(Response response) {
        response.status(HTTP_CREATED);
        ResponseModel responseModel = new ResponseModel();
        String responseBody = response.body();
        responseModel.setContent(responseBody);
        responseModel.setMessage(CREATED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel updated(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        String responseBody = response.body();
        responseModel.setContent(responseBody);
        responseModel.setMessage(UPDATED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel deleted(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        String responseBody = response.body();
        responseModel.setContent(responseBody);
        responseModel.setMessage(DELETED_MESSAGE);
        return responseModel;
    }
}
