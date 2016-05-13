package com.sistearth.view.response.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sistearth.api.beans.Post;
import com.sistearth.api.beans.User;
import com.sistearth.jsonutils.jsonapi.PostBuilder;
import com.sistearth.view.response.PostView;
import com.sistearth.view.response.ViewException;
import com.sistearth.view.serializer.JacksonSerializer;

import java.util.List;

public class JsonApiPostView extends PostView {
    public JsonApiPostView(List<Post> posts, List<User> authors) {
        setPosts(posts);
        setAuthors(authors);
    }

    public JsonApiPostView(Post post, User author) {
        setPosts(post);
        setAuthors(author);
    }

    @Override
    public String render() throws ViewException {
        try {
            return new JacksonSerializer().render(new PostBuilder().build(posts, authors));
        } catch (JsonProcessingException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
