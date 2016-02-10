package com.sistearth.view.request.payloads;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class EmptyPayloadTest {
    @Test
    public void testEmptyPayload() throws Exception {
        EmptyPayload payload = new EmptyPayload();
        assertTrue(payload.isValid());
        assertNull(payload.getEntity());
        assertTrue(payload.getErrors().isEmpty());
    }
}