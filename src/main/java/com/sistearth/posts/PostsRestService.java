package com.sistearth.posts;

import com.sistearth.service.Service;
import com.sistearth.service.ServiceException;

import static spark.Spark.get;

public class PostsRestService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        get("/posts", (req, res) -> "Posts");
    }
}
