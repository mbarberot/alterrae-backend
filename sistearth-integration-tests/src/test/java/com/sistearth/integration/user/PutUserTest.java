package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.authRestApi;
import static com.sistearth.integration.utils.TestHelper.tryAuthenticate;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;

public class PutUserTest {
    @Test
    public void testPutUser_ChangeEmail() throws Exception {
        authRestApi("bhunter5", "pUJWnwSY6")
                .contentType("application/json")
                .content(changeData("email", "changed@mbox.com", "pUJWnwSY6"))
                .when()
                .put("/api/users")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(
                        "data.id", equalTo("6"),
                        "data.attributes.username", equalTo("bhunter5"),
                        "data.attributes.email", equalTo("changed@mbox.com")
                );
    }

    @Test
    public void testPutUser_ChangeEmail_WrongPassword() throws Exception {
        authRestApi("bross6", "dbo2u4nfVr")
                .contentType("application/json")
                .content(changeData("email", "changed@mbox.com", "wrong-password"))
                .when()
                .put("/api/users")
                .then()
                .statusCode(400);

        authRestApi("bross6", "dbo2u4nfVr")
                .when()
                .get("/api/users/profile")
                .then()
                .body(
                        "data.id", equalTo("7"),
                        "data.attributes.username", equalTo("bross6"),
                        "data.attributes.email", equalTo("vbrooks6@theguardian.com")
                );
    }

    @Test
    public void testPutUser_ChangePassword() throws Exception {
        authRestApi("jtucker9", "PypuMk6r1YAN")
                .contentType("application/json")
                .content(changeData("password", "nouveau-password", "PypuMk6r1YAN"))
                .when()
                .put("/api/users")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body(
                        "data.id", equalTo("8"),
                        "data.attributes.username", equalTo("jtucker9"),
                        "data.attributes.email", equalTo("cmontgomery9@newyorker.com")
                );

        tryAuthenticate("jtucker9", "nouveau-password").statusCode(200);
        tryAuthenticate("jtucker9", "PypuMk6r1YAN").statusCode(402);
    }

    private String changeData(String field, String value, String confirmationPassword) {
        return format("{ \"data\": { \"attributes\" : { \"%s\": \"%s\", \"actualPassword\": \"%s\" } } }",
                field,
                value,
                confirmationPassword
        );
    }

}
