package ru.todo.list;

import com.google.inject.Guice;
import com.google.inject.Inject;
import ru.todo.list.config.AppModule;
import ru.todo.list.controller.TaskRoute;
import ru.todo.list.controller.TaskExceptionHandler;

import static ru.todo.list.constant.AppConst.APP_PORT;
import static ru.todo.list.constant.Path.*;
import static spark.Spark.*;


public class Application {

    private TaskRoute taskRoute;

    private TaskExceptionHandler exceptionHandler;

    @Inject
    private Application(TaskRoute taskRoute, TaskExceptionHandler exceptionHandler) {
        this.taskRoute = taskRoute;
        this.exceptionHandler = exceptionHandler;
    }

    public static void main(String[] args) {
        Guice.createInjector(new AppModule()).getInstance(Application.class).run();
    }

    private void run() {
        port(APP_PORT);
        initExceptionHandlers();
        initRoutes();
    }

    private void initRoutes() {
        path(SLASH + API, () ->
                path(SLASH + V1 + SLASH, () -> {
                    post(TASK, taskRoute.createTask);
                    path(TASK + SLASH, () -> {
                        get(NAME + SLASH + PARAM_PREFIX + NAME, taskRoute.getTaskByName);
                        put(NAME + SLASH + PARAM_PREFIX + NAME, taskRoute.updateTaskContentByName);
                        delete(NAME + SLASH + PARAM_PREFIX + NAME, taskRoute.deleteTaskByName);
                        put(NAME + SLASH + PARAM_PREFIX + NAME + SLASH + CLOSE, taskRoute.closeTaskByName);
                        get(ALL, taskRoute.getAllTask);
                        delete(ALL, taskRoute.deleteAllTask);
                        path(ALL + SLASH, () -> {
                            get(ACTIVE, taskRoute.getAllActiveTask);
                            get(CLOSE, taskRoute.getAllClosedTask);
                            get(TOPIC + SLASH + PARAM_PREFIX + TOPIC, taskRoute.getTaskByTopic);
                            delete(TOPIC + SLASH + PARAM_PREFIX + TOPIC, taskRoute.deleteAllTaskByTopic);
                        });
                    });
                })
        );
    }

    private void initExceptionHandlers() {
        exception(Exception.class, exceptionHandler.defaultExceptionHandler);
    }
}
