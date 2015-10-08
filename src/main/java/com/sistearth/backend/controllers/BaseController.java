package com.sistearth.backend.controllers;

import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.View;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class BaseController<T> implements Route, Controller {

    protected final ModelManager<T> manager;
    protected final View<T> view;

    public BaseController(View<T> view, ModelManager<T> manager) {
        this.view = view;
        this.manager = manager;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Answer answer = process(request, response);
        response.status(answer.getCode());
        return answer.getBody();
    }
}
