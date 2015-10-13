package com.sistearth.backend.views.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.json.JsonSerializer;
import com.sistearth.backend.views.json.api.JSONApiUserBuilder;

public class JsonApiUserView extends UserView {
    @Override
    public String render() throws ViewException {
        try {
            return new JsonSerializer().render(new JSONApiUserBuilder().build(user));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to render bean", e);
        }
    }
}
