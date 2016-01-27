package com.sistearth.view.request.payloads;


import com.sistearth.db.beans.User;
import com.sistearth.view.request.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Data
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public abstract class UserPayload implements Payload<User> {
    protected String username;
    protected String password;
    protected String email;
    protected String actualPassword;

    @Override
    public User getEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
