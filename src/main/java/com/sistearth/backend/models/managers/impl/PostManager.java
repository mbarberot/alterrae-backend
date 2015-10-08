package com.sistearth.backend.models.managers.impl;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
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
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery("SELECT * FROM posts WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
            if (postsForId.size() > 0) {
                return postsForId.get(0);
            } else {
                throw new ModelException("Post with id " + id + " does not exists.");
            }
        }
    }

    @Override
    public void create(Post post) throws ModelException {
        try (Connection conn = database.beginTransaction()) {
            conn.createQuery("INSERT INTO post (title, body, created_at) VALUES (:title, :body, :created_at)")
                    .addParameter("title", post.getTitle())
                    .addParameter("body", post.getBody())
                    .addParameter("created_at", new Date())
                    .executeUpdate();
            conn.commit();
        }
    }

    @Override
    public boolean exists(int id) throws ModelException {
        try (Connection conn = database.open()) {
            List<Post> postsForId = conn.createQuery("SELECT * FROM posts WHERE id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("created_at", "createdAt")
                    .executeAndFetch(Post.class);
            return postsForId.size() > 0;
        }
    }
}
