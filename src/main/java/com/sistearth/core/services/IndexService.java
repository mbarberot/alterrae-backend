package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.routes.IndexGet;

import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/", new IndexGet());
    }
}
