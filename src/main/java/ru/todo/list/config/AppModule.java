package ru.todo.list.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import ru.todo.list.Application;
import ru.todo.list.dao.repo.TaskRepo;
import ru.todo.list.dao.repo.TopicRepo;
import ru.todo.list.dao.repo.impl.TaskRepoImpl;
import ru.todo.list.dao.repo.impl.TopicRepoImpl;
import ru.todo.list.utils.MapperUtils;

public class AppModule extends AbstractModule {

    protected void configure() {
        bind(Application.class).in(Singleton.class);
        bind(TaskRepo.class).to(TaskRepoImpl.class).in(Singleton.class);
        bind(TopicRepo.class).to(TopicRepoImpl.class).in(Singleton.class);
        bindAllMappers();
    }

    private void bindAllMappers() {
        MapperUtils.getMapperTypes().entrySet().stream()
                .forEach(m -> bind(m.getKey()).to(m.getValue()).in(Singleton.class));
    }

}
