package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.OldPayload;
import com.sistearth.view.request.PayloadException;
import spark.Request;

import java.util.Map;

@Deprecated
public abstract class OldBasePayloadExtractor<V extends OldPayload> implements OldPayloadExtractor<V> {
    @Override
    public V extractPayload(Request request) throws PayloadException {
        return extractPayload(request.body(), PayloadExtractorUtils.getRequestHeaders(request));
    }

    protected abstract V extractPayload(String body, Map<String, String> requestHeaders) throws PayloadException;
}
