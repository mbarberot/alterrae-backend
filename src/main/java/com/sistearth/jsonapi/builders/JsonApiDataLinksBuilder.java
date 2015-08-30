package com.sistearth.jsonapi.builders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JsonApiDataLinksBuilder implements JsonApiBuilder {

    private final Map<String, Object> content;

    public JsonApiDataLinksBuilder(JsonApiRelationShipLinkBuilder links) {
        content = newHashMap();
        content.put("links", links.build());
    }

    public static JsonApiDataLinksBuilder dataLinksBuilder(JsonApiRelationShipLinkBuilder links) {
        return new JsonApiDataLinksBuilder(links);
    }

    @Override
    public Map<String, Object> build() {
        return content;
    }
}
