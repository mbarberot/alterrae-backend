package com.sistearth.tools.jsonapi.builders;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class JSONApiBuilder {
    protected final Map<String,Object> data;

    protected JSONApiBuilder() {
        data = newHashMap();
    }

    public Map<String, Object> build() {
        return data;
    }
}
