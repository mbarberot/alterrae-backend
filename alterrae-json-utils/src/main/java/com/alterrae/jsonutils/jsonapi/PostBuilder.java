package com.alterrae.jsonutils.jsonapi;

import com.mbarberot.jsonapi.builders.data.JSONApiDataBuilder;
import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.entity.User;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static com.mbarberot.jsonapi.JSONApi.Data.*;
import static com.mbarberot.jsonapi.JSONApi.newDataDocument;

public class PostBuilder {

    private final SimpleDateFormat dateFormatter;

    public PostBuilder() {
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Object build(List<Post> posts, List<User> authors) {
        return newDataDocument()
                .data(buildDatas(posts))
                .included(new UserBuilder().buildDatas(authors))
                .build();
    }

    public Object build(Post post, User author) {
        return build(newArrayList(post), newArrayList(author));
    }

    public JSONApiDataBuilder[] buildDatas(List<Post> posts) {
        List<JSONApiDataBuilder> builders = newArrayList();
        for (Post post : posts) {
            builders.add(buildData(post));
        }

        return toArray(builders, JSONApiDataBuilder.class);
    }

    public JSONApiDataBuilder buildData(Post post) {
        return newData(post.getStringId(), "posts")
                .attributes(
                        newAttributes()
                                .add("title", post.getTitle())
                                .add("body", post.getBody())
                                .add("created_at", dateFormatter.format(post.getCreatedAt()))
                )
                .relationships(
                        newRelationships()
                                .addSingleData("author", post.getAuthor().getStringId(), "users")
                )
                ;
    }
}
