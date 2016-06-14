package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;
import com.sistearth.db.api.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDeletePayload implements Payload<String> {
    private String actualPassword;

    public UserDeletePayload(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    @Override
    public String getEntity() {
        return actualPassword;
    }
}
