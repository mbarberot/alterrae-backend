package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;
import spark.Request;

public class EmptyPayloadExtractor implements PayloadExtractor<EmptyPayload> {
    @Override
    public EmptyPayload extractPayload(Request request) {
        return new EmptyPayload();
    }
}
