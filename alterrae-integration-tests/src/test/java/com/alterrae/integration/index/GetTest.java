package com.alterrae.integration.index;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetTest {
    @Test
    public void testGetIndex() throws Exception {
        restApi()
                .when()
                .get("/")
                .then()
                .body("message", equalTo("Welcome to Alterrae v4 REST API."));
    }
}
