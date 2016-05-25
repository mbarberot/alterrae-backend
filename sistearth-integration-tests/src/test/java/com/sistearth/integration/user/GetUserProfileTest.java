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
        authRestApi("twilson2", "V1qReXAVgED")
                .when()
                .get("/api/users/profile")
                .then()
                .body(
                        "data.id", equalTo("fe291792-43d1-4ae2-a0d5-0c934c34b75a"),
                        "data.attributes.username", equalTo("twilson2"),
                        "data.attributes.email", equalTo("bgriffin2@time.com")
                );
    }
}
