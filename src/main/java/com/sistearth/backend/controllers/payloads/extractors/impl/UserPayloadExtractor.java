package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;

public class UserPayloadExtractor implements PayloadExtractor<UserPayload> {

    @Override
    public UserPayload extractPayload(String requestBody) {
        return null;
    }
}
