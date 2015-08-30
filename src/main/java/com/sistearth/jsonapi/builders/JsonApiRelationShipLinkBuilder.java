package com.sistearth.jsonapi.builders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JsonApiRelationShipLinkBuilder implements JsonApiBuilder {
    private final Map<String, String> links;

    public static JsonApiRelationShipLinkBuilder linkBuilder() {
        return new JsonApiRelationShipLinkBuilder();
    }

    public JsonApiRelationShipLinkBuilder() {
        links = newHashMap();
    }

    public JsonApiRelationShipLinkBuilder self(String url) {
        links.put("self", url);
        return this;
    }

    public JsonApiRelationShipLinkBuilder related(String url) {
        links.put("related", url);
        return this;
    }

    @Override
    public Map<String, String> build() {
        return links;
    }
}
