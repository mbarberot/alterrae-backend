package com.sistearth.backend.controllers;

import com.sistearth.backend.controllers.payloads.Payload;
import com.sistearth.backend.controllers.payloads.extractors.PayloadExtractor;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public abstract class BaseController<V extends Payload> implements Route, Controller<V> {

    protected final PayloadExtractor<V> payloadExtractor;

    public BaseController(PayloadExtractor<V> payloadExtractor) {
        this.payloadExtractor = payloadExtractor;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        V payload = this.payloadExtractor.extractPayload(request.body(), getRequestHeaders(request));
        Answer answer = process(payload, request.params()
        );
        response.status(answer.getStatus());
        return answer.getBody();
    }

    private Map<String, String> getRequestHeaders(Request request) {
        Map<String, String> requestHeaders = newHashMap();
        addIfExists(request, requestHeaders, "Authorization");
        return requestHeaders;
    }

    private void addIfExists(Request request, Map<String, String> requestHeaders, String header) {
        String value = request.headers(header);
        if (value != null) {
            requestHeaders.put(header, value);
        }
    }
}
