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
        path(API, () ->
                path(V1, () ->
                        path(TASK, () -> {
                            get(SLASH + NAME_PARAM, taskRoute.getTaskByName);
                            delete(SLASH + NAME_PARAM, taskRoute.deleteTaskByName);
                            post(SLASH, taskRoute.createTask);
                            put(SLASH, taskRoute.updateTaskContent);
                            put(SLASH, taskRoute.closeTaskByName);
                            path(ALL, () -> {
                                get(ACTIVE, taskRoute.getAllActiveTask);
                                get(CLOSE, taskRoute.getAllClosedTask);
                                get(SLASH, taskRoute.getAllTask);
                                delete(SLASH, taskRoute.deleteAll);
                                path(TOPIC, () -> {
                                    delete(SLASH, taskRoute.deleteAllTaskByTopic);
                                    get(SLASH + TOPIC_PARAM, taskRoute.getTaskByTopic);
                                });
                            });
                        })
                )
        );
    }

    private void initExceptionHandlers() {
        exception(Exception.class, exceptionHandler.defaultExceptionHandler);
    }
}
