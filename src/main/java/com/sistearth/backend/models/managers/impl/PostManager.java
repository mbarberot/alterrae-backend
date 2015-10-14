package com.sistearth.backend.models.managers.impl;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static java.lang.String.format;

public class PostManager implements ModelManager<Post> {
    private Sql2o database;

    public PostManager(Sql2o database) {
        this.database = database;
    }

    @Override
    public List<Post> getAll() throws ModelException {
        List<Post> posts;
        try (Connection conn = database.open()) {
            posts = conn.createQuery("SELECT * FROM posts")
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
        }
        return posts;
    }

    @Override
    public Post getById(int id) throws ModelException {
        return getBy("id", id);
    }

    @Override
    public void create(Post post) throws ModelException {
        throw new ModelException("[Not implemented] PostManager->create(post)");
    }

    @Override
    public boolean exists(int id) throws ModelException {
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery("SELECT * FROM posts WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
            return !postsForId.isEmpty();
        }
    }

    @Override
    public Post getBy(String field, Object value) throws ModelException {
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery(format("SELECT * FROM posts WHERE %s = :%s", field, field))
                    .addParameter(field, value)
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
            if (!postsForId.isEmpty()) {
                return postsForId.get(0);
            } else {
                throw new ModelException(format("Post with %s: %s does not exists.", field, value));
            }
        }
    }

    @Override
    public void delete(Post entity) throws ModelException {
        throw new ModelException("[Not implemented] PostManager->delete(post)");
    }
}
