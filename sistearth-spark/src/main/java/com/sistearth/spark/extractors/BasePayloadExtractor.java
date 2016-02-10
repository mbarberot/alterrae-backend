package com.sistearth.spark.extractors;

import com.sistearth.view.request.Payload;
import com.sistearth.view.request.PayloadException;
import spark.Request;

import java.util.Map;

import static com.sistearth.spark.extractors.PayloadExtractorUtils.getRequestHeaders;

public abstract class BasePayloadExtractor<V extends Payload> implements PayloadExtractor<V> {
    @Override
    public V extractPayload(Request request) throws PayloadException {
        return extractPayload(request.body(), getRequestHeaders(request));
    }

    protected abstract V extractPayload(String body, Map<String, String> requestHeaders) throws PayloadException;
}
