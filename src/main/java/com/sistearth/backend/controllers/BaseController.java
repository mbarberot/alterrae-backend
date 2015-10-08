package com.sistearth.backend.controllers;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class BaseController<V extends Payload> implements Route, Controller<V> {

    protected final PayloadExtractor<V> payloadExtractor;

    public BaseController(PayloadExtractor<V> payloadExtractor) {
        this.payloadExtractor = payloadExtractor;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Answer answer = process(payloadExtractor.extractPayload(request), request.params());
        response.status(answer.getStatus());
        return answer.getBody();
    }
}
