package com.sistearth.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import spark.Request;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.lang.StringUtils.isBlank;

public class PayloadExtractorUtils {
    private PayloadExtractorUtils() {
    }

    public static boolean isFilled(JsonNode usernameNode) {
        return !usernameNode.isMissingNode()
                && !usernameNode.isNull()
                && !isBlank(usernameNode.asText());
    }

    public static Map<String, String> getRequestHeaders(Request request) {
        Map<String, String> requestHeaders = newHashMap();
        addIfExists(request, requestHeaders, "Authorization");
        return requestHeaders;
    }

    private static void addIfExists(Request request, Map<String, String> requestHeaders, String header) {
        String value = request.headers(header);
        if (value != null) {
            requestHeaders.put(header, value);
        }
    }
}
