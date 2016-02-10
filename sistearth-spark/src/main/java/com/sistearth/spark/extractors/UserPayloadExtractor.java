package com.sistearth.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.view.request.PayloadException;
import com.sistearth.view.request.payloads.UserCreationPayload;
import com.sistearth.view.request.payloads.UserPayload;
import org.apache.commons.lang.NotImplementedException;

import java.io.IOException;
import java.util.Map;

import static com.sistearth.spark.extractors.PayloadExtractorUtils.isFilled;

public class UserPayloadExtractor extends BasePayloadExtractor<UserPayload> {
    private PayloadType payloadType;

    public UserPayloadExtractor(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    @Override
    public UserPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
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
