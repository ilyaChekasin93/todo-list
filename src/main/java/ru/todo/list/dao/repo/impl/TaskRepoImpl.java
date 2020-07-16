package ru.todo.list.dao.repo.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.todo.list.dao.entity.TaskEntity;
import ru.todo.list.dao.entity.TopicEntity;
import ru.todo.list.dao.repo.AbstractRepo;
import ru.todo.list.dao.repo.TaskRepo;

import java.util.List;
import java.util.Optional;


@Singleton
public class TaskRepoImpl extends AbstractRepo<TaskEntity> implements TaskRepo {

    @Inject
    public TaskRepoImpl(){ super(); }

    public Optional<TaskEntity> findFirstByName(String name) {
        return findFirstBy("name", name);
    }

    public Optional<TaskEntity> findFirstByContent(String content) {
        return findFirstBy("content", content);
    }

    public Optional<TaskEntity> findFirstByTopic(TopicEntity topic) { return findFirstBy("topic", topic); }

    public List<TaskEntity> findAllByTopic(TopicEntity topic) { return findAllBy("topic", topic); }

    public List<TaskEntity> findAllActive() { return findAllBy("active", true); }

    public List<TaskEntity> findAllClosed() { return findAllBy("active", false); }
}
