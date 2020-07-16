package ru.todo.list.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String name;

    private String content;

    private String topic;

    public TaskDto(String name, String content){
        this.name = name;
        this.content = content;
    }

}