package com.sistearth.view.response.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.db.beans.Error;
import com.sistearth.jsonutils.jsonapi.ErrorBuilder;
import com.sistearth.view.response.ErrorView;
import com.sistearth.view.response.ViewException;
import com.sistearth.view.serializer.JacksonSerializer;

import java.util.List;

public class JsonApiErrorView extends ErrorView {
    public JsonApiErrorView(List<Error> errors) {
        super(errors);
    }

    public JsonApiErrorView(String status, String title) {
        super(status,title);
    }

    public JsonApiErrorView(String status) {
        super(status);
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JacksonSerializer().render(new ErrorBuilder().build(errors));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
