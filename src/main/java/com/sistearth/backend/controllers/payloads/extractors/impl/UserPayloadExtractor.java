package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import spark.Request;

public class UserPayloadExtractor implements PayloadExtractor<UserPayload> {
    @Override
    public UserPayload extractPayload(Request request) {
        return null;
    }
}
