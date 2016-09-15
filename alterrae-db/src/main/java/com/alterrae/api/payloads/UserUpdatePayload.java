package com.alterrae.api.payloads;

import com.alterrae.db.api.entity.User;
import lombok.NoArgsConstructor;

import static com.google.common.collect.Lists.newArrayList;

@NoArgsConstructor
public class UserUpdatePayload extends UserPayload {

    public UserUpdatePayload(String username, String password, String email, String actualPassword) {
        super(username, password, email, actualPassword);
    }

    @Override
    public User getEntity() {
        return User.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
