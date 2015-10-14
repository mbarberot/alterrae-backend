package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.EmptyPayloadController;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.impl.error.SimpleErrorView;

import java.util.Map;

import static com.sistearth.backend.controllers.AnswerFactory.handleView;
import static java.lang.Integer.valueOf;

public class GetUserByIdController extends EmptyPayloadController {

    private ModelManager<User> userManager;
    private UserView view;

    public GetUserByIdController(ModelManager<User> userModelManager, UserView view) {
        this.userManager = userModelManager;
        this.view = view;
    }

    @Override
    public Answer process(Map<String, String> params) throws ControllerException {
        try {
            User user = userManager.getById(valueOf(params.get(":id")));
            view.setUser(user);
            return handleView(200, view);
        } catch (ModelException e) {
            return handleView(404, new SimpleErrorView("404"));
        }
    }
}
