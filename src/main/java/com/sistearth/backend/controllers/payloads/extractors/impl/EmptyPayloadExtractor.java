package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;

import java.util.Map;

public class EmptyPayloadExtractor implements PayloadExtractor<EmptyPayload> {
    @Override
    public EmptyPayload extractPayload(String requestBody, Map<String, String> requestHeaders) {
        return new EmptyPayload();
    }
}
