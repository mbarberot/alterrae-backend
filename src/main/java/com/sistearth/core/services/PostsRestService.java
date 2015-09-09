package com.sistearth.core.services;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.database.PostManager;
import com.sistearth.core.database.UserManager;
import com.sistearth.core.models.Post;
import com.sistearth.core.models.User;
import com.sistearth.core.serializers.JsonSerializer;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.sistearth.api.database.Database.getDatabase;
import static java.lang.Integer.valueOf;
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
            return newHashMap(of("posts", posts, "authors", authors));
        }, new JsonSerializer());

        get("/api/posts/:id", (request, response) -> {
            Post post = postManager.getById(valueOf(request.params("id")));
            User user = userManager.getById(post.getAuthor());
            return newHashMap(of("post", post, "author", user));
        }, new JsonSerializer());
    }


}
