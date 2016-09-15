package com.alterrae.spark.extractors;

import com.alterrae.view.request.PayloadException;
import com.alterrae.api.payloads.LoginPayload;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.*;

public class LoginPayloadExtractorTest {
    @Test
    public void testExtractPayload() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\",\"password\":\"jonsecret\"}")
                .build();

        assertEquals(
                new LoginPayload("jon", "jonsecret"),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void testExtractInvalidPayload_NoUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"password\":\"jonsecret\"}")
                .build();

        assertEquals(
                new LoginPayload(null, "jonsecret"),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractInvalidPayload_EmptyUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"\",\"password\":\"jonsecret\"}")
                .build();

        assertEquals(
                new LoginPayload(null, "jonsecret"),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractInvalidPayload_NullUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":null,\"password\":\"jonsecret\"}")
                .build();

        assertEquals(
                new LoginPayload(null, "jonsecret"),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void testExtractInvalidPayload_NoPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\"}")
                .build();

        assertEquals(
                new LoginPayload("jon", null),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractInvalidPayload_EmptyPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\",\"password\":\"\"}")
                .build();

        assertEquals(
                new LoginPayload("jon", null),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractInvalidPayload_NullPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\",\"password\":null}")
                .build();

        assertEquals(
                new LoginPayload("jon", null),
                new LoginPayloadExtractor().extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void testExtractInvalidPayload_UnexpectedField() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"username\":\"jon\",\"password\":\"jonsecret\",\"foo\":\"bar\"}")
                .build();

        new LoginPayloadExtractor().extractPayload(request);
    }

    @Test(expected = PayloadException.class)
    public void testFailToExtractPayload_IncorrectJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"foo\":\"jon\",\"password\":\"jonsecret\"")
                .build();

        new LoginPayloadExtractor().extractPayload(request);
    }
}