package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;
import com.sistearth.db.api.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDeletePayload implements Payload<User> {
    private String actualPassword;

    public UserDeletePayload(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public User getEntity() {
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
