package com.sistearth.game.validators;

import com.sistearth.api.beans.Error;
import com.sistearth.api.payloads.UserCreationPayload;
import com.sistearth.api.validators.Validator;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserCreationValidator implements Validator {

    private UserCreationPayload payload;

    public UserCreationValidator(UserCreationPayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(payload.getUsername())
                && isNotBlank(payload.getEmail())
                && EmailValidator.getInstance().isValid(payload.getEmail())
                && isNotBlank(payload.getPassword());
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(payload.getUsername())) {
            errors.add(new Error("400", "username"));
        }

        if (isBlank(payload.getEmail())) {
            errors.add(new Error("400", "email"));
        } else if (!EmailValidator.getInstance().isValid(payload.getEmail())) {
            errors.add(new Error("400", "email-bad-syntax"));
        }

        if (isBlank(payload.getPassword())) {
            errors.add(new Error("400", "password"));
        }
        return errors;
    }
}
