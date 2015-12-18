package com.sistearth.backend.services.impl;

import com.sistearth.backend.controllers.impl.GetUserByIdController;
import com.sistearth.backend.controllers.impl.GetUserProfileController;
import com.sistearth.backend.controllers.impl.PostUserController;
import com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.models.managers.impl.UserManager;
import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.backend.services.filter.impl.AuthorizationTokenFilter;
import com.sistearth.backend.views.impl.JsonApiUserView;
import spark.Spark;

import static com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor.PayloadType.CREATION;
import static com.sistearth.backend.utils.Database.getDatabase;
import static spark.Spark.get;
import static spark.Spark.post;

public class UserRestService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        ModelManager<User> userManager = new UserManager(getDatabase());

        get("/api/users/profile", new GetUserProfileController(userManager, new JsonApiUserView()));
        get("/api/users/:id", new GetUserByIdController(userManager, new JsonApiUserView()));
        post("/api/users", new PostUserController(userManager, new UserPayloadExtractor(CREATION), new JsonApiUserView()));
    }

    @Override
    public void registerFilters() throws ServiceException {
        Spark.before("/api/users/profile", new AuthorizationTokenFilter());
    }
}
