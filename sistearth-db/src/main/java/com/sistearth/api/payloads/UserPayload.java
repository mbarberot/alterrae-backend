package com.sistearth.api.payloads;


import com.sistearth.db.api.entity.User;
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
}
