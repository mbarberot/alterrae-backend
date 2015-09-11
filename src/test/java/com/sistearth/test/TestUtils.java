package com.sistearth.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.core.models.Post;
import com.sistearth.core.models.User;
import com.sistearth.core.serializers.JsonSerializer;

import java.util.Date;

public class TestUtils {
    public static User createUser(int id, String username, String password, String email) {
        User user = new User();
        user.setId(0);
        user.setUsername("foo");
        user.setPassword("secret");
        user.setEmail("foo@bar.com");
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
