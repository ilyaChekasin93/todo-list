package ru.todo.list.dao.repo;

import ru.todo.list.dao.entity.TopicEntity;

import java.util.Optional;

public interface TopicRepo extends Repository<TopicEntity> {

    Optional<TopicEntity> findFirstByName(String name);

}
