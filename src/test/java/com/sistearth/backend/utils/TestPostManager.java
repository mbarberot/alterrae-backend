package com.sistearth.backend.utils;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

public class TestPostManager implements ModelManager<Post> {
    @Override
    public List<Post> getAll() throws ModelException {
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
}
