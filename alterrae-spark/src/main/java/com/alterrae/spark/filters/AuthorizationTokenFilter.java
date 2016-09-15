package com.alterrae.spark.filters;

import spark.Filter;
import spark.Request;
import spark.Response;

import java.util.Objects;

import static spark.Spark.halt;

public class AuthorizationTokenFilter implements Filter {

    @Override
    public void handle(Request request, Response response) throws Exception {
        if(Objects.equals("OPTIONS", request.requestMethod())) {
            // never check OPTIONS requests
            return;
        }

        if(request.headers("Authorization") == null) {
            halt(401);
        }
    }
}
