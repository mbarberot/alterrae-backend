package com.alterrae.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.alterrae.api.payloads.LoginPayload;
import com.alterrae.view.request.PayloadException;

import java.util.Map;

import static com.alterrae.spark.extractors.PayloadExtractorUtils.isFilled;
import static com.alterrae.spark.extractors.utils.PayloadExtractingUtils.parseJson;

public class LoginPayloadExtractor extends BasePayloadExtractor<LoginPayload> {
    @Override
    public LoginPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        LoginPayload payload = new LoginPayload();
        JsonNode rootNode = parseJson(requestBody);

        if (rootNode.size() == 2) {
            JsonNode usernameNode = rootNode.path("username");
            JsonNode passwordNode = rootNode.path("password");

            if (isFilled(usernameNode)) {
                payload.setUsername(usernameNode.asText());
            }
            if (isFilled(passwordNode)) {
                payload.setPassword(passwordNode.asText());
            }
        } else {
            throw new PayloadException(rootNode.size() > 2 ? "Too many fields" : "Missing fields");
        }

        return payload;
    }
}
