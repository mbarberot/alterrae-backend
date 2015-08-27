package com.sistearth;

import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;
import com.sistearth.core.services.IndexService;
import com.sistearth.core.services.PostsRestService;

import org.sql2o.Sql2o;
import org.sql2o.Connection;

import static spark.SparkBase.port;
import static spark.SparkBase.secure;

public class App {

    private static String DB_URL = "jdbc:mariadb://database:3306/sistearth";
    private static String DB_USER = "sistearth";
    private static String DB_PASSWORD = "sistearth";

    public static void main(String[] args) {
        setConfig();

        Sql2o sql2o = new Sql2o(DB_URL, DB_USER, DB_PASSWORD);
        try (Connection conn = sql2o.open()) {
            System.out.println("DB OPEN");
        }

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
