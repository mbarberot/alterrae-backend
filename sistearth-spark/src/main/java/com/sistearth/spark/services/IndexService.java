package com.sistearth.spark.services;

import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/", (request, response) -> "Welcome to Sistearth v4 REST API.");
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
