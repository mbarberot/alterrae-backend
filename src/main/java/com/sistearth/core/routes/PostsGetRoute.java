package com.sistearth.core.routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.core.models.Post;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.sistearth.api.handler.Answer.ok;

public class PostsGetRoute implements Route, Handler {
    @Override
    public Answer process() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Lorem ipsum");
        post.setBody("Dolor sit amet");

        List<Post> posts = newArrayList(post);
        Map<String, List<Post>> map = newHashMap();
        map.put("posts", posts);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, map);
            return ok(sw.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize", e);
        }
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Answer answer = process();
        response.status(answer.getCode());
        response.type("application/json");
        return answer.getBody();
    }
}
