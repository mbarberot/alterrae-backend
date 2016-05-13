package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.TokenPayload;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.*;

public class TokenPayloadExtractorTest {
    @Test
    public void testExtractValidPayload() throws Exception {
        Request request = RequestMock.builder()
                .addHeader("Authorization", "Bearer secret-token")
                .build();

        TokenPayload payload = new TokenPayloadExtractor().extractPayload(request);
        assertTrue(payload.isValid());
        assertEquals("secret-token", payload.getToken());
    }

    @Test
    public void testExtractInvalidPayload_NoHeader() throws Exception {
        Request request = RequestMock.builder().build();
        assertFalse(new TokenPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_IncorrectHeader() throws Exception {
        Request request = RequestMock.builder()
                .addHeader("Authorization", "Foo secret-token")
                .build();
        assertFalse(new TokenPayloadExtractor().extractPayload(request).isValid());
    }
}