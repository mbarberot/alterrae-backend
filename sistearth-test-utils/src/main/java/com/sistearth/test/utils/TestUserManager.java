package com.sistearth.test.utils;

import com.sistearth.db.api.manager.ModelException;
import com.sistearth.db.api.manager.ModelManager;
import com.sistearth.db.api.entity.User;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;
import java.util.Map;

public class TestUserManager implements ModelManager<User> {
    @Override
    public List<User> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public User getBy(String field, Object value) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public User getById(String id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public void create(User entity) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public boolean exists(String id) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> findById(String id) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> findBy(String field, Object value) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public void update(User entity, Map<String, Object> data) {
        throw new NotImplementedException();
    }
}

