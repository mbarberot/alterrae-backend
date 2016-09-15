package com.alterrae.db.core;

import com.alterrae.db.api.manager.ModelException;
import com.alterrae.db.api.manager.ModelManager;
import com.alterrae.db.api.entity.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class UserManager implements ModelManager<User> {
    private Datastore datastore;

    public UserManager() {
        this.datastore = Database.getDatastore();
    }

    @Override
    public List<User> getAll() {
        return datastore.createQuery(User.class).asList();
    }

    @Override
    public User getBy(String field, Object value) throws ModelException {
        User user = datastore.createQuery(User.class)
                .field(field).equal(value)
                .get();

        if (user == null) {
            throw new ModelException(format("No user found for %s: %s", field, value));
        } else {
            return user;
        }
    }

    @Override
    public User getById(String id) throws ModelException {
        return this.getBy("id", new ObjectId(id));
    }

    @Override
    public void create(User entity) throws ModelException {
        datastore.save(entity);
    }

    @Override
    public boolean exists(String id) {
        return !findById(id).isEmpty();
    }

    @Override
    public List<User> findById(String id) {
        return findBy("id", id);
    }

    @Override
    public List<User> findBy(String field, Object value) {
        return datastore.createQuery(User.class)
                .field(field).equal(value)
                .asList();
    }

    @Override
    public void delete(User entity) {
        datastore.delete(entity);
    }

    @Override
    public void update(User entity, Map<String, Object> data) {
        datastore.update(entity, new MongoUpdateHelper<>(datastore, User.class).createUpdateOperations(data));
    }
}
