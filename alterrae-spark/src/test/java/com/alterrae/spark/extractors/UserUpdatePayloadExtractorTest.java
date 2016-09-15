package com.alterrae.spark.extractors;

import com.alterrae.view.request.PayloadException;
import com.alterrae.api.payloads.UserUpdatePayload;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.assertEquals;

public class UserUpdatePayloadExtractorTest {

    @Test
    public void testExtractUserValidPayload() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@new.com\",\"username\":\"Jon\",\"password\":\"newsecret\",\"actualPassword\":\"jonsecret\"}}}")
                .build();

        assertEquals(
                new UserUpdatePayload("Jon", "newsecret", "jon@new.com", "jonsecret"),
                new UserUpdatePayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_EmptyJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{}")
                .build();

        assertEquals(
                new UserUpdatePayload(),
                new UserUpdatePayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_MissingActualPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@new.com\",\"username\":\"Jon\",\"password\":\"newsecret\"}}}")
                .build();

        UserUpdatePayload expectedPayload = new UserUpdatePayload();
        expectedPayload.setEmail("jon@new.com");
        expectedPayload.setPassword("newsecret");
        expectedPayload.setUsername("Jon");
        assertEquals(
                expectedPayload,
                new UserUpdatePayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_MissingEmailAndPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"username\":\"Jon\",\"actualPassword\":\"jonsecret\"}}}")
                .build();

        UserUpdatePayload expectedPayload = new UserUpdatePayload();
        expectedPayload.setUsername("Jon");
        expectedPayload.setActualPassword("jonsecret");
        assertEquals(
                expectedPayload,
                new UserUpdatePayloadExtractor().extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void testFailExtractUserPayload_IncorrectJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@new.com\",\"username\":\"Jon\",\"password\":\"newsecret\",\"actualPassword\":\"jonsecret\"")
                .build();
        new UserUpdatePayloadExtractor().extractPayload(request);
    }
}