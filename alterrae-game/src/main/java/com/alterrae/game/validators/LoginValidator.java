package com.alterrae.game.validators;

import com.alterrae.api.beans.Error;
import com.alterrae.api.payloads.LoginPayload;
import com.alterrae.api.validators.Validator;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class LoginValidator implements Validator {
    private LoginPayload payload;

    public LoginValidator(LoginPayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(payload.getUsername())
                && isNotBlank(payload.getPassword());
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(payload.getUsername())) {
            errors.add(new Error("400", "username"));
        }
        if (isBlank(payload.getPassword())) {
            errors.add(new Error("400", "password"));
        }
        return errors;
    }
}
