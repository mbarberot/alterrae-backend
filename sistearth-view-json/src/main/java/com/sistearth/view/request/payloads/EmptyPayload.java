package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;
import com.sistearth.view.request.Payload;
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
