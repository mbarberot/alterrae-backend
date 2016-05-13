package com.sistearth.view.request.payloads;

import com.sistearth.api.payloads.TokenPayload;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenPayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        TokenPayload payload = new TokenPayload();
        payload.setToken("azerty");
        assertTrue(payload.isValid());
        assertNull(payload.getEntity());
        assertNull(payload.getErrors());
    }

    @Test
    public void testInvalidPayload() throws Exception {
        assertFalse(new TokenPayload().isValid());
        assertFalse(new TokenPayload(null).isValid());
        assertFalse(new TokenPayload("").isValid());
    }
}