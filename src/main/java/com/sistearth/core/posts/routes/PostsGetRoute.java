package com.sistearth.core.posts.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.sistearth.api.handler.Answer.ok;

public class PostsGetRoute implements Route, Handler {
    @Override
    public Answer process() {
        return ok("Posts");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return process().getBody();
    }
}
