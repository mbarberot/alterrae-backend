package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;

import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/", (request, response) -> "Welcome to Sistearth v4 REST API.");
    }
}
