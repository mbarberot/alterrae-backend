package com.sistearth.spark.services;

import com.sistearth.db.Database;
import com.sistearth.db.beans.Post;
import com.sistearth.db.beans.User;
import com.sistearth.db.mysql.PostManager;
import com.sistearth.db.mysql.UserManager;
import com.sistearth.spark.view.Answer;
import com.sistearth.view.response.jsonapi.JsonApiPostView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.valueOf;
import static spark.Spark.get;

public class PostsRestService implements Service {

    @Override
    public void registerRoutes() throws ServiceException {
        PostManager postManager = new PostManager(Database.getDatabase());
        UserManager userManager = new UserManager(Database.getDatabase());

        get("/api/posts", (request, response) -> {
            List<Post> posts = postManager.getAll();
            List<User> authors = newArrayList();
            for (Post post : posts) {
                authors.add(userManager.getById(post.getAuthor()));
            }
            return new Answer(response).body(new JsonApiPostView(posts, authors)).build();
        });

        get("/api/posts/:id", (request, response) -> {
            Post post = postManager.getById(valueOf(request.params(":id")));
            User author = userManager.getById(post.getAuthor());
            return new Answer(response).body(new JsonApiPostView(post, author)).build();
        });
    }

    @Override
    public void registerFilters() throws ServiceException {
        // nothing to do
    }
}
