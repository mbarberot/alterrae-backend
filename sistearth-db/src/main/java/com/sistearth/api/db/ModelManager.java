package com.sistearth.api.db;

import java.util.List;

public interface ModelManager<T> {
    List<T> getAll();

    T getBy(String field, Object value) throws ModelException;

    T getById(String id) throws ModelException;

    void create(T entity) throws ModelException;

    boolean exists(String id);

    List<T> findById(String id);

    List<T> findBy(String field, Object value);

    void delete(T entity);

    void update(T entity);
}
