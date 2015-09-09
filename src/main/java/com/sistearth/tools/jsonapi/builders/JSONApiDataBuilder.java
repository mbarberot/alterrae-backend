package com.sistearth.tools.jsonapi.builders;

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
}
