package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class UserCreationPayload extends UserPayload {
    @Override
    public boolean isValid() {
        return isNotBlank(username)
                && isNotBlank(email)
                && isNotBlank(password);
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(username)) {
            errors.add(new Error("400", "username"));
        }
        if (isBlank(email)) {
            errors.add(new Error("400", "email"));
        }
        if (isBlank(password)) {
            errors.add(new Error("400", "password"));
        }
        return errors;
    }
}
