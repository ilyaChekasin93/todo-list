package ru.todo.list.utils;

import ru.todo.list.model.BaseTaskResponse;
import ru.todo.list.model.TaskModel;
import ru.todo.list.model.TaskResponse;

import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static BaseTaskResponse created() {
        return new BaseTaskResponse("Task successfully created");
    }

    public static BaseTaskResponse updated() {
        return new BaseTaskResponse("Task successfully updated");
    }

    public static BaseTaskResponse closed() {
        return new BaseTaskResponse("Task successfully closed");
    }

    public static BaseTaskResponse deleted() {
        return new BaseTaskResponse("Task successfully deleted");
    }

    public static TaskResponse success(List<TaskModel> taskModelList) {
        return new TaskResponse(taskModelList);
    }

    public static TaskResponse success(TaskModel taskModel) {
        return new TaskResponse(Arrays.asList(taskModel));
    }

}
