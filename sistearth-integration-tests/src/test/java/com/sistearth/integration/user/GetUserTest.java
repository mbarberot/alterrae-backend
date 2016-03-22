package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;

public class GetUserTest {

    @Test
    public void testGetUsers_404() throws Exception {
        restApi()
                .when()
                .get("/api/users")
                .then()
                .statusCode(404);
    }
}
