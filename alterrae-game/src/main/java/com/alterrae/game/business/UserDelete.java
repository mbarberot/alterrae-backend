package com.alterrae.game.business;

import com.alterrae.api.beans.Error;
import com.alterrae.api.business.BusinessPromise;
import com.alterrae.api.payloads.UserDeletePayload;
import com.alterrae.db.api.entity.User;
import com.alterrae.db.api.manager.ModelException;
import com.alterrae.db.api.manager.ModelManager;
import com.alterrae.game.auth.Authenticator;
import com.alterrae.game.validators.UserDeleteValidator;

import static com.google.common.collect.Maps.newHashMap;
import static com.alterrae.game.auth.Authenticator.Result.ACCEPTED;

public class UserDelete extends BusinessPromise<User> {
    private final UserDeletePayload payload;
    private final ModelManager<User> userManager;
    private final String token;
    private final Authenticator authenticator;
    private final UserDeleteValidator validator;

    public UserDelete(UserDeletePayload payload, ModelManager<User> userManager, String token) {
        this.payload = payload;
        this.userManager = userManager;
        this.token = token;
        this.authenticator = new Authenticator(userManager);
        this.validator = new UserDeleteValidator(payload);
    }

    @Override
    protected void doIt() {
        if (!validator.isValid()) {
            errors.addAll(validator.getErrors());
            return;
        }

        User user;
        try {
            user = userManager.getBy("username", token);
        } catch (ModelException e) {
            errors.add(new Error("401", "bad-token"));
            return;
        }

        if (authenticator.authenticate(user.getUsername(), payload.getActualPassword()) != ACCEPTED) {
            errors.add(new Error("400", "Wrong password confirmation"));
            return;
        }

        userManager.delete(user);
    }
}
