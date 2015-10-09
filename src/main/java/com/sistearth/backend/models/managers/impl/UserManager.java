package com.sistearth.backend.models.managers.impl;

import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static java.lang.String.format;

public class UserManager implements ModelManager<User>{
    private Sql2o database;

    public UserManager(Sql2o database) {
        this.database = database;
    }

    @Override
    public List<User> getAll() throws ModelException {
        throw new ModelException("[Not implemented] UserManager->getAll()");
    }

    @Override
    public User getById(int id) throws ModelException {
        return this.getBy("id", id);
    }

    @Override
    public void create(User user) throws ModelException {
        try (Connection conn = database.beginTransaction()) {
            conn.createQuery("INSERT INTO users (username, password, email) VALUES (:username, :password, :email)")
                    .addParameter("username", user.getUsername())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .executeUpdate();
            conn.commit();
        }
    }

    @Override
    public boolean exists(int id) throws ModelException {
        throw new ModelException("[Not implemented] UserManager->exists(id)");
    }

    @Override
    public User getBy(String field, Object value) throws ModelException {
        try (Connection conn = database.open()) {
            List<User> usersForId = conn.createQuery(format("SELECT * FROM users WHERE %s = :%s", field, field))
                    .addParameter(field, value)
                    .executeAndFetch(User.class);
            if (usersForId.size() > 0) {
                return usersForId.get(0);
            } else {
                throw new ModelException(format("User with %s: %s does not exists.", field, value));
            }
        }
    }

    @Override
    public void delete(User entity) throws ModelException {
        throw new ModelException("[Not implemented] UserManager->delete(user)");
    }
}
