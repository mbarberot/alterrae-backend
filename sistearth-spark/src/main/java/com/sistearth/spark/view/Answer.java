package com.sistearth.spark.view;

import com.sistearth.view.response.View;
import com.sistearth.view.response.ViewException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.Response;

public class Answer {
    private static Log LOG = LogFactory.getLog(Answer.class.getName());
    private final Response response;
    private int status;
    private String body;

    public Answer(Response response) {
        this.response = response;
        this.status = 200;
        this.body = "";
    }

    public Answer body(String body) {
        this.body = body;
        return this;
    }

    public Answer body(View view) {
        try {
            this.body = view.render();
        } catch (ViewException e) {
            LOG.warn("Failed to render view", e);
            this.status(500).body("");
        }
        return this;
    }

    public Answer status(int status) {
        this.status = status;
        return this;
    }

    public String build() {
        response.status(status);
        response.body(body);
        return body;
    }
}
