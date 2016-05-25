package com.sistearth.integration.user;

import org.junit.Test;

import static com.sistearth.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserTest {
    @Test
    public void testGetUser() throws Exception {
        restApi()
                .when()
                .get("/api/users/1f5c6bf0-8e3b-4b0e-b111-7b47f75a8c55")
                .then()
                .contentType("application/json")
                .body(
                        "data.id", equalTo("1f5c6bf0-8e3b-4b0e-b111-7b47f75a8c55"),
                        "data.attributes.username", equalTo("jroberts3")
                );
    }
}
