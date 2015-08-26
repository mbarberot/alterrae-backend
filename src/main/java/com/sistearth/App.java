package com.sistearth;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.services.IndexService;
import com.sistearth.core.services.PostsRestService;

import static spark.SparkBase.port;
import static spark.SparkBase.secure;

public class App {
    public static void main(String[] args) {
        setConfig();
        setServices(
                new IndexService(),
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
