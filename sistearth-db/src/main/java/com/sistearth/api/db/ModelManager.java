package com.sistearth.api.db;

import java.util.List;

public interface ModelManager<T> {
    List<T> getAll();

    T getBy(String field, Object value) throws ModelException;

    T getById(int id) throws ModelException;

    void create(T entity) throws ModelException;

    boolean exists(int id);

    List<T> findById(int id);

    List<T> findBy(String field, Object value);

    void delete(T entity);

    void update(T entity);
}
