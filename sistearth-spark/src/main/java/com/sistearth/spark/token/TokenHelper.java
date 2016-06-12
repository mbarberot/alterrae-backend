package com.sistearth.spark.token;

import com.sistearth.spark.extractors.TokenPayloadExtractor;
import com.sistearth.view.request.PayloadException;
import spark.Request;

public class TokenHelper {
    public static String getTokenFromPayload(Request request, TokenManager tokenManager) throws PayloadException {
        return tokenManager.decodeToken(new TokenPayloadExtractor().extractPayload(request).getToken());
    }
}
