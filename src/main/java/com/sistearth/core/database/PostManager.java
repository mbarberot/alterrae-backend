package com.sistearth.core.database;

import com.sistearth.api.model.ModelManager;
import com.sistearth.api.model.ModelException;
import com.sistearth.core.models.Post;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;
import java.util.List;

public class PostManager implements ModelManager<Post> {
    private Sql2o database;

    public PostManager(Sql2o database) {
        this.database = database;
    }

    @Override
    public List<Post> getAll() throws ModelException {
        try (Connection conn = database.open()) {
            return conn.createQuery("SELECT * FROM posts").executeAndFetch(Post.class);
        }
    }

    @Override
    public Post getById(int id) throws ModelException {
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery("SELECT * FROM posts WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetch(Post.class);
            if (postsForId.size() > 0) {
                return postsForId.get(0);
            } else {
                throw new ModelException("Post with id " + id + " does not exists.");
            }
        }
    }

    @Override
    public Post create(Post post) throws ModelException {
        try (Connection conn = database.beginTransaction()) {
            conn.createQuery("INSERT INTO post (title, body, created_at) VALUES (:title, :body, :created_at)")
                    .addParameter("title", post.getTitle())
                    .addParameter("body", post.getBody())
                    .addParameter("created_at", new Date())
                    .executeUpdate();
            conn.commit();
            return conn.createQuery("SELECT * FROM post ORDER BY id DESC LIMIT 1").executeAndFetch(Post.class).get(0);
        }
    }

    @Override
    public boolean exists(int id) throws ModelException {
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery("SELECT * FROM posts WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetch(Post.class);
            return postsForId.size() > 0;
        }
    }
}
