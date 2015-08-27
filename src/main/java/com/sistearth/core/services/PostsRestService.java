package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.database.PostModel;
import com.sistearth.core.routes.PostsGetAll;
import com.sistearth.core.routes.PostsGetSingle;
import org.sql2o.Sql2o;

import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes(Sql2o database) throws ServiceException {
        PostModel model = new PostModel(database);
        get("/api/posts", new PostsGetAll(model));
        get("/api/posts/:id", new PostsGetSingle(model));
    }
}
