package com.sistearth.backend.utils;

import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

public class TestUserManager implements ModelManager<User> {
    @Override
    public List<User> getAll() throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public User getById(int id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public User create(User entity) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public boolean exists(int id) throws ModelException {
        throw new NotImplementedException();
    }
}