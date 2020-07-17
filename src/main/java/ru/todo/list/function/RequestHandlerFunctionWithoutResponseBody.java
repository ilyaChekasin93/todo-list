package ru.todo.list.function;

import ru.todo.list.model.RequestModel;
import spark.QueryParamsMap;
import spark.Request;

import java.util.Map;

import static ru.todo.list.utils.JsonUtils.json2Obj;


@FunctionalInterface
public interface RequestHandlerFunctionWithoutResponseBody<T> {

    default void handleRequest(Request request, Class<T> clazz) {
        String strRequestBody = request.body();
        T body = json2Obj(strRequestBody, clazz);

        QueryParamsMap queryParamsMap = request.queryMap();
        Map<String, String> urlParams = request.params();

        RequestModel<T> requestModel = new RequestModel<>(body, urlParams, queryParamsMap);
        process(requestModel);
    }

    default void handleRequest(Request request) {
        QueryParamsMap queryParamsMap = request.queryMap();
        Map<String, String> urlParams = request.params();

        RequestModel requestModel = new RequestModel(urlParams, queryParamsMap);
        process(requestModel);
    }

    void process(RequestModel<T> requestModel);

}
