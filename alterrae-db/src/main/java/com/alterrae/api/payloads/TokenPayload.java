package com.alterrae.api.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayload implements Payload<String> {
    private String token;

    @Override
    public String getEntity() {
        return token;
    }
}
