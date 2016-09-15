package com.alterrae.game.validators;

import com.alterrae.api.beans.Error;
import com.alterrae.api.payloads.UserDeletePayload;
import com.alterrae.api.validators.Validator;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserDeleteValidator implements Validator {
    private UserDeletePayload payload;

    public UserDeleteValidator(UserDeletePayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(payload.getActualPassword());
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();

        if(isBlank(payload.getActualPassword())) {
            errors.add(new Error("400", "missing-actual-password"));
        }

        return errors;
    }
}
