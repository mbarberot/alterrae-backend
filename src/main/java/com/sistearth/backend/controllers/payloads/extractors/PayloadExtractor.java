package com.sistearth.backend.controllers.payloads.extractors;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.controllers.payloads.PayloadException;

public interface PayloadExtractor<V extends Payload> {
    V extractPayload(String requestBody) throws PayloadException;
}
