package com.alterrae.spark.extractors;

import com.alterrae.view.request.PayloadException;
import spark.Request;

interface PayloadExtractor<P> {
    P extractPayload(Request request) throws PayloadException;
}
