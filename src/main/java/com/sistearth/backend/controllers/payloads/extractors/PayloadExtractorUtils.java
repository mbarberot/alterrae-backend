package com.sistearth.backend.controllers.payloads.extractors;

import com.fasterxml.jackson.databind.JsonNode;

import static org.apache.commons.lang.StringUtils.isBlank;

public class PayloadExtractorUtils {
    private PayloadExtractorUtils() {
    }

    public static boolean isFilled(JsonNode usernameNode) {
        return !usernameNode.isMissingNode()
                && !usernameNode.isNull()
                && !isBlank(usernameNode.asText());
    }
}
