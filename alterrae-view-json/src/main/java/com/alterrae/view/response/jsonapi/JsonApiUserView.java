package com.alterrae.view.response.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.alterrae.db.api.entity.User;
import com.alterrae.jsonutils.jsonapi.UserBuilder;
import com.alterrae.view.response.UserView;
import com.alterrae.view.response.ViewException;
import com.alterrae.view.serializer.JacksonSerializer;

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
