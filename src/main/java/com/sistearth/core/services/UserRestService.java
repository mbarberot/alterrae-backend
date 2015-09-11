package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.database.UserManager;
import com.sistearth.core.models.User;
import com.sistearth.core.serializers.JSONApiUserBuilder;
import com.sistearth.core.serializers.JsonSerializer;

import static com.sistearth.api.database.Database.getDatabase;
import static spark.Spark.get;

public class UserRestService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        UserManager userManager = new UserManager(getDatabase());
        get("/api/users/:id", (request, response) -> {
            User user = userManager.getById(Integer.valueOf(request.params("id")));
            return new JSONApiUserBuilder().build(user);
        }, new JsonSerializer());
    }
}
