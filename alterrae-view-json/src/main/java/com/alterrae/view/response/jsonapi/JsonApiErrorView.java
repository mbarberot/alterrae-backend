package com.alterrae.view.response.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.alterrae.api.beans.Error;
import com.alterrae.api.misc.Errors;
import com.alterrae.jsonutils.jsonapi.ErrorBuilder;
import com.alterrae.view.response.ErrorView;
import com.alterrae.view.response.ViewException;
import com.alterrae.view.serializer.JacksonSerializer;

import java.util.List;

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
            return new JacksonSerializer().render(new ErrorBuilder().build(errors));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
