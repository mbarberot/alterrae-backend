package com.alterrae.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import com.alterrae.api.payloads.UserDeletePayload;
import com.alterrae.view.request.PayloadException;

import java.util.Map;

import static com.alterrae.spark.extractors.PayloadExtractorUtils.isFilled;
import static com.alterrae.spark.extractors.utils.PayloadExtractingUtils.parseJson;

public class UserDeletePayloadExtractor extends BasePayloadExtractor<UserDeletePayload> {
    @Override
    protected UserDeletePayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        UserDeletePayload payload = new UserDeletePayload();
        JsonNode rootNode = parseJson(requestBody);

        JsonNode actualPasswordNode = rootNode.path("data").path("attributes").path("actualPassword");
        if (isFilled(actualPasswordNode)) {
            payload.setActualPassword(actualPasswordNode.asText());
        }

        return payload;
    }
}
