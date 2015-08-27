package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.routes.PostsGetRoute;

import static spark.Spark.get;

public class PostsRestService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/api/posts", new PostsGetRoute());
    }
}
