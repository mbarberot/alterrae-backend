package com.sistearth.backend.views.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.views.PostView;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.json.JsonSerializer;
import com.sistearth.backend.views.json.api.JSONApiPostBuilder;

public class JsonApiPostView extends PostView {
    @Override
    public String render() throws ViewException {
        try {
            return new JsonSerializer().render(new JSONApiPostBuilder().build(posts, authors));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
