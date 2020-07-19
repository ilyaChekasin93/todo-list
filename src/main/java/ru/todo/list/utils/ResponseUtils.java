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
        responseModel.setMessage(SUCCESS_MESSAGE);
        return responseModel;
    }

    public static ResponseModel success(String responseBody, Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage(SUCCESS_MESSAGE);
        return responseModel;
    }

    public static ResponseModel created(Response response) {
        response.status(HTTP_CREATED);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage(CREATED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel created(String responseBody, Response response) {
        response.status(HTTP_CREATED);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage(CREATED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel updated(String responseBody, Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage(UPDATED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel updated(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage(UPDATED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel deleted(String responseBody, Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage(DELETED_MESSAGE);
        return responseModel;
    }

    public static ResponseModel deleted(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage(DELETED_MESSAGE);
        return responseModel;
    }

}
