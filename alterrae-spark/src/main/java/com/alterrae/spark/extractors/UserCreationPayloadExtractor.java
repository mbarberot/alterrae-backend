package com.alterrae.spark.extractors;

import com.alterrae.api.payloads.UserCreationPayload;
import com.alterrae.view.request.PayloadException;

import java.util.Map;

import static com.alterrae.spark.extractors.utils.PayloadExtractingUtils.fillUserPayload;

public class UserCreationPayloadExtractor extends BasePayloadExtractor<UserCreationPayload> {
    @Override
    public UserCreationPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        UserCreationPayload payload = new UserCreationPayload();
        fillUserPayload(payload, requestBody);
        return payload;
    }
}
