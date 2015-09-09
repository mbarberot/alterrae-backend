package com.sistearth.tools.jsonapi.builders;

public class JSONApiDataDocumentBuilder extends JSONApiBuilder {
    public JSONApiDataDocumentBuilder() {
        super();
    }

    public JSONApiDataDocumentBuilder data(JSONApiDataBuilder dataBuilder) {
        data.put("data", dataBuilder.build());
        return this;
    }
}
