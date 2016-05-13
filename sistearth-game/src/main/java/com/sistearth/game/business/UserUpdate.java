package com.sistearth.game.business;

import com.sistearth.api.beans.Error;
import com.sistearth.api.beans.User;
import com.sistearth.api.business.BusinessPromise;
import com.sistearth.api.db.ModelException;
import com.sistearth.api.db.ModelManager;
import com.sistearth.api.payloads.UserUpdatePayload;
import com.sistearth.game.auth.Authenticator;

import static com.sistearth.game.auth.Authenticator.Result.ACCEPTED;
import static org.apache.commons.lang.StringUtils.isBlank;

public class UserUpdate extends BusinessPromise<User> {

    private final UserUpdatePayload payload;
    private final ModelManager<User> userManager;
    private final String tokenInfo;
    private final Authenticator authenticator;

    public UserUpdate(UserUpdatePayload payload, ModelManager<User> userManager, String tokenInfo) {
        this.payload = payload;
        this.userManager = userManager;
        this.tokenInfo = tokenInfo;
        this.authenticator = new Authenticator(userManager);
    }

    @Override
    protected void doIt() {
        if (!payload.isValid()) {
            errors.addAll(payload.getErrors());
            return;
        }

        User authenticatedUser;
        try {
            authenticatedUser = userManager.getBy("username", tokenInfo);
        } catch (ModelException e) {
            errors.add(new Error("401", "bad-token"));
            return;
        }

        if (authenticator.authenticate(authenticatedUser.getUsername(), payload.getActualPassword()) != ACCEPTED) {
            errors.add(new Error("400", "Wrong password confirmation"));
            return;
        }

        User payloadUser = mergeUser(payload.getEntity(), authenticatedUser);
        userManager.update(payloadUser);

        try {
            entity = userManager.getById(payloadUser.getId());
        } catch (ModelException e) {
            errors.add(new Error("500", "update-failed"));
        }
    }

    private User mergeUser(User payloadUser, User authenticatedUser) {
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
        return payloadUser;
    }
}
