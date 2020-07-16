package ru.todo.list.handler;

import spark.Request;
import spark.Response;

import static java.net.HttpURLConnection.HTTP_OK;
import static ru.todo.list.utils.JsonUtils.jsonObj2String;
import static ru.todo.list.utils.JsonUtils.json2Obj;


@FunctionalInterface
public interface ModelHandlerFunction<T> {

    default String handleRequest(Request request, Response response, Class<T> clazz) {
        String strBody = request.body();
        T body = json2Obj(strBody, clazz);
        Object responseBody = process(body);
        response.status(HTTP_OK);

        return jsonObj2String(responseBody);
    }

    Object process(T data);

}
