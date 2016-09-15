package com.alterrae.spark.extractors;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import spark.Request;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@NoArgsConstructor(access = PRIVATE)
public class PayloadExtractorUtils {

    public static boolean isFilled(JsonNode node) {
        return !node.isMissingNode()
                && !node.isNull()
                && isNotBlank(node.asText());
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
