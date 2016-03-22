package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserTest {

    @Test
    public void testGetUsers_404() throws Exception {
        restApi()
                .when()
                .get("/api/users")
                .then()
                .statusCode(404);
    }


    @Test
    public void testGetUser_Anonymous() throws Exception {
        restApi()
                .when()
                .get("/api/users/2")
                .then()
                .contentType("application/json")
                .body(
                        "data.id", equalTo("2"),
                        "data.attributes.username", equalTo("dale")
                );
    }
}
