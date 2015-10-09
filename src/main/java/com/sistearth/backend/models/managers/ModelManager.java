package com.sistearth.backend.models.managers;

import java.util.List;

public interface ModelManager<T> {
    List<T> getAll() throws ModelException;

    T getById(int id) throws ModelException;

    void create(T entity) throws ModelException;

    boolean exists(int id) throws ModelException;

    T getBy(String field, Object value) throws ModelException;

    void delete(T entity) throws ModelException;
}
