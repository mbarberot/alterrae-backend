package com.sistearth.backend.utils;

public class TokenHeaderUtils {
    public static String extractToken(String authorizationHeaderValue) {
        return authorizationHeaderValue
                .replace("Bearer", "")
                .trim()
                ;
    }
}
