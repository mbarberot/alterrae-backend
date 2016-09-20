package com.alterrae.view.response.jsonapi;

import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.entity.User;
import com.alterrae.view.response.PostView;
import com.alterrae.view.response.ViewException;
import com.alterrae.view.serializer.JacksonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mbarberot.java.jsonapi.core.process.JsonApiProcessException;

import java.util.List;

import static com.alterrae.view.serializer.JsonApiFactory.getConverter;

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
            return new JacksonSerializer().render(
                    (posts.size() == 1) ?
                            getConverter().convertEntity(posts.get(0)) :
                            getConverter().convertEntities(posts)
            );
        } catch (JsonProcessingException | JsonApiProcessException e) {
            throw new ViewException("Failed to serialize", e);
        }
    }
}
