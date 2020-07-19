package ru.todo.list.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.todo.list.dao.entity.TaskEntity;
import ru.todo.list.dto.TaskDto;
import ru.todo.list.model.TaskModel;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "jsr330")
public interface TaskMapper {

    @Mapping( target = "topic", ignore = true)
    TaskDto taskEntity2TaskDto(TaskEntity taskEntity);

    TaskDto taskModel2TaskDto(TaskModel taskModel);

    default List<TaskDto> taskEntities2TaskDtoList(List<TaskEntity> taskEntities) {
        return taskEntities.stream().map(e -> taskEntity2TaskDto(e)).collect(Collectors.toList());
    }

    TaskModel taskDto2TaskModel(TaskDto taskDto);

}
