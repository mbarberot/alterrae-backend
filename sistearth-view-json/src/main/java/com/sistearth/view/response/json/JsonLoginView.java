package com.sistearth.view.response.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.api.beans.User;
import com.sistearth.view.response.LoginView;
import com.sistearth.view.response.ViewException;
import com.sistearth.view.serializer.JacksonSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class JsonLoginView extends LoginView {

    public JsonLoginView(User user, String token) {
        super(user, token);
    }

    @Override
    public String render() throws ViewException {
        Map<String,Object> result = newHashMap(of(
                "username", user.getUsername(),
                "token", token
        ));

        try {
            return new JacksonSerializer().render(result);
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
