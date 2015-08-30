package com.sistearth.jsonapi.builders;

import com.sistearth.jsonapi.beans.JsonApiBasicData;

import java.util.Map;

public class JsonApiRelationshipDataBuilder implements JsonApiBuilder {
    private final JsonApiBasicData data;

    public static JsonApiRelationshipDataBuilder relationshipDataBuilder(String id, String type) {
        return new JsonApiRelationshipDataBuilder(id, type);
    }

    public JsonApiRelationshipDataBuilder(String id, String type) {
        data = new JsonApiBasicData();
        data.setId(id);
        data.setType(type);
    }

    @Override
    public JsonApiBasicData build() {
        return data;
    }
}
