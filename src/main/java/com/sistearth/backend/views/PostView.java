package com.sistearth.backend.views;

import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import lombok.Getter;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Getter
public abstract class PostView implements View {
    protected List<Post> posts;
    protected List<User> authors;

    protected PostView() {
        posts = newArrayList();
        authors = newArrayList();
    }

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
