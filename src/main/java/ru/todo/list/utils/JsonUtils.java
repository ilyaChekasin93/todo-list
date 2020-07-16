package ru.todo.list.utils;

import com.google.gson.Gson;


public class JsonUtils {

    private static final Gson gson = new Gson();

    public static String jsonObj2String(Object json) { return gson.toJson(json); }

    public static <T> T json2Obj(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
