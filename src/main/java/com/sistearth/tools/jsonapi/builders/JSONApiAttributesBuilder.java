package com.sistearth.tools.jsonapi.builders;

public class JSONApiAttributesBuilder extends JSONApiBuilder {
    public JSONApiAttributesBuilder() {
        super();
    }

    public JSONApiAttributesBuilder add(String attrName, String attrValue) {
        data.put(attrName, attrValue);
        return this;
    }
}
