package ru.todo.list.mapper;

import org.mapstruct.Mapper;
import ru.todo.list.dao.entity.TaskEntity;
import ru.todo.list.dto.TaskDto;
import ru.todo.list.model.TaskModel;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "jsr330")
public interface TaskMapper {

    default TaskDto taskEntity2TaskDto(TaskEntity taskEntity){
        return new TaskDto(taskEntity.getName(), taskEntity.getContent());
    }

    TaskDto taskModel2TaskDto(TaskModel taskModel);

    default List<TaskDto> taskEntities2TaskDtoList(List<TaskEntity> taskEntities) {
        return taskEntities.stream().map(e -> taskEntity2TaskDto(e)).collect(Collectors.toList());
    }

    TaskModel taskDto2TaskModel(TaskDto taskDto);

}
