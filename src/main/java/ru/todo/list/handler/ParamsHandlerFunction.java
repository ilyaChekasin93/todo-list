package ru.todo.list.handler;

import spark.Request;
import spark.Response;

import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;
import static ru.todo.list.utils.JsonUtils.jsonObj2String;


@FunctionalInterface
public interface ParamsHandlerFunction {

    default String handleRequest(Request request, Response response) {
        Map<String, String> params = request.params();
        Object responseBody = process(params);
        response.status(HTTP_OK);

        return jsonObj2String(responseBody);
    }

    Object process(Map<String, String> params);

}
