package ru.todo.list.function;

import ru.todo.list.model.RequestModel;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

import java.util.Map;

import static ru.todo.list.utils.JsonUtils.jsonObj2String;
import static ru.todo.list.utils.JsonUtils.json2Obj;


@FunctionalInterface
public interface RequestHandlerFunctionWithResponseBody<T> {

    default void handleRequest(Request request, Response response, Class<T> clazz) {
        String strRequestBody = request.body();
        T body = json2Obj(strRequestBody, clazz);

        QueryParamsMap queryParamsMap = request.queryMap();
        Map<String, String> urlParams = request.params();

        RequestModel<T> requestModel = new RequestModel<>(body, urlParams, queryParamsMap);
        Object responseBody = process(requestModel);
        String strResponseBody =jsonObj2String(responseBody);

        response.body(strResponseBody);
    }

    default void handleRequest(Request request, Response response) {
        QueryParamsMap queryParamsMap = request.queryMap();
        Map<String, String> urlParams = request.params();

        RequestModel requestModel = new RequestModel(urlParams, queryParamsMap);
        Object responseBody = process(requestModel);

        String strResponseBody =jsonObj2String(responseBody);

        response.body(strResponseBody);
    }

    Object process(RequestModel<T> requestModel);

}
