package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;

public class EmptyPayloadExtractor implements PayloadExtractor<EmptyPayload> {
    @Override
    public EmptyPayload extractPayload(String requestBody) {
        return new EmptyPayload();
    }
}
