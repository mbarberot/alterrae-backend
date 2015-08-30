package com.sistearth.jsonapi.builders;

import com.sistearth.jsonapi.beans.JsonApiData;

import java.util.Map;

public class JsonApiDataBuilder implements JsonApiBuilder {
    private final JsonApiData data;

    public static JsonApiDataBuilder dataBuilder(String id, String type) {
        return new JsonApiDataBuilder().data(id, type);
    }

    public JsonApiDataBuilder() {
        data = new JsonApiData();
    }

    public JsonApiDataBuilder data(String id, String type) {
        data.setId(id);
        data.setType(type);
        return this;
    }

    public JsonApiDataBuilder attributes(JsonApiDataAttributeBuilder attributes) {
        data.setAttributes(attributes.build());
        return this;
    }

    public JsonApiDataBuilder relationships(JsonApiRelationShipsBuilder relationships) {
        data.setRelationships(relationships.build());
        return this;
    }

    public JsonApiDataBuilder links(JsonApiRelationShipLinkBuilder linkBuilder) {
        data.setLinks(linkBuilder.build());
        return this;
    }

    @Override
    public JsonApiData build() {
        return data;
    }
}
