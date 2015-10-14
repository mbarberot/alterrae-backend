package com.sistearth.backend.views.impl.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.utils.Error;
import com.sistearth.backend.views.View;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.json.JsonSerializer;
import com.sistearth.tools.jsonapi.JSONApi;
import com.sistearth.tools.jsonapi.builders.error.JsonApiErrorBuilder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ErrorView implements View {
    private List<Error> errors;

    public ErrorView() {
    }

    public ErrorView(List<Error> errors) {
        this.setErrors(errors);
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void setErrors(Error... errors) {
        this.setErrors(newArrayList(errors));
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JsonSerializer().render(
                    JSONApi.newErrorDocument().error(
                            errors.stream().map(error -> JSONApi.Error.newError()
                                            .title(error.getTitle())
                                            .status(error.getStatus())
                            ).toArray(JsonApiErrorBuilder[]::new)
                    ).build()
            );
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
