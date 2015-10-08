package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.controllers.payloads.extractors.impl.EmptyPayloadExtractor;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.ViewException;

import java.util.Map;

import static java.lang.Integer.valueOf;

public class GetUserByIdController extends BaseController<User> {

    private UserView view;

    public GetUserByIdController(ModelManager<User> manager, UserView view) {
        super(manager, new EmptyPayloadExtractor());
        this.view = view;
    }

    @Override
    public Answer process(Payload payload, Map<String, String> params) throws ControllerException {
        try {
            User user = manager.getById(valueOf(params.get("id")));
            view.setUser(user);
            return new Answer(200, view.render());
        } catch (ViewException | ModelException e) {
            throw new ControllerException("Failed", e);
        }
    }
}
