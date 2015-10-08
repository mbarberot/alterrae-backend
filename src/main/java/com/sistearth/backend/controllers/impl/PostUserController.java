package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;

import java.util.Map;

public class PostUserController extends BaseController<UserPayload> {
    private ModelManager<User> userManager;

    public PostUserController(ModelManager<User> userManager, UserPayloadExtractor payloadExtractor) {
        super(payloadExtractor);
        this.userManager = userManager;
    }

    @Override
    public Answer process(UserPayload payload, Map<String, String> params) throws ControllerException {
        if (payload.isValid()) {
            try {
                userManager.create(payload.getUser());
                return new Answer(200);
            } catch (ModelException e) {
                throw new ControllerException("Failed", e);
            }
        } else {
            return new Answer(400);
        }
    }
}
