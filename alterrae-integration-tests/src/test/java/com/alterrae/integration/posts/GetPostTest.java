package com.alterrae.integration.posts;

import org.junit.Test;

import static com.alterrae.integration.utils.TestHelper.restApi;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPostTest {
    @Test
    public void testGetPost() throws Exception {
        restApi()
                .when()
                .get("/api/posts/57581171c35c1c060094d790")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", equalTo("57581171c35c1c060094d790"));
    }
}
