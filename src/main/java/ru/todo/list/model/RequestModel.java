package ru.todo.list.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spark.QueryParamsMap;

import java.util.Map;

import static ru.todo.list.constant.Path.PARAM_PREFIX;

@NoArgsConstructor
@AllArgsConstructor
public class RequestModel<T> {

    @Getter
    private T body;

    private Map<String, String> urlParams;

    private QueryParamsMap queryParams;

    public RequestModel(Map<String, String> urlParams, QueryParamsMap queryParams){
        this.urlParams = urlParams;
        this.queryParams = queryParams;
    }

    public String getUrlParam(String name){
        return urlParams.get(PARAM_PREFIX + name);
    }

    public String getQueryParam(String name){
        return queryParams.value(name);
    }

}
