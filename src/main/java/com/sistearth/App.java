package com.sistearth;

import com.sistearth.posts.PostsRestService;
import com.sistearth.service.Service;
import com.sistearth.service.ServiceException;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello World");

        List<Service> services = newArrayList(
                new PostsRestService()
        );

        for (Service service : services) {
            try {
                service.registerRoutes();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
