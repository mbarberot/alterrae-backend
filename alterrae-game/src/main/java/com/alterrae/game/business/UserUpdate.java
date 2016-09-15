package com.alterrae.game.business;

import com.alterrae.api.beans.Error;
import com.alterrae.api.business.BusinessPromise;
import com.alterrae.api.payloads.UserUpdatePayload;
import com.alterrae.db.api.entity.User;
import com.alterrae.db.api.manager.ModelException;
import com.alterrae.db.api.manager.ModelManager;
import com.alterrae.game.auth.Authenticator;
import com.alterrae.game.validators.UserUpdateValidator;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static com.alterrae.game.auth.Authenticator.Result.ACCEPTED;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserUpdate extends BusinessPromise<User> {

    private final UserUpdatePayload payload;
    private final ModelManager<User> userManager;
    private final String tokenInfo;
    private final Authenticator authenticator;
    private final UserUpdateValidator validator;

    public UserUpdate(UserUpdatePayload payload, ModelManager<User> userManager, String tokenInfo) {
        this.payload = payload;
        this.userManager = userManager;
        this.tokenInfo = tokenInfo;
        this.authenticator = new Authenticator(userManager);
        this.validator = new UserUpdateValidator(payload);
    }

    @Override
    protected void doIt() {
        if (!validator.isValid()) {
            errors.addAll(validator.getErrors());
            return;
        }

        User user;
        try {
            user = userManager.getBy("username", tokenInfo);
        } catch (ModelException e) {
            errors.add(new Error("401", "bad-token"));
            return;
        }

        if (authenticator.authenticate(user.getUsername(), payload.getActualPassword()) != ACCEPTED) {
            errors.add(new Error("400", "Wrong password confirmation"));
            return;
        }

        Map<String, Object> data = newHashMap();
        if (isNotBlank(payload.getPassword())) {
            data.put("password", payload.getPassword());
        }
        if (isNotBlank(payload.getEmail())) {
            data.put("email", payload.getEmail());
        }

        userManager.update(user, data);

        try {
            entity = userManager.getById(user.getId().toString());
        } catch (ModelException e) {
            errors.add(new Error("500", "update-failed"));
        }
    }

}
