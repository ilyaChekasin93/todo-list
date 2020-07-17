package ru.todo.list.exception;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

public class MapperImplNotFoundException extends BaseException {

    public MapperImplNotFoundException(String mapperImplName) {
        super(String.format("Mapper impl with name %s not found", mapperImplName), HTTP_NOT_FOUND);
    }

    public MapperImplNotFoundException() {
        super(String.format("Mapper impl not found"), HTTP_NOT_FOUND);
    }
}
