package com.alterrae.api.payloads;

import com.alterrae.db.api.entity.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserCreationPayload extends UserPayload {

    public UserCreationPayload(String username, String password, String email) {
        super(username, password, email, null);
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
