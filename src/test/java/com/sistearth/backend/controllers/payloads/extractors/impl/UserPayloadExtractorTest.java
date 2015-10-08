package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserPayloadExtractorTest {

    @Test
    public void testExtractPayload() throws Exception {
        assertEquals(
                new UserPayload(),
                new UserPayloadExtractor().extractPayload("some json")
        );
    }
}