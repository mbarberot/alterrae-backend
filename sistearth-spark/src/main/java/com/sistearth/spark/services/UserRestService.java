package com.sistearth.spark.services;

import com.sistearth.db.Database;
import com.sistearth.db.api.ModelException;
import com.sistearth.db.api.ModelManager;
import com.sistearth.db.beans.User;
import com.sistearth.db.mysql.UserManager;
import com.sistearth.spark.extractors.TokenPayloadExtractor;
import com.sistearth.spark.extractors.UserPayloadExtractor;
import com.sistearth.spark.filters.AuthorizationTokenFilter;
import com.sistearth.spark.token.TokenManager;
import com.sistearth.spark.view.Answer;
import com.sistearth.view.request.payloads.TokenPayload;
import com.sistearth.view.request.payloads.UserPayload;
import com.sistearth.view.response.jsonapi.JsonApiErrorView;
import com.sistearth.view.response.jsonapi.JsonApiUserView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.Spark;

import static com.sistearth.spark.extractors.UserPayloadExtractor.PayloadType.CREATION;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static spark.Spark.get;
import static spark.Spark.post;

public class UserRestService implements Service {
    private static final Log LOG = LogFactory.getLog(UserRestService.class.getName());

    @Override
    public void registerRoutes() throws ServiceException {
        ModelManager<User> userManager = new UserManager(Database.getDatabase());
        TokenManager tokenManager = new TokenManager();

        get("/api/users/profile", (request, response) -> {
            TokenPayload payload = new TokenPayloadExtractor().extractPayload(request);
            User user = userManager.getBy("username", tokenManager.decodeToken(payload.getToken()));
            return new Answer(response).body(new JsonApiUserView(user)).build();
        });

        get("/api/users/:id", (request, response) -> {
            User user = userManager.getById(valueOf(request.params(":id")));
            return new Answer(response).body(new JsonApiUserView(user)).build();
        });

        post("/api/users", (request, response) -> {
            UserPayload payload = new UserPayloadExtractor(CREATION).extractPayload(request);
            Answer answer  = new Answer(response);
            if (payload.isValid()) {
                User payloadUser = payload.getEntity();
                try {
                    userManager.create(payloadUser);
                } catch (ModelException e) {
                    answer.status(400);
                    if (isNotBlank(e.getCode())) {
                        answer.body(new JsonApiErrorView("400", e.getCode()));
                    }
                    return answer.build();
                }

                User user;
                try {
                    user = userManager.getBy("username", payloadUser.getUsername());
                } catch (ModelException e) {
                    LOG.error("Failed to get created user", e);
                    return answer.status(500).build();
                }

                return answer.body(new JsonApiUserView(user)).build();
            } else {
                return answer.body(new JsonApiErrorView(payload.getErrors())).build();
            }
        });
    }

    @Override
    public void registerFilters() throws ServiceException {
        Spark.before("/api/users/profile", new AuthorizationTokenFilter());
    }
}
