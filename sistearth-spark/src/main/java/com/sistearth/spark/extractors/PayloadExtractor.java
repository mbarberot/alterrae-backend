package com.sistearth.spark.extractors;

import com.sistearth.view.request.Payload;
import com.sistearth.view.request.PayloadException;
import spark.Request;

public interface PayloadExtractor<V extends Payload> {
    V extractPayload(Request request) throws PayloadException;
}
