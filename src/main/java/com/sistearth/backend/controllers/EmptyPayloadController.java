package com.sistearth.backend.controllers;

import com.sistearth.backend.controllers.payloads.extractors.impl.EmptyPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;

import java.util.Map;

public abstract class EmptyPayloadController extends BaseController<EmptyPayload> {

    public EmptyPayloadController() {
        super(new EmptyPayloadExtractor());
    }

    @Override
    public Answer process(EmptyPayload payload, Map<String, String> params) throws ControllerException {
        return process(params);
    }

    protected abstract Answer process(Map<String, String> params) throws ControllerException;
}
