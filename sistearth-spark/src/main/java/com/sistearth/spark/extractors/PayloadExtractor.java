package com.sistearth.spark.extractors;

import com.sistearth.view.request.PayloadException;
import spark.Request;

interface PayloadExtractor<P> {
    P extractPayload(Request request) throws PayloadException;
}
