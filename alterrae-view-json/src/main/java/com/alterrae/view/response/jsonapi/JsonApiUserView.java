package com.alterrae.view.response.jsonapi;

import com.alterrae.db.api.entity.User;
import com.alterrae.view.response.UserView;
import com.alterrae.view.response.ViewException;
import com.alterrae.view.serializer.JacksonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiProcessException;

import static com.alterrae.view.serializer.JsonApiFactory.getConverter;

public class JsonApiUserView extends UserView {
    public JsonApiUserView(User user) {
        super(user);
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JacksonSerializer().render(getConverter().convertEntity(user));
        } catch (JsonProcessingException | JsonApiProcessException e) {
            throw new ViewException("Failed to render bean", e);
        }
    }
}
