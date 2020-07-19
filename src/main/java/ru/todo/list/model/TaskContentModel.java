package ru.todo.list.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spark.QueryParamsMap;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskContentModel {

    private String content;

}
