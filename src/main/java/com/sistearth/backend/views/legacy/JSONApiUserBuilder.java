package com.sistearth.backend.views.legacy;

import com.sistearth.backend.models.beans.User;
import com.sistearth.tools.jsonapi.builders.JSONApiDataBuilder;

import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newAttributes;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newData;
import static com.sistearth.tools.jsonapi.JSONApi.newDataDocument;

public class JSONApiUserBuilder {
    public Object build(List<User> users) {
        return newDataDocument()
                .data(buildDatas(users))
                .build();
    }

    public Object build(User user) {
        return build(newArrayList(user));
    }

    public JSONApiDataBuilder[] buildDatas(List<User> users) {
        List<JSONApiDataBuilder> builders = newArrayList();
        for (User user : users) {
            builders.add(buildData(user));
        }
        return toArray(builders, JSONApiDataBuilder.class);
    }

    public JSONApiDataBuilder buildData(User user) {
        return newData(
                user.getId().toString(),
                "users"
        ).attributes(
                newAttributes()
                        .add("username", user.getUsername())
                        .add("email", user.getEmail())
        );
    }
}
