package com.alterrae.integration.user;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.restApi;
import static com.alterrae.integration.utils.TestHelper.tryAuthenticate;
import static java.lang.String.format;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;

public class PostUserTest {
    @Test
    public void testPostUser() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("jon", "mysecretpassword", "jon@doe.com"))
                .when()
                .post("/api/users")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(
                        "data.id", not(isEmptyString()),
                        "data.attributes.username", equalTo("jon"),
                        "data.attributes.email", equalTo("jon@doe.com")
                );

        tryAuthenticate("jon", "mysecretpassword").statusCode(200);
    }

    @Test
    public void testPostUser_FailNameAlreadyExists() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("twatson4", "anothersecret", "foobar@another.mbox"))
                .when()
                .post("/api/users")
                .then()
                .statusCode(400);
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
    public void testPostUser_FailPasswordTooShort() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("foo", "abc123*", "foobar@mbox.com"))
                .when()
                .post("/api/users")
                .then()
                .statusCode(400)
                .body(
                        "errors.status", hasItem("400"),
                        "errors.title", hasItems("password-bad")
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
    public void testPostUser_FailBadEmail() throws Exception {
        restApi()
                .contentType("application/json")
                .content(userData("jane", "mysecretpassword", "jane@doe@plop.com"))
                .when()
                .post("/api/users")
                .then()
                .contentType("application/json")
                .statusCode(400)
                .body(
                        "errors.status", hasItem("400"),
                        "errors.title", hasItems("email-bad-syntax")
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
