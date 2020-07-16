package ru.todo.list.utils;

import com.google.inject.Singleton;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.reflections.Reflections;
import ru.todo.list.exception.CreateTableException;
import ru.todo.list.exception.DatabaseConnectException;

import javax.persistence.Entity;
import java.sql.SQLException;
import java.util.Set;

import static ru.todo.list.constant.AppConst.APP_ROOT_PATH;

@Singleton
public class DatabaseUtils {

    private static JdbcPooledConnectionSource connectionSource;

    public static JdbcPooledConnectionSource getConnectionSource() {
        if (connectionSource == null)
            initDatabase();

        return connectionSource;
    }

    private static void initDatabase() {
        String url = "jdbc:h2:mem:myDb";
        try {
            connectionSource = new JdbcPooledConnectionSource(url);
        } catch (SQLException e) {
            throw new DatabaseConnectException(url, e.getMessage());
        }
        createAllTable();
    }

    private static void createAllTable() {
        Set<Class<?>> entityClasses = getAllEntityClasses();
        for (Class entityClass : entityClasses) {
            try {
                TableUtils.createTableIfNotExists(connectionSource, entityClass);
            } catch (SQLException e) {
                throw new CreateTableException(entityClass.getSimpleName(), e.getMessage());
            }
        }
    }

    private static Set<Class<?>> getAllEntityClasses() {
        Reflections reflections = new Reflections(APP_ROOT_PATH);

        return reflections.getTypesAnnotatedWith(Entity.class);
    }

}
