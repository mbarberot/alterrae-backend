package com.alterrae.view.request.payloads;

import com.alterrae.db.api.entity.User;
import com.alterrae.api.payloads.UserUpdatePayload;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserUpdatePayloadTest extends PayloadTest {
    @Test
    public void testValidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload("jon", "newsecret", "jon@new.com", "jonsecret");
        assertEquals(
                User.builder().username("jon").password("newsecret").email("jon@new.com").build(),
                payload.getEntity()
        );
    }

    @Test
    public void testInvalidPayload() throws Exception {
        UserUpdatePayload payload = new UserUpdatePayload();
        assertEquals(new User(), payload.getEntity());
    }
}