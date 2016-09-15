package com.alterrae.jsonutils.jsonapi;

import com.alterrae.api.beans.Error;
import com.github.mbarberot.core.builder.JsonApiErrorBuilder;

import java.util.List;

public class ErrorBuilder {
    public Object build(List<Error> errors) {
        return newErrorDocument().error(
                errors.stream().map(error -> JSONApi.Error.newError()
                        .title(error.getTitle())
                        .status(error.getStatus())
                ).toArray(JsonApiErrorBuilder[]::new)
        ).build();
    }
}
