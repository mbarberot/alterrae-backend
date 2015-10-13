package com.sistearth.backend;

import com.sistearth.backend.services.Service;
import com.sistearth.backend.services.ServiceException;
import com.sistearth.backend.services.impl.IndexService;
import com.sistearth.backend.services.impl.LoginService;
import com.sistearth.backend.services.impl.PostsRestService;
import com.sistearth.backend.services.impl.UserRestService;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.SparkBase.port;
import static spark.SparkBase.secure;

public class App {

    public static final String BASE_URL = "https://localhost:8080";

    public static void main(String[] args) {
        setConfig();
        enableCORS();
        setServices(
                new IndexService(),
                new UserRestService(),
                new LoginService(),
                new PostsRestService()
        );
    }

    private static void setConfig() {
        port(8080);
        secure(
                "/sistearth/ssl/keystore",
                "sistearth",
                null,
                null
        );
    }

    private static void enableCORS() {
        before("/api/*", (request, response) -> {
            response.header("Access-Control-Allow-Origin", request.headers("origin"));
            response.header("Access-Control-Allow-Headers", "Origin, x-requested-with, content-type, Accept, authorization");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        });

        options("/api/*", (request, response) -> {
            response.status(200);
            return "";
        });
    }

    private static void setServices(Service... services) {
        for (Service service : services) {
            try {
                service.registerRoutes();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
