package ru.todo.list.utils;

import ru.todo.list.model.ResponseModel;
import spark.Response;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class ResponseUtils {

    public static ResponseModel success(String responseBody, Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage("Success");
        return responseModel;
    }

    public static ResponseModel created(Response response) {
        response.status(HTTP_CREATED);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("Successfully created");
        return responseModel;
    }

    public static ResponseModel created(String responseBody, Response response) {
        response.status(HTTP_CREATED);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage("Successfully created");
        return responseModel;
    }

    public static ResponseModel updated(String responseBody, Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage("Successfully updated");
        return responseModel;
    }

    public static ResponseModel updated(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("Successfully updated");
        return responseModel;
    }

    public static ResponseModel deleted(String responseBody, Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setBody(responseBody);
        responseModel.setMessage("Successfully deleted");
        return responseModel;
    }

    public static ResponseModel deleted(Response response) {
        response.status(HTTP_OK);
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage("Successfully deleted");
        return responseModel;
    }

}
