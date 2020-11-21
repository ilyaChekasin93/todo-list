package ru.todo.list.dao.repo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import ru.todo.list.dao.entity.BaseEntity;
import ru.todo.list.exception.CreateDaoException;
import ru.todo.list.exception.DatabaseOperationException;
import ru.todo.list.utils.DatabaseUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public abstract class AbstractRepo<T extends BaseEntity> implements Repository<T> {

    private final Dao<T, Long> dao;

    private final Class baseEntityClass;

    public AbstractRepo() {
        baseEntityClass = BaseEntity.class;
        Class<T> typeOfT = getTypeOfT();
        JdbcPooledConnectionSource connectionSource = DatabaseUtils.getConnectionSource();
        try {
            dao = DaoManager.createDao(connectionSource, typeOfT);
        } catch (SQLException e) {
            throw new CreateDaoException(typeOfT.getSimpleName(), e.getMessage());
        }
    }

    public T save(T entity) {
        return findById(entity.getId()).isPresent() ? refresh(entity) : create(entity);
    }

    public Optional<T> findFirstBy(String column, Object value) {
        try {
            T entity = dao.queryBuilder().where().eq(column, value).queryForFirst();
            return Optional.ofNullable(entity);
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public Optional<T> findById(long id) {
        String fieldIdName = getIdFieldName(baseEntityClass);

        return findFirstBy(fieldIdName, id);
    }

    public List<T> findAllBy(String column, Object value) {
        try {
            return dao.queryBuilder().where().eq(column, value).query();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public List<T> findAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public void delete(T entity) {
        try {
            dao.delete(entity);
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public void deleteAll() {
        String idFieldName = getIdFieldName(baseEntityClass);
        try {
            dao.deleteBuilder().where().isNotNull(idFieldName).query();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public int count() {
        return findAll().size();
    }

    private T refresh(T entity) {
        try {
            dao.refresh(entity);
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }

        return entity;
    }

    private T create(T entity) {
        try {
            dao.create(entity);
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }

        return entity;
    }

    private String getIdFieldName(Class entityClass) {
        Reflections reflections = new Reflections(entityClass, new FieldAnnotationsScanner());
        Field idField = reflections.getFieldsAnnotatedWith(Id.class)
                .stream()
                .findFirst()
                .orElseThrow(() -> new DatabaseOperationException(
                        String.format("Error find id field in class %s", entityClass.getSimpleName())));

        return idField.getName();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTypeOfT() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
