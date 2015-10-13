package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.backend.controllers.payloads.PayloadException;
import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserCreationPayload;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import org.apache.commons.lang.NotImplementedException;

import java.io.IOException;

import static com.sistearth.backend.controllers.payloads.extractors.PayloadExtractorUtils.isFilled;

public class UserPayloadExtractor implements PayloadExtractor<UserPayload> {

    private PayloadType payloadType;

    public UserPayloadExtractor(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    @Override
    public UserPayload extractPayload(String requestBody) throws PayloadException {

        UserPayload payload;
        switch (payloadType) {
            case CREATION:
                payload = new UserCreationPayload();
                break;
            default:
                throw new NotImplementedException();
        }

        fillPayload(payload, requestBody);

        return payload;
    }

    private void fillPayload(UserPayload payload, String requestBody) throws PayloadException {
        JsonNode rootNode;
        try {
            rootNode = new ObjectMapper().readTree(requestBody);
        } catch (IOException e) {
            throw new PayloadException("Failed to extract", e);
        }

        JsonNode attributes = rootNode.path("data").path("attributes");
        JsonNode usernameNode = attributes.path("username");
        JsonNode passwordNode = attributes.path("password");
        JsonNode emailNode = attributes.path("email");

        if (isFilled(usernameNode)) {
            payload.setUsername(usernameNode.asText());
        }
        if (isFilled(passwordNode)) {
            payload.setPassword(passwordNode.asText());
        }
        if (isFilled(emailNode)) {
            payload.setEmail(emailNode.asText());
        }
    }

    public enum PayloadType {
        CREATION
    }
}
