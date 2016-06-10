package com.sistearth.spark.token;

import com.sistearth.db.api.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenManagerTest {
    @Test
    public void testDecodeToken() throws Exception {
        String token = new TokenManager().createToken(new User("57596105fc13ae0f3a001507", "Jon", "secret", "jon@snow.com"));
        assertEquals("Jon", new TokenManager().decodeToken(token));
    }
}