package com.sistearth.backend.controllers.payloads.extractors;

import com.sistearth.backend.controllers.payloads.Payload;
import spark.Request;

public interface PayloadExtractor {
    Payload extractPayload(Request request);
}
