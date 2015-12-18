package com.sistearth.backend.services.impl;

import com.sistearth.backend.controllers.impl.LoginController;
import com.sistearth.backend.controllers.payloads.extractors.impl.LoginPayloadExtractor;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.models.managers.impl.UserManager;
import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.backend.utils.TokenManager;
import com.sistearth.backend.views.impl.LoginView;

import static com.sistearth.backend.utils.Database.getDatabase;
import static spark.Spark.post;

public class LoginService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        ModelManager<User> userManager = new UserManager(getDatabase());
        post("/api/login", new LoginController(
                userManager,
                new TokenManager(),
                new LoginPayloadExtractor(),
                new LoginView()
        ));
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
