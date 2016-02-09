package com.mbarberot.jsonapi.builders.data;

import com.mbarberot.jsonapi.builders.JSONApiBuilder;

public class JSONApiAttributesBuilder extends JSONApiBuilder {
    public JSONApiAttributesBuilder() {
        super();
    }

    public JSONApiAttributesBuilder add(String attrName, String attrValue) {
        data.put(attrName, attrValue);
        return this;
    }
}
