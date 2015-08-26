package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import spark.Request;
import spark.Response;
import spark.Route;

public class IndexGetRoute implements Route, Handler {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return process().getBody();
    }

    @Override
    public Answer process() {
        return Answer.ok("Hello world !");
    }
}
