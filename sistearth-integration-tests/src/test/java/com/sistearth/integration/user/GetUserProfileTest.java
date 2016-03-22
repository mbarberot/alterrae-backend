package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.authRestApi;
import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserProfileTest {
    @Test
    public void getUserProfile_FailAnonymous() throws Exception {
        restApi()
                .when()
                .get("/api/users/profile")
                .then()
                .statusCode(401);
    }

    @Test
    public void getUserProfile_Authenticated() throws Exception {
        authRestApi("dale", "dale")
                .when()
                .get("/api/users/profile")
                .then()
                .body(
                        "data.id", equalTo("2"),
                        "data.attributes.username", equalTo("dale"),
                        "data.attributes.email", equalTo("dale@sistearth.com")
                );
    }
}
