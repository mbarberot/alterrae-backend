package com.alterrae.db.core;

import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.manager.ModelException;
import com.alterrae.db.api.manager.ModelManager;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import java.util.List;
import java.util.Map;

public class PostManager implements ModelManager<Post> {
    private Datastore datastore;

    public PostManager() {
        this.datastore = Database.getDatastore();
    }

    @Override
    public List<Post> getAll() {
        return datastore.createQuery(Post.class).asList();
    }

    @Override
    public Post getBy(String field, Object value) throws ModelException {
        return datastore.createQuery(Post.class)
                .field(field).equal(value)
                .get();
    }

    @Override
    public Post getById(String id) throws ModelException {
        return this.getBy("id", new ObjectId(id));
    }

    @Override
    public void create(Post entity) throws ModelException {
        datastore.save(entity);
    }

    @Override
    public boolean exists(String id) {
        return !findById(id).isEmpty();
    }

    @Override
    public List<Post> findById(String id) {
        return findBy("id", id);
    }

    @Override
    public List<Post> findBy(String field, Object value) {
        return datastore.createQuery(Post.class)
                .field(field).equal(value)
                .asList();
    }

    @Override
    public void delete(Post entity) {
        datastore.delete(entity);
    }

    @Override
    public void update(Post entity, Map<String, Object> data) {
        datastore.update(entity, new MongoUpdateHelper<>(datastore, Post.class).createUpdateOperations(data));
    }
}
