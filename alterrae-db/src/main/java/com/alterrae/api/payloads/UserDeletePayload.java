package com.alterrae.api.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

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
