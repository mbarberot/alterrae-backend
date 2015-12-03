package com.sistearth.backend.controllers.payloads.extractors.impl;

import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;
import org.junit.Test;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;

public class EmptyPayloadExtractorTest {
    @Test
    public void testExtractPayload() throws Exception {
        assertEquals(
                new EmptyPayload(),
                new EmptyPayloadExtractor().extractPayload("whatever could be that string", emptyMap())
        );
    }
}