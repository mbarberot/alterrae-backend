package com.sistearth;

import com.sistearth.core.index.IndexService;
import com.sistearth.core.posts.PostsRestService;
import com.sistearth.api.service.Service;
import com.sistearth.api.service.ServiceException;

import static com.google.common.collect.Lists.newArrayList;
import static spark.Spark.get;
import static spark.SparkBase.port;

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
