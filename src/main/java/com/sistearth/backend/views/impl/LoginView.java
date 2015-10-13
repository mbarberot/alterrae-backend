package com.sistearth.backend.views.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.json.JsonSerializer;
import lombok.Data;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

@Data
public class LoginView extends UserView {
    protected String token;

    @Override
    public String render() throws ViewException {
        Map<String,Object> result = newHashMap(of(
                "username", user.getUsername(),
                "token", token
        ));

        try {
            return new JsonSerializer().render(result);
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
