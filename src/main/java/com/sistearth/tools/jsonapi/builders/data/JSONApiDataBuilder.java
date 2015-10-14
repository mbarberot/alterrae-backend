package com.sistearth.tools.jsonapi.builders.data;

import com.sistearth.tools.jsonapi.builders.JSONApiBuilder;

public class JSONApiDataBuilder extends JSONApiBuilder {
    public JSONApiDataBuilder(String id, String type) {
        super();
        data.put("id", id);
        data.put("type", type);
    }

    public JSONApiDataBuilder attributes(JSONApiAttributesBuilder attributesBuilder) {
        data.put("attributes", attributesBuilder.build());
        return this;
    }

    public JSONApiDataBuilder relationships(JSONApiRelationshipsBuilder relationshipsBuilder) {
        data.put("relationships", relationshipsBuilder.build());
        return this;
    }
}
