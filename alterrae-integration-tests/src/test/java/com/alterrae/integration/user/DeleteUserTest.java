package com.alterrae.integration.user;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.authRestApi;

public class DeleteUserTest {
    @Test
    public void delete_ok() throws Exception {
        authRestApi("mramosd", "geYdrNrjflQV")
                .contentType("application/json")
                .content("{ \"data\": { \"attributes\" : { \"actualPassword\": \"geYdrNrjflQV\" } } }")
                .when()
                .delete("/api/users")
                .then()
                .statusCode(200)
                .contentType("application/json");
    }

    @Test
    public void delete_wrongpassword() throws Exception {
        authRestApi("sdayg", "XY3Npo2LoT")
                .contentType("application/json")
                .content("{ \"data\": { \"attributes\" : { \"actualPassword\": \"wrong-password\" } } }")
                .when()
                .delete("/api/users")
                .then()
                .statusCode(400)
                .contentType("application/json");
    }

    @Test
    public void delete_badrequest() throws Exception {
        authRestApi("sdayg", "XY3Npo2LoT")
                .contentType("application/json")
                .content("{}")
                .when()
                .delete("/api/users")
                .then()
                .statusCode(400)
                .contentType("application/json");
    }
}
