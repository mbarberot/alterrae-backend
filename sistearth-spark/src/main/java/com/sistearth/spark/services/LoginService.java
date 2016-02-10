package com.sistearth.spark.services;

import com.sistearth.db.beans.User;
import com.sistearth.db.mysql.UserManager;
import com.sistearth.game.auth.Authenticator;
import com.sistearth.spark.extractors.LoginPayloadExtractor;
import com.sistearth.spark.token.TokenManager;
import com.sistearth.spark.view.Answer;
import com.sistearth.view.request.payloads.LoginPayload;
import com.sistearth.view.response.json.JsonLoginView;
import com.sistearth.view.response.jsonapi.JsonApiErrorView;

import static com.sistearth.db.Database.getDatabase;
import static com.sistearth.game.auth.Authenticator.Result.REJECTED;
import static com.sistearth.spark.utils.LabelUtils.getLabel;
import static spark.Spark.post;

public class LoginService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        TokenManager tokenManager = new TokenManager();
        post("/api/login", (request, response) -> {
            LoginPayload payload = new LoginPayloadExtractor().extractPayload(request);
            Authenticator auth = new Authenticator(new UserManager(getDatabase()));

            if (!payload.isValid() || auth.authenticate(payload.getUsername(), payload.getPassword()) == REJECTED) {
                return new Answer(response)
                        .status(402)
                        .body(new JsonApiErrorView("402", getLabel("error.auth.bad-credential")))
                        .build();
            }

            User authenticatedUser = auth.getAuthenticatedUser();
            return new Answer(response).body(new JsonLoginView(authenticatedUser, tokenManager.createToken(authenticatedUser))).build();
        });
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
