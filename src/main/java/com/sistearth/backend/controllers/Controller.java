package com.sistearth.backend.controllers;

import spark.Request;
import spark.Response;

public interface Controller {
    Answer process(Request request, Response response) throws ControllerException;
}
