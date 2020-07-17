package ru.todo.list.dao.repo.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.todo.list.dao.entity.TopicEntity;
import ru.todo.list.dao.repo.AbstractRepo;
import ru.todo.list.dao.repo.TopicRepo;

import java.util.Optional;

@Singleton
public class TopicRepoImpl extends AbstractRepo<TopicEntity> implements TopicRepo {

    @Inject
    public TopicRepoImpl(){ super(); }

    public Optional<TopicEntity> findFirstByName(String name) {
        return findFirstBy("name", name);
    }

}
