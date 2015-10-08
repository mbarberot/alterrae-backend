package com.sistearth.backend.views.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.views.View;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.legacy.JSONApiUserBuilder;
import com.sistearth.backend.views.legacy.JsonSerializer;

public class JsonApiUserView implements View<User> {
    @Override
    public String renderBean(User bean) throws ViewException {
        try {
            return new JsonSerializer().render2(new JSONApiUserBuilder().build(bean));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to render bean", e);
        }
    }
}
