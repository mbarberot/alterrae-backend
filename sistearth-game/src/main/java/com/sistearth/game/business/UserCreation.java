package com.sistearth.game.business;

import com.sistearth.api.db.ModelException;
import com.sistearth.api.db.ModelManager;
import com.sistearth.api.beans.Error;
import com.sistearth.api.beans.User;
import com.sistearth.api.payloads.UserCreationPayload;
import com.sistearth.api.business.BusinessPromise;
import com.sistearth.game.validators.UserCreationValidator;

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
            errors.addAll(payload.getErrors());
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
