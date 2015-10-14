package com.sistearth.tools.jsonapi.builders.error;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

public class JSONApiErrorDocumentBuilder extends JsonApiErrorBuilder {
    public JSONApiErrorDocumentBuilder error(JsonApiErrorBuilder ... errorBuilders) {
        data.put(
                "errors",
                newArrayList(errorBuilders).stream().map(JsonApiErrorBuilder::build).collect(toList())
        );
        return this;
    }
}
