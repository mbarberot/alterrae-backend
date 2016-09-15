package com.alterrae.spark.extractors;

import com.alterrae.api.payloads.TokenPayload;
import com.alterrae.view.request.PayloadException;

import java.util.Map;

import static org.apache.commons.lang.StringUtils.contains;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class TokenPayloadExtractor extends BasePayloadExtractor<TokenPayload> {

    private static final String BEARER = "Bearer";

    @Override
    public TokenPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        String authorizationHeader = requestHeaders.get("Authorization");
        if (isValid(authorizationHeader)) {
            String token = extractToken(authorizationHeader);
            if (isNotBlank(token)) {
                return new TokenPayload(token);
            }
        }

        throw new PayloadException("Invalid authorization header");
    }

    private boolean isValid(String authorizationHeader) {
        return isNotBlank(authorizationHeader) && contains(authorizationHeader, BEARER);
    }

    private static String extractToken(String authorizationHeaderValue) {
        return authorizationHeaderValue
                .replace(BEARER, "")
                .trim()
                ;
    }
}
