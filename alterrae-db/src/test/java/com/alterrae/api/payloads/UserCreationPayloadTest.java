package com.alterrae.api.payloads;

import com.alterrae.db.api.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCreationPayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        UserCreationPayload payload = new UserCreationPayload("jon", "jonsecret", "jon@mbox.com");
        assertEquals(
                User.builder().username("jon").password("jonsecret").email("jon@mbox.com").build(),
                payload.getEntity()
        );
    }
}
