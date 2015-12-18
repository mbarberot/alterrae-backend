package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.PayloadException;
import com.sistearth.backend.controllers.payloads.impl.UserCreationPayload;
import org.junit.Test;

import static com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor.PayloadType.CREATION;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserPayloadExtractorTest {

    @Test
    public void testExtractPayloadSuccess() throws Exception {
        UserCreationPayload expectedPayload = new UserCreationPayload();
        expectedPayload.setUsername("jon");
        expectedPayload.setEmail("jon@snow.com");
        expectedPayload.setPassword("winterfell");

        String json = "{\"data\":{\"attributes\":{\"username\":\"jon\",\"email\":\"jon@snow.com\",\"password\":\"winterfell\",\"actual-password\":null},\"type\":\"users\"}}";

        assertEquals(expectedPayload, new UserPayloadExtractor(CREATION).extractPayload(json, emptyMap()));
    }

    @Test
    public void testExtractPayloadDoNotFailWhenFieldMissing() throws Exception {
        UserCreationPayload expectedPayload = new UserCreationPayload();
        expectedPayload.setUsername("jon");
        expectedPayload.setPassword("winterfell");

        String json = "{\"data\":{\"attributes\":{\"username\":\"jon\",\"password\":\"winterfell\",\"actual-password\":null},\"type\":\"users\"}}";
        try {
            assertEquals(expectedPayload, new UserPayloadExtractor(CREATION).extractPayload(json, emptyMap()));
        } catch (PayloadException e) {
            fail();
        }
    }

    @Test
    public void testExtractPayloadDoNotFailWhenFieldNull() throws Exception {
        UserCreationPayload expectedPayload = new UserCreationPayload();
        expectedPayload.setEmail("jon@snow.com");
        expectedPayload.setPassword("winterfell");

        String json = "{\"data\":{\"attributes\":{\"username\":null,\"email\":\"jon@snow.com\",\"password\":\"winterfell\",\"actual-password\":null},\"type\":\"users\"}}";
        try {
            assertEquals(expectedPayload, new UserPayloadExtractor(CREATION).extractPayload(json, emptyMap()));
        } catch (PayloadException e) {
            fail();
        }
    }
}