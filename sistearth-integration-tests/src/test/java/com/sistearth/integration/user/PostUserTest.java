package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static java.lang.String.format;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostUserTest {
    @Test
    public void testPostUser() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("jon", "secret", "jon@doe.com"))
                .when()
                .post("/api/users")
                .then()
                .contentType("application/json")
                .body(
                        "data.id", equalTo("4"),
                        "data.attributes.username", equalTo("jon"),
                        "data.attributes.email", equalTo("jon@doe.com")
                );
    }

    @Test
    public void testPostUser_FailNoUsername() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("", "secret", "jon@doe.com"))
                .when()
                .post("/api/users")
                .then()
                .contentType("application/json")
                .statusCode(400)
                .body(
                        "errors.status", hasItem("400"),
                        "errors.title", hasItems("username")
                );
    }

    @Test
    public void testPostUser_FailNoPassword() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("jon", "", "jon@doe.com"))
                .when()
                .post("/api/users")
                .then()
                .contentType("application/json")
                .statusCode(400)
                .body(
                        "errors.status", hasItem("400"),
                        "errors.title", hasItems("password")
                );
    }

    @Test
    public void testPostUser_FailNoEmail() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("jon", "secret", ""))
                .when()
                .post("/api/users")
                .then()
                .contentType("application/json")
                .statusCode(400)
                .body(
                        "errors.status", hasItem("400"),
                        "errors.title", hasItems("email")
                );
    }

    @Test
    public void testPostUser_FailNoFields() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("", "", ""))
                .when()
                .post("/api/users")
                .then()
                .contentType("application/json")
                .body(
                        "errors.status", hasItem("400"),
                        "errors.title", hasItems("email", "password", "username")
                );
    }


    private String userData(String username, String password, String email) {
        return format("{ \"data\": { \"attributes\" : { \"username\": \"%s\", \"password\": \"%s\", \"email\": \"%s\" } } }",
                username,
                password,
                email
        );
    }
}
