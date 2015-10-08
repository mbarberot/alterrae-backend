package com.sistearth.backend.controllers;

import com.sistearth.backend.controllers.payloads.Payload;

import java.util.Map;

public interface Controller<V extends Payload> {
    Answer process(V payload, Map<String,String> params) throws ControllerException;
}
