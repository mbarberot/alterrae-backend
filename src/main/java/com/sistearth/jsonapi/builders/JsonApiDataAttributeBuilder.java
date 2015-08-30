package com.sistearth.jsonapi.builders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JsonApiDataAttributeBuilder implements JsonApiBuilder {
    private final Map<String,String> attributes;

    public JsonApiDataAttributeBuilder() {
        attributes = newHashMap();
    }

    public JsonApiDataAttributeBuilder attribute(String field, String value) {
        attributes.put(field, value);
        return this;
    }

    @Override
    public Map<String,String> build() {
        return attributes;
    }

    public static JsonApiDataAttributeBuilder attributeBuilder() {
        return new JsonApiDataAttributeBuilder();
    }
}
