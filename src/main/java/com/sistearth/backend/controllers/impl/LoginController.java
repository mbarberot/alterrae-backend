package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.extractors.impl.LoginPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.impl.LoginView;

import java.util.Map;

public class LoginController extends BaseController<LoginPayload> {
    private ModelManager<User> userManager;
    private LoginView view;

    public LoginController(ModelManager<User> userManager, LoginPayloadExtractor loginPayloadExtractor, LoginView view) {
        super(loginPayloadExtractor);
        this.userManager = userManager;
        this.view = view;
    }

    @Override
    public Answer process(LoginPayload payload, Map<String, String> params) throws ControllerException {



        view.setUser(payload.getEntity());
        try {
            return new Answer(200, view.render());
        } catch (ViewException e) {
            throw new ControllerException("Failed to render", e);
        }
    }
}
