package ru.todo.list.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseModel extends BaseResponseModel {

    private String time;

}
