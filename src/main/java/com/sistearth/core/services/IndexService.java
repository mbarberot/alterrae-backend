package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.routes.IndexGet;
import org.sql2o.Sql2o;

import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes(Sql2o database) throws ServiceException {
        get("/", new IndexGet());
    }
}
