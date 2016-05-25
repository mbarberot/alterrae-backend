package com.sistearth.db.mysql;

import com.sistearth.api.db.ModelException;
import com.sistearth.api.db.ModelManager;
import com.sistearth.api.beans.User;
import org.apache.commons.lang.NotImplementedException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

public class UserManager implements ModelManager<User> {
    private Sql2o database;

    public UserManager(Sql2o database) {
        this.database = database;
    }

    @Override
    public List<User> getAll() {
        throw new NotImplementedException("[Not implemented] UserManager->getAll()");
    }

    @Override
    public User getBy(String field, Object value) throws ModelException {
        List<User> users = this.findBy(field, value);
        if (users.isEmpty()) {
            throw new ModelException(format("User with %s: %s does not exists.", field, value));
        }
        return users.get(0);
    }

    @Override
    public User getById(String id) throws ModelException {
        return this.getBy("id", id);
    }

    @Override
    public void create(User user) throws ModelException {
        try (Connection conn = database.beginTransaction()) {
            conn.createQuery("INSERT INTO users (id, username, password, email) VALUES (UUID(), :username, :password, :email)")
                    .addParameter("username", user.getUsername())
                    .addParameter("password", user.getPassword())
                    .addParameter("email", user.getEmail())
                    .executeUpdate();
            conn.commit();
        } catch (Sql2oException e) {
            String message = e.getMessage();
            throw new ModelException(
                    "Failed to create user",
                    (message.contains("'username'")) ? "username" :
                            (message.contains("'email'")) ? "email" : "",
                    e
            );
        }
    }

    @Override
    public boolean exists(String id) {
        throw new NotImplementedException("[Not implemented] UserManager->exists(id)");
    }

    @Override
    public List<User> findById(String id) {
        return this.findBy("id", id);
    }

    @Override
    public List<User> findBy(String field, Object value) {
        try (Connection conn = database.open()) {
            return conn.createQuery(format("SELECT * FROM users WHERE %s = :%s", field, field))
                    .addParameter(field, value)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void delete(User entity) {
        try (Connection conn = database.open()) {
            conn.createQuery("DELETE FROM users WHERE id = :id")
                    .addParameter("id", entity.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void update(User entity) {
        // Changing username is not allowed
        try (Connection conn = database.open()) {
            conn.createQuery("UPDATE users SET password = :password, email = :email WHERE id = :id")
                    .addParameter("id", entity.getId())
                    .addParameter("password", entity.getPassword())
                    .addParameter("email", entity.getEmail())
                    .executeUpdate();
        }
    }
}
