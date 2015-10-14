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
import com.sistearth.backend.views.impl.ErrorView.ErrorView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

import static com.sistearth.backend.controllers.AnswerFactory.handleView;
import static com.sistearth.backend.utils.Errors.User.ALREADY_EXISTS;
import static com.sistearth.backend.utils.Errors.User.NOT_VALID;

public class PostUserController extends BaseController<UserPayload> {
    private static final Log LOG = LogFactory.getLog(PostUserController.class.getName());

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
            User payloadUser = payload.getEntity();

            try {
                userManager.create(payloadUser);
            } catch (ModelException e) {
                return handleView(400, new ErrorView("400", ALREADY_EXISTS));
            }

            User user;
            try {
                user = userManager.getBy("username", payloadUser.getUsername());
            } catch (ModelException e) {
                LOG.error("Failed to get created user", e);
                return new Answer(500);
            }

            view.setUser(user);

            return handleView(200, view);
        } else {
            return handleView(400, new ErrorView("400", NOT_VALID));
        }
    }
}
