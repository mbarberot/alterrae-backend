package com.mbarberot.jsonapi.builders.data;

import com.mbarberot.jsonapi.builders.JSONApiBuilder;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class JSONApiDataDocumentBuilder extends JSONApiBuilder {
    public JSONApiDataDocumentBuilder() {
        super();
    }

    public JSONApiDataDocumentBuilder data(JSONApiDataBuilder... dataBuilders) {
        data.put(
                "data",
                dataBuilders.length == 1 ?
                        dataBuilders[0].build() :
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
