package com.alterrae.api.payloads;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDeletePayloadTest {
    @Test
    public void testPayload() throws Exception {
        assertEquals(
                "my-password",
                new UserDeletePayload("my-password").getEntity()
        );
    }
}