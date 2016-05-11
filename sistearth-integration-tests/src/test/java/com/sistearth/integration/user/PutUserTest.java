package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.authRestApi;
import static com.sistearth.integration.utils.TestHelper.tryAuthenticate;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;

public class PutUserTest {
    @Test
    public void testPutUser_ChangeEmail() throws Exception {
        authRestApi("kevin", "kevin")
                .contentType("application/json")
                .content(changeData("email", "kevin@changed.com", "kevin"))
                .when()
                .put("/api/users")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(
                        "data.id", equalTo("3"),
                        "data.attributes.username", equalTo("kevin"),
                        "data.attributes.email", equalTo("kevin@changed.com")
                );
    }

    @Test
    public void testPutUser_ChangeEmail_WrongPassword() throws Exception {
        authRestApi("juanlucas", "juanlucas")
                .contentType("application/json")
                .content(changeData("email", "juan.lucas@wrong.com", "wrong-password"))
                .when()
                .put("/api/users")
                .then()
                .statusCode(400);

        authRestApi("juanlucas", "juanlucas")
                .when()
                .get("/api/users/profile")
                .then()
                .body(
                        "data.id", equalTo("5"),
                        "data.attributes.username", equalTo("juanlucas"),
                        "data.attributes.email", equalTo("juan.lucas@mbox.com")
                );
    }

    @Test
    public void testPutUser_ChangePassword() throws Exception {
        authRestApi("vanessa", "vanessa")
                .contentType("application/json")
                .content(changeData("password", "assenav", "vanessa"))
                .when()
                .put("/api/users")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(
                        "data.id", equalTo("4"),
                        "data.attributes.username", equalTo("vanessa"),
                        "data.attributes.email", equalTo("vanessa@mail.com")
                );

        tryAuthenticate("vanessa", "assenav").statusCode(200);
        tryAuthenticate("vanessa", "vanessa").statusCode(402);
    }

    /*
     * TODO : fail with bad new password
     * TODO : fail because empty fields
     */


    private String changeData(String field, String value, String confirmationPassword) {
        return format("{ \"data\": { \"attributes\" : { \"%s\": \"%s\", \"actualPassword\": \"%s\" } } }",
                field,
                value,
                confirmationPassword
        );
    }

}
