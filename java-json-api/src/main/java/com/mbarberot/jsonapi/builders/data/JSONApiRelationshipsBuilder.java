package com.mbarberot.jsonapi.builders.data;

import com.mbarberot.jsonapi.builders.JSONApiBuilder;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

public class JSONApiRelationshipsBuilder extends JSONApiBuilder {
    public JSONApiRelationshipsBuilder() {
        super();
    }

    public JSONApiRelationshipsBuilder addSingleData(String name, String id, String type) {
        Map<String, String> singleData = newHashMap(of("id", id, "type", type));
        data.put(name, newHashMap(of("data", singleData)));
        return this;
    }
}
