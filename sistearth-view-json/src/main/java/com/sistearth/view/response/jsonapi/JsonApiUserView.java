package com.sistearth.view.response.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.db.beans.User;
import com.sistearth.jsonutils.jsonapi.UserBuilder;
import com.sistearth.view.response.UserView;
import com.sistearth.view.response.ViewException;
import com.sistearth.view.serializer.JacksonSerializer;

public class JsonApiUserView extends UserView {
    public JsonApiUserView(User user) {
        super(user);
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JacksonSerializer().render(new UserBuilder().build(user));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to render bean", e);
        }
    }
}
