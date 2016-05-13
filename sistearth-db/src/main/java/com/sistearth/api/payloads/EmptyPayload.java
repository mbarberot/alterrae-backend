package com.sistearth.api.payloads;

import com.sistearth.api.beans.Error;
import lombok.EqualsAndHashCode;

import java.util.List;

import static java.util.Collections.emptyList;

@EqualsAndHashCode
public class EmptyPayload implements Payload<Void> {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Void getEntity() {
        return null;
    }

    @Override
    public List<Error> getErrors() {
        return emptyList();
    }
}
