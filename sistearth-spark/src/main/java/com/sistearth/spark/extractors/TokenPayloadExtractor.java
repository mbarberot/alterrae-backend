package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.TokenPayload;
import com.sistearth.view.request.PayloadException;

import java.util.Map;

import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class TokenPayloadExtractor extends OldBasePayloadExtractor<TokenPayload> {

    public static final String BEARER = "Bearer";

    @Override
    public TokenPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        String authorizationHeader = requestHeaders.get("Authorization");
        TokenPayload payload = new TokenPayload();
        if (isValid(authorizationHeader)) {
            payload.setToken(extractToken(authorizationHeader));
        }
        return payload;
    }

    private boolean isValid(String authorizationHeader) {
        return isNotBlank(authorizationHeader) && contains(authorizationHeader, BEARER);
    }

    public static String extractToken(String authorizationHeaderValue) {
        return authorizationHeaderValue
                .replace(BEARER, "")
                .trim()
                ;
    }
}
