package ru.todo.list.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.todo.list.dao.entity.TaskEntity;
import ru.todo.list.dao.entity.TopicEntity;
import ru.todo.list.dao.repo.TaskRepo;
import ru.todo.list.dao.repo.TopicRepo;
import ru.todo.list.dto.TaskDto;
import ru.todo.list.exception.TaskAlreadyExistException;
import ru.todo.list.exception.TaskNotFoundException;
import ru.todo.list.exception.TopicNotFoundException;
import ru.todo.list.mapper.TaskMapper;

import java.util.List;


@Singleton
public class TaskService {

    private TaskRepo taskRepo;

    private TopicRepo topicRepo;

    private TaskMapper taskMapper;


    @Inject
    public TaskService(TaskRepo taskRepo, TopicRepo topicRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.topicRepo = topicRepo;
        this.taskMapper = taskMapper;
    }

    public TaskDto findTask(String name) {
        TaskEntity task = taskRepo.findFirstByName(name)
                .orElseThrow(() -> new TaskNotFoundException(name));

        return taskMapper.taskEntity2TaskDto(task);
    }

    public List<TaskDto> findAllActiveTask() {
        List<TaskEntity> tasks = taskRepo.findAllActive();

        return taskMapper.taskEntities2TaskDtoList(tasks);
    }

    public List<TaskDto> findAllCloseTask() {
        List<TaskEntity> tasks = taskRepo.findAllClosed();

        return taskMapper.taskEntities2TaskDtoList(tasks);
    }

    public List<TaskDto> findAllTaskWithTopic(String topicName) {
        TopicEntity topic = topicRepo.findFirstByName(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));

        List<TaskEntity> tasks = taskRepo.findAllByTopic(topic);

        return taskMapper.taskEntities2TaskDtoList(tasks);
    }

    public List<TaskDto> findAllTask() {
        List<TaskEntity> tasks = taskRepo.findAll();

        return taskMapper.taskEntities2TaskDtoList(tasks);
    }

    public void addTask(TaskDto taskDto) {
        String taskName = taskDto.getName();
        String topicName = taskDto.getTopic();
        String content = taskDto.getContent();

        if (taskRepo.findFirstByName(taskName).isPresent())
            throw new TaskAlreadyExistException(taskName);

        TopicEntity topic = topicRepo.findFirstByName(topicName)
                .orElseGet(() -> topicRepo.save(new TopicEntity(topicName)));

        TaskEntity task = new TaskEntity(taskName, content, topic);

        taskRepo.save(task);
    }

    public void updateTaskContent(TaskDto taskDto) {
        String name = taskDto.getName();
        String content = taskDto.getContent();
        TaskEntity task = taskRepo.findFirstByName(name)
                .orElseThrow(() -> new TaskNotFoundException(name));
        task.setContent(content);

        taskRepo.save(task);
    }

    public void closeTask(TaskDto taskDto) {
        String name = taskDto.getName();
        TaskEntity task = taskRepo.findFirstByName(name)
                .orElseThrow(() -> new TaskNotFoundException(name));
        task.setActive(false);

        taskRepo.save(task);
    }

    public void deleteTask(TaskDto taskDto) {
        String name = taskDto.getName();
        TaskEntity task = taskRepo.findFirstByName(name)
                .orElseThrow(() -> new TaskNotFoundException(name));

        taskRepo.delete(task);
    }

    public void deleteAllTaskWithTopic(String topicName) {
        TopicEntity topic = topicRepo.findFirstByName(topicName)
                .orElseThrow(() -> new TopicNotFoundException(topicName));

        TaskEntity task = taskRepo.findFirstByTopic(topic)
                .orElseThrow(() -> new TaskNotFoundException());

        taskRepo.delete(task);
    }

    public void deleteAllTask() {
        taskRepo.deleteAll();
    }

}
