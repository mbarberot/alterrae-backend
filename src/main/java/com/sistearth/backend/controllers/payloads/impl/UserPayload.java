package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.models.beans.User;
import lombok.Data;

@Data
public abstract class UserPayload implements Payload<User> {
    protected String username;
    protected String password;
    protected String email;
    protected String actualPassword;

    public User getEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
