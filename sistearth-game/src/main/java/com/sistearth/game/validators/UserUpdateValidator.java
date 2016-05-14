package com.sistearth.game.validators;

import com.sistearth.api.beans.Error;
import com.sistearth.api.payloads.UserUpdatePayload;
import com.sistearth.api.validators.Validator;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserUpdateValidator implements Validator{
    private UserUpdatePayload payload;

    public UserUpdateValidator(UserUpdatePayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(payload.getActualPassword())
                && (isNotBlank(payload.getEmail()) || isNotBlank(payload.getPassword()));
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(payload.getActualPassword())) {
            errors.add(new Error("400", "actualPassword"));
        }
        if (isBlank(payload.getEmail()) && isBlank(payload.getPassword())) {
            errors.add(new Error("400", "emailOrPassword"));
        }
        return errors;
    }
}
