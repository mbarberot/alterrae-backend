package com.sistearth.backend.controllers.payloads.impl;

import com.sistearth.backend.controllers.payloads.Payload;

public class EmptyPayload implements Payload {
    @Override
    public boolean isValid() {
        return true;
    }
}
