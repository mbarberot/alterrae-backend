package com.mbarberot.jsonapi.builders.error;

import com.mbarberot.jsonapi.builders.JSONApiBuilder;

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
