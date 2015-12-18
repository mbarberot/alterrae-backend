package com.sistearth.backend.utils;

import org.junit.Test;

import static com.sistearth.backend.utils.TestUtils.createUser;
import static org.junit.Assert.assertEquals;

public class TokenManagerTest {
    @Test
    public void testDecodeToken() throws Exception {
        String token = new TokenManager().createToken(createUser(0, "Jon", "secret", "jon@snow.com"));
        assertEquals("Jon", new TokenManager().decodeToken(token));
    }
}