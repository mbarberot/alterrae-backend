package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.api.serializer.Serializer;
import com.sistearth.core.database.PostModel;
import com.sistearth.core.models.Post;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.api.handler.Answer.ok;

public class PostsGetSingle implements Route, Handler {
    private PostModel model;
    private Serializer<Post> serializer;

    public PostsGetSingle(PostModel model) {
        this.model = model;
        this.serializer = new Serializer<>("posts");
    }

    @Override
    public Answer process(String param) {
        Integer id = Integer.valueOf(param);
        try {
            return ok(serializer.serialize(newArrayList(model.getById(id))));
        } catch (Exception e) {
            return new Answer(500, e.getMessage());
        }
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Answer answer = process(request.params(":id"));
        response.status(answer.getCode());
        response.type("application/json");
        return answer.getBody();
    }
}
