package com.alterrae.spark.services;

import static com.alterrae.spark.view.Answer.newJsonAnswer;
import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/", (request, response) -> newJsonAnswer(response).body("{ \"message\": \"Welcome to Sistearth v4 REST API.\" }").build());
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
