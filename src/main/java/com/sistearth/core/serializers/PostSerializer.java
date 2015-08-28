package com.sistearth.core.serializers;

import com.sistearth.api.serializer.Serializer;
import com.sistearth.core.models.Post;

public class PostSerializer extends Serializer<Post> {
    public PostSerializer() {
        super("posts");
    }
}
