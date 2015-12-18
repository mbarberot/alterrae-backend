package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.extractors.impl.TokenPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.TokenPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TokenManager;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.impl.error.SimpleErrorView;

import java.util.Map;

import static com.sistearth.backend.controllers.AnswerFactory.handleView;

public class GetUserProfileController extends BaseController<TokenPayload> {
    private final ModelManager<User> userManager;
    private final UserView view;

    public GetUserProfileController(ModelManager<User> userManager, UserView view) {
        super(new TokenPayloadExtractor());
        this.userManager = userManager;
        this.view = view;
    }

    @Override
    public Answer process(TokenPayload payload, Map<String, String> params) throws ControllerException {
        String username = new TokenManager().decodeToken(payload.getToken());
        try {
            view.setUser(userManager.getBy("username", username));
            return handleView(200, view);
        } catch (ModelException e) {
            return handleView(404, new SimpleErrorView("404"));
        }
    }
}
