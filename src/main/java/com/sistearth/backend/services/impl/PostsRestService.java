package com.sistearth.backend.services.impl;

import com.sistearth.backend.controllers.impl.GetPostByIdController;
import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.impl.PostManager;
import com.sistearth.backend.models.managers.impl.UserManager;
import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.backend.views.impl.JsonApiPostView;
import com.sistearth.backend.views.legacy.JSONApiPostBuilder;
import com.sistearth.backend.views.legacy.JsonSerializer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.backend.utils.Database.getDatabase;
import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes() throws ServiceException {
        PostManager postManager = new PostManager(getDatabase());
        UserManager userManager = new UserManager(getDatabase());

        get("/api/posts", (request, response) -> {
            List<Post> posts = postManager.getAll();
            List<User> authors = newArrayList();
            for (Post post : posts) {
                authors.add(userManager.getById(post.getAuthor()));
            }
            return new JSONApiPostBuilder().build(posts, authors);
        }, new JsonSerializer());

        get("/api/posts/:id", new GetPostByIdController(postManager, userManager, new JsonApiPostView()));
    }
}
