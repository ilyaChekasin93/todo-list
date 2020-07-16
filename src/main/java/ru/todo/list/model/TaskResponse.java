package ru.todo.list.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TaskResponse {

    private String message;

    private List<TaskModel> task;

    public TaskResponse(String message) {
        this.message = message;
        this.task = new ArrayList<>();
    }

    public TaskResponse(List<TaskModel> taskModelList) {
        this.message = "Success";
        this.task = taskModelList;
    }

}
