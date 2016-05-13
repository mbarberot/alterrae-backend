package com.sistearth.test.utils;

import com.sistearth.api.db.ModelException;
import com.sistearth.api.db.ModelManager;
import com.sistearth.api.beans.User;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

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
    public User getById(int id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public void create(User entity) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public boolean exists(int id) {
        throw new NotImplementedException();
    }

    @Override
    public List<User> findById(int id) {
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
    public void update(User entity) {
        throw new NotImplementedException();
    }
}

