package com.sistearth.spark.services;

import com.sistearth.db.api.entity.Post;
import com.sistearth.db.api.entity.User;
import com.sistearth.db.core.PostManager;
import com.sistearth.db.core.UserManager;
import com.sistearth.view.response.jsonapi.JsonApiPostView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.spark.view.Answer.newJsonAnswer;
import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes() throws ServiceException {
        PostManager postManager = new PostManager();
        UserManager userManager = new UserManager();

        get("/api/posts", (request, response) -> {
            List<Post> posts = postManager.getAll();
            List<User> authors = newArrayList();
            for (Post post : posts) {
                authors.add(userManager.getById(post.getAuthor().getId().toString()));
            }
            return newJsonAnswer(response).body(new JsonApiPostView(posts, authors)).build();
        });

        get("/api/posts/:id", (request, response) -> {
            Post post = postManager.getById(request.params(":id"));
            User author = userManager.getById(post.getAuthor().getId().toString());
            return newJsonAnswer(response).body(new JsonApiPostView(post, author)).build();
        });
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
