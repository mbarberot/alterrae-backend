package com.sistearth.utils;

import com.sistearth.core.models.Post;

import java.util.Date;

public class TestUtils {
    public static Post createPost(String title, String body) {
        Post post = new Post();
        post.setBody(body);
        post.setTitle(title);
        post.setCreatedAt(new Date());
        return post;
    }
}
