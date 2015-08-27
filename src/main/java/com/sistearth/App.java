package com.sistearth;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.services.IndexService;
import com.sistearth.core.services.PostsRestService;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.before;
import static spark.Spark.options;
import static spark.SparkBase.port;
import static spark.SparkBase.secure;

public class App {

    private static String DB_URL = "jdbc:mariadb://database:3306/sistearth";
    private static String DB_USER = "sistearth";
    private static String DB_PASSWORD = "sistearth";

    public static void main(String[] args) {
        Sql2o database = new Sql2o(DB_URL, DB_USER, DB_PASSWORD);

        setConfig();
        enableCORS();
        setServices(
                database,
                new IndexService(),
                new PostsRestService()
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

    private static void setConfig() {
        port(8080);
        secure(
                "/sistearth/ssl/keystore",
                "sistearth",
                null,
                null
        );
    }

    private static void setServices(Sql2o database, Service... services) {
        for (Service service : services) {
            try {
                service.registerRoutes(database);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
