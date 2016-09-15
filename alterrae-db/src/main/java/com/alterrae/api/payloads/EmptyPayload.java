package com.alterrae.api.payloads;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EmptyPayload implements Payload<Void> {
    @Override
    public Void getEntity() {
        return null;
    }
}
