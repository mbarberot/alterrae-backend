package com.sistearth.jsonapi.builders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JsonApiRelationShipBuilder implements JsonApiBuilder {

    private final Map<String, Object> content;

    public JsonApiRelationShipBuilder() {
        content = newHashMap();
    }

    public JsonApiRelationShipBuilder links(JsonApiRelationShipLinkBuilder links) {
        content.put("links", links.build());
        return this;
    }

    public JsonApiRelationShipBuilder data(JsonApiRelationshipDataBuilder data) {
        content.put("data", data.build());
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return content;
    }

    public static JsonApiRelationShipBuilder relationshipBuilder() {
        return new JsonApiRelationShipBuilder();
    }
}
