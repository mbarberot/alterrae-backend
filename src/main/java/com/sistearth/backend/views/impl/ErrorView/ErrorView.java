package com.sistearth.backend.views.impl.ErrorView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.views.View;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.json.JsonSerializer;
import com.sistearth.tools.jsonapi.JSONApi;

public class ErrorView implements View {
    private String title;
    private String status;

    public ErrorView(String status, String title) {
        this.status = status;
        this.title = title;
    }

    public ErrorView(String status) {
        this(status, "");
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JsonSerializer().render(
                    JSONApi.newErrorDocument().error(
                            JSONApi.Error.newError()
                            .title(title)
                            .status(status)
                    ).build()
            );
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
