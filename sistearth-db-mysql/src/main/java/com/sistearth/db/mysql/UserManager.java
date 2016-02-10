package com.sistearth.db.mysql;

import com.sistearth.db.api.ModelException;
import com.sistearth.db.api.ModelManager;
import com.sistearth.db.beans.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

import static java.lang.String.format;

public class UserManager implements ModelManager<User> {
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
    public boolean exists(int id) throws ModelException {
        throw new ModelException("[Not implemented] UserManager->exists(id)");
    }

    @Override
    public User getBy(String field, Object value) throws ModelException {
        try (Connection conn = database.open()) {
            List<User> usersForId = conn.createQuery(format("SELECT * FROM users WHERE %s = :%s", field, field))
                    .addParameter(field, value)
                    .executeAndFetch(User.class);
            if (!usersForId.isEmpty()) {
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
