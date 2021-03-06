package com.alterrae.integration.user;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.authRestApi;
import static com.alterrae.integration.utils.TestHelper.restApi;
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
        authRestApi("twilson2", "V1qReXAVgED")
                .when()
                .get("/api/users/profile")
                .then()
                .body(
                        "data.id", equalTo("57486acab69c12fe0cf4a727"),
                        "data.attributes.username", equalTo("twilson2"),
                        "data.attributes.email", equalTo("bgriffin2@time.com")
                );
    }
}
