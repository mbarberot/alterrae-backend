package com.sistearth.tools.jsonapi.builders.errors;

import com.sistearth.tools.jsonapi.builders.JSONApiBuilder;

public class JsonApiErrorBuilder extends JSONApiBuilder {
    public JsonApiErrorBuilder status(String status) {
        data.put("status", status);
        return this;
    }

    public JsonApiErrorBuilder title(String title) {
        data.put("title", title);
        return this;
    }
}
