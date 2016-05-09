package com.sistearth.spark.extractors;

import com.sistearth.view.request.PayloadException;
import com.sistearth.view.request.payloads.UserCreationPayload;

import java.util.Map;

import static com.sistearth.spark.extractors.utils.PayloadExtractingUtils.fillUserPayload;

public class UserCreationPayloadExtractor extends BasePayloadExtractor<UserCreationPayload> {
    @Override
    public UserCreationPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        UserCreationPayload payload = new UserCreationPayload();
        fillUserPayload(payload, requestBody);
        return payload;
    }
}