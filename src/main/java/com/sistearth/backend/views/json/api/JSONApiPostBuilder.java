package com.sistearth.backend.views.json.api;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.tools.jsonapi.JSONApi;
import com.sistearth.tools.jsonapi.builders.data.JSONApiDataBuilder;

import java.text.SimpleDateFormat;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.tools.jsonapi.JSONApi.Data.*;

public class JSONApiPostBuilder {

    private final SimpleDateFormat dateFormatter;

    public JSONApiPostBuilder() {
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Object build(List<Post> posts, List<User> authors) {
        return JSONApi.newDataDocument()
                .data(buildDatas(posts))
                .included(new JSONApiUserBuilder().buildDatas(authors))
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
        return newData(post.getId().toString(), "posts")
                .attributes(
                        newAttributes()
                                .add("title", post.getTitle())
                                .add("body", post.getBody())
                                .add("created_at", dateFormatter.format(post.getCreatedAt()))
                )
                .relationships(
                        newRelationships()
                                .addSingleData("author", post.getAuthor().toString(), "users")
                )
                ;
    }
}
