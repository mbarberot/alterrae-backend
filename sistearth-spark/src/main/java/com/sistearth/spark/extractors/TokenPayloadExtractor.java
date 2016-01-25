package com.sistearth.spark.extractors;

import com.sistearth.view.request.PayloadException;
import com.sistearth.view.request.payloads.TokenPayload;

import java.util.Map;

public class TokenPayloadExtractor extends BasePayloadExtractor<TokenPayload> {
    @Override
    public TokenPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        TokenPayload payload = new TokenPayload();
        payload.setToken(extractToken(requestHeaders.get("Authorization")));
        return payload;
    }

    public static String extractToken(String authorizationHeaderValue) {
        return authorizationHeaderValue
                .replace("Bearer", "")
                .trim()
                ;
    }
}
