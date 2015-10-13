package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.extractors.impl.LoginPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TokenManager;
import com.sistearth.backend.views.ViewException;
import com.sistearth.backend.views.impl.LoginView;

import java.util.Map;

public class LoginController extends BaseController<LoginPayload> {
    private ModelManager<User> userManager;
    private TokenManager tokenManager;
    private LoginView view;

    public LoginController(ModelManager<User> userManager, TokenManager tokenManager, LoginPayloadExtractor loginPayloadExtractor, LoginView view) {
        super(loginPayloadExtractor);
        this.userManager = userManager;
        this.tokenManager = tokenManager;
        this.view = view;
    }

    @Override
    public Answer process(LoginPayload payload, Map<String, String> params) throws ControllerException {
        try {
            User user = userManager.getBy("username", payload.getUsername());
            view.setUser(user);
            view.setToken(tokenManager.createToken(user));
            return new Answer(200, view.render());
        } catch (ModelException | ViewException e) {
            throw new ControllerException("Failed", e);
        }
    }
}
