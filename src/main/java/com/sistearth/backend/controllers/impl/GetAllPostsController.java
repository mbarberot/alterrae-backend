package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.controllers.payloads.extractors.impl.EmptyPayloadExtractor;
import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.PostView;
import com.sistearth.backend.views.ViewException;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

public class GetAllPostsController extends BaseController<Post>{
    private ModelManager<User> userManager;
    private PostView view;

    public GetAllPostsController(ModelManager<Post> postManager, ModelManager<User> userManager, PostView view) {
        super(postManager, new EmptyPayloadExtractor());
        this.userManager = userManager;
        this.view = view;
    }

    @Override
    public Answer process(Payload payload, Map<String, String> params) throws ControllerException {
        try {
            List<Post> posts = manager.getAll();
            List<User> authors = newArrayList();
            for (Post post : posts) {
                authors.add(userManager.getById(post.getAuthor()));
            }

            view.setPosts(posts);
            view.setAuthors(authors);

            return new Answer(200, view.render());
        } catch (ModelException | ViewException e) {
            throw new ControllerException("Failed", e);
        }
    }
}
