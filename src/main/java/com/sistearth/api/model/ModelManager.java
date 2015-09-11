package com.sistearth.api.model;

import java.util.List;

public interface ModelManager<T> {
    List<T> getAll() throws ModelException;

    T getById(int id) throws ModelException;

    T create(T entity) throws ModelException;

    boolean exists(int id) throws ModelException;
}
