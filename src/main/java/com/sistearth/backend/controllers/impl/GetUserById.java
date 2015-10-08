package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.BaseController;
import com.sistearth.backend.controllers.ControllerException;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.View;
import com.sistearth.backend.views.ViewException;
import spark.Request;
import spark.Response;

public class GetUserById extends BaseController<User> {

    public GetUserById(ModelManager<User> manager, View<User> view) {
        super(view, manager);
    }

    @Override
    public Answer process(Request request, Response response) throws ControllerException {
        Answer answer = new Answer();
        try {
            answer.setBody(view.render(manager.getById(Integer.valueOf(request.params("id")))));
            answer.setCode(200);
        } catch (ViewException | ModelException e) {
            throw new ControllerException("Failed", e);
        }
        return answer;
    }
}
