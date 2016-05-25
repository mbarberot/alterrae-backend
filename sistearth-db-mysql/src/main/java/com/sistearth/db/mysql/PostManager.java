package com.sistearth.db.mysql;


import com.sistearth.api.db.ModelException;
import com.sistearth.api.db.ModelManager;
import com.sistearth.api.beans.Post;
import org.apache.commons.lang.NotImplementedException;
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
    public List<Post> getAll() {
        List<Post> posts;
        try (Connection conn = database.open()) {
            posts = conn.createQuery("SELECT * FROM posts")
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
        }
        return posts;
    }

    @Override
    public Post getBy(String field, Object value) throws ModelException {
        List<Post> posts = findBy(field, value);
        if (posts.isEmpty()) {
            throw new ModelException(format("User with %s: %s does not exists.", field, value));
        }
        return posts.get(0);
    }

    @Override
    public Post getById(String id) throws ModelException {
        return this.getBy("id", id);
    }

    @Override
    public void create(Post post) {
        throw new NotImplementedException("[Not implemented] PostManager->create(post)");
    }

    @Override
    public boolean exists(String id) {
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery("SELECT * FROM posts WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
            return !postsForId.isEmpty();
        }
    }

    @Override
    public List<Post> findById(String id) {
        return this.findBy("id", id);
    }

    @Override
    public List<Post> findBy(String field, Object value) {
        try (Connection conn = database.open()) {
            return conn.createQuery(format("SELECT * FROM posts WHERE %s = :%s", field, field))
                    .addParameter(field, value)
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
        }
    }

    @Override
    public void delete(Post entity) {
        // This feature is not planned yet
        throw new NotImplementedException("[Not implemented] PostManager->delete(post)");
    }

    @Override
    public void update(Post entity) {
        // This feature is not planned yet
        throw new NotImplementedException("[Not implemented] PostManager->update(post)");
    }
}
