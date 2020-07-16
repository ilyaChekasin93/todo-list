package ru.todo.list.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.todo.list.dto.TaskDto;
import ru.todo.list.handler.ModelHandlerFunction;
import ru.todo.list.handler.QueryParamsHandlerFunction;
import ru.todo.list.mapper.TaskMapper;
import ru.todo.list.model.TaskModel;
import ru.todo.list.service.TaskService;
import spark.Route;

import java.util.stream.Collectors;

import static ru.todo.list.constant.Path.*;
import static ru.todo.list.utils.ResponseUtils.*;


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
            ((QueryParamsHandlerFunction) queryParamsMap ->
                    success(taskService.findAllTask()
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList())))
                    .handleRequest(request, response);

    public Route getTaskByName = (request, response) ->
            ((QueryParamsHandlerFunction) queryParamsMap -> {
                String name = request.params(NAME_PARAM);
                TaskDto taskDto = taskService.findTask(name);
                TaskModel taskModel = taskMapper.taskDto2TaskModel(taskDto);
                return success(taskModel);
            }).handleRequest(request, response);

    public Route getAllActiveTask = (request, response) ->
            ((QueryParamsHandlerFunction) queryParamsMap ->
                    success(taskService.findAllActiveTask()
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList())))
                    .handleRequest(request, response);

    public Route getAllClosedTask = (request, response) ->
            ((QueryParamsHandlerFunction) queryParamsMap ->
                    success(taskService.findAllCloseTask()
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList())))
                    .handleRequest(request, response);

    public Route getTaskByTopic = (request, response) ->
            ((QueryParamsHandlerFunction) queryParamsMap ->
                    success(taskService.findAllTaskWithTopic(
                            queryParamsMap.value(TOPIC_PARAM_KEY))
                            .stream()
                            .map(task -> taskMapper.taskDto2TaskModel(task))
                            .collect(Collectors.toList())))
                    .handleRequest(request, response);

    public Route createTask = (request, response) ->
            ((ModelHandlerFunction<TaskModel>) taskRequest -> {
                TaskDto taskDto = taskMapper.taskModel2TaskDto(taskRequest);
                taskService.addTask(taskDto);
                return created();
            }).handleRequest(request, response, TaskModel.class);

    public Route updateTaskContent = (request, response) ->
            ((QueryParamsHandlerFunction) taskRequest -> {
                taskService.updateTaskContent(taskRequest.value(NAME_PARAM_KEY), taskRequest.value(CONTENT_PARAM_KEY));
                return updated();
            }).handleRequest(request, response);

    public Route closeTaskByName = (request, response) ->
            ((QueryParamsHandlerFunction) taskRequest -> {
                taskService.closeTask(taskRequest.value(NAME_PARAM_KEY));
                return closed();
            }).handleRequest(request, response);

    public Route deleteTaskByName = (request, response) ->
            ((QueryParamsHandlerFunction) taskRequest -> {
                taskService.deleteTask(taskRequest.value(NAME_PARAM_KEY));
                return deleted();
            }).handleRequest(request, response);

    public Route deleteAllTaskByTopic = (request, response) ->
            ((ModelHandlerFunction<TaskModel>) taskRequest -> {
                taskService.deleteAllTaskWithTopic(taskRequest.getTopic());
                return deleted();
            }).handleRequest(request, response, TaskModel.class);

    public Route deleteAll = (request, response) ->
            ((QueryParamsHandlerFunction) taskRequest -> {
                taskService.deleteAllTask();
                return deleted();
            }).handleRequest(request, response);
}
