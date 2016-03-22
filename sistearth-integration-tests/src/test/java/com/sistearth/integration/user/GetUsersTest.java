package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;

public class GetUsersTest {

    @Test
    public void testGetUsers() throws Exception {
        restApi()
                .when()
                .get("/api/users")
                .then()
                .statusCode(404);
    }
}
