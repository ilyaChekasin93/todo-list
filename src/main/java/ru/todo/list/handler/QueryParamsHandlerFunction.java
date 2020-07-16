package ru.todo.list.handler;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

import static java.net.HttpURLConnection.HTTP_OK;
import static ru.todo.list.utils.JsonUtils.jsonObj2String;


@FunctionalInterface
public interface QueryParamsHandlerFunction {

    default String handleRequest(Request request, Response response) {
        QueryParamsMap queryParamsMap = request.queryMap();
        Object responseBody = process(queryParamsMap);
        response.status(HTTP_OK);

        return jsonObj2String(responseBody);
    }

    Object process(QueryParamsMap data);

}
