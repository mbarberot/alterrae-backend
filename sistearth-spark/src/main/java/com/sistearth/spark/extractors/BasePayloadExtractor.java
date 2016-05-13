package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.Payload;
import com.sistearth.view.request.PayloadException;
import spark.Request;

import java.util.Map;

public abstract class BasePayloadExtractor<V extends Payload> implements PayloadExtractor<V> {
    @Override
    public V extractPayload(Request request) throws PayloadException {
        return extractPayload(request.body(), PayloadExtractorUtils.getRequestHeaders(request));
    }

    protected abstract V extractPayload(String body, Map<String, String> requestHeaders) throws PayloadException;
}
