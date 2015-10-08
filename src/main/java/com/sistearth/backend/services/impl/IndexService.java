package com.sistearth.backend.services.impl;

import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;

import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/", (request, response) -> "Welcome to Sistearth v4 REST API.");
    }
}
