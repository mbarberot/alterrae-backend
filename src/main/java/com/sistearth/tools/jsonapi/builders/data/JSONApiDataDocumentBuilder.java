package com.sistearth.tools.jsonapi.builders.data;

import com.sistearth.tools.jsonapi.builders.JSONApiBuilder;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class JSONApiDataDocumentBuilder extends JSONApiBuilder {
    public JSONApiDataDocumentBuilder() {
        super();
    }

    public JSONApiDataDocumentBuilder data(JSONApiDataBuilder... dataBuilders) {
        data.put(
                "data",
                newArrayList(dataBuilders).stream().map(JSONApiDataBuilder::build).collect(toList())
        );
        return this;
    }

    public JSONApiBuilder included(JSONApiDataBuilder... dataBuilders) {
        data.put(
                "included",
                newArrayList(dataBuilders).stream().map(JSONApiDataBuilder::build).collect(toList())
        );
        return this;
    }
}
