package com.sistearth.game.validators;

import com.sistearth.api.beans.Error;
import com.sistearth.api.payloads.UserUpdatePayload;
import com.sistearth.api.validators.Validator;
import com.sistearth.game.Settings;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.game.Settings.*;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserUpdateValidator implements Validator {
    private UserUpdatePayload payload;

    public UserUpdateValidator(UserUpdatePayload payload) {
        this.payload = payload;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(payload.getActualPassword())
                && hasOneOf(payload.getEmail(), payload.getPassword())
                && isEmailValid(payload.getEmail())
                && isPasswordValid(payload.getPassword());
    }

    private boolean isPasswordValid(String password) {
        return isBlank(password) || password.length() >= PASSWORD_MIN_LENGTH;
    }

    private boolean isEmailValid(String email) {
        return isBlank(email) || EmailValidator.getInstance().isValid(email);
    }

    private boolean hasOneOf(String email, String password) {
        return isNotBlank(email) || isNotBlank(password);
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(payload.getActualPassword())) {
            errors.add(new Error("400", "actualPassword"));
        }

        boolean hasEmailOrPassword = false;

        if (isNotBlank(payload.getEmail())) {
            hasEmailOrPassword = true;
            if (!EmailValidator.getInstance().isValid(payload.getEmail())) {
                errors.add(new Error("400", "email-bad-syntax"));
            }
        }

        if (isNotBlank(payload.getPassword())) {
            hasEmailOrPassword = true;
            if (payload.getPassword().length() < PASSWORD_MIN_LENGTH) {
                errors.add(new Error("400", "password-bad"));
            }
        }

        if (!hasEmailOrPassword) {
            errors.add(new Error("400", "emailOrPassword"));
        }

        return errors;
    }
}
