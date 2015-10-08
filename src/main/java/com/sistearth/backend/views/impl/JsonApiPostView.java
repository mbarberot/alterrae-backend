package com.sistearth.backend.views.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.views.PostView;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.legacy.JSONApiPostBuilder;
import com.sistearth.backend.views.legacy.JsonSerializer;

public class JsonApiPostView extends PostView {
    @Override
    public String render() throws ViewException {
        try {
            return new JsonSerializer().render2(new JSONApiPostBuilder().build(post, author));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
