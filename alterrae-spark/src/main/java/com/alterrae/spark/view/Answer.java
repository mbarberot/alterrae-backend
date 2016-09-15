package com.alterrae.spark.view;

import com.alterrae.view.response.View;
import com.alterrae.view.response.ViewException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spark.Response;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Answer {
    private static Log LOG = LogFactory.getLog(Answer.class.getName());
    private final Response response;
    private int status;
    private String body;
    private Map<String, String> headers;

    public static Answer newJsonAnswer(Response response) {
        return new Answer(response).addHeader("Content-Type", "application/json");
    }

    public static Answer newJsonApiAnswer(Response response) {
        return new Answer(response).addHeader("Content-Type", "application/vnd.api+json");
    }

    public Answer(Response response) {
        this.response = response;
        this.status = 200;
        this.body = "";
        this.headers = newHashMap();
    }

    public Answer addHeader(String header, String value){
        headers.put(header, value);
        return this;
    }

    public Answer body(String body) {
        if (body != null) {
            this.body = body;
        } else {
            this.status(500).body("");
        }
        return this;
    }

    public Answer body(View view) {
        try {
            this.body = view.render();
        } catch (ViewException | NullPointerException e) {
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
        for (Map.Entry<String, String> header : headers.entrySet()) {
            response.header(header.getKey(), header.getValue());
        }
        response.status(status);
        response.body(body);
        return body;
    }
}
