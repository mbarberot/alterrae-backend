package com.sistearth.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.view.request.PayloadException;
import com.sistearth.view.request.payloads.LoginPayload;

import java.io.IOException;
import java.util.Map;

import static com.sistearth.spark.extractors.PayloadExtractorUtils.isFilled;

public class LoginPayloadExtractor extends BasePayloadExtractor<LoginPayload> {
    @Override
    public LoginPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        LoginPayload payload = new LoginPayload();
        JsonNode rootNode;
        try {
            rootNode = new ObjectMapper().readTree(requestBody);
        } catch (IOException e) {
            throw new PayloadException("Failed to deserialize", e);
        }

        if(rootNode.size() == 2) {
            JsonNode usernameNode = rootNode.path("username");
            JsonNode passwordNode = rootNode.path("password");

            if (isFilled(usernameNode)) {
                payload.setUsername(usernameNode.asText());
            }
            if (isFilled(passwordNode)) {
                payload.setPassword(passwordNode.asText());
            }
        }

        return payload;
    }
}
