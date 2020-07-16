package ru.todo.list.dao.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Repository<T> {

    T save(T entity);

    default List<T> saveAll(Collection<T> enties){
        return enties.stream().map(e -> save(e)).collect(Collectors.toList());
    }

    List<T> findAll();

    List<T> findAllBy(String column, Object value);

    Optional<T> findById(long id);

    Optional<T> findFirstBy(String column, Object value);

    void delete(T entity);

    void deleteAll();

    int count();

}
