package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserTest {
    @Test
    public void testGetUser() throws Exception {
        restApi()
                .when()
                .get("/api/users/57486acab69c12fe0cf4a728")
                .then()
                .contentType("application/json")
                .body(
                        "data.id", equalTo("57486acab69c12fe0cf4a728"),
                        "data.attributes.username", equalTo("jroberts3")
                );
    }
}
