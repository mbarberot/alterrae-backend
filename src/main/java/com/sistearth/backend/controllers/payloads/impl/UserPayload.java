package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.models.beans.User;

import static org.eclipse.jetty.util.StringUtil.isNotBlank;

public class UserPayload implements Payload {
    private User user;

    public User getUser() {
        return user;
    }

    @Override
    public boolean isValid() {
        return isNotBlank(user.getUsername())
                && isNotBlank(user.getEmail())
                && isNotBlank(user.getPassword());
    }
}
