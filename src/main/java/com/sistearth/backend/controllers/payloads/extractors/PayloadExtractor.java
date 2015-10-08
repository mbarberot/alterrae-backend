package com.sistearth.backend.controllers.payloads.extractors;

import com.sistearth.backend.controllers.payloads.Payload;

public interface PayloadExtractor<V extends Payload> {
    V extractPayload(String requestBody);
}
