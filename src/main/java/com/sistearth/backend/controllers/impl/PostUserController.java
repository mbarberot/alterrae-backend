package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.ViewException;

import java.util.Map;

public class PostUserController extends BaseController<UserPayload> {
    private ModelManager<User> userManager;
    private UserView view;

    public PostUserController(ModelManager<User> userManager, UserPayloadExtractor payloadExtractor, UserView view) {
        super(payloadExtractor);
        this.userManager = userManager;
        this.view = view;
    }

    @Override
    public Answer process(UserPayload payload, Map<String, String> params) throws ControllerException {
        if (payload.isValid()) {
            try {
                User payloadUser = payload.getUser();
                userManager.create(payloadUser);
                view.setUser(userManager.getBy("username", payloadUser.getUsername()));

                return new Answer(200, view.render());
            } catch (ModelException | ViewException e) {
                throw new ControllerException("Failed", e);
            }
        } else {
            return new Answer(400);
        }
    }
}
