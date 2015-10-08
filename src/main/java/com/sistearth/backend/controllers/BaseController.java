package com.sistearth.backend.controllers;

import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.View;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class BaseController<T> implements Route, Controller {

    protected final ModelManager<T> manager;
    protected final View<T> view;
    protected final PayloadExtractor payloadExtractor;

    public BaseController(View<T> view, ModelManager<T> manager, PayloadExtractor payloadExtractor) {
        this.view = view;
        this.manager = manager;
        this.payloadExtractor = payloadExtractor;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Answer answer = process(payloadExtractor.extractPayload(request), request.params());
        response.status(answer.getStatus());
        return answer.getBody();
    }
}
