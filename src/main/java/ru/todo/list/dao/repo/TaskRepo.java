package ru.todo.list.dao.repo;

import ru.todo.list.dao.entity.TaskEntity;
import ru.todo.list.dao.entity.TopicEntity;

import java.util.List;
import java.util.Optional;


public interface TaskRepo extends Repository<TaskEntity> {

    Optional<TaskEntity> findFirstByName(String name);

    Optional<TaskEntity> findFirstByContent(String content);

    Optional<TaskEntity> findFirstByTopic(TopicEntity topic);

    List<TaskEntity> findAllByTopic(TopicEntity topic);

    List<TaskEntity> findAllActive();

    List<TaskEntity> findAllClosed();

}

