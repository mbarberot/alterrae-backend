package com.sistearth.backend.views.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.json.JsonSerializer;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

public class LoginView extends UserView {
    @Override
    public String render() throws ViewException {
        Map<String,Object> result = newHashMap(of(
                "username", user.getUsername(),
                "password", user.getPassword()
        ));

        try {
            return new JsonSerializer().render(result);
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
