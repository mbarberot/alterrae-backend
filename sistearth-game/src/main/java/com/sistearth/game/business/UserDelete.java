package com.sistearth.game.business;

import com.sistearth.api.beans.Error;
import com.sistearth.api.business.BusinessPromise;
import com.sistearth.api.payloads.UserDeletePayload;
import com.sistearth.db.api.entity.User;
import com.sistearth.db.api.manager.ModelException;
import com.sistearth.db.api.manager.ModelManager;
import com.sistearth.game.auth.Authenticator;
import com.sistearth.game.validators.UserDeleteValidator;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.sistearth.game.auth.Authenticator.Result.ACCEPTED;
import static org.apache.commons.lang.StringUtils.isNotBlank;

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
