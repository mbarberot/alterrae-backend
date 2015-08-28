package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.api.serializer.Serializer;
import com.sistearth.core.database.PostManager;
import com.sistearth.core.models.Post;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.sistearth.api.handler.Answer.ok;

public class PostsGetAll implements Route, Handler {

    private final Serializer<Post> serializer;
    private final PostManager model;

    public PostsGetAll(PostManager model) {
        this.model = model;
        this.serializer = new Serializer<>("posts");
    }

    @Override
    public Answer process(String params) {
        try {
            return ok(serializer.serialize(model.getAll()));
        } catch (Exception e) {
            return new Answer(500, e.getMessage());
        }
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Answer answer = process(null); // Todo replace null
        response.status(answer.getCode());
        response.type("application/json");
        return answer.getBody();
    }
}
