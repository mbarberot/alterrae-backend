package com.alterrae.spark.token;

import com.alterrae.spark.extractors.TokenPayloadExtractor;
import com.alterrae.view.request.PayloadException;
import spark.Request;

public class TokenHelper {
    public static String getTokenFromPayload(Request request, TokenManager tokenManager) throws PayloadException {
        return tokenManager.decodeToken(new TokenPayloadExtractor().extractPayload(request).getToken());
    }
}
