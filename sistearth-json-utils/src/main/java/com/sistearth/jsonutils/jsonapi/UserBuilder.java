package com.sistearth.jsonutils.jsonapi;


import com.mbarberot.jsonapi.builders.data.JSONApiDataBuilder;
import com.sistearth.db.beans.User;

import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static com.mbarberot.jsonapi.JSONApi.Data.newAttributes;
import static com.mbarberot.jsonapi.JSONApi.Data.newData;
import static com.mbarberot.jsonapi.JSONApi.newDataDocument;

public class UserBuilder {
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
