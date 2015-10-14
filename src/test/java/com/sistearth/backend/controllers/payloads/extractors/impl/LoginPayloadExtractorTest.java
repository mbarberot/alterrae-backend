package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.PayloadException;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginPayloadExtractorTest {

    @Test
    public void testExtractPayloadSuccess() throws Exception {
        LoginPayload expectedPayload = new LoginPayload();
        expectedPayload.setUsername("jon");
        expectedPayload.setPassword("winterfell");

        String json = "{\"password\":\"winterfell\",\"username\":\"jon\"}";

        assertEquals(expectedPayload, new LoginPayloadExtractor().extractPayload(json));
    }

    @Test
    public void testExtractPayloadDoNotFailWhenFieldMissing() throws Exception {
        LoginPayload expectedPayload = new LoginPayload();
        expectedPayload.setUsername("jon");

        String json = "{\"username\":\"jon\"}";
        try {
            assertEquals(expectedPayload, new LoginPayloadExtractor().extractPayload(json));
        } catch (PayloadException e) {
            fail();
        }
    }

    @Test
    public void testExtractPayloadDoNotFailWhenFieldNull() throws Exception {
        LoginPayload expectedPayload = new LoginPayload();
        expectedPayload.setUsername("jon");

        String json = "{\"password\":null,\"username\":\"jon\"}";
        try {
            assertEquals(expectedPayload, new LoginPayloadExtractor().extractPayload(json));
        } catch (PayloadException e) {
            fail();
        }
    }

}