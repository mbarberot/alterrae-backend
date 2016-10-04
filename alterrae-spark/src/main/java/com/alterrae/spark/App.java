package com.alterrae.spark;

import com.alterrae.spark.services.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.alterrae.db.core.Database.initDB;
import static spark.Spark.*;
import static spark.route.RouteOverview.enableRouteOverview;

public class App {

    public static final String BASE_URL = "https://localhost:8080";
    private static final Log LOG = LogFactory.getLog(App.class.getName());

    private App() {
    }

    public static void main(String[] args) {
        setConfig();
        enableCORS();
        initDB();
        setServices(
                new IndexService(),
                new UserRestService(),
                new LoginService(),
                new PostsRestService()
        );

        enableRouteOverview("/api/doc");
    }

    private static void setConfig() {
        port(8080);
        secure(
                "/alterrae/ssl/keystore",
                "alterrae",
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
                service.registerFilters();
            } catch (ServiceException e) {
                LOG.error("Failed to register routes", e);
            }
        }
    }
}
