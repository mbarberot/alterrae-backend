package com.sistearth.backend.controllers.payloads.extractors;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.controllers.payloads.PayloadException;

import java.util.Map;

public interface PayloadExtractor<V extends Payload> {
    V extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException;
}
