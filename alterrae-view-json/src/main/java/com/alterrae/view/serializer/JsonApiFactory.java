package com.alterrae.view.serializer;

import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.entity.User;
import com.github.mbarberot.java.jsonapi.core.JsonApiConverter;
import com.github.mbarberot.java.jsonapi.structure.errors.Error;

import java.util.Date;

import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationField.field;
import static com.github.mbarberot.java.jsonapi.configuration.EntityConfigurationRelationship.relationship;
import static com.github.mbarberot.java.jsonapi.configuration.JsonApiConfiguration.newConfiguration;
import static com.github.mbarberot.java.jsonapi.configuration.JsonApiEntityConfiguration.newEntityConfiguration;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public class JsonApiFactory {
    private static JsonApiConverter converter = null;

    private JsonApiFactory() {
    }

    private static void initConverter() {
        converter = new JsonApiConverter(
                newConfiguration()
                        .entityConfigurations(newArrayList(
                                newEntityConfiguration()
                                        .entityClass(Post.class)
                                        .type("posts")
                                        .idField(field("id"))
                                        .attributeFields(newArrayList(field("title"), field("body"), field("creationDate").withConverter(o -> format("%d", ((Date) o).getTime()))))
                                        .relationshipFields(newArrayList(relationship("author", "users")))
                                        .build(),
                                newEntityConfiguration()
                                        .entityClass(User.class)
                                        .type("users")
                                        .idField(field("id"))
                                        .attributeFields(newArrayList(field("username"), field("email")))
                                        .build()
                        ))
                        .build()
        );

    }

    public static JsonApiConverter getConverter() {
        if (converter == null) {
            initConverter();
        }
        return converter;
    }
}
