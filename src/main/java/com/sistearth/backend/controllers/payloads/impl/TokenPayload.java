package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.utils.Error;
import lombok.Data;

import java.util.List;

@Data
public class TokenPayload implements Payload {
    private String token;

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public Object getEntity() {
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return null;
    }
}
