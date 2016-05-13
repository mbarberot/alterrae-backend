package com.sistearth.spark.extractors;

import com.sistearth.view.request.PayloadException;
import com.sistearth.api.payloads.LoginPayload;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.*;

public class LoginPayloadExtractorTest {
    @Test
    public void testExtractValidPayload() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\",\"password\":\"jonsecret\"}")
                .build();

        LoginPayload payload = new LoginPayloadExtractor().extractPayload(request);
        assertTrue(payload.isValid());
        assertEquals("jon", payload.getUsername());
        assertEquals("jonsecret", payload.getPassword());
    }

    @Test
    public void testExtractInvalidPayload_NoUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"password\":\"jonsecret\"}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_EmptyUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"\",\"password\":\"jonsecret\"}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_NullUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":null,\"password\":\"jonsecret\"}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_NoPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\"}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_EmptyPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"\",\"password\":\"\"}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_NullPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"\",\"password\":null}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test
    public void testExtractInvalidPayload_UnexpectedField() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\",\"password\":\"jonsecret\",\"foo\":\"bar\"}")
                .build();

        assertFalse(new LoginPayloadExtractor().extractPayload(request).isValid());
    }

    @Test(expected = PayloadException.class)
    public void testFailToExtractPayload_IncorrectJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"foo\":\"jon\",\"password\":\"jonsecret\"")
                .build();

        new LoginPayloadExtractor().extractPayload(request);
    }
}