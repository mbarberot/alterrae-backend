package com.sistearth.jsonutils.jsonapi;

import com.mbarberot.jsonapi.JSONApi;
import com.mbarberot.jsonapi.builders.error.JsonApiErrorBuilder;
import com.sistearth.api.beans.Error;

import java.util.List;

public class ErrorBuilder {
    public Object build(List<Error> errors) {
        return JSONApi.newErrorDocument().error(
                errors.stream().map(error -> JSONApi.Error.newError()
                        .title(error.getTitle())
                        .status(error.getStatus())
                ).toArray(JsonApiErrorBuilder[]::new)
        ).build();
    }
}
