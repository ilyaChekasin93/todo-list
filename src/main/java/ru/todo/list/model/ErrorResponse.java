package ru.todo.list.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends BaseTaskResponse {

    private String time;

}
