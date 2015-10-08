package com.sistearth.backend.models.managers.impl;

import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class UserManager implements ModelManager<User>{
    private Sql2o database;

    public UserManager(Sql2o database) {
        this.database = database;
    }

    @Override
    public List<User> getAll() throws ModelException {
        return null;
    }

    @Override
    public User getById(int id) throws ModelException {
        try (Connection conn = database.open()) {
            List<User> usersForId = conn.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetch(User.class);
            if (usersForId.size() > 0) {
                return usersForId.get(0);
            } else {
                throw new ModelException("User with id " + id + " does not exists.");
            }
        }
    }

    @Override
    public User create(User entity) throws ModelException {
        return null;
    }

    @Override
    public boolean exists(int id) throws ModelException {
        return false;
    }
}
