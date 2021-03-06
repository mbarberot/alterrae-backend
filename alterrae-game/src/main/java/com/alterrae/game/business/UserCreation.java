package com.alterrae.game.business;

import com.alterrae.db.api.manager.ModelException;
import com.alterrae.db.api.manager.ModelManager;
import com.alterrae.api.beans.Error;
import com.alterrae.db.api.entity.User;
import com.alterrae.api.payloads.UserCreationPayload;
import com.alterrae.api.business.BusinessPromise;
import com.alterrae.game.validators.UserCreationValidator;

public class UserCreation extends BusinessPromise<User> {

    private final UserCreationPayload payload;
    private final ModelManager<User> userManager;
    private final UserCreationValidator validator;

    public UserCreation(UserCreationPayload payload, ModelManager<User> userManager) {
        super();
        this.payload = payload;
        this.userManager = userManager;
        this.validator = new UserCreationValidator(payload);
    }

    @Override
    protected void doIt() {
        if (!validator.isValid()) {
            errors.addAll(validator.getErrors());
            return;
        }

        if (alreadyExists(payload.getUsername())) {
            errors.add(new Error("400", "username-already-exists"));
            return;
        }

        try {
            userManager.create(payload.getEntity());
            entity = userManager.getBy("username", payload.getUsername());
        } catch (ModelException e) {
            errors.add(new Error("500", "creation-failed"));
        }
    }

    private boolean alreadyExists(String username) {
        return !userManager.findBy("username", username).isEmpty();
    }
}
