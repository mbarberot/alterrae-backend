package com.sistearth.core.serializers;

import com.sistearth.core.models.User;
import com.sistearth.tools.jsonapi.JSONApi;
import com.sistearth.tools.jsonapi.builders.JSONApiDataBuilder;

public class JSONApiUserBuilder {
    private User user;

    public JSONApiUserBuilder(User user) {
        this.user = user;
    }

    public Object build() {
        return JSONApi.newDataDocument()
                .data(buildData())
                .build();
    }

    public JSONApiDataBuilder buildData() {
        return JSONApi.newData(
                user.getId().toString(),
                "user"
        ).attributes(
                JSONApi.newAttributes()
                        .add("username", user.getUsername())
                        .add("email", user.getEmail())
        );
    }
}
