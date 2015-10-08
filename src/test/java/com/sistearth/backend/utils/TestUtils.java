package com.sistearth.backend.utils;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.views.legacy.JsonSerializer;

import java.util.Date;

public class TestUtils {
    public static User createUser(int id, String username, String password, String email) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }

    public static Post createPost(int id, String title, String body, Date createdAt, Integer authorId) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setBody(body);
        post.setCreatedAt(createdAt);
        post.setAuthor(authorId);
        return post;
    }

    public static String serialize(Object object) throws Exception {
        return new JsonSerializer().render(object);
    }
}
