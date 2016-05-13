package com.sistearth.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.sistearth.api.payloads.LoginPayload;
import com.sistearth.view.request.PayloadException;

import java.util.Map;

import static com.sistearth.spark.extractors.utils.PayloadExtractingUtils.parseJson;

public class LoginPayloadExtractor extends BasePayloadExtractor<LoginPayload> {
    @Override
    public LoginPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        LoginPayload payload = new LoginPayload();
        JsonNode rootNode = parseJson(requestBody);

        if(rootNode.size() == 2) {
            JsonNode usernameNode = rootNode.path("username");
            JsonNode passwordNode = rootNode.path("password");

            if (PayloadExtractorUtils.isFilled(usernameNode)) {
                payload.setUsername(usernameNode.asText());
            }
            if (PayloadExtractorUtils.isFilled(passwordNode)) {
                payload.setPassword(passwordNode.asText());
            }
        }

        return payload;
    }
}
