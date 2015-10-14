package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.backend.controllers.payloads.PayloadException;
import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;

import java.io.IOException;

import static com.sistearth.backend.controllers.payloads.extractors.PayloadExtractorUtils.isFilled;

public class LoginPayloadExtractor implements PayloadExtractor<LoginPayload> {

    @Override
    public LoginPayload extractPayload(String requestBody) throws PayloadException {
        LoginPayload payload = new LoginPayload();
        JsonNode rootNode;
        try {
            rootNode = new ObjectMapper().readTree(requestBody);
        } catch (IOException e) {
            throw new PayloadException("Failed to deserialize", e);
        }

        JsonNode usernameNode = rootNode.path("username");
        JsonNode passwordNode = rootNode.path("password");

        if(isFilled(usernameNode)) {
            payload.setUsername(usernameNode.asText());
        }
        if(isFilled(passwordNode)){
            payload.setPassword(passwordNode.asText());
        }

        return payload;
    }
}
