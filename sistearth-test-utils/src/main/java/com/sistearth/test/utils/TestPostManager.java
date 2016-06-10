package com.sistearth.test.utils;

import com.sistearth.db.api.manager.ModelException;
import com.sistearth.db.api.manager.ModelManager;
import com.sistearth.db.api.entity.Post;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;
import java.util.Map;

public class TestPostManager implements ModelManager<Post> {
    @Override
    public List<Post> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public Post getBy(String field, Object value) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public Post getById(String id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public void create(Post entity) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public boolean exists(String id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Post> findById(String id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Post> findBy(String field, Object value) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Post entity) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Post entity, Map<String, Object> data) {
        throw new NotImplementedException();
    }
}
