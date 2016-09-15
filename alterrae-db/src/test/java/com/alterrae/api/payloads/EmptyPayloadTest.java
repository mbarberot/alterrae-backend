package com.alterrae.api.payloads;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class EmptyPayloadTest {
    @Test
    public void testEmptyPayload() throws Exception {
        EmptyPayload payload = new EmptyPayload();
        assertNull(payload.getEntity());
    }
}