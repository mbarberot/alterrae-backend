package com.sistearth.spark.services;

import com.sistearth.api.beans.Error;
import com.sistearth.api.business.BusinessPromise;
import com.sistearth.api.payloads.TokenPayload;
import com.sistearth.db.api.entity.User;
import com.sistearth.db.api.manager.ModelException;
import com.sistearth.db.api.manager.ModelManager;
import com.sistearth.db.core.UserManager;
import com.sistearth.game.business.UserCreation;
import com.sistearth.game.business.UserDelete;
import com.sistearth.game.business.UserUpdate;
import com.sistearth.spark.extractors.TokenPayloadExtractor;
import com.sistearth.spark.extractors.UserCreationPayloadExtractor;
import com.sistearth.spark.extractors.UserDeletePayloadExtractor;
import com.sistearth.spark.extractors.UserUpdatePayloadExtractor;
import com.sistearth.spark.filters.AuthorizationTokenFilter;
import com.sistearth.spark.token.TokenHelper;
import com.sistearth.spark.token.TokenManager;
import com.sistearth.view.request.PayloadException;
import com.sistearth.view.response.jsonapi.JsonApiErrorView;
import com.sistearth.view.response.jsonapi.JsonApiUserView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.Spark;

import static com.sistearth.spark.token.TokenHelper.getTokenFromPayload;
import static com.sistearth.spark.view.Answer.newJsonAnswer;
import static spark.Spark.*;

public class UserRestService implements Service {
    private static final Log LOG = LogFactory.getLog(UserRestService.class.getName());

    @Override
    public void registerRoutes() throws ServiceException {
        ModelManager<User> userManager = new UserManager();
        TokenManager tokenManager = new TokenManager();

        get("/api/users/profile", (request, response) -> new BusinessPromise<User>() {
                    @Override
                    protected void doIt() {
                        try {
                            entity = userManager.getBy("username", getTokenFromPayload(request, tokenManager));
                        } catch (ModelException | PayloadException e) {
                            errors.add(new Error("500", e.getMessage()));
                        }
                    }
                }.onSuccess(entity -> newJsonAnswer(response)
                        .status(200)
                        .body(new JsonApiUserView(entity))
                        .build()
                ).onFailure(errors -> newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView(errors))
                        .build()
                ).resolve()
        );

        get("/api/users/:id", (request, response) -> new BusinessPromise<User>() {
                    @Override
                    protected void doIt() {
                        try {
                            entity = userManager.getById(request.params(":id"));
                        } catch (ModelException e) {
                            errors.add(new Error("500", e.getMessage()));
                        }
                    }
                }.onSuccess(entity -> newJsonAnswer(response)
                        .status(200)
                        .body(new JsonApiUserView(entity))
                        .build()
                ).onFailure(errors -> newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView(errors))
                        .build()
                ).resolve()
        );

        post("/api/users", (request, response) -> new UserCreation(
                        new UserCreationPayloadExtractor().extractPayload(request),
                        userManager
                ).onSuccess(entity -> newJsonAnswer(response)
                        .status(200)
                        .body(new JsonApiUserView(entity))
                        .build()
                ).onFailure(errors -> newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView(errors))
                        .build()
                ).resolve()
        );

        put("/api/users", (request, response) -> new UserUpdate(
                        new UserUpdatePayloadExtractor().extractPayload(request),
                        userManager,
                        getTokenFromPayload(request, tokenManager)
                ).onSuccess(entity -> newJsonAnswer(response)
                        .status(200)
                        .body(new JsonApiUserView(entity))
                        .build()
                ).onFailure(errors -> newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView(errors))
                        .build()
                ).resolve()
        );

        delete("/api/users", (request, response) -> new UserDelete(
                        new UserDeletePayloadExtractor().extractPayload(request),
                        userManager,
                        getTokenFromPayload(request, tokenManager)
                ).onSuccess(entity -> newJsonAnswer(response)
                        .status(200)
                        .build()
                ).onFailure(errors -> newJsonAnswer(response)
                        .status(400)
                        .body(new JsonApiErrorView(errors))
                        .build()
                ).resolve()
        );
    }

    @Override
    public void registerFilters() throws ServiceException {
        Spark.before("/api/users/profile", new AuthorizationTokenFilter());
    }
}
