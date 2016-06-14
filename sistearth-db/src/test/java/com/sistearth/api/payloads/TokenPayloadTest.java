package com.sistearth.api.payloads;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenPayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        TokenPayload payload = new TokenPayload("azerty");
        assertEquals("azerty", payload.getEntity());
    }

    @Test
    public void testInvalidPayload() throws Exception {
        assertEquals(null, new TokenPayload().getEntity());
    }
}