package com.sistearth.integration.login;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static java.lang.String.format;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostLoginTest {
    @Test
    public void testPostLogin_Ok() throws Exception {
        restApi()
                .contentType("application/json")
                .content(loginData("admin", "admin"))
                .when()
                .post("/api/login")
                .then()
                .contentType("application/json")
                .body("username", equalTo("admin"));
    }

    @Test
    public void testPostLogin_UnregisteredUser() throws Exception {
        restApi()
                .contentType("application/json")
                .content(loginData("john doe", "secret"))
                .when()
                .post("/api/login")
                .then()
                .contentType("application/json")
                .body("errors.status", hasItems("402"));
    }

    @Test
    public void testPostLogin_WrongPassword() throws Exception {
        restApi()
                .contentType("application/json")
                .content(loginData("admin", "wrongpassword"))
                .when()
                .post("/api/login")
                .then()
                .contentType("application/json")
                .body("errors.status", hasItems("402"));
    }

    @Test
    public void testPostLogin_WrongUsername() throws Exception {
        restApi()
                .contentType("application/json")
                .content(loginData("amdin", "admin"))
                .when()
                .post("/api/login")
                .then()
                .contentType("application/json")
                .body("errors.status", hasItems("402"));
    }


    private String loginData(String username, String password) {
        return format("{\"username\": \"%s\",\"password\": \"%s\"}", username, password);
    }
}
