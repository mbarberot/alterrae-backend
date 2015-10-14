package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.EmptyPayloadController;
import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.PostView;
import com.sistearth.backend.views.impl.error.SimpleErrorView;

import java.util.Map;

import static com.sistearth.backend.controllers.AnswerFactory.handleView;
import static java.lang.Integer.valueOf;

public class GetPostByIdController extends EmptyPayloadController {

    private ModelManager<Post> postManager;
    private ModelManager<User> userManager;
    private PostView view;

    public GetPostByIdController(ModelManager<Post> postManager, ModelManager<User> userManager, PostView view) {
        this.postManager = postManager;
        this.userManager = userManager;
        this.view = view;
    }

    @Override
    public Answer process(Map<String, String> params) throws ControllerException {
        try {
            Post post = postManager.getById(valueOf(params.get(":id")));
            User author = userManager.getById(post.getAuthor());

            view.setPosts(post);
            view.setAuthors(author);

            return handleView(200, view);
        } catch (ModelException  e) {
            return handleView(404, new SimpleErrorView("404"));
        }
    }
}
