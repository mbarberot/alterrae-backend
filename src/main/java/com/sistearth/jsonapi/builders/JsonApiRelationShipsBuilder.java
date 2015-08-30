package com.sistearth.jsonapi.builders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JsonApiRelationShipsBuilder implements JsonApiBuilder {
    private final Map<String, Object> relationship;

    public JsonApiRelationShipsBuilder() {
        relationship = newHashMap();
    }

    public JsonApiRelationShipsBuilder relationship(String name, JsonApiRelationShipBuilder content) {
        relationship.put(name, content.build());
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return relationship;
    }

    public static JsonApiRelationShipsBuilder relationshipsBuilder() {
        return new JsonApiRelationShipsBuilder();
    }
}
