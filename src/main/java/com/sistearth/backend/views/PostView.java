package com.sistearth.backend.views;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import lombok.Data;

@Data
public abstract class PostView implements View {
    protected Post post;
    protected User author;
}
