package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.Payload;
import com.sistearth.view.request.PayloadException;
import spark.Request;

import java.util.Map;

import static com.sistearth.spark.extractors.PayloadExtractorUtils.getRequestHeaders;

abstract class BasePayloadExtractor<P extends Payload> implements PayloadExtractor<P> {
    @Override
    public P extractPayload(Request request) throws PayloadException {
        return extractPayload(request.body(), getRequestHeaders(request));
    }

    protected abstract P extractPayload(String body, Map<String, String> requestHeaders) throws PayloadException;
}
