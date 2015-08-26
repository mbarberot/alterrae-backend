package com.sistearth.core.index;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.index.routes.IndexGetRoute;

import static spark.Spark.get;

public class IndexService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/", new IndexGetRoute());
    }
}
