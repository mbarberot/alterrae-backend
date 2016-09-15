package com.alterrae.integration.user;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.restApi;

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
