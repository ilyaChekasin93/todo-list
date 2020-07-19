package ru.todo.list.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.todo.list.dto.TaskDto;
import ru.todo.list.function.RequestHandlerFunctionWithRequestBody;
import ru.todo.list.function.RequestHandlerFunctionWithoutRequestBody;
import ru.todo.list.mapper.TaskMapper;
import ru.todo.list.model.TaskModel;
import ru.todo.list.service.TaskService;
import spark.Route;

import java.util.stream.Collectors;

import static ru.todo.list.constant.Path.*;
import static ru.todo.list.utils.ResponseUtils.*;
import static ru.todo.list.utils.ResponseUtils.updated;


@Singleton
public class TaskRoute {

    private TaskService taskService;

    private TaskMapper taskMapper;

    @Inject
    public TaskRoute(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    public Route getAllTask = (request, response) ->
            success(((RequestHandlerFunctionWithRequestBody) requestModel ->
                    taskService.findAllTask().stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList()))
                    .handleRequest(request), response);

    public Route getTaskByName = (request, response) ->
            success(((RequestHandlerFunctionWithRequestBody) requestModel -> {
                String name = requestModel.getUrlParam(NAME_PARAM);
                TaskDto taskDto = taskService.findTask(name);
                return taskMapper.taskDto2TaskModel(taskDto);
            }).handleRequest(request), response);

    public Route getAllActiveTask = (request, response) ->
            success(((RequestHandlerFunctionWithRequestBody) requestModel ->
                    taskService.findAllActiveTask()
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList()))
                    .handleRequest(request), response);

    public Route getAllClosedTask = (request, response) ->
            success(((RequestHandlerFunctionWithRequestBody) requestModel ->
                    taskService.findAllCloseTask()
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList()))
                    .handleRequest(request), response);

    public Route getTaskByTopic = (request, response) ->
            success(((RequestHandlerFunctionWithRequestBody) requestModel ->
                    taskService.findAllTaskWithTopic(
                            requestModel.getUrlParam(TOPIC_PARAM))
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList()))
                    .handleRequest(request), response);

    public Route createTask = (request, response) -> {
        ((RequestHandlerFunctionWithoutRequestBody<TaskModel>) requestModel -> {
            TaskModel requestBody = requestModel.getBody();
            TaskDto taskDto = taskMapper.taskModel2TaskDto(requestBody);
            taskService.addTask(taskDto);
        }).handleRequest(request, TaskModel.class);

        return created(response);
    };

    public Route updateTaskContent = (request, response) -> {
        ((RequestHandlerFunctionWithoutRequestBody) requestModel -> {
            String name = requestModel.getUrlParam(NAME_PARAM);
            String param = requestModel.getUrlParam(CONTENT_PARAM);
            taskService.updateTaskContent(name, param);
        }).handleRequest(request);
        return updated(response);
    };

    public Route closeTaskByName = (request, response) -> {
        ((RequestHandlerFunctionWithoutRequestBody) requestModel -> {
            String name = requestModel.getUrlParam(NAME_PARAM);
            taskService.closeTask(name);
        }).handleRequest(request);
        return updated(response);
    };

    public Route deleteTaskByName = (request, response) -> {
        ((RequestHandlerFunctionWithoutRequestBody) requestModel -> {
            String name = requestModel.getUrlParam(NAME_PARAM);
            taskService.deleteTask(name);
        }).handleRequest(request);
        return deleted(response);
    };

    public Route deleteAllTaskByTopic = (request, response) -> {
        ((RequestHandlerFunctionWithoutRequestBody) requestModel -> {
            String topic = requestModel.getUrlParam(TOPIC_PARAM);
            taskService.deleteAllTaskWithTopic(topic);
        }).handleRequest(request);
        return deleted(response);
    };

    public Route deleteAll = (request, response) -> {
        ((RequestHandlerFunctionWithoutRequestBody) requestModel ->
                taskService.deleteAllTask()).handleRequest(request);
        return deleted(response);
    };
}
