package com.sistearth.backend.services.impl;

import com.sistearth.backend.controllers.impl.GetUserById;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.models.managers.impl.UserManager;
import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.backend.views.View;
import com.sistearth.backend.views.impl.JsonApiUserView;

import static com.sistearth.backend.utils.Database.getDatabase;
import static spark.Spark.get;

public class UserRestService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        ModelManager<User> userManager = new UserManager(getDatabase());
        View<User> userView = new JsonApiUserView();

        get("/api/users/:id", new GetUserById(userManager, userView));
    }
}
