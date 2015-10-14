package com.sistearth.backend.controllers;

import com.sistearth.backend.views.View;
import com.sistearth.backend.views.ViewException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AnswerFactory {

    private static final Log LOG = LogFactory.getLog(AnswerFactory.class.getName());

    private AnswerFactory() {
    }

    public static Answer handleView(int status, View view) {
        try {
            return new Answer(status, view.render());
        } catch (ViewException e) {
            LOG.error("Failed to render view", e);
            return new Answer(500);
        }
    }
}
