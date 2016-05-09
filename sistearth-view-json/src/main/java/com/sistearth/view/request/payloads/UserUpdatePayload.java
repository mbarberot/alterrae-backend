package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;
import com.sistearth.db.beans.User;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.valueOf;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@NoArgsConstructor
public class UserUpdatePayload extends UserPayload {

    public UserUpdatePayload(String id, String username, String password, String email, String actualPassword) {
        super(id, username, password, email, actualPassword);
    }

    @Override
    public boolean isValid() {
        return isNotBlank(id)
                && isNotBlank(actualPassword)
                && (isNotBlank(email) || isNotBlank(password));
    }

    @Override
    public User getEntity() {
        return User.builder()
                .id(valueOf(id))
                .username(username)
                .email(email)
                .password(password)
                .build();
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if (isBlank(id)) {
            errors.add(new Error("400", "id"));
        }
        if (isBlank(actualPassword)) {
            errors.add(new Error("400", "actualPassword"));
        }
        if (isBlank(email) && isBlank(password)) {
            errors.add(new Error("400", "emailOrPassword"));
        }
        return errors;
    }

}
