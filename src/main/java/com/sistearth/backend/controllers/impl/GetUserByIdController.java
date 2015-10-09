package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.EmptyPayloadController;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.ViewException;

import java.util.Map;

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
            return new Answer(200, view.render());
        } catch (ViewException | ModelException e) {
            throw new ControllerException("Failed", e);
        }
    }
}
