package com.alterrae.view.response;

import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.entity.User;
import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Getter
public abstract class PostView implements View {
    protected List<Post> posts;
    protected List<User> authors;

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setPosts(Post... posts) {
        this.setPosts(newArrayList(posts));
    }

    public void setAuthors(List<User> authors) {
        this.authors = authors;
    }

    public void setAuthors(User... authors) {
        this.setAuthors(newArrayList(authors));
    }
}
