package com.sistearth.tools.jsonapi.builders;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class JSONApiRelationshipsBuilder extends JSONApiBuilder {
    List<Map<String,String>> dataRelationships;

    public JSONApiRelationshipsBuilder() {
        super();
        this.dataRelationships = newArrayList();
    }

    public JSONApiRelationshipsBuilder addData(String name, String id, String type) {
        dataRelationships.add(newHashMap(of("id", id, "type", type)));
        data.put(name, newHashMap(of("data", dataRelationships)));
        return this;
    }
}
