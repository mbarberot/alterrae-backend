package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.PayloadException;
import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.TokenPayload;

import java.util.Map;

import static com.sistearth.backend.utils.TokenHeaderUtils.extractToken;

public class TokenPayloadExtractor implements PayloadExtractor<TokenPayload> {
    @Override
    public TokenPayload extractPayload(String requestBody, Map<String, String> requestHeaders) throws PayloadException {
        TokenPayload payload = new TokenPayload();
        payload.setToken(extractToken(requestHeaders.get("Authorization")));
        return payload;
    }

}
