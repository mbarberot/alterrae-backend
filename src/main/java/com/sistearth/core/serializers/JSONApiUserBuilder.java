package com.sistearth.core.serializers;

import com.sistearth.core.models.User;
import com.sistearth.tools.jsonapi.JSONApi;
import com.sistearth.tools.jsonapi.builders.JSONApiDataBuilder;

import static com.sistearth.tools.jsonapi.JSONApi.Data.newAttributes;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newData;
import static com.sistearth.tools.jsonapi.JSONApi.newDataDocument;

public class JSONApiUserBuilder {
    private User user;

    public JSONApiUserBuilder(User user) {
        this.user = user;
    }

    public Object build() {
        return newDataDocument()
                .data(buildData())
                .build();
    }

    public JSONApiDataBuilder buildData() {
        return newData(
                user.getId().toString(),
                "user"
        ).attributes(
                newAttributes()
                        .add("username", user.getUsername())
                        .add("email", user.getEmail())
        );
    }
}
