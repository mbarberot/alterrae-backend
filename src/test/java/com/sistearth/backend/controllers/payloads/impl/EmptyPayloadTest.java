package com.sistearth.backend.controllers.payloads.impl;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmptyPayloadTest {
    @Test
    public void testIsValid() throws Exception {
        assertTrue(new EmptyPayload().isValid());
    }
}