package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;
import com.sistearth.db.api.entity.User;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

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
