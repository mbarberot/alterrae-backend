package com.sistearth.spark.services;

import com.sistearth.db.Database;
import com.sistearth.api.db.ModelException;
import com.sistearth.api.db.ModelManager;
import com.sistearth.api.beans.User;
import com.sistearth.db.mysql.UserManager;
import com.sistearth.game.auth.Authenticator;
import com.sistearth.game.business.UserCreation;
import com.sistearth.spark.extractors.TokenPayloadExtractor;
import com.sistearth.spark.extractors.UserCreationPayloadExtractor;
import com.sistearth.spark.extractors.UserUpdatePayloadExtractor;
import com.sistearth.spark.filters.AuthorizationTokenFilter;
import com.sistearth.spark.token.TokenManager;
import com.sistearth.api.payloads.TokenPayload;
import com.sistearth.api.payloads.UserCreationPayload;
import com.sistearth.api.payloads.UserUpdatePayload;
import com.sistearth.view.response.jsonapi.JsonApiErrorView;
import com.sistearth.view.response.jsonapi.JsonApiUserView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.Spark;

import static com.sistearth.game.auth.Authenticator.Result.ACCEPTED;
import static com.sistearth.spark.view.Answer.newJsonAnswer;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.StringUtils.isBlank;
import static spark.Spark.*;

public class UserRestService implements Service {
    private static final Log LOG = LogFactory.getLog(UserRestService.class.getName());

    @Override
    public void registerRoutes() throws ServiceException {
        ModelManager<User> userManager = new UserManager(Database.getDatabase());
        TokenManager tokenManager = new TokenManager();

        get("/api/users/profile", (request, response) -> {
            TokenPayload payload = new TokenPayloadExtractor().extractPayload(request);
            User user = userManager.getBy("username", tokenManager.decodeToken(payload.getToken()));
            return newJsonAnswer(response).body(new JsonApiUserView(user)).build();
        });

        get("/api/users/:id", (request, response) -> {
            User user = userManager.getById(valueOf(request.params(":id")));
            return newJsonAnswer(response).body(new JsonApiUserView(user)).build();
        });

        post("/api/users", (request, response) -> {
            UserCreationPayload payload = new UserCreationPayloadExtractor().extractPayload(request);

            return new UserCreation(payload, userManager)
                    .onSuccess(entity -> newJsonAnswer(response)
                            .status(200)
                            .body(new JsonApiUserView(entity))
                            .build())
                    .onFailure(errors -> newJsonAnswer(response)
                            .status(400)
                            .body(new JsonApiErrorView(errors))
                            .build()
                    )
                    .resolve();
        });

        put("/api/users", (request, response) -> {
            UserUpdatePayload payload = new UserUpdatePayloadExtractor().extractPayload(request);

            if (!payload.isValid()) {
                return newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView(payload.getErrors()))
                        .build();
            }

            String token = new TokenPayloadExtractor().extractPayload(request).getToken();
            User authenticatedUser = userManager.getBy("username", tokenManager.decodeToken(token));
            User payloadUser = payload.getEntity();

            Authenticator authenticator = new Authenticator(userManager);
            if (authenticator.authenticate(authenticatedUser.getUsername(), payload.getActualPassword()) != ACCEPTED) {
                return newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView("400", "Wrong password confirmation"))
                        .build();
            }

            if (payloadUser.getId() == null) {
                payloadUser.setId(authenticatedUser.getId());
            }
            if (isBlank(payloadUser.getUsername())) {
                payloadUser.setUsername(authenticatedUser.getUsername());
            }
            if (isBlank(payloadUser.getPassword())) {
                payloadUser.setPassword(authenticatedUser.getPassword());
            }
            if (isBlank(payloadUser.getEmail())) {
                payloadUser.setEmail(authenticatedUser.getEmail());
            }


            try {
                userManager.update(payloadUser);
                User user = userManager.getById(payloadUser.getId());
                return newJsonAnswer(response).body(new JsonApiUserView(user)).build();
            } catch (ModelException e) {
                LOG.error("Failed to get updated user", e);
                return newJsonAnswer(response).status(500).build();
            }


        });
    }


    @Override
    public void registerFilters() throws ServiceException {
        Spark.before("/api/users/profile", new AuthorizationTokenFilter());
    }
}
