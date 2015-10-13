package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.models.beans.User;
import lombok.Data;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Data
public class LoginPayload implements Payload<User> {
    private String username;
    private String password;

    @Override
    public boolean isValid() {
        return isNotBlank(username)
                && isNotBlank(password);
    }

    @Override
    public User getEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
