package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.authRestApi;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;

public class PutUserTest {
    @Test
    public void testPutUser_ChangeEmail() throws Exception {
        authRestApi("kevin", "kevin")
                .contentType("application/json")
                .content(changeEmailData("kevin@changed.com", "kevin"))
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


    /*
     * Tests to implement after:
     * - Change password
     * - Try to change anything with a wrong confirmation password
     * - Change with empty fields
     * - Check with bad email syntax
     */


    private String changeEmailData(String email, String confirmationPassword) {
        return format("{ \"data\": { \"attributes\" : { \"email\": \"%s\", \"actualPassword\": \"%s\" } } }",
                email,
                confirmationPassword
        );
    }

}
