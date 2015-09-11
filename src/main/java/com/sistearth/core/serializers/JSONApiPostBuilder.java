package com.sistearth.core.serializers;

import com.sistearth.core.models.Post;
import com.sistearth.core.models.User;
import com.sistearth.tools.jsonapi.JSONApi;
import com.sistearth.tools.jsonapi.builders.JSONApiDataBuilder;

import java.text.SimpleDateFormat;

import static com.sistearth.tools.jsonapi.JSONApi.Data.newAttributes;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newData;

public class JSONApiPostBuilder {

    private final Post post;
    private final User user;
    private final SimpleDateFormat dateFormatter;

    public JSONApiPostBuilder(Post post, User user) {
        this.post = post;
        this.user = user;
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Object build() {
        return JSONApi.newDataDocument()
                .data(buildData())
                .build();
    }

    public JSONApiDataBuilder buildData() {
        return newData(post.getId().toString(), "post")
                .attributes(
                        newAttributes()
                                .add("title", post.getTitle())
                                .add("body", post.getBody())
                                .add("created_at", dateFormatter.format(post.getCreatedAt()))
                )
                ;
    }
}
