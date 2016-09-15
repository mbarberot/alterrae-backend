package com.alterrae.spark.extractors;

import com.alterrae.api.payloads.UserUpdatePayload;
import com.alterrae.view.request.PayloadException;

import java.util.Map;

import static com.alterrae.spark.extractors.utils.PayloadExtractingUtils.fillUserPayload;

public class UserUpdatePayloadExtractor extends BasePayloadExtractor<UserUpdatePayload> {

    @Override
    protected UserUpdatePayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        UserUpdatePayload payload = new UserUpdatePayload();
        fillUserPayload(payload, requestBody);
        return payload;
    }
}
