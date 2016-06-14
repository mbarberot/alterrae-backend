package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.OldPayload;
import com.sistearth.view.request.PayloadException;
import spark.Request;

@Deprecated
interface OldPayloadExtractor<V extends OldPayload> {
    V extractPayload(Request request) throws PayloadException;
}
