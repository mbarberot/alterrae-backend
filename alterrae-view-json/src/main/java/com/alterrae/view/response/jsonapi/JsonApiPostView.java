package com.alterrae.view.response.jsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.entity.User;
import com.alterrae.jsonutils.jsonapi.PostBuilder;
import com.alterrae.view.response.PostView;
import com.alterrae.view.response.ViewException;
import com.alterrae.view.serializer.JacksonSerializer;

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
