package com.alterrae.api.payloads;

import com.alterrae.db.api.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginPayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        LoginPayload payload = new LoginPayload("jon", "jonsecret");
        assertEquals(
                User.builder().username("jon").password("jonsecret").build(),
                payload.getEntity()
        );
        assertEquals("jon", payload.getUsername());
        assertEquals("jonsecret", payload.getPassword());
    }
}