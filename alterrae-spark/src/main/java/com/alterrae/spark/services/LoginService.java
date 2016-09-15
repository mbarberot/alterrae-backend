package com.alterrae.spark.services;

import com.alterrae.api.validators.Validator;
import com.alterrae.db.api.entity.User;
import com.alterrae.db.core.UserManager;
import com.alterrae.game.auth.Authenticator;
import com.alterrae.game.validators.LoginValidator;
import com.alterrae.spark.extractors.LoginPayloadExtractor;
import com.alterrae.spark.token.TokenManager;
import com.alterrae.api.payloads.LoginPayload;
import com.alterrae.view.response.json.JsonLoginView;
import com.alterrae.view.response.jsonapi.JsonApiErrorView;

import static com.alterrae.game.auth.Authenticator.Result.REJECTED;
import static com.alterrae.spark.utils.LabelUtils.getLabel;
import static com.alterrae.spark.view.Answer.newJsonAnswer;
import static spark.Spark.post;

public class LoginService implements Service {
    @Override
    public void registerRoutes() throws ServiceException {
        TokenManager tokenManager = new TokenManager();
        post("/api/login", (request, response) -> {
            LoginPayload payload = new LoginPayloadExtractor().extractPayload(request);
            Authenticator auth = new Authenticator(new UserManager());
            Validator validator = new LoginValidator(payload);

            if (!validator.isValid() || auth.authenticate(payload.getUsername(), payload.getPassword()) == REJECTED) {
                return newJsonAnswer(response)
                        .status(402)
                        .body(new JsonApiErrorView("402", getLabel("error.auth.bad-credential")))
                        .build();
            }

            User authenticatedUser = auth.getAuthenticatedUser();
            return newJsonAnswer(response)
                    .body(new JsonLoginView(authenticatedUser, tokenManager.createToken(authenticatedUser)))
                    .build();
        });
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
