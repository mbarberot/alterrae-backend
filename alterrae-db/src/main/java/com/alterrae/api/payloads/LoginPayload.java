package com.alterrae.api.payloads;

import com.alterrae.db.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginPayload implements Payload<User> {
    private String username;
    private String password;

    @Override
    public User getEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
