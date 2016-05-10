package com.sistearth.test.utils;

import com.sistearth.db.api.ModelException;
import com.sistearth.db.api.ModelManager;
import com.sistearth.db.beans.Post;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

public class TestPostManager implements ModelManager<Post> {
    @Override
    public List<Post> getAll() throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public Post getBy(String field, Object value) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public Post getById(int id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public void create(Post entity) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public boolean exists(int id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public List<Post> findById(int id) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public List<Post> findBy(String field, Object value) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Post entity) throws ModelException {
        throw new NotImplementedException();
    }

    @Override
    public void update(Post entity) throws ModelException {
        throw new NotImplementedException();
    }
}
