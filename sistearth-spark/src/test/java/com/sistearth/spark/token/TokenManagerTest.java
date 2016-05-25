package com.sistearth.spark.token;

import com.sistearth.api.beans.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenManagerTest {
    @Test
    public void testDecodeToken() throws Exception {
        String token = new TokenManager().createToken(new User("0", "Jon", "secret", "jon@snow.com"));
        assertEquals("Jon", new TokenManager().decodeToken(token));
    }
}