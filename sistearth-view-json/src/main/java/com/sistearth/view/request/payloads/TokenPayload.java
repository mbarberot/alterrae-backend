package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;
import com.sistearth.view.request.Payload;
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
