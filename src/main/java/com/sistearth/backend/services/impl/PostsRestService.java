package com.sistearth.backend.services.impl;

import com.sistearth.backend.controllers.impl.GetAllPostsController;
import com.sistearth.backend.controllers.impl.GetPostByIdController;
import com.sistearth.backend.models.managers.impl.PostManager;
import com.sistearth.backend.models.managers.impl.UserManager;
import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.backend.views.impl.JsonApiPostView;

import static com.sistearth.backend.utils.Database.getDatabase;
import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes() throws ServiceException {
        PostManager postManager = new PostManager(getDatabase());
        UserManager userManager = new UserManager(getDatabase());

        get("/api/posts", new GetAllPostsController(postManager, userManager, new JsonApiPostView()));
        get("/api/posts/:id", new GetPostByIdController(postManager, userManager, new JsonApiPostView()));
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
