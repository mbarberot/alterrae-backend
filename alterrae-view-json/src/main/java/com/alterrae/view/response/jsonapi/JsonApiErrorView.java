package com.alterrae.view.response.jsonapi;

import com.alterrae.api.beans.Error;
import com.alterrae.api.misc.Errors;
import com.alterrae.view.response.ErrorView;
import com.alterrae.view.response.ViewException;
import com.alterrae.view.serializer.JacksonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mbarberot.java.jsonapi.structure.document.ErrorDocument;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class JsonApiErrorView extends ErrorView {
    public JsonApiErrorView(List<Error> errors) {
        super(errors);
    }

    public JsonApiErrorView(Errors errors) {
        super(errors.getErrors());
    }

    public JsonApiErrorView(String status, String title) {
        super(status, title);
    }

    public JsonApiErrorView(String status) {
        super(status);
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JacksonSerializer().render(build(errors));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }

    // TODO DO IT WITH JAVA-JSONAPI
    public Object build(List<Error> errors) {
        return new ErrorDocument(
                errors.stream().map(
                        error -> new com.github.mbarberot.java.jsonapi.structure.errors.Error(error.getStatus())
                                .setTitle(error.getTitle())
                                .setStatus(error.getStatus())
                ).collect(toList())
        );
    }
}
