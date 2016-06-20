package com.sistearth.api.payloads;

import com.sistearth.api.payloads.EmptyPayload;
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