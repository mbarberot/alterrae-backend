package com.sistearth.core.services;

import com.sistearth.api.database.Database;
import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.database.PostManager;
import com.sistearth.core.routes.PostsGetAll;
import com.sistearth.core.routes.PostsGetSingle;

import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes() throws ServiceException {
        PostManager model = new PostManager(Database.getDatabase());
        get("/api/posts", new PostsGetAll(model));
        get("/api/posts/:id", new PostsGetSingle(model));
    }
}
